package jp.ne.hyukke.wts.hello.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

import jp.ne.hyukke.wts.hello.domain.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * {@code M_ROLE}のエンティティクラス.
 *
 * @author hyukke
 */
@Entity
@Table(name = "M_ROLE")
@Data
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class MRole extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** {@code ID} */
    @Id
    @GeneratedValue
    @Column(name = "ROLE_ID")
    private Integer id;

    /** ロール名 */
    @Column(name = "ROLE_NAME")
    private String name;

    /** ロール種別 */
    @Column(name = "ROLE_TYPE")
    private String type;

    /** システム管理 */
    @Column(name = "SYSTEM_MANAGEMENT")
    private boolean systemManagement;

    /**
     * モデルに変換する.
     *
     * @return モデル
     */
    @Transient
    public Role toModel() {
        Role model = Role.valueOf(this.id);
        model.setName(this.name);
        model.setType(this.type);
        model.setSystemManagement(this.systemManagement);
        return model;
    }
}
