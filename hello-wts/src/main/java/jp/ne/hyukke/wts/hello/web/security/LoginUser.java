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

    // ロールに付与する接頭辞を定義
    // Spring Security による認証において、 hasRole(String) を呼び出した際に判定されてしまうため、付与しておく必要がある
    private static final String ROLE_PREFIX = "ROLE_";

    @Getter
    private final jp.ne.hyukke.wts.hello.domain.model.User user;

    /**
     * @param id ユーザー{@code ID}
     * @param username ユーザー名
     * @param password パスワード
     * @param displayName 表示名
     */
    public LoginUser(jp.ne.hyukke.wts.hello.domain.model.User user) {
        super(user.getUsername(), user.getPassword(), AuthorityUtils
                .createAuthorityList(ROLE_PREFIX.concat(user.getRole().getType())));
        this.user = user;
    }
}
