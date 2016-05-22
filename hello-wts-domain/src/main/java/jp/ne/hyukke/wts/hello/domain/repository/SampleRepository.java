package jp.ne.hyukke.wts.hello.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import jp.ne.hyukke.wts.hello.domain.dao.SampleDao;
import jp.ne.hyukke.wts.hello.domain.entity.Sample;

/**
 * Repository of Sample.
 *
 * @author hyukke
 */
@Repository
public class SampleRepository {

    @Autowired
    private ApplicationContext context;
    @Autowired
    private SampleDao sampleDao;

    public SampleDomain findById(Integer id) {

        Sample entity = this.sampleDao.findById(id);

        SampleDomain domain = this.context.getBean(SampleDomain.class);
        domain.setEntity(entity);
        return domain;
    }
}
