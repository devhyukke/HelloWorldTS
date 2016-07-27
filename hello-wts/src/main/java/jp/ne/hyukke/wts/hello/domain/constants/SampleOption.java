package jp.ne.hyukke.wts.hello.domain.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * サンプルオプションを表す列挙.
 *
 * @author hyukke
 */
@AllArgsConstructor
public enum SampleOption {

    NONE("未指定"), OPTION_A("オプションＡ"), OPTION_B("オプションＢ"), OPTION_C("オプションＣ");

    @Getter
    private String label;
}
