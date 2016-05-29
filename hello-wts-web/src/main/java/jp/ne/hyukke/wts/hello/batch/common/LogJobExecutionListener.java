package jp.ne.hyukke.wts.hello.batch.common;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

/**
 * ログ出力するジョブ実行リスナークラス.
 *
 * @author hyukke
 */
@Component
public class LogJobExecutionListener extends JobExecutionListenerSupport {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        super.beforeJob(jobExecution);
        String jobName = jobExecution.getJobInstance().getJobName();
        System.out.println(String.format("**** Start job [%s]", jobName));
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        super.afterJob(jobExecution);
        String jobName = jobExecution.getJobInstance().getJobName();
        System.out.println(String.format("**** End job [%s]", jobName));
    }
}
