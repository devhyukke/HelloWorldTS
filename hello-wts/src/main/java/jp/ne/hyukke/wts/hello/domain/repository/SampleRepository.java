package jp.ne.hyukke.wts.hello.domain.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import jp.ne.hyukke.wts.hello.core.domain.model.ResultPage;
import jp.ne.hyukke.wts.hello.domain.dao.SampleDao;
import jp.ne.hyukke.wts.hello.domain.model.Sample;
import jp.ne.hyukke.wts.hello.domain.vo.SampleConditionVo;

/**
 * サンプルのドメインモデルを扱うリポジトリクラス.
 *
 * @author hyukke
 */
@Repository
public class SampleRepository {

    @Autowired
    private SampleDao sampleDao;

    /**
     * 指定された{@code ID}でドメインモデルを検索する.
     *
     * @param id {@code ID}
     * @return ドメインモデル
     */
    public Sample findById(Integer id) {
        Assert.notNull(id);

        return this.sampleDao.findById(id);
    }

    /**
     * すべてのドメインモデルを検索する.
     *
     * @return ドメインモデル
     */
    public List<Sample> findAll() {

        return this.sampleDao.findAll();
    }

    /**
     * 指定された条件に合致するドメインモデルを検索する.
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
     * 指定されたドメインモデルを登録する.
     *
     * @param entity ドメインモデル
     * @return 登録済みのドメインモデル
     */
    public Sample register(Sample model) {
        Assert.notNull(model);

        // TODO ドメインレイヤにおける入力値の検証

        return this.sampleDao.register(model);
    }

    /**
     * 指定されたドメインモデルを更新する.
     *
     * @param entity ドメインモデル
     * @return 更新済みのドメインモデル
     */
    public Sample update(Sample model) {
        Assert.notNull(model);

        return this.sampleDao.update(model);
    }

    /**
     * 指定された{@code ID}でドメインモデルを削除する.
     *
     * @param id {@code ID}
     */
    public void delete(Integer id) {
        Assert.notNull(id);

        this.sampleDao.delete(id);
    }
}
