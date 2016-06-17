package jp.ne.hyukke.wts.hello.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import jp.ne.hyukke.wts.hello.core.domain.model.AbstractRepository;
import jp.ne.hyukke.wts.hello.domain.dao.UserDao;
import jp.ne.hyukke.wts.hello.domain.entity.User;

/**
 * ユーザーを扱うリポジトリクラス.
 *
 * @author hyukke
 */
@Repository
public class UserRepository extends AbstractRepository {

    @Autowired
    private UserDao userDao;

    /**
     * 指定されたユーザー名でドメインを検索する.
     *
     * @param username ユーザー名
     * @return ドメイン
     */
    public UserDomain findByUsername(String username) {
        Assert.hasText(username);

        User entity = this.userDao.findByUsername(username);

        return this.createDomain(entity, UserDomain.class);
    }
}
