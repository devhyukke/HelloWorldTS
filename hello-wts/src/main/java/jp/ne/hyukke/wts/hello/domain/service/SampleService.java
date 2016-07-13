package jp.ne.hyukke.wts.hello.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import jp.ne.hyukke.wts.hello.core.domain.model.ResultPage;
import jp.ne.hyukke.wts.hello.domain.dto.SampleRegisterDto;
import jp.ne.hyukke.wts.hello.domain.dto.SampleUpdateDto;
import jp.ne.hyukke.wts.hello.domain.entity.Sample;
import jp.ne.hyukke.wts.hello.domain.repository.SampleDomain;
import jp.ne.hyukke.wts.hello.domain.repository.SampleRepository;
import jp.ne.hyukke.wts.hello.domain.vo.SampleConditionVo;

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
     * 指定された条件に合致するエンティティを検索する.
     *
     * @param condition 条件
     * @return 結果ページ
     */
    public ResultPage<Sample> findByCondition(SampleConditionVo condition) {
        Assert.notNull(condition);

        return this.repository.findByCondition(condition);
    }

    /**
     * 指定された{@code DTO}でエンティティを登録する.
     *
     * @param dto {@code DTO}
     * @return 登録済みのエンティティ
     */
    @Transactional
    public Sample register(SampleRegisterDto dto) {
        Assert.notNull(dto);

        Sample entity = new Sample();
        entity.setName(dto.getName());
        entity.setType(dto.getType());

        return this.repository.register(entity);
    }

    /**
     * 指定された{@code DTO}でエンティティを更新する.
     *
     * @param dto {@code DTO}
     * @return 更新済みのエンティティ
     */
    @Transactional
    public Sample update(SampleUpdateDto dto) {
        Assert.notNull(dto);

        Sample entity = Sample.valueOf(dto.getId());
        entity.setName(dto.getName());
        entity.setType(dto.getType());

        return this.repository.update(entity);
    }

    /**
     * 指定された{@link SampleRegisterDto}でエンティティを削除する.
     *
     * @param id {@code ID}
     */
    @Transactional
    public void delete(Integer id) {
        Assert.notNull(id);

        this.repository.delete(id);
    }
}
