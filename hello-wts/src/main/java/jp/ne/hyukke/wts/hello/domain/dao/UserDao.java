package jp.ne.hyukke.wts.hello.domain.dao;

import jp.ne.hyukke.wts.hello.core.domain.model.ResultPage;
import jp.ne.hyukke.wts.hello.domain.model.User;
import jp.ne.hyukke.wts.hello.domain.vo.UserConditionVo;

/**
 * ユーザーのデータにアクセスするインタフェース.
 *
 * @author hyukke
 */
public interface UserDao {

    /**
     * 指定されたユーザー名でエンティティを検索する.
     *
     * @param username ユーザー名
     * @return エンティティ
     */
    User findByUsername(String username);

    /**
     * 指定された{@code ID}でエンティティを検索する.
     *
     * @param id {@code ID}
     * @return エンティティ
     */
    User findById(Integer id);

    /**
     * 指定された条件でエンティティを検索する.
     *
     * @param condition 条件
     * @return 結果ページ
     */
    ResultPage<User> findByCondition(UserConditionVo condition);

    /**
     * エンティティを登録する.
     *
     * @param entity エンティティ
     * @return 登録済みのエンティティ
     */
    User register(User entity);

    /**
     * エンティティを更新する.
     *
     * @param entity エンティティ
     * @return 更新済みのエンティティ
     */
    User update(User entity);

    /**
     * 指定された{@code ID}でエンティティを削除する.
     *
     * @param id {@code ID}
     */
    void delete(Integer id);
}
