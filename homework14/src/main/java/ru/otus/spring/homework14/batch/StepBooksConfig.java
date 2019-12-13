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
import ru.otus.spring.homework14.domain.mongo.BookMongo;
import ru.otus.spring.homework14.domain.sql.BookSql;
import ru.otus.spring.homework14.service.MySqlReaderService;
import ru.otus.spring.homework14.storage.sql.BookSqlDao;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class StepBooksConfig {
    private final StepBuilderFactory stepBuilderFactory;
    private final Logger logger = LoggerFactory.getLogger("Batch");
    private final BookSqlDao bookDaoSQL;

    @Bean
    public ItemReader<BookSql> readerBooks() {
        /*JdbcCursorItemReader jdbcReader = new JdbcCursorItemReader<BookSql>();
        jdbcReader.setDataSource(dataSource);
        jdbcReader.setSql("select b.id, a.author_name as author, b.book_name as name, g.genre_name genre from books b " +
                "inner join books_genre bg on b.id=bg.book_id inner join genres g on bg.genre_id = g.id " +
                "inner join authors a on a.id=b.author_id");
        jdbcReader.setRowMapper(new BeanPropertyRowMapper<>(BookSql.class));*/
        MySqlReaderService<BookSql> reader = new MySqlReaderService<>();
        reader.setJpaRepo(bookDaoSQL);
        return reader;
    }

    @Bean
    public ItemProcessor<BookSql, BookMongo> processorBooks() {
        return BookMongo::convetToDomain;
    }

    @Bean
    public MongoItemWriter<BookMongo> writerBooks(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<BookMongo>()
                .template(mongoTemplate)
                .collection("books")
                .build();
    }

    @Bean
    public Step stepBooks(ItemReader<BookSql> readerBooks, MongoItemWriter<BookMongo> writerBooks, ItemProcessor<BookSql, BookMongo> processorBooks) {
        return stepBuilderFactory.get("step2")
                .<BookSql, BookMongo>chunk(2)
                .reader(readerBooks)
                .processor(processorBooks)
                .writer(writerBooks)
                .listener(new ItemReadListener<BookSql>() {
                    public void beforeRead() {
                        logger.info("Начало чтения");
                    }

                    public void afterRead(BookSql o) {
                        logger.info("Конец чтения");
                    }

                    public void onReadError(Exception e) {
                        logger.info("Ошибка чтения");
                    }
                })
                .listener(new ItemWriteListener<BookMongo>() {
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
                .listener(new ItemProcessListener<BookSql, BookMongo>() {
                    public void beforeProcess(BookSql o) {
                        logger.info("Начало обработки");
                    }

                    public void afterProcess(BookSql o, BookMongo o2) {
                        logger.info("Конец обработки");
                    }

                    public void onProcessError(BookSql o, Exception e) { logger.info("Ошбка обработки"); }
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
