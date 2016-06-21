package jp.ne.hyukke.wts.hello.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * {@code Spring Security}の設定クラス.
 *
 * @author hyukke
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${hello-wts.rememberMe.tokenValiditySeconds}")
    private int tokenValiditySeconds;

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 静的リソースに対するアクセスにはセキュリティ設定を無視
        web.ignoring().antMatchers("/images/**", "/css/**", "/js/**", "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // セッション管理を設定
        http.sessionManagement()
                .invalidSessionUrl("/login?timeout")    // セッション無効の URL
                .sessionFixation().newSession()         // セッション ID を変更し新しく再作成
                .maximumSessions(1)                     // 最大セッション数
                .expiredUrl("/login?expired");          // セッション有効期限切れの URL

        // 認可を設定
        http.authorizeRequests()
                .antMatchers("/login").permitAll()  // 認証なしでアクセス可能
                .anyRequest().authenticated();      // それ以外は認証なしではアクセス不可

        // ログインおよびログアウトを設定
        http.formLogin()
                .loginProcessingUrl("/authenticate")                            // 認証処理の URL
                .loginPage("/login")                                            // ログインページの URL
                .failureUrl("/login?error")                                     // ログイン失敗時の URL
                .defaultSuccessUrl("/index")                                    // ログイン成功時の URL
                .usernameParameter("username")                                  // ユーザー名のパラメータ
                .passwordParameter("password")                                  // パスワードのパラメータ
        .and().rememberMe()
            .tokenValiditySeconds(this.tokenValiditySeconds)                    // トークンの有効期間
        .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))   // ログアウトの URL
                .logoutSuccessUrl("/login?success")                             // ログアウト成功時の URL
                .invalidateHttpSession(false)                                   // セッションを無効にする
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
    // インメモリでの認証
    @Configuration
    @Profile("mock")
    static class LocalAuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            // インメモリによる認証
            auth.inMemoryAuthentication()
                    .withUser("Username").password("Password").roles("ADMIN");
        }
    }

    // カスタムでの認証
    @Configuration
    @Profile("local")
    static class DevelopmentAuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {

        @Autowired
        private UserDetailsServiceImpl userDetailsServiceImpl;
        @Autowired
        private PasswordEncoder passwordEncoder;

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(this.userDetailsServiceImpl)
                    .passwordEncoder(this.passwordEncoder);
        }
    }

    // XXX case 2 メソッドで定義 @Profileによる識別はできない
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
//
//    @Autowired
//    public void configureAuthenticationLocal(AuthenticationManagerBuilder auth) throws Exception {
//        // インメモリでの認証
//        auth.inMemoryAuthentication()
//                .withUser("Username").password("Password").roles("ADMIN");
//    }
//
//    @Autowired
//    public void configureAuthenticationDevelopment(AuthenticationManagerBuilder auth) throws Exception {
//        // カスタムでの認証
//        auth.userDetailsService(this.userDetailsService)
//                .passwordEncoder(this.passwordEncoder());
//    }
}
