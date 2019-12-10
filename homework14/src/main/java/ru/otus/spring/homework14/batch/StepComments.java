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
import ru.otus.spring.homework14.domain.Comment;
import ru.otus.spring.homework14.domain.CommentDto;
import ru.otus.spring.homework14.service.BookService;
import ru.otus.spring.homework14.service.CommentService;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class StepComments {
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;
    private final Logger logger = LoggerFactory.getLogger("Batch");
    private final CommentService commentService;

    @Bean
    public ItemReader<CommentDto> readerComments() {
        JdbcCursorItemReader jdbcReader = new JdbcCursorItemReader<CommentDto>();
        jdbcReader.setDataSource(dataSource);
        jdbcReader.setSql("select c.id as id, c.comment, b.book_name as book from comments c " +
                "inner join books b on c.book_id=b.id");
        jdbcReader.setRowMapper(new BeanPropertyRowMapper<>(CommentDto.class));
        return jdbcReader;
    }

    @Bean
    public ItemProcessor<CommentDto, Comment> processorComments() { return commentDto -> commentService.convertToDomain(commentDto); }

    @Bean
    public MongoItemWriter<Comment> writerComments(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<Comment>()
                .template(mongoTemplate)
                .collection("comments")
                .build();
    }

    @Bean
    public Step step3(ItemReader readerComments, MongoItemWriter writerComments, ItemProcessor processorComments) {
        return stepBuilderFactory.get("step3")
                .chunk(2)
                .reader(readerComments)
                .processor(processorComments)
                .writer(writerComments)
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
