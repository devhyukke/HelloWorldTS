package jp.ne.yukke.wts.hello.batch.job;

import jp.ne.yukke.wts.hello.batch.HelloWorldTsBatchApplication;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.JobLauncherCommandLineRunner;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.OutputCapture;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * SampleJobTest.
 *
 * @author y_hiraune
 */
@IntegrationTest
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HelloWorldTsBatchApplication.class)
public class SampleDoSomethingJobTest {

	@Autowired
	private JobLauncherCommandLineRunner runner;
	@Rule
	public OutputCapture capture = new OutputCapture();

	// XXX http://stackoverflow.com/questions/31943442/launch-spring-batch-job-problems-in-spring-boot-applications
	@Test
	public void testSampleDoSomethingJob() throws Exception {

		this.runner.run(new String[] { "spring.batch.job.names=sampleDoSomething" });

		String output = this.capture.toString();
		assertThat(output, containsString("Do something!!!"));
	}
}
