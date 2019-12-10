package ru.otus.spring.homework14.batch;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import ru.otus.spring.homework14.domain.Author;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class StepAuthors {

    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;
    private final Logger logger = LoggerFactory.getLogger("Batch");

    @Bean
    public ItemReader<Author> readerAuthor() {
        JdbcCursorItemReader jdbcReader = new JdbcCursorItemReader<Author>();
        jdbcReader.setDataSource(dataSource);
        jdbcReader.setSql("select id, author_name as name from authors");
        jdbcReader.setRowMapper(new BeanPropertyRowMapper<>(Author.class));
        return jdbcReader;
    }

    @Bean
    public ItemProcessor<Author, Author> processorAuthor() {
        return author -> author;
    }

    @Bean
    public MongoItemWriter<Author> writerAuthor(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<Author>()
                .template(mongoTemplate)
                .collection("authors")
                .build();
    }

    @Bean
    public Step step1(ItemReader readerAuthor, MongoItemWriter writerAuthor, ItemProcessor processorAuthor) {
        return stepBuilderFactory.get("step1")
                .chunk(2)
                .reader(readerAuthor)
                .processor(processorAuthor)
                .writer(writerAuthor)
                .listener(new ItemReadListener() {
                    public void beforeRead() {
                        logger.info("Начало чтения");
                    }

                    public void afterRead(Object o) {
                        logger.info("Конец чтения");
                    }

                    public void onReadError(Exception e) {
                        logger.info("Ошибка чтения");
                    }
                })
                .listener(new ItemWriteListener() {
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
                .listener(new ItemProcessListener() {
                    public void beforeProcess(Object o) {
                        logger.info("Начало обработки");
                    }

                    public void afterProcess(Object o, Object o2) {
                        logger.info("Конец обработки");
                    }

                    public void onProcessError(Object o, Exception e) {
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
