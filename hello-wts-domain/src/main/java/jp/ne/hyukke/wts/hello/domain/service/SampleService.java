package jp.ne.hyukke.wts.hello.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import jp.ne.hyukke.wts.hello.domain.entity.Sample;
import jp.ne.hyukke.wts.hello.domain.repository.SampleDomain;
import jp.ne.hyukke.wts.hello.domain.repository.SampleRepository;

/**
 * Service of Sample.
 *
 * @author hyukke
 */
@Service
public class SampleService {

    @Autowired
    private SampleRepository repository;

    /**
     * This find sample data by id.
     *
     * @param id ID
     * @return Sample data
     */
    @Transactional(readOnly = true)
    public Sample findById(Integer id) {
        Assert.notNull(id);

        SampleDomain sample = this.repository.findById(id);
        if (sample == null) {
            return null;
        }
        return sample.getEntity();
    }
}
