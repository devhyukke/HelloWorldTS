package jp.ne.hyukke.wts.hello.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import jp.ne.hyukke.wts.hello.domain.dto.SampleDto;
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

    /**
     * 指定された{@link SampleDto}でエンティティを登録する.
     *
     * @param dto {@code DTO}
     */
    @Transactional
    public void register(SampleDto dto) {
        Assert.notNull(dto);

        Sample entity = new Sample();
        entity.setName(dto.getName());
        entity.setType(dto.getType());

        this.repository.register(entity);
    }

    /**
     * 指定された{@link SampleDto}でエンティティを更新する.
     *
     * @param dto {@code DTO}
     */
    @Transactional
    public void update(SampleDto dto) {
        Assert.notNull(dto);

        Sample entity = Sample.valueOf(dto.getId());
        entity.setName(dto.getName());
        entity.setType(dto.getType());

        this.repository.update(entity);
    }

    /**
     * 指定された{@link SampleDto}でエンティティを削除する.
     *
     * @param dto {@code DTO}
     */
    @Transactional
    public void delete(SampleDto dto) {
        Assert.notNull(dto);

        this.repository.delete(dto.getId());
    }
}
