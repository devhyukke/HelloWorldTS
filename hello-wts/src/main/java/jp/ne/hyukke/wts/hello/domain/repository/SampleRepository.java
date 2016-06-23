package jp.ne.hyukke.wts.hello.domain.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import jp.ne.hyukke.wts.hello.core.domain.model.AbstractRepository;
import jp.ne.hyukke.wts.hello.core.domain.model.ResultPage;
import jp.ne.hyukke.wts.hello.domain.dao.SampleDao;
import jp.ne.hyukke.wts.hello.domain.entity.Sample;
import jp.ne.hyukke.wts.hello.domain.vo.SampleConditionVo;

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
        if (entity == null) {
            return null;
        }

        return this.createDomain(entity, SampleDomain.class);
    }

    /**
     * すべてのドメインを検索する.
     *
     * @return ドメイン
     */
    public List<SampleDomain> findAll() {

        List<Sample> entities = this.sampleDao.findAll();

        return entities.stream()
                .map(entity -> this.createDomain(entity, SampleDomain.class))
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

        return this.sampleDao.findByCondition(condition);
    }

    // XXX エンティティではなくドメインモデルが正解と思われる
    /**
     * 指定されたエンティティを登録する.
     *
     * @param entity エンティティ
     * @return 登録済みのエンティティ
     */
    public Sample register(Sample entity) {
        Assert.notNull(entity);

        // TODO ドメインレイヤにおける入力値の検証

        return this.sampleDao.register(entity);
    }

    /**
     * 指定されたエンティティを更新する.
     *
     * @param entity エンティティ
     * @return 更新済みのエンティティ
     */
    public Sample update(Sample entity) {
        Assert.notNull(entity);

        return this.sampleDao.update(entity);
    }

    /**
     * 指定された{@code ID}でエンティティを削除する.
     *
     * @param id {@code ID}
     */
    public void delete(Integer id) {
        Assert.notNull(id);

        this.sampleDao.delete(id);
    }
}
