package jp.ne.hyukke.wts.hello.domain.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import jp.ne.hyukke.wts.hello.core.domain.model.ResultPage;
import jp.ne.hyukke.wts.hello.domain.dao.UserDao;
import jp.ne.hyukke.wts.hello.domain.model.User;
import jp.ne.hyukke.wts.hello.domain.vo.UserConditionVo;
import jp.ne.hyukke.wts.hello.persistence.entity.MUser;
import jp.ne.hyukke.wts.hello.persistence.repository.MRoleRepository;
import jp.ne.hyukke.wts.hello.persistence.repository.MUserRepository;
import jp.ne.hyukke.wts.hello.persistence.spec.MUserSpecs;

/**
 * {@link UserDao}の実装クラス.
 *
 * @author hyukke
 */
@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private MUserRepository repository;
    @Autowired
    private MRoleRepository roleRepository;

    @Override
    public User findByUsername(String username) {
        Assert.hasText(username);

        MUser entity = this.repository.findByUsername(username);
        if (entity == null) {
            return null;
        }

        return entity.toModel();
    }

    @Override
    public User findById(Integer id) {
        Assert.notNull(id);

        MUser entity = this.repository.findOne(id);
        if (entity == null) {
            return null;
        }

        return entity.toModel();
    }

    @Override
    public ResultPage<User> findByCondition(UserConditionVo condition) {
        Assert.notNull(condition);

        long total = this.repository.count();
        List<MUser> entities = this.repository.findAll(MUserSpecs.byCondition(condition));

        return new ResultPage<>(total, entities.stream()
                .map(MUser::toModel)
                .collect(Collectors.toList()));
    }

    @Override
    public User register(User model) {
        Assert.notNull(model);
        Assert.notNull(model.getRole());

        MUser entity = new MUser();
        entity.setId(model.getId());
        entity.setUsername(model.getUsername());
        entity.setPassword(model.getPassword());
        entity.setDisplayName(model.getDisplayName());
        entity.setRole(this.roleRepository.findOne(model.getRole().getId()));

        entity = this.repository.save(entity);
        return entity.toModel();
    }

    @Override
    public User update(User model) {
        Assert.notNull(model);
        Assert.notNull(model.getRole());

        MUser entity = this.repository.findOne(model.getId());
        entity.setUsername(model.getUsername());
        entity.setDisplayName(model.getDisplayName());
        entity.setRole(this.roleRepository.findOne(model.getRole().getId()));

        entity = this.repository.save(entity);
        return entity.toModel();
    }

    @Override
    public void delete(Integer id) {
        Assert.notNull(id);

        this.repository.delete(id);
    }
}
