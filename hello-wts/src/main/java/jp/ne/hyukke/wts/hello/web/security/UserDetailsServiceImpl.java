package jp.ne.hyukke.wts.hello.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import jp.ne.hyukke.wts.hello.domain.repository.UserDomain;
import jp.ne.hyukke.wts.hello.domain.repository.UserRepository;

/**
 * {@link UserDetailsService}の実装クラス.
 *
 * @author hyukke
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDomain user = this.repository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User is not found.");
        }

        return new LoginUser(user.getEntity());
    }
}
