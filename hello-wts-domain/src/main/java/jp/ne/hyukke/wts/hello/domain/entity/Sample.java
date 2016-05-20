package jp.ne.hyukke.wts.hello.domain.entity;

import java.io.Serializable;

import jp.ne.hyukke.wts.hello.domain.constants.SampleType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * Sample Entity.
 * 
 * @author hyukke
 */
@Data
@EqualsAndHashCode(of = "id")
@RequiredArgsConstructor(staticName = "valueOf")
public class Sample implements Serializable {

    private static final long serialVersionUID = 7133708553303041581L;

    private final Integer id;

    private String name;

    private SampleType type;
}
