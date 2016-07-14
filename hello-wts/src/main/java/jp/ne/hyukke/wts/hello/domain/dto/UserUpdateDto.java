package jp.ne.hyukke.wts.hello.domain.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * ユーザーを更新するための情報を保持するDTOクラス.
 *
 * @author hyukke
 */
@Data
public class UserUpdateDto implements Serializable {

    private static final long serialVersionUID = 6566865909917094125L;

    /** {@code ID} */
    private Integer id;
    /** {@code Spring Security}ユーザー名 */
    private String username;
    /** 表示名 */
    private String displayName;
    /** ロール{@code ID} */
    private Integer roleId;
}
