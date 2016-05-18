package jp.ne.hyukke.wts.hello.domain.entity;

import java.io.Serializable;

import jp.ne.hyukke.wts.hello.domain.constants.SampleType;

import lombok.Data;

/**
 * Sample Entity.
 * 
 * @author hyukke
 */
@Data
public class Sample implements Serializable {

    private static final long serialVersionUID = 7133708553303041581L;

    private Integer id;

    private String name;

    private SampleType type;
}
