package jp.ne.hyukke.wts.hello.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import jp.ne.hyukke.wts.hello.core.domain.model.AbstractRepository;
import jp.ne.hyukke.wts.hello.domain.dao.SampleDao;
import jp.ne.hyukke.wts.hello.domain.entity.Sample;

/**
 * サンプルを扱うリポジトリクラス.
 *
 * @author hyukke
 */
@Repository
public class SampleRepository extends AbstractRepository {

    @Autowired
    private SampleDao sampleDao;

    /**
     * 指定された{@code ID}でドメインを検索する.
     *
     * @param id {@code ID}
     * @return ドメイン
     */
    public SampleDomain findById(Integer id) {
        Assert.notNull(id);

        Sample entity = this.sampleDao.findById(id);

        return this.createDomain(entity, SampleDomain.class);
    }
}
