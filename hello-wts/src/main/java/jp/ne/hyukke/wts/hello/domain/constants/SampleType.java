package jp.ne.hyukke.wts.hello.domain.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * サンプル種別を表す列挙.
 *
 * @author hyukke
 */
@AllArgsConstructor
public enum SampleType {

    TYPE_A("サンプル種別Ａ"), TYPE_B("サンプル種別Ｂ"), TYPE_C("サンプル種別Ｃ");

    @Getter
    private String label;
}
