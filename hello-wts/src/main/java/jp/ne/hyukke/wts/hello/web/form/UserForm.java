package jp.ne.hyukke.wts.hello.web.form;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.StringUtils;

import lombok.Data;

/**
 * ユーザーの入力を保持するフォームクラス.
 *
 * @author hyukke
 */
@Data
public class UserForm {

    public static interface Create {

    }

    /** {@code Spring Security}ユーザー名 */
    @NotEmpty
    private String username;
    /** 表示名 */
    @NotEmpty
    private String displayName;
    /** ロール{@code ID} */
    @NotNull
    private Integer roleId;
    // 新規作成時のみパスワードを指定する想定
    /** パスワード */
    @NotEmpty(groups = Create.class)
    private String password;
    /** 確認パスワード */
    @NotEmpty(groups = Create.class)
    private String confirmationPassword;

    /**
     * @return 妥当なパスワードである場合は{@code true}
     */
    @AssertTrue(message = "{custom.validation.user.password.unmatch}")
    private boolean isValidPassword() {
        if (StringUtils.isEmpty(this.password) || StringUtils.isEmpty(this.confirmationPassword)) {
            return true;
        }
        return this.password.equals(confirmationPassword);
    }
}
