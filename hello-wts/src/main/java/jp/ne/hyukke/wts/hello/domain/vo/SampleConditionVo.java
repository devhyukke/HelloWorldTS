package jp.ne.hyukke.wts.hello.domain.vo;

import java.io.Serializable;

import jp.ne.hyukke.wts.hello.domain.constants.SampleType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * サンプルの検索条件を保持する値オブジェクトクラス.
 *
 * @author hyukke
 */
@Data
@EqualsAndHashCode(of = {"id", "name", "type"})
@RequiredArgsConstructor(staticName = "valueOf")
public class SampleConditionVo implements Serializable {

    private static final long serialVersionUID = 8727850454584019273L;

    /** {@code ID} */
    private final Integer id;
    /** 名称 */
    private final String name;
    /** 種別 */
    private final SampleType type;
}
