package jp.ne.hyukke.wts.hello.domain.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * ユーザーのエンティティクラス.
 *
 * @author hyukke
 */
@Data
@EqualsAndHashCode(of = "id")
@RequiredArgsConstructor(staticName = "valueOf")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /** {@code ID} */
    private final Integer id;
    /** {@code Spring Security}ユーザー名 */
    private String username;
    /** パスワード */
    private String password;
    /** 表示名 */
    private String displayName;
    /** {@link Role} */
    private Role role;

    public User() {
        this.id = Integer.valueOf(0);
    }

    /**
     * @return システム管理者であれば{@code true}
     */
    public boolean isSystemAdmin() {
        return this.role.isSystemManagement();
    }
}
