package jp.ne.hyukke.wts.hello.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.ne.hyukke.wts.hello.persistence.entity.MUser;

/**
 * {@link MUser}のリポジトリインタフェース.
 *
 * @author hyukke
 */
public interface MUserRepository extends JpaRepository<MUser, Integer> {

    /**
     * 指定されたユーザー名でエンティティを検索する.
     *
     * @param username ユーザー名
     * @return エンティティ
     */
    MUser findByUsername(String username);
}
