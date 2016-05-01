package jp.ne.yukke.wts.hello.web;

import jp.ne.yukke.wts.hello.web.HelloWorldTsWebApplication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HelloWorldTsWebApplication.class)
@WebAppConfiguration
public class HelloWorldTsWebApplicationTests {

	@Test
	public void contextLoads() {
	}

}
