package jp.ne.hyukke.wts.hello.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jp.ne.hyukke.wts.hello.domain.dao.UserDao;
import jp.ne.hyukke.wts.hello.domain.entity.LoginUser;

/**
 * {@link UserDetailsService}の実装クラス.
 *
 * @author hyukke
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    // TODO Repositoryを通すように修正
    @Autowired
    private UserDao dao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LoginUser user = this.dao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User is not found.");
        }

        // TODO パスワードの取り扱いを検討
//        return new LoginUser(null, "Username", this.passwordEncoder.encode("Password"), "");
        return user;
    }
}
