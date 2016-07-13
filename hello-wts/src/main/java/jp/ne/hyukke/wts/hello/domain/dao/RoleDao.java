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
     * すべてのエンティティを検索する.
     *
     * @return エンティティ
     */
    List<Role> findAll();
}
