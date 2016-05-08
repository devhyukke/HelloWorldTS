package jp.ne.yukke.wts.hello.batch.job;

import java.util.ArrayDeque;
import java.util.Deque;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jp.ne.yukke.wts.hello.batch.common.LogJobExecutionListener;
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
    @Autowired
    private LogJobExecutionListener logJobExecutionListener;

    // Bean の name 属性が指定されていなければ、メソッド名が Bean の名称になる
    // 引数はステップの Bean と同一の名称で定義すると、FW によって自動的にその Bean が渡される
	@Bean
    public Job sampleDoSomething(Step doSomethingOneTask, Step doSomethingProcessUnit)
            throws Exception {

        return this.jobBuilderFactory.get("sampleDoSomething")
                .listener(logJobExecutionListener)
                .start(doSomethingOneTask)
                .next(doSomethingProcessUnit)
				.build();
	}

    // トランザクションはステップ単位
	@Bean
    public Step doSomethingOneTask(Tasklet doSomethingTasklet) throws Exception {

        return this.stepBuilderFactory.get("doSomethingOneTask")
                .tasklet(doSomethingTasklet)
				.build();
	}

	@Bean
    public Tasklet doSomethingTasklet() {

        Tasklet tasklet = (contribution, context) -> {
            System.out.println("Tasklet executes something.");
            return RepeatStatus.FINISHED;
        };
        return tasklet;
	}

    @Bean
    public Step doSomethingProcessUnit(ItemReader<Sample> reader,
            ItemProcessor<Sample, Sample> processor, ItemWriter<Sample> writer) {

        SimpleStepBuilder<Sample, Sample> builder = this.stepBuilderFactory
                .get("doSomethingProcessUnit")
                .chunk(10);

        return builder
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public ItemReader<Sample> doSomethingReader() {

        // データベースを使わない一時的なデータを生成
        Deque<Sample> deque = new ArrayDeque<>();

        Sample item1 = new Sample();
        item1.setId("1");
        item1.setName("SampleItem1");
        deque.push(item1);
        Sample item2 = new Sample();
        item2.setId("2");
        item2.setName("SampleItem2");
        deque.push(item2);
        Sample item3 = new Sample();
        item3.setId("3");
        item3.setName("SampleItem3");
        deque.push(item3);

        ItemReader<Sample> reader = () -> {
            // 読み込んだ結果返される値が null であれば FW の読み込みが終了になる
            Sample item = deque.pollLast();
            if (item != null) {
                System.out.println(String.format("Item reader reads %s.", item.getName()));
            }
            return item;
        };
        return reader;
    }

    @Bean
    public ItemProcessor<Sample, Sample> doSomethingProcessor() {
        ItemProcessor<Sample, Sample> processor = (item) -> {
            Sample processedItem = new Sample();
            processedItem.setId(item.getId());
            processedItem.setName(item.getName());
            System.out.println(String.format("Item processor processes %s.", item.getName()));
            return processedItem;
        };
        return processor;
    }

    @Bean
    public ItemWriter<Sample> doSomethingWriter() {
        ItemWriter<Sample> writer = (items) -> {
            items.forEach(item -> System.out.println(
                    String.format("Item writer writes %s.", item.getName())));
        };
        return writer;
    }
}
