package jp.ne.hyukke.wts.hello.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * {@code Spring Security}の設定クラス.
 *
 * @author hyukke
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 静的リソースに対するアクセスにはセキュリティ設定を無視
        web.ignoring().antMatchers("/images/**", "/css/**", "/js/**", "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 認可の設定
        http.authorizeRequests()
                .antMatchers("/login").permitAll()  // 認証なしでアクセス可能
                .anyRequest().authenticated();      // それ以外は認証なしではアクセス不可

        // ログイン設定
        http.formLogin()
                .loginProcessingUrl("/authenticate")    // 認証処理の URL
                .loginPage("/login")                    // ログインページの URL
                .failureUrl("/login?error")             // ログイン失敗時の URL
                .defaultSuccessUrl("/index")            // ログイン成功時の URL
                .usernameParameter("username")          // ユーザー名のパラメータ
                .passwordParameter("password");         // パスワードのパラメータ

        // ログアウト設定
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))   // ログアウトの URL
                .logoutSuccessUrl("/login?success")                             // ログアウト成功時の URL
                .deleteCookies("JSESSIONID");                                   // クッキーからの削除
    }

    @Configuration
    @Profile("local")
    static class LocalAuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            // インメモリによる認証
            auth.inMemoryAuthentication()
                    .withUser("Username").password("Password").roles("ADMIN");
        }
    }
}
