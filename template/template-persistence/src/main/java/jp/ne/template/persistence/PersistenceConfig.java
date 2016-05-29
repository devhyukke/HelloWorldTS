package jp.ne.template.persistence;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 永続層における設定クラス.
 *
 * @author hyukke
 */
@Configuration
@EntityScan(basePackages = "jp.ne.hyukke.wts.hello.persistence.entity")
@EnableJpaRepositories(basePackages = "jp.ne.hyukke.wts.hello.persistence.repository")
public class PersistenceConfig {

}
