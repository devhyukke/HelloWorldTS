package jp.ne.hyukke.wts.hello.web.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

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
    private String username;
    /** パスワード */
    @NotEmpty
    private String password;
    /** 表示名 */
    @NotEmpty
    private String displayName;
    /** ロール{@code ID} */
    @NotNull
    private Integer roleId;
}
