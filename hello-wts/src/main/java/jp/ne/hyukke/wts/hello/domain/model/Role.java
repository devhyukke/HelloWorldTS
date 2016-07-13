package jp.ne.hyukke.wts.hello.domain.model;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * ロールのエンティティクラス.
 *
 * @author hyukke
 */
@Data
@EqualsAndHashCode(of = "id")
@RequiredArgsConstructor(staticName = "valueOf")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /** {@code ID} */
    private final Integer id;
    /** ロール名 */
    private String name;
    /** ロール種別 */
    private String type;
    /** システム管理 */
    private boolean systemManagement;
}
