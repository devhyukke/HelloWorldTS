package jp.ne.hyukke.wts.hello.domain.dao.impl;

import java.time.LocalDateTime;
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
    public User register(User entity) {
        Assert.notNull(entity);
        Assert.notNull(entity.getRole());

        MUser saved = new MUser();
        saved.setId(entity.getId());
        saved.setUsername(entity.getUsername());
        saved.setPassword(entity.getPassword());
        saved.setDisplayName(entity.getDisplayName());
        saved.setRole(this.roleRepository.findOne(entity.getRole().getId()));
        // TODO 横断的に登録できるように修正
        LocalDateTime today = LocalDateTime.now();
        saved.setRegisteredUsername("Username");
        saved.setRegisteredDate(today);
        saved.setUpdatedUsername("Username");
        saved.setUpdatedDate(today);

        saved = this.repository.save(saved);
        return saved.toModel();
    }

    @Override
    public User update(User entity) {
        Assert.notNull(entity);
        Assert.notNull(entity.getRole());

        MUser saved = this.repository.findOne(entity.getId());
        saved.setUsername(entity.getUsername());
        saved.setDisplayName(entity.getDisplayName());
        saved.setRole(this.roleRepository.findOne(entity.getRole().getId()));
        // TODO 横断的に登録できるように修正
        LocalDateTime today = LocalDateTime.now();
        saved.setUpdatedUsername("Username");
        saved.setUpdatedDate(today);
        saved.setVersion(saved.getVersion() + 1L);

        saved = this.repository.save(saved);
        return saved.toModel();
    }

    @Override
    public void delete(Integer id) {
        Assert.notNull(id);

        this.repository.delete(id);
    }
}
