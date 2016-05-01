package jp.ne.yukke.wts.hello.batch.job;

import jp.ne.yukke.wts.hello.batch.HelloWorldTsBatchApplication;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
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
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HelloWorldTsBatchApplication.class)
public class SampleJobTest {

	@Rule
	public OutputCapture capture = new OutputCapture();

	@Test
	public void testSample() throws Exception {

		int exitCode = SpringApplication.exit(SpringApplication.run(HelloWorldTsBatchApplication.class,
				"spring.batch.job.names=sample"));

		assertThat(Integer.valueOf(exitCode), equalTo(Integer.valueOf(0)));
		String output = this.capture.toString();
		assertThat(output, containsString("Do something!!!"));
	}
}
