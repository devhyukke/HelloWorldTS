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
 * SampleDoSomethingJob.
 *
 * @author y_hiraune
 */
@Configuration
@EnableBatchProcessing
public class SampleDoSomethingJob {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	// Bean と同一の名称で引数を指定すると、FW によって渡される
	@Bean
	public Job sampleDoSomething(Step sampleDoSomethingTask) throws Exception {

		return this.jobBuilderFactory.get("sampleDoSomething")
				.start(sampleDoSomethingTask)
				.build();
	}

	@Bean
	public Step sampleDoSomethingTask(Tasklet sampleDoSomething) throws Exception {

		return this.stepBuilderFactory.get("sampleDoSomethingByTasklet")
				.tasklet(sampleDoSomething)
				.build();
	}

	@Bean
	public Tasklet sampleDoSomething() {

		return (contribution, context) -> {
			System.out.println("Do something!!!");
			return RepeatStatus.FINISHED;
		};
	}
}
