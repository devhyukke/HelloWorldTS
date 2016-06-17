package jp.ne.hyukke.wts.hello.domain.dto;

import java.io.Serializable;

import jp.ne.hyukke.wts.hello.domain.constants.SampleType;

import lombok.Data;

/**
 * サンプルを保持するDTOクラス.
 *
 * @author hyukke
 */
@Data
public class SampleDto implements Serializable {

    private static final long serialVersionUID = 7133708553303041581L;

    /** {@code ID} */
    private Integer id;
    /** 名称 */
    private String name;
    /** 種別 */
    private SampleType type;
}
