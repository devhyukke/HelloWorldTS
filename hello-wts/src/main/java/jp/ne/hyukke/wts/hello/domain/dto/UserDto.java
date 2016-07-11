package jp.ne.hyukke.wts.hello.domain.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * ユーザーを保持するDTOクラス.
 *
 * @author hyukke
 */
@Data
public class UserDto implements Serializable {

    private static final long serialVersionUID = -244101414929158482L;

    /** {@code ID} */
    private Integer id;
    /** {@code Spring Security}ユーザー名 */
    private String username;
    /** パスワード */
    private String password;
    /** 表示名 */
    private String displayName;
    /** ロール{@code ID} */
    private Integer roleId;
}
