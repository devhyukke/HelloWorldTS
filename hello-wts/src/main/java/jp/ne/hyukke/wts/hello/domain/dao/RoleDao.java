package jp.ne.hyukke.wts.hello.domain.dao;

import java.util.List;

import jp.ne.hyukke.wts.hello.domain.model.Role;

/**
 * ロールのデータにアクセスするインタフェース.
 *
 * @author hyukke
 */
public interface RoleDao {

    /**
     * すべてのデータを検索する.
     *
     * @return ドメインモデル
     */
    List<Role> findAll();
}
