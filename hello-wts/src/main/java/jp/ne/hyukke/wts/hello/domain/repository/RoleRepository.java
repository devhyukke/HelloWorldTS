package jp.ne.hyukke.wts.hello.domain.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jp.ne.hyukke.wts.hello.core.domain.model.AbstractRepository;
import jp.ne.hyukke.wts.hello.domain.dao.RoleDao;
import jp.ne.hyukke.wts.hello.domain.entity.Role;

/**
 * ロールを扱うリポジトリクラス.
 *
 * @author hyukke
 */
@Repository
public class RoleRepository extends AbstractRepository {

    @Autowired
    private RoleDao roleDao;

    /**
     * すべてのドメインを検索する.
     *
     * @return ドメイン
     */
    public List<Role> findAll() {

        return this.roleDao.findAll();
    }
}
