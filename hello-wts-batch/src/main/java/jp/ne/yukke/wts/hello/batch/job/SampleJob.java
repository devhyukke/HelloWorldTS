package jp.ne.yukke.wts.hello.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SampleJob.
 *
 * @author y_hiraune
 */
@Configuration
@EnableBatchProcessing
public class SampleJob {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job sample(Step doSomething) throws Exception {
		return this.jobBuilderFactory.get("sample")
				.start(doSomething)
				.build();
	}

	@Bean
	public Step doSomething(Tasklet printSomething) throws Exception {
		return this.stepBuilderFactory.get("doSomething")
				.tasklet(printSomething)
				.build();
	}

	@Bean
	public Tasklet printSomething() {

		return (contribution, context) -> {
			System.out.println("Do something!!!");
			return RepeatStatus.FINISHED;
		};
	}
}
