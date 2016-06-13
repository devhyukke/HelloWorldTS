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

    TYPE_A("種別Ａ"), TYPE_B("種別Ｂ"), TYPE_C("種別Ｃ");

    @Getter
    private String label;
}
