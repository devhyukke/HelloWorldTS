package jp.ne.hyukke.wts.hello.web.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

/**
 * ログインユーザーの情報を保持するクラス.
 *
 * @author hyukke
 */
public class LoginUser extends User {

    private static final long serialVersionUID = 1L;

    @Getter
    private final jp.ne.hyukke.wts.hello.domain.entity.User user;

    /**
     * @param id ユーザー{@code ID}
     * @param username ユーザー名
     * @param password パスワード
     * @param displayName 表示名
     */
    public LoginUser(jp.ne.hyukke.wts.hello.domain.entity.User user) {
        super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().getType()));
        this.user = user;
    }
}
