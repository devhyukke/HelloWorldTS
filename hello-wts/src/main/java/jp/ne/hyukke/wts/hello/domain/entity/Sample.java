package jp.ne.hyukke.wts.hello.domain.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import jp.ne.hyukke.wts.hello.domain.constants.SampleType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * サンプルのエンティティクラス.
 *
 * @author hyukke
 */
@Data
@EqualsAndHashCode(of = "id")
@RequiredArgsConstructor(staticName = "valueOf")
public class Sample implements Serializable {

    private static final long serialVersionUID = 7133708553303041581L;

    /** {@code ID} */
    private final Integer id;
    /** 名称 */
    @NotEmpty
    private String name;
    /** 種別 */
    @NotNull
    private SampleType type;

    public Sample() {
        this.id = Integer.valueOf(0);
    }
}
