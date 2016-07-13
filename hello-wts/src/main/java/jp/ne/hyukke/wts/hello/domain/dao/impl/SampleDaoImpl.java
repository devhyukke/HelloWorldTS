package jp.ne.hyukke.wts.hello.domain.dao.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import jp.ne.hyukke.wts.hello.core.domain.model.ResultPage;
import jp.ne.hyukke.wts.hello.domain.dao.SampleDao;
import jp.ne.hyukke.wts.hello.domain.model.Sample;
import jp.ne.hyukke.wts.hello.domain.vo.SampleConditionVo;
import jp.ne.hyukke.wts.hello.persistence.entity.TSample;
import jp.ne.hyukke.wts.hello.persistence.repository.TSampleRepository;
import jp.ne.hyukke.wts.hello.persistence.spec.TSampleSpecs;

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

        return entity.toModel();
    }

    @Override
    public List<Sample> findAll() {

        List<TSample> entities = this.repository.findAll();

        return entities.stream()
                .map(TSample::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public ResultPage<Sample> findByCondition(SampleConditionVo condition) {

        long total = this.repository.count();
        List<TSample> entities = this.repository.findAll(TSampleSpecs.byCondition(condition));

        return new ResultPage<>(total, entities.stream()
                .map(TSample::toModel)
                .collect(Collectors.toList()));
    }

    @Override
    public Sample register(Sample entity) {
        Assert.notNull(entity);

        TSample e = new TSample();
        e.setName(entity.getName());
        e.setType(entity.getType());
        // TODO 横断的に登録できるように修正
        LocalDateTime today = LocalDateTime.now();
        e.setRegisteredUsername("Username");
        e.setRegisteredDate(today);
        e.setUpdatedUsername("Username");
        e.setUpdatedDate(today);

        e = this.repository.save(e);
        return e.toModel();
    }

    @Override
    public Sample update(Sample entity) {
        Assert.notNull(entity);

        // TODO 排他制御

        TSample e = this.repository.findOne(entity.getId());
        e.setName(entity.getName());
        e.setType(entity.getType());
        // TODO 横断的に登録できるように修正
        LocalDateTime today = LocalDateTime.now();
        e.setUpdatedUsername("Username");
        e.setUpdatedDate(today);
        e.setVersion(e.getVersion() + 1L);

        e = this.repository.save(e);
        return e.toModel();
    }

    @Override
    public void delete(Integer id) {
        Assert.notNull(id);

        this.repository.delete(id);
    }
}
