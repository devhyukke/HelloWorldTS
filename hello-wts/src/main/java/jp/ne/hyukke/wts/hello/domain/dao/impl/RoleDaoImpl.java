package jp.ne.hyukke.wts.hello.domain.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import jp.ne.hyukke.wts.hello.domain.dao.RoleDao;
import jp.ne.hyukke.wts.hello.domain.entity.Role;
import jp.ne.hyukke.wts.hello.persistence.entity.MRole;
import jp.ne.hyukke.wts.hello.persistence.repository.MRoleRepository;

/**
 * {@link RoleDao}の実装クラス.
 *
 * @author hyukke
 */
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private MRoleRepository repository;

    @Override
    public List<Role> findAll() {

        List<MRole> entities = this.repository.findAll();

        return entities.stream()
                .map(MRole::toModel)
                .collect(Collectors.toList());
    }
}
