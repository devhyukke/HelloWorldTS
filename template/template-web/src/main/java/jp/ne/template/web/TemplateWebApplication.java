package jp.ne.template.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import jp.ne.template.domain.DomainConfig;
import jp.ne.template.persistence.PersistenceConfig;

/**
 * Hello WorldTS!! Web アプリケーションを起動するクラス.
 *
 * @author hyukke
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = {
        TemplateWebApplication.class,
        DomainConfig.class,
        PersistenceConfig.class })
public class TemplateWebApplication {

    /**
     * アプリケーションを起動する.
     *
     * @param args パラメータ
     */
	public static void main(String[] args) {
		SpringApplication.run(TemplateWebApplication.class, args);
	}
}
