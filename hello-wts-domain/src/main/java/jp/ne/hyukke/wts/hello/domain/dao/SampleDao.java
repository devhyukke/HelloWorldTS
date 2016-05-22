package jp.ne.hyukke.wts.hello.domain.dao;

import jp.ne.hyukke.wts.hello.domain.entity.Sample;

/**
 * Data access object of Sample.
 *
 * @author hyukke
 */
public interface SampleDao {

    /**
     * This find entity by id.
     *
     * @param id ID
     * @return Entity
     */
    Sample findById(Integer id);
}
