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
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import ru.otus.project.domain.Vine;
import ru.otus.project.stroge.VineDao;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class StepVineConfig {

    private final StepBuilderFactory stepBuilderFactory;
    private final Logger logger = LoggerFactory.getLogger("BatchVine");
    private final VineDao vineDao;
    private Resource resource;

    public void setResource(String name) { this.resource = new FileSystemResource(name); }

    @Bean
    @StepScope
    public ItemReader<Vine> readerVines() {
        MySqlItemReader<Vine> reader = new MySqlItemReader<>();
        reader.setJpaRepo(vineDao);
        return reader;
    }

    @Bean
    @StepScope
    public ItemProcessor<Vine, Vine> processorVines() {
        return vine -> vine;
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<Vine> writerVines() {
        FlatFileItemWriter<Vine> writer = new FlatFileItemWriter<>();
        writer.setResource(resource);
        writer.setHeaderCallback(writer1 -> writer1.write("id, Имя, Год, Тип, " +
                "Цвет, Объём, Страна, В наличие , Цена, Производитель, Ссылка"));
        writer.setLineAggregator(new DelimitedLineAggregator<Vine>() {
            {
                setDelimiter(",");
                setFieldExtractor(new BeanWrapperFieldExtractor<Vine>() {
                    {
                        setNames(new String[]{"id", "name", "year", "type", "color",
                                "capacity", "country", "isAvailable" ,"cost", "producer.name", "url"});
                    }
                });
            }
        });

        return writer;
    }

    @Bean
    public Step stepVine(ItemReader<Vine> readerVines, ItemProcessor<Vine, Vine> processorVines, ItemWriter<Vine> writerVines) {
        return stepBuilderFactory.get("step1")
                .<Vine, Vine>chunk(100)
                .reader(readerVines)
                .processor(processorVines)
                .writer(writerVines)
                .listener(new ItemReadListener<Vine>() {
                    public void beforeRead() {
                        logger.info("Начало чтения");
                    }

                    public void afterRead(Vine o) {
                        logger.info("Конец чтения");
                    }

                    public void onReadError(Exception e) {
                        logger.info("Ошибка чтения");
                    }
                })
                .listener(new ItemWriteListener<Vine>() {
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
                .listener(new ItemProcessListener<Vine, Vine>() {
                    public void beforeProcess(Vine o) {
                        logger.info("Начало обработки");
                    }

                    public void afterProcess(Vine o, Vine o2) {
                        logger.info("Конец обработки");
                    }

                    public void onProcessError(Vine o, Exception e) {
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
