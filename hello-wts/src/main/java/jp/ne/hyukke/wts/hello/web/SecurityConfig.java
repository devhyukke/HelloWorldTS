package jp.ne.hyukke.wts.hello.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jp.ne.hyukke.wts.hello.domain.service.UserDetailsServiceImpl;

/**
 * {@code Spring Security}の設定クラス.
 *
 * @author hyukke
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

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

    /**
     * @return パスワードエンコーダー
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // XXX case 1 クラスで定義
//    @Configuration
//    @Profile("local")
//    static class LocalAuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
//
//        @Override
//        public void init(AuthenticationManagerBuilder auth) throws Exception {
//            // インメモリによる認証
//            auth.inMemoryAuthentication()
//                    .withUser("Username").password("Password").roles("ADMIN");
//        }
//    }
//
//    @Configuration
//    @Profile("development")
//    static class DevelopmentAuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
//
//        @Autowired
//        private UserDetailsServiceImpl userDetailsServiceImpl;
//        @Autowired
//        private PasswordEncoder passwordEncoder;
//
//        @Override
//        public void init(AuthenticationManagerBuilder auth) throws Exception {
//            auth.userDetailsService(this.userDetailsServiceImpl)
//                    .passwordEncoder(this.passwordEncoder);
//        }
//    }

    // XXX case 2 メソッドで定義
    @Autowired
    @Profile("mock")
    public void configureAuthenticationLocal(AuthenticationManagerBuilder auth) throws Exception {
        // インメモリでの認証
        // TODO 権限について整理
        auth.inMemoryAuthentication()
                .withUser("Username").password("Password").roles("ADMIN");
    }

    @Autowired
    @Profile("local")
    public void configureAuthenticationDevelopment(AuthenticationManagerBuilder auth) throws Exception {
        // カスタムでの認証
        auth.userDetailsService(this.userDetailsService)
                .passwordEncoder(this.passwordEncoder());
    }
}
