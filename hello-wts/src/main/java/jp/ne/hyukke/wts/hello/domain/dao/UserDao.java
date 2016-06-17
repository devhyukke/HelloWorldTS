package jp.ne.hyukke.wts.hello.domain.dao;

import jp.ne.hyukke.wts.hello.domain.entity.LoginUser;

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
    LoginUser findByUsername(String username);
}
