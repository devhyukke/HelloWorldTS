package jp.ne.hyukke.wts.hello.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.ne.hyukke.wts.hello.persistence.entity.TSample;

/**
 * Repository Class of {@link TSample}.
 *
 * @author hyukke
 */
public interface TSampleRepository extends JpaRepository<TSample, Integer> {

}
