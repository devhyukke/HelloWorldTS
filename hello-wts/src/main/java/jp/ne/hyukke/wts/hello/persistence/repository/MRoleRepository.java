package jp.ne.hyukke.wts.hello.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.ne.hyukke.wts.hello.persistence.entity.MRole;

/**
 * {@link MRole}のリポジトリインタフェース.
 *
 * @author hyukke
 */
public interface MRoleRepository extends JpaRepository<MRole, Integer> {

}
