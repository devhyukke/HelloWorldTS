package jp.ne.hyukke.wts.hello.web.form;

import lombok.Data;

/**
 * ユーザーの検索条件を保持するフォームクラス.
 *
 * @author hyukke
 */
@Data
public class UserSearchForm {

    /** {@code ID} */
    private Integer id;
    /** {@code Spring Security}ユーザー名 */
    private String username;
    /** 表示名 */
    private String displayName;
    /** ロール{@code ID} */
    private Integer roleId;
}
