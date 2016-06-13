package jp.ne.hyukke.wts.hello.web.form;

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
    private String name;
    /** 種別 */
    private SampleType type;
}
