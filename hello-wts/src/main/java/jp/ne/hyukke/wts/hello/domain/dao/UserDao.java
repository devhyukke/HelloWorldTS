package jp.ne.hyukke.wts.hello.domain.dao;

import jp.ne.hyukke.wts.hello.domain.entity.User;

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
}
