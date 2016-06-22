package jp.ne.hyukke.wts.hello.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Data;

/**
 * エンティティの抽象クラス.
 *
 * @author hyukke
 */
@Data
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = 8163409377779169058L;

    /** 登録ユーザー名 */
    @Column(name = "REGISTERED_USERNAME")
    private String registeredUsername;

    /** 登録日 */
    // TODO LocalDateTimeに習性
    @Column(name = "REGISTERED_DATE")
    private Date registeredDate;

    /** 更新ユーザー名 */
    @Column(name = "UPDATED_USERNAME")
    private String updatedUsername;

    /** 更新日 */
    @Column(name = "UPDATED_DATE")
    private Date updatedDate;

    /** 削除 */
    @Column(name = "DELETED")
    private boolean deleted;
}
