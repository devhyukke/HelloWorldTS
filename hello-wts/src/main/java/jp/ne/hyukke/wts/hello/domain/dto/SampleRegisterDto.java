package jp.ne.hyukke.wts.hello.domain.dto;

import java.io.Serializable;

import jp.ne.hyukke.wts.hello.domain.constants.SampleType;

import lombok.Data;

/**
 * サンプルを登録するための情報を保持するDTOクラス.
 *
 * @author hyukke
 */
@Data
public class SampleRegisterDto implements Serializable {

    private static final long serialVersionUID = -7481816099189309984L;

    /** 名称 */
    private String name;
    /** 種別 */
    private SampleType type;
}
