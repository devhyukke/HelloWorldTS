package jp.ne.hyukke.wts.hello.persistence.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

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
    @CreatedBy
    private String registeredUsername;

    /** 登録日 */
    @Column(name = "REGISTERED_DATE")
    @Convert(converter = LocalDateTimeConverter.class)
    @CreatedDate
    private LocalDateTime registeredDate;

    /** 更新ユーザー名 */
    @Column(name = "UPDATED_USERNAME")
    @LastModifiedBy
    private String updatedUsername;

    /** 更新日 */
    @Column(name = "UPDATED_DATE")
    @Convert(converter = LocalDateTimeConverter.class)
    @LastModifiedDate
    private LocalDateTime updatedDate;

    /** 削除 */
    @Column(name = "DELETED")
    private boolean deleted;

    /** バージョン */
    @Column(name = "VERSION")
    @Version
    private int version;
}
