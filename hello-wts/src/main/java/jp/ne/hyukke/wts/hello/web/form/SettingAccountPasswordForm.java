package jp.ne.hyukke.wts.hello.web.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import jp.ne.hyukke.wts.hello.core.web.validation.groups.Registration;

import lombok.Data;

/**
 * アカウント設定におけるパスワードの入力を保持するフォームクラス.
 *
 * @author hyukke
 */
@Data
public class SettingAccountPasswordForm {

    /** 古いパスワード */
    @NotEmpty(groups = Registration.class)
    @Length(min = 0, max = 64, groups = Registration.class)
    private String oldPassword;
    /** 新しいパスワード */
    @NotEmpty(groups = Registration.class)
    @Length(min = 0, max = 64, groups = Registration.class)
    private String newPassword;
    /** 確認パスワード */
    @NotEmpty(groups = Registration.class)
    @Length(min = 0, max = 64, groups = Registration.class)
    private String confirmationPassword;
}
