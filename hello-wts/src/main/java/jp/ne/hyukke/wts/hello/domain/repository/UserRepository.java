package jp.ne.hyukke.wts.hello.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import jp.ne.hyukke.wts.hello.core.domain.model.ResultPage;
import jp.ne.hyukke.wts.hello.domain.dao.UserDao;
import jp.ne.hyukke.wts.hello.domain.model.User;
import jp.ne.hyukke.wts.hello.domain.vo.UserConditionVo;

/**
 * ユーザーのドメインモデルを扱うリポジトリクラス.
 *
 * @author hyukke
 */
@Repository
public class UserRepository {

    @Autowired
    private UserDao userDao;

    /**
     * 指定されたユーザー名でドメインモデルを検索する.
     *
     * @param username ユーザー名
     * @return ドメインモデル
     */
    public User findByUsername(String username) {
        Assert.hasText(username);

        return this.userDao.findByUsername(username);
    }

    /**
     * 指定された{@code ID}でドメインモデルを検索する.
     *
     * @param id {@code ID}
     * @return ドメインモデル
     */
    public User findById(Integer id) {
        Assert.notNull(id);

        return this.userDao.findById(id);
    }

    /**
     * 指定された条件に合致するドメインモデルを検索する.
     *
     * @param condition 条件
     * @return 結果ページ
     */
    public ResultPage<User> findByCondition(UserConditionVo condition) {
        Assert.notNull(condition);

        return this.userDao.findByCondition(condition);
    }

    /**
     * 指定されたドメインモデルを登録する.
     *
     * @param model ドメインモデル
     * @return 登録済みのドメインモデル
     */
    public User register(User model) {
        Assert.notNull(model);

        return this.userDao.register(model);
    }

    /**
     * 指定されたドメインモデルを更新する.
     *
     * @param model ドメインモデル
     * @return 登録済みのドメインモデル
     */
    public User update(User model) {
        Assert.notNull(model);

        return this.userDao.update(model);
    }

    /**
     * 指定された{@code ID}でドメインモデルを削除する.
     *
     * @param id {@code ID}
     */
    public void delete(Integer id) {
        Assert.notNull(id);

        this.userDao.delete(id);
    }
}
