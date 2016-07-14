package jp.ne.hyukke.wts.hello.domain.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * ユーザーを登録するための情報を保持するDTOクラス.
 *
 * @author hyukke
 */
@Data
public class UserRegisterDto implements Serializable {

    private static final long serialVersionUID = -2846551738577453270L;

    /** {@code Spring Security}ユーザー名 */
    private String username;
    /** パスワード */
    private String password;
    /** 表示名 */
    private String displayName;
    /** ロール{@code ID} */
    private Integer roleId;
}
