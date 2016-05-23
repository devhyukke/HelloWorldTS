package jp.ne.hyukke.wts.hello.domain.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import jp.ne.hyukke.wts.hello.domain.entity.Sample;
import jp.ne.hyukke.wts.hello.persistence.entity.TSample;
import jp.ne.hyukke.wts.hello.persistence.repository.TSampleRepository;

/**
 * {@link SampleDao}の実装クラス.
 *
 * @author hyukke
 */
@Component
public class SampleDaoImpl implements SampleDao {

    @Autowired
    private TSampleRepository repository;

    @Override
    public Sample findById(Integer id) {
        Assert.notNull(id);

        TSample entity = this.repository.findOne(id);
        if (entity == null) {
            return null;
        }

        Sample sample = Sample.valueOf(entity.getId());
        sample.setName(entity.getName());
        sample.setType(entity.getType());
        return sample;
    }
}
