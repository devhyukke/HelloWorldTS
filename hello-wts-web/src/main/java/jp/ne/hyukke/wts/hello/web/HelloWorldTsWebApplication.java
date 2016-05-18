package jp.ne.hyukke.wts.hello.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import jp.ne.hyukke.wts.hello.domain.DomainConfig;
import jp.ne.hyukke.wts.hello.persistence.PersistenceConfig;

/**
 * Hello WorldTS!! web application.
 *
 * @author hyukke
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = {
        HelloWorldTsWebApplication.class,
        DomainConfig.class,
        PersistenceConfig.class })
public class HelloWorldTsWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldTsWebApplication.class, args);
	}
}
