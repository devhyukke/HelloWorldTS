package jp.ne.hyukke.wts.hello.web.form;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.StringUtils;

import jp.ne.hyukke.wts.hello.core.validation.groups.Registration;

import lombok.Data;

/**
 * ユーザーの入力を保持するフォームクラス.
 *
 * @author hyukke
 */
@Data
public class UserForm {

    /** {@code Spring Security}ユーザー名 */
    @NotEmpty
    @Length(min = 0, max = 32)
    private String username;
    /** 表示名 */
    @NotEmpty
    @Length(min = 0, max = 64)
    private String displayName;
    /** ロール{@code ID} */
    @NotNull
    private Integer roleId;
    // 新規作成時のみパスワードを指定する想定
    /** パスワード */
    @NotEmpty(groups = Registration.class)
    @Length(min = 0, max = 64, groups = Registration.class)
    private String password;
    /** 確認パスワード */
    @NotEmpty(groups = Registration.class)
    @Length(min = 0, max = 64, groups = Registration.class)
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
