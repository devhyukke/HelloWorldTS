package jp.ne.hyukke.wts.hello.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import jp.ne.hyukke.wts.hello.core.domain.model.ResultPage;
import jp.ne.hyukke.wts.hello.domain.dto.SampleRegisterDto;
import jp.ne.hyukke.wts.hello.domain.dto.SampleUpdateDto;
import jp.ne.hyukke.wts.hello.domain.model.Sample;
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
     * 指定された{@code ID}でドメインモデルを検索する.
     *
     * @param id {@code ID}
     * @return ドメインモデル
     */
    @Transactional(readOnly = true)
    public Sample findById(Integer id) {
        Assert.notNull(id);

        return this.repository.findById(id);
    }

    /**
     * すべてのドメインモデルを検索する.
     *
     * @return ドメインモデル
     */
    @Transactional(readOnly = true)
    public List<Sample> findAll() {

        return this.repository.findAll();
    }

    /**
     * 指定された条件に合致するドメインモデルを検索する.
     *
     * @param condition 条件
     * @return 結果ページ
     */
    public ResultPage<Sample> findByCondition(SampleConditionVo condition) {
        Assert.notNull(condition);

        return this.repository.findByCondition(condition);
    }

    /**
     * 指定された{@code DTO}でドメインモデルを登録する.
     *
     * @param dto {@code DTO}
     * @return 登録済みのドメインモデル
     */
    @Transactional
    public Sample register(SampleRegisterDto dto) {
        Assert.notNull(dto);

        Sample model = new Sample();
        model.setName(dto.getName());
        model.setType(dto.getType());

        return this.repository.register(model);
    }

    /**
     * 指定された{@code DTO}でドメインモデルを更新する.
     *
     * @param dto {@code DTO}
     * @return 更新済みのドメインモデル
     */
    @Transactional
    public Sample update(SampleUpdateDto dto) {
        Assert.notNull(dto);

        Sample model = Sample.valueOf(dto.getId());
        model.setName(dto.getName());
        model.setType(dto.getType());

        return this.repository.update(model);
    }

    /**
     * 指定された{@code ID}でドメインモデルを削除する.
     *
     * @param id {@code ID}
     */
    @Transactional
    public void delete(Integer id) {
        Assert.notNull(id);

        this.repository.delete(id);
    }
}
