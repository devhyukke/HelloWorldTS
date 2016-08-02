package jp.ne.hyukke.wts.hello.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import jp.ne.hyukke.wts.hello.core.domain.model.ResultPage;
import jp.ne.hyukke.wts.hello.domain.dto.UserRegisterDto;
import jp.ne.hyukke.wts.hello.domain.dto.UserUpdateDto;
import jp.ne.hyukke.wts.hello.domain.model.Role;
import jp.ne.hyukke.wts.hello.domain.model.User;
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
     * 指定された{@code ID}でドメインモデルを検索する.
     *
     * @param id {@code ID}
     * @return ドメインモデル
     */
    @Transactional(readOnly = true)
    public User findById(Integer id) {
        Assert.notNull(id);

        return this.repository.findById(id);
    }

    /**
     * 指定された条件に合致するドメインモデルを検索する.
     *
     * @param condition 条件
     * @return 結果ページ
     */
    public ResultPage<User> findByCondition(UserConditionVo condition) {
        Assert.notNull(condition);

        return this.repository.findByCondition(condition);
    }

    /**
     * 指定された{@code DTO}でドメインモデルを登録する.
     *
     * @param dto {@code DTO}
     * @return 登録済みのドメインモデル
     */
    @Transactional
    public User register(UserRegisterDto dto) {
        Assert.notNull(dto);

        User model = new User();
        model.setUsername(dto.getUsername());
        model.setPassword(this.passwordEncoder.encode(dto.getPassword()));
        model.setDisplayName(dto.getDisplayName());
        model.setRole(Role.valueOf(dto.getRoleId()));

        return this.repository.register(model);
    }

    /**
     * 指定された{@code DTO}でドメインモデルを更新する.
     *
     * @param dto {@code DTO}
     * @return 更新済みのドメインモデル
     */
    @Transactional
    public User update(UserUpdateDto dto) {
        Assert.notNull(dto);

        User model = User.valueOf(dto.getId());
        model.setUsername(dto.getUsername());
        model.setDisplayName(dto.getDisplayName());
        model.setRole(Role.valueOf(dto.getRoleId()));

        return this.repository.update(model);
    }

    /**
     * 指定された{@code ID}でドメインモデルを削除する.
     *
     * @param id {@code ID}
     */
    @Transactional
    public void delete(Integer id) {
        Assert.notNull(id);

        this.repository.delete(id);
    }

    // TODO 実装中
    @Transactional
    public void changePassword(Integer id, String oldPassword, String newPassword, String confirmationPassword) {
        Assert.notNull(id);
        Assert.hasText(oldPassword);
        Assert.hasText(newPassword);
        Assert.hasText(confirmationPassword);

        this.changePassword(id, oldPassword, newPassword, confirmationPassword);
    }
}
