package stg.onyou.batch.jobs.feed;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import stg.onyou.batch.common.JobListener;
import stg.onyou.batch.common.StepListener;
import stg.onyou.batch.jobs.feed.FeedWeightService;
import stg.onyou.batch.jobs.feed.FeedWeightTasklet;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Configuration
public class FeedBatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    @Autowired
    private FeedWeightTasklet feedWeightTasklet;
    @Autowired
    private FeedWeightService feedWeightService;

    @Bean
    public Job myJob() {
        return jobBuilderFactory.get("myJob")
                .listener(new JobListener())
                .start(myStep())
                .build();
    }

    @Bean
    public Step myStep() {
        return stepBuilderFactory.get("myStep")
                .listener(new StepListener())
                .tasklet(feedWeightTasklet)
                .build();
    }

}
