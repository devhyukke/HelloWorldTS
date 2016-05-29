package jp.ne.hyukke.wts.hello.domain.service;

import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * すべてのエンティティを検索する.
     *
     * @return エンティティ
     */
    @Transactional(readOnly = true)
    public List<Sample> findAll() {

        List<SampleDomain> domains = this.repository.findAll();

        return domains.stream()
                .map(SampleDomain::getEntity)
                .collect(Collectors.toList());
    }
}
