package jp.ne.hyukke.wts.hello.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import jp.ne.hyukke.wts.hello.domain.DomainConfig;
import jp.ne.hyukke.wts.hello.persistence.PersistenceConfig;

/**
 * Hello WorldTS!! Web アプリケーションを起動するクラス.
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

    /**
     * アプリケーションを起動する.
     *
     * @param args パラメータ
     */
	public static void main(String[] args) {
		SpringApplication.run(HelloWorldTsWebApplication.class, args);
	}
}
