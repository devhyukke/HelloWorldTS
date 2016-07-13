package jp.ne.hyukke.wts.hello.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

import jp.ne.hyukke.wts.hello.domain.constants.SampleType;
import jp.ne.hyukke.wts.hello.domain.model.Sample;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * {@code T_SAMPLE}のエンティティクラス.
 *
 * @author hyukke
 */
@Entity
@Table(name = "T_SAMPLE")
@Data
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class TSample extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** {@code ID} */
    @Id
    @GeneratedValue
    @Column(name = "SAMPLE_ID")
    private Integer id;

    /** 名称 */
    @Column(name = "SAMPLE_NAME")
    private String name;

    /** 種別 */
    @Enumerated(EnumType.STRING)
    @Column(name = "SAMPLE_TYPE")
    private SampleType type;

    /**
     * モデルに変換する.
     *
     * @return モデル
     */
    @Transient
    public Sample toModel() {
        Sample sample = Sample.valueOf(this.id);
        sample.setName(this.name);
        sample.setType(this.type);
        sample.setLastUpdatedDate(this.getUpdatedDate());
        sample.setVersion(this.getVersion());
        return sample;
    }
}
