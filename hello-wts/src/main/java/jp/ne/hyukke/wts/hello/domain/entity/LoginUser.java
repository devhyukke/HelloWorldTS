package jp.ne.hyukke.wts.hello.domain.entity;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

/**
 * ログインユーザーの情報を保持するクラス.
 *
 * @author hyukke
 */
public class LoginUser extends User {

    @Getter
    private Integer id;

    @Getter
    private String displayName;

    /**
     * @param id ユーザー{@code ID}
     * @param username ユーザー名
     * @param password パスワード
     * @param displayName 表示名
     */
    public LoginUser(Integer id, String username, String password, String displayName) {
        // TODO 権限の取り扱い
        super(username, password, AuthorityUtils.createAuthorityList("ADMIN"));
        this.id = id;
        this.displayName = displayName;
    }
}
