package ru.otus.spring.homework14.batch;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.spring.homework14.domain.mongo.AuthorMongo;
import ru.otus.spring.homework14.domain.sql.AuthorSql;
import ru.otus.spring.homework14.service.MySqlReaderService;
import ru.otus.spring.homework14.storage.sql.AuthorSqlDao;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class StepAuthorsConfig {

    private final StepBuilderFactory stepBuilderFactory;
    private final AuthorSqlDao authorDaoSql;
    private final Logger logger = LoggerFactory.getLogger("Batch");

    @Bean
    public ItemReader<AuthorSql> readerAuthor() {
        /*JdbcCursorItemReader jdbcReader = new JdbcCursorItemReader<Author>();
        jdbcReader.setDataSource(dataSource);
        jdbcReader.setSql("select id, author_name as name from authors");
        jdbcReader.setRowMapper(new BeanPropertyRowMapper<>(Author.class));*/
        MySqlReaderService<AuthorSql> reader = new MySqlReaderService<>();
        reader.setJpaRepo(authorDaoSql);
        return reader;
    }

    @Bean
    public ItemProcessor<AuthorSql, AuthorMongo> processorAuthor() {
        return AuthorMongo::convertToDomain;
    }

    @Bean
    public MongoItemWriter<AuthorMongo> writerAuthor(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<AuthorMongo>()
                .template(mongoTemplate)
                .collection("authors")
                .build();
    }

    @Bean
    public Step stepAuthors(ItemReader<AuthorSql> readerAuthor, MongoItemWriter<AuthorMongo> writerAuthor, ItemProcessor<AuthorSql, AuthorMongo> processorAuthor) {
        return stepBuilderFactory.get("step1")
                .<AuthorSql, AuthorMongo>chunk(2)
                .reader(readerAuthor)
                .processor(processorAuthor)
                .writer(writerAuthor)
                .listener(new ItemReadListener<AuthorSql>() {
                    public void beforeRead() {
                        logger.info("Начало чтения");
                    }

                    public void afterRead(AuthorSql o) {
                        logger.info("Конец чтения");
                    }

                    public void onReadError(Exception e) {
                        logger.info("Ошибка чтения");
                    }
                })
                .listener(new ItemWriteListener<AuthorMongo>() {
                    public void beforeWrite(List list) {
                        logger.info("Начало записи");
                    }

                    public void afterWrite(List list) {
                        logger.info("Конец записи");
                    }

                    public void onWriteError(Exception e, List list) {
                        logger.info("Ошибка записи");
                    }
                })
                .listener(new ItemProcessListener<AuthorSql, AuthorMongo>() {
                    public void beforeProcess(AuthorSql o) {
                        logger.info("Начало обработки");
                    }

                    public void afterProcess(AuthorSql o, AuthorMongo o2) {
                        logger.info("Конец обработки");
                    }

                    public void onProcessError(AuthorSql o, Exception e) {
                        logger.info("Ошбка обработки");
                    }
                })
                .listener(new ChunkListener() {
                    public void beforeChunk(ChunkContext chunkContext) {
                        logger.info("Начало пачки");
                    }

                    public void afterChunk(ChunkContext chunkContext) {
                        logger.info("Конец пачки");
                    }

                    public void afterChunkError(ChunkContext chunkContext) {
                        logger.info("Ошибка пачки");
                    }
                })
                .build();
    }
}
