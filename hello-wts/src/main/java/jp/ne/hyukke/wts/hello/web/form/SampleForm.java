package jp.ne.hyukke.wts.hello.web.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import jp.ne.hyukke.wts.hello.domain.constants.SampleOption;
import jp.ne.hyukke.wts.hello.domain.constants.SampleType;

import lombok.Data;

/**
 * サンプルの入力を保持するフォームクラス.
 *
 * @author hyukke
 */
@Data
public class SampleForm {

    /** 名称 */
    @NotEmpty
    @Length(min = 0, max = 64)
    private String name;
    /** 種別 */
    @NotNull
    private SampleType type;
    /** メールアドレス */
    @Length(min = 0, max = 256)
    @Email
    private String email;
    /** パスワード */
    @Length(min = 0, max = 64)
    private String password;
    /** チェック */
    @NotNull
    private boolean checked;
    /** オプション */
    @NotNull
    private SampleOption option;
    /** 備考 */
    @Length(min = 0, max = 10000)
    private String remark;
}
