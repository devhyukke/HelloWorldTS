package jp.ne.hyukke.wts.hello.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import jp.ne.hyukke.wts.hello.core.domain.model.ResultPage;
import jp.ne.hyukke.wts.hello.domain.dto.UserRegisterDto;
import jp.ne.hyukke.wts.hello.domain.dto.UserUpdateDto;
import jp.ne.hyukke.wts.hello.domain.entity.Role;
import jp.ne.hyukke.wts.hello.domain.entity.User;
import jp.ne.hyukke.wts.hello.domain.repository.UserDomain;
import jp.ne.hyukke.wts.hello.domain.repository.UserRepository;
import jp.ne.hyukke.wts.hello.domain.vo.UserConditionVo;

/**
 * ユーザーを扱うサービスクラス.
 *
 * @author hyukke
 */
@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 指定された{@code ID}でエンティティを検索する.
     *
     * @param id {@code ID}
     * @return エンティティ
     */
    @Transactional(readOnly = true)
    public User findById(Integer id) {
        Assert.notNull(id);

        UserDomain domain = this.repository.findById(id);
        if (domain == null) {
            return null;
        }
        return domain.getEntity();
    }

    /**
     * 指定された条件に合致するエンティティを検索する.
     *
     * @param condition 条件
     * @return 結果ページ
     */
    public ResultPage<User> findByCondition(UserConditionVo condition) {
        Assert.notNull(condition);

        return this.repository.findByCondition(condition);
    }

    /**
     * 指定された{@code DTO}でエンティティを登録する.
     *
     * @param dto {@code DTO}
     * @return 登録済みのエンティティ
     */
    @Transactional
    public User register(UserRegisterDto dto) {
        Assert.notNull(dto);

        User entity = new User();
        entity.setUsername(dto.getUsername());
        entity.setPassword(this.passwordEncoder.encode(dto.getPassword()));
        entity.setDisplayName(dto.getDisplayName());
        entity.setRole(Role.valueOf(dto.getRoleId()));

        return this.repository.register(entity);
    }

    /**
     * 指定された{@code DTO}でエンティティを更新する.
     *
     * @param dto {@code DTO}
     * @return 更新済みのエンティティ
     */
    @Transactional
    public User update(UserUpdateDto dto) {
        Assert.notNull(dto);

        User entity = User.valueOf(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setDisplayName(dto.getDisplayName());
        entity.setRole(Role.valueOf(dto.getRoleId()));

        return this.repository.update(entity);
    }

    /**
     * 指定された{@code ID}でエンティティを削除する.
     *
     * @param id {@code ID}
     */
    @Transactional
    public void delete(Integer id) {
        Assert.notNull(id);

        this.repository.delete(id);
    }
}
