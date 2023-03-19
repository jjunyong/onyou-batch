package stg.onyou.batch.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

@Slf4j
public class StepListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution){
        log.info("-----start step------");
        log.info("stepName: "+stepExecution.getStepName());
        log.info("stepParams: "+stepExecution.getJobParameters());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution){
        log.info("-----end job------");
        log.info("exit status: "+stepExecution.getExitStatus());
        return stepExecution.getExitStatus();
    }


}
