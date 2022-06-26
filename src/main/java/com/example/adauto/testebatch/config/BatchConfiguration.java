package com.example.adauto.testebatch.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Sort.Direction;

import com.example.adauto.testebatch.entity.Person;
import com.example.adauto.testebatch.listener.JobCompletionNotificationListener;
import com.example.adauto.testebatch.processor.PersonItemProcessor;
import com.example.adauto.testebatch.repository.PersonRepository;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration
{
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    @Lazy
    public PersonRepository personRepository;

    @Bean
    public RepositoryItemReader<Person> reader() {
        RepositoryItemReader<Person> reader = new RepositoryItemReader<Person>();
        reader.setRepository(personRepository);
        reader.setMethodName("findAll");
        reader.setPageSize(100);

        Map<String, Direction> sorts = new HashMap<>();
        sorts.put("id", Direction.ASC);
        reader.setSort(sorts);

        return reader;
    }

    @Bean
    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }

    @Bean
    public FlatFileItemWriter<Person> writer() {
        FlatFileItemWriter<Person> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource("output/data.csv"));
        writer.setAppendAllowed(false);

        writer.setLineAggregator(new DelimitedLineAggregator<Person>() {
            {
                setDelimiter(",");
                setFieldExtractor(new BeanWrapperFieldExtractor<Person>() {
                    {
                        setNames(new String[] { "id", "name" });
                    }
                });
            }
        });

        return writer;
    }

    @Bean
    public Job exportPersonJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("exportPersonJob")
                                .incrementer(new RunIdIncrementer())
                                .listener(listener)
                                .flow(step1)
                                .end()
                                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                                 .<Person, Person> chunk(10)
                                 .reader(reader())
                                 .processor(processor())
                                 .writer(writer())
                                 .build();
    }
}
