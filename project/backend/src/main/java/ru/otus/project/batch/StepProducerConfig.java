package ru.otus.project.batch;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import ru.otus.project.domain.Producer;
import ru.otus.project.domain.Vine;
import ru.otus.project.stroge.ProducerDao;
import ru.otus.project.stroge.VineDao;

import javax.sql.DataSource;
import java.util.List;

@Service
@Configuration
@RequiredArgsConstructor
public class StepProducerConfig {

    private final StepBuilderFactory stepBuilderFactory;
    private final Logger logger = LoggerFactory.getLogger("BatchProducer");
    private final ProducerDao producerDao;
    private Resource resource;

    public void setResource(String name) { this.resource = new FileSystemResource(name); }

    @Bean
    @StepScope
    public ItemReader<Producer> readerProducers() {
        MySqlItemReader<Producer> reader = new MySqlItemReader<>();
        reader.setJpaRepo(producerDao);
        return reader;
    }

    @Bean
    @StepScope
    public ItemProcessor<Producer, Producer> processorProducers() {
        return Producer -> Producer;
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<Producer> writerProducers() {
        FlatFileItemWriter<Producer> writer = new FlatFileItemWriter<>();
        writer.setResource(resource);
        writer.setAppendAllowed(true);
        writer.setHeaderCallback(writer1 -> writer1.write("id, Имя"));
        writer.setLineAggregator(new DelimitedLineAggregator<Producer>() {
            {
                setDelimiter(",");
                setFieldExtractor(new BeanWrapperFieldExtractor<Producer>() {
                    {
                        setNames(new String[]{"id", "name"});
                    }
                });
            }
        });

        return writer;
    }

    @Bean
    public Step stepProducer(ItemReader<Producer> readerProducers, ItemProcessor<Producer, Producer> processorProducers, ItemWriter<Producer> writerProducers) {
        return stepBuilderFactory.get("step1")
                .<Producer, Producer>chunk(100)
                .reader(readerProducers)
                .processor(processorProducers)
                .writer(writerProducers)
                .listener(new ItemReadListener<Producer>() {
                    public void beforeRead() {
                        logger.info("Начало чтения");
                    }

                    public void afterRead(Producer o) {
                        logger.info("Конец чтения");
                    }

                    public void onReadError(Exception e) {
                        logger.info("Ошибка чтения");
                    }
                })
                .listener(new ItemWriteListener<Producer>() {
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
                .listener(new ItemProcessListener<Producer, Producer>() {
                    public void beforeProcess(Producer o) {
                        logger.info("Начало обработки");
                    }

                    public void afterProcess(Producer o, Producer o2) {
                        logger.info("Конец обработки");
                    }

                    public void onProcessError(Producer o, Exception e) {
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
