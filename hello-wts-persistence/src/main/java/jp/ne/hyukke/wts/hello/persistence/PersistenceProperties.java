package jp.ne.hyukke.wts.hello.persistence;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Persistence Properties.
 *
 * @author hyukke
 */
@Configuration
@PropertySource("classpath:persistence-${spring.profiles.active}.properties")
public class PersistenceProperties {

}
