package jp.ne.hyukke.wts.hello.web.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

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
    private String name;
    /** 種別 */
    @NotNull
    private SampleType type;
}
