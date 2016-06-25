package jp.ne.hyukke.wts.hello.domain.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * ユーザーの検索条件を保持する値オブジェクトクラス.
 *
 * @author hyukke
 */
@Data
@EqualsAndHashCode(of = {"id", "username", "displayName", "type"})
@RequiredArgsConstructor(staticName = "valueOf")
public class UserConditionVo implements Serializable {

    private static final long serialVersionUID = 1313155694100125177L;

    /** {@code ID} */
    private Integer id;
    /** {@code Spring Security}ユーザー名 */
    private String username;
    /** 表示名 */
    private String displayName;
    /** ロール種別 */
    private String type;
}
