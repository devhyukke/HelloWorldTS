package jp.ne.hyukke.wts.hello.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import jp.ne.hyukke.wts.hello.persistence.entity.TSample;

/**
 * {@link TSample}のリポジトリインタフェース.
 *
 * @author hyukke
 */
public interface TSampleRepository extends JpaRepository<TSample, Integer>, JpaSpecificationExecutor<TSample> {

}
