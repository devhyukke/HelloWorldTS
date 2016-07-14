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
     * 指定されたユーザー名でデータを検索する.
     *
     * @param username ユーザー名
     * @return ドメインモデル
     */
    User findByUsername(String username);

    /**
     * 指定された{@code ID}でデータを検索する.
     *
     * @param id {@code ID}
     * @return ドメインモデル
     */
    User findById(Integer id);

    /**
     * 指定された条件でデータを検索する.
     *
     * @param condition 条件
     * @return 結果ページ
     */
    ResultPage<User> findByCondition(UserConditionVo condition);

    /**
     * データを登録する.
     *
     * @param model ドメインモデル
     * @return 登録済みのドメインモデル
     */
    User register(User model);

    /**
     * データを更新する.
     *
     * @param model ドメインモデル
     * @return 更新済みのドメインモデル
     */
    User update(User model);

    /**
     * 指定された{@code ID}でデータを削除する.
     *
     * @param id {@code ID}
     */
    void delete(Integer id);
}
