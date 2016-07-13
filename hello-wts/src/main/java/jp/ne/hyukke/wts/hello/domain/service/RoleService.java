package jp.ne.hyukke.wts.hello.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.ne.hyukke.wts.hello.domain.entity.Role;
import jp.ne.hyukke.wts.hello.domain.repository.RoleRepository;

/**
 * ロールを扱うサービスクラス.
 *
 * @author hyukke
 */
@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    /**
     * すべてのエンティティを検索する.
     *
     * @return エンティティ
     */
    @Transactional(readOnly = true)
    public List<Role> findAll() {

        return this.repository.findAll();
    }
}
