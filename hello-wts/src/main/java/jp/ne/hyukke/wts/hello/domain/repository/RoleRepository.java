package jp.ne.hyukke.wts.hello.domain.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jp.ne.hyukke.wts.hello.core.domain.model.AbstractRepository;
import jp.ne.hyukke.wts.hello.domain.dao.RoleDao;
import jp.ne.hyukke.wts.hello.domain.model.Role;

/**
 * ロールのドメインモデルを扱うリポジトリクラス.
 *
 * @author hyukke
 */
@Repository
public class RoleRepository extends AbstractRepository {

    @Autowired
    private RoleDao roleDao;

    /**
     * すべてのドメインモデルを検索する.
     *
     * @return ドメインモデル
     */
    public List<Role> findAll() {

        return this.roleDao.findAll();
    }
}
