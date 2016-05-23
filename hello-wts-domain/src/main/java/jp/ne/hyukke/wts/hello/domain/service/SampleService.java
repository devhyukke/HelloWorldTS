package jp.ne.hyukke.wts.hello.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import jp.ne.hyukke.wts.hello.domain.entity.Sample;
import jp.ne.hyukke.wts.hello.domain.repository.SampleDomain;
import jp.ne.hyukke.wts.hello.domain.repository.SampleRepository;

/**
 * サンプルを扱うサービスクラス.
 *
 * @author hyukke
 */
@Service
public class SampleService {

    @Autowired
    private SampleRepository repository;

    /**
     * 指定された{@code ID}でエンティティを検索する.
     *
     * @param id {@code ID}
     * @return エンティティ
     */
    @Transactional(readOnly = true)
    public Sample findById(Integer id) {
        Assert.notNull(id);

        SampleDomain domain = this.repository.findById(id);
        if (domain == null) {
            return null;
        }
        return domain.getEntity();
    }
}
