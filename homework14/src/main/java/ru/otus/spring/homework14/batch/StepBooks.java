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
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import ru.otus.spring.homework14.domain.Book;
import ru.otus.spring.homework14.domain.BookDto;
import ru.otus.spring.homework14.service.BookService;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class StepBooks {
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;
    private final Logger logger = LoggerFactory.getLogger("Batch");
    private final BookService bookService;

    @Bean
    public ItemReader<BookDto> readerBooks() {
        JdbcCursorItemReader jdbcReader = new JdbcCursorItemReader<BookDto>();
        jdbcReader.setDataSource(dataSource);
        jdbcReader.setSql("select b.id, a.author_name as author, b.book_name as name, g.genre_name genre from books b " +
                "inner join books_genre bg on b.id=bg.book_id inner join genres g on bg.genre_id = g.id " +
                "inner join authors a on a.id=b.author_id");
        jdbcReader.setRowMapper(new BeanPropertyRowMapper<>(BookDto.class));
        return jdbcReader;
    }

    @Bean
    public ItemProcessor<BookDto, Book> processorBooks() {
        return bookDto -> bookService.convertToDomain(bookDto);
    }

    @Bean
    public MongoItemWriter<Book> writerBooks(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<Book>()
                .template(mongoTemplate)
                .collection("books")
                .build();
    }

    @Bean
    public Step step2(ItemReader readerBooks, MongoItemWriter writerBooks, ItemProcessor processorBooks) {
        return stepBuilderFactory.get("step2")
                .chunk(2)
                .reader(readerBooks)
                .processor(processorBooks)
                .writer(writerBooks)
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
