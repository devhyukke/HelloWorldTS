package jp.ne.hyukke.wts.hello.persistence;

import java.util.Calendar;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 永続層における設定クラス.
 *
 * @author hyukke
 */
@Configuration
@EntityScan(basePackages = "jp.ne.hyukke.wts.hello.persistence.entity")
@EnableJpaRepositories(basePackages = "jp.ne.hyukke.wts.hello.persistence.repository")
@EnableJpaAuditing(auditorAwareRef = "auditorAware", dateTimeProviderRef = "dateTimeProvider")
public class PersistenceConfig {

    // "/META-INF/orm.xml"でリスナーを指定し、カラムにアノテーションを付与することで自動的に値が設定される
    /**
     * @return 作成者を供給するオブジェクト
     */
    @Bean(name = "auditorAware")
    AuditorAware<String> auditorAwareImpl() {

        return new AuditorAware<String>() {

            @Override
            public String getCurrentAuditor() {

                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (authentication == null || !authentication.isAuthenticated()) {
                    return null;
                }

                return authentication.getName();
            }
        };
    }

    /**
     * @return 日時を供給するオブジェクト
     */
    @Bean(name = "dateTimeProvider")
    DateTimeProvider dateTimeProvider() {

        return new DateTimeProvider() {
            @Override
            public Calendar getNow() {
                return Calendar.getInstance();
            }
        };
    }
}
