package jp.ne.hyukke.wts.hello.web;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

/**
 * ログインユーザーの情報を保持するクラス.
 *
 * @author hyukke
 */
public class LoginUser extends User {

    // TODO ユーザー情報の取り扱いを検討

    public LoginUser(String username, String password) {
        // TODO 権限の取り扱い
        super(username, password, AuthorityUtils.createAuthorityList("ADMIN"));
    }
}
