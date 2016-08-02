package jp.ne.hyukke.wts.hello.web.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

/**
 * アカウント設定におけるユーザー名の入力を保持するフォームクラス.
 *
 * @author hyukke
 */
@Data
public class SettingAccountUsernameForm {

    /** {@code Spring Security}ユーザー名 */
    @NotEmpty
    @Length(min = 0, max = 32)
    private String username;
}
