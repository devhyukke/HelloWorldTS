package jp.ne.hyukke.wts.hello.batch.job;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.OutputCapture;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jp.ne.hyukke.wts.hello.batch.HelloWorldTsBatchApplication;

/**
 * SampleJobTest.
 *
 * @author hyukke
 */
@IntegrationTest
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HelloWorldTsBatchApplication.class)
// @SpringApplicationConfiguration(classes = TestConfiguration.class)
public class SampleDoSomethingJobTest {

	// TODO now working...
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	private JobRepository jobRepository;
	@Autowired
	private JobRegistry jobRegistry;
	@Autowired
	private JobOperator jobOperator;
	// @Autowired
	// private JobLauncherCommandLineRunner runner;
	@Rule
	public OutputCapture capture = new OutputCapture();

	// XXX http://stackoverflow.com/questions/31943442/launch-spring-batch-job-problems-in-spring-boot-applications
	@Test
	public void testSampleDoSomethingJob() throws Exception {

		jobOperator.start("sampleDoSomething", "");
		// this.runner.run(new String[] { "-job=sampleDoSomething" });
		// int exitCode = SpringApplication.exit(SpringApplication.run(HelloWorldTsBatchApplication.class,
		// "spring.batch.job.names=sampleDoSomething"));
		//
		// assertThat(Integer.valueOf(exitCode), equalTo(Integer.valueOf(0)));
		String output = this.capture.toString();
		assertThat(output, containsString("Do something!!!"));
	}
}
