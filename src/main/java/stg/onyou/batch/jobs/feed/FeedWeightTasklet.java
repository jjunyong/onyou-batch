package stg.onyou.batch.jobs.feed;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@StepScope
@Scope(value = "step", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class FeedWeightTasklet implements Tasklet {


    @Autowired
    private FeedWeightService feedWeightService;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        feedWeightService.updateFeedWeight();
        return RepeatStatus.FINISHED;
    }
}
