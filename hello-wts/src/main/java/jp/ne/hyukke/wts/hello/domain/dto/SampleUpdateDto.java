package jp.ne.hyukke.wts.hello.domain.dto;

import java.io.Serializable;

import jp.ne.hyukke.wts.hello.domain.constants.SampleType;

import lombok.Data;

/**
 * サンプルを更新するための情報を保持するDTOクラス.
 *
 * @author hyukke
 */
@Data
public class SampleUpdateDto implements Serializable {

    private static final long serialVersionUID = -4587652969784381212L;

    /** {@code ID} */
    private Integer id;
    /** 名称 */
    private String name;
    /** 種別 */
    private SampleType type;
}
