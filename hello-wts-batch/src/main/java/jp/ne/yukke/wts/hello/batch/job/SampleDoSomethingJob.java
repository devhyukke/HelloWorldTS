package jp.ne.yukke.wts.hello.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jp.ne.yukke.wts.hello.batch.domain.entity.Sample;

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
    public Job sampleDoSomething(Step doSomethingOneTask) throws Exception {

		return this.jobBuilderFactory.get("sampleDoSomething")
                .start(doSomethingOneTask)
				.build();
	}

	@Bean
    public Step doSomethingOneTask(Tasklet doSomethingTasklet) throws Exception {

        return this.stepBuilderFactory.get("doSomethingOneTask").tasklet(doSomethingTasklet)
				.build();
	}

	@Bean
    public Tasklet doSomethingTasklet() {

		return (contribution, context) -> {
			System.out.println("Do something!!!");
			return RepeatStatus.FINISHED;
		};
	}

    @Bean
    public Step doSomethingProcessUnit(ItemReader<Sample> reader,
            ItemProcessor<Sample, Sample> processor, ItemWriter<Sample> writer) {

        // FIXME Type Compiled Error!!
        return this.stepBuilderFactory.get("doSomethingProcessUnit").chunk(10).reader(reader)
                .processor(null).writer(null).build();
    }

    // FIXME Compiled Error!!
    // @Bean
    // public ItemReader<Sample> doSomethingReader() {
    // JpaPagingItemReader<Sample> reader = new JpaPagingItemReader<>();
    // return reader;
    // }
    //
    // @Bean
    // public ItemProcessor<Sample, Sample> doSomethingProcessor() {
    // ItemProcessor<Sample, Sample> processor = new CompositeItemProcessor<>();
    // return processor;
    // }
    //
    // @Bean
    // public ItemWriter<Sample> doSomethingWriter() {
    // ItemWriter<Sample> writer = new JdbcBatchItemWriter<>();
    // return writer;
    // }
}
