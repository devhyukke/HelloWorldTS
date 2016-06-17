package jp.ne.hyukke.wts.hello.domain.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import jp.ne.hyukke.wts.hello.domain.dao.UserDao;
import jp.ne.hyukke.wts.hello.domain.entity.LoginUser;
import jp.ne.hyukke.wts.hello.persistence.entity.MUser;
import jp.ne.hyukke.wts.hello.persistence.repository.MUserRepository;

/**
 * {@link UserDao}の実装クラス.
 *
 * @author hyukke
 */
@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private MUserRepository repository;

    @Override
    public LoginUser findByUsername(String username) {
        Assert.hasText(username);

        MUser entity = this.repository.findByUsername(username);
        if (entity == null) {
            return null;
        }

        return new LoginUser(entity.getId(), entity.getUsername(), entity.getPassword(), entity.getDisplayName());
    }
}
