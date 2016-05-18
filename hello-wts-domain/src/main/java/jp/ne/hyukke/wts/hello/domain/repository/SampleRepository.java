package jp.ne.hyukke.wts.hello.domain.repository;

import jp.ne.hyukke.wts.hello.domain.entity.Sample;

/**
 * Repository of Sample.
 *
 * @author hyukke
 */
public interface SampleRepository {

    /**
     * This find entity by id.
     *
     * @param id ID
     * @return Entity
     */
    Sample findById(Integer id);
}
