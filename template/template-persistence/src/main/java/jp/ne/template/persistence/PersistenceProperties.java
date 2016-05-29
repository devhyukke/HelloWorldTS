package jp.ne.template.persistence;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 永続層におけるプロパティクラス.
 *
 * @author hyukke
 */
@Configuration
@PropertySource("classpath:persistence-${spring.profiles.active}.properties")
public class PersistenceProperties {

}
