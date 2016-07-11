package jp.ne.hyukke.wts.hello.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import jp.ne.hyukke.wts.hello.core.domain.model.AbstractRepository;
import jp.ne.hyukke.wts.hello.core.domain.model.ResultPage;
import jp.ne.hyukke.wts.hello.domain.dao.UserDao;
import jp.ne.hyukke.wts.hello.domain.entity.User;
import jp.ne.hyukke.wts.hello.domain.vo.UserConditionVo;

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
        if (entity == null) {
            return null;
        }

        return this.createDomain(entity, UserDomain.class);
    }

    /**
     * 指定された{@code ID}でドメインを検索する.
     *
     * @param id {@code ID}
     * @return ドメイン
     */
    public UserDomain findById(Integer id) {
        Assert.notNull(id);

        User entity = this.userDao.findById(id);
        if (entity == null) {
            return null;
        }

        return this.createDomain(entity, UserDomain.class);
    }

    /**
     * 指定された条件に合致するエンティティを検索する.
     *
     * @param condition 条件
     * @return 結果ページ
     */
    public ResultPage<User> findByCondition(UserConditionVo condition) {
        Assert.notNull(condition);

        return this.userDao.findByCondition(condition);
    }

    /**
     * 指定されたエンティティを登録する.
     *
     * @param entity エンティティ
     * @return 登録済みのエンティティ
     */
    public User register(User entity) {
        Assert.notNull(entity);

        return this.userDao.register(entity);
    }

    /**
     * 指定されたエンティティを更新する.
     *
     * @param entity エンティティ
     * @return 登録済みのエンティティ
     */
    public User update(User entity) {
        Assert.notNull(entity);

        return this.userDao.update(entity);
    }

    /**
     * 指定された{@code ID}でエンティティを削除する.
     *
     * @param id {@code ID}
     */
    public void delete(Integer id) {
        Assert.notNull(id);

        this.userDao.delete(id);
    }
}
