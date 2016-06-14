package jp.ne.hyukke.wts.hello.domain.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import jp.ne.hyukke.wts.hello.domain.dao.SampleDao;
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

        return entity.toSample();
    }

    @Override
    public List<Sample> findAll() {

        List<TSample> entities = this.repository.findAll();

        return entities.stream()
                .map(TSample::toSample)
                .collect(Collectors.toList());
    }

    @Override
    public void register(Sample entity) {
        Assert.notNull(entity);

        TSample e = new TSample();
        e.setName(entity.getName());
        e.setType(entity.getType());
        this.repository.save(e);
    }

    @Override
    public void update(Sample entity) {
        Assert.notNull(entity);

        // TODO 排他制御

        TSample e = new TSample();
        e.setId(entity.getId());
        e.setName(entity.getName());
        e.setType(entity.getType());
        this.repository.save(e);
    }

    @Override
    public void delete(Integer id) {
        Assert.notNull(id);

        this.repository.delete(id);
    }
}
