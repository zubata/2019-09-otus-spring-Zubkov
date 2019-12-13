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
import ru.otus.spring.homework14.domain.mongo.CommentMongo;
import ru.otus.spring.homework14.domain.sql.CommentSql;
import ru.otus.spring.homework14.service.MySqlReaderService;
import ru.otus.spring.homework14.storage.sql.CommentSqlDao;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class StepCommentsConfig {
    private final StepBuilderFactory stepBuilderFactory;
    private final Logger logger = LoggerFactory.getLogger("Batch");
    private final CommentSqlDao commentSqlDao;

    @Bean
    public ItemReader<CommentSql> readerComments() {
        MySqlReaderService<CommentSql> reader = new MySqlReaderService<>();
        reader.setJpaRepo(commentSqlDao);
        return reader;
    }

    @Bean
    public ItemProcessor<CommentSql, CommentMongo> processorComments() { return CommentMongo::convertToDomain; }

    @Bean
    public MongoItemWriter<CommentMongo> writerComments(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<CommentMongo>()
                .template(mongoTemplate)
                .collection("comments")
                .build();
    }

    @Bean
    public Step stepComments(ItemReader<CommentSql> readerComments, MongoItemWriter<CommentMongo> writerComments, ItemProcessor<CommentSql, CommentMongo> processorComments) {
        return stepBuilderFactory.get("step3")
                .<CommentSql, CommentMongo> chunk(2)
                .reader(readerComments)
                .processor(processorComments)
                .writer(writerComments)
                .listener(new ItemReadListener<CommentSql>() {
                    public void beforeRead() {
                        logger.info("Начало чтения");
                    }

                    public void afterRead(CommentSql o) {
                        logger.info("Конец чтения");
                    }

                    public void onReadError(Exception e) {
                        logger.info("Ошибка чтения");
                    }
                })
                .listener(new ItemWriteListener<CommentMongo>() {
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
                .listener(new ItemProcessListener<CommentSql, CommentMongo>() {
                    public void beforeProcess(CommentSql o) { logger.info("Начало обработки"); }

                    public void afterProcess(CommentSql o, CommentMongo o2) {
                        logger.info("Конец обработки");
                    }

                    public void onProcessError(CommentSql o, Exception e) {
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
