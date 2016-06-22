package jp.ne.hyukke.wts.hello.domain.dao;

import java.util.List;

import jp.ne.hyukke.wts.hello.core.domain.model.ResultPage;
import jp.ne.hyukke.wts.hello.domain.entity.Sample;
import jp.ne.hyukke.wts.hello.domain.vo.SampleConditionVo;

/**
 * サンプルのデータにアクセスするインタフェース.
 *
 * @author hyukke
 */
public interface SampleDao {

    /**
     * 指定された{@code ID}でエンティティを検索する.
     *
     * @param id {@code ID}
     * @return エンティティ
     */
    Sample findById(Integer id);

    /**
     * すべてのエンティティを検索する.
     *
     * @return エンティティ
     */
    List<Sample> findAll();

    /**
     * 指定された条件でエンティティを検索する.
     *
     * @param condition 条件
     * @return 結果ページ
     */
    ResultPage<Sample> findByCondition(SampleConditionVo condition);

    /**
     * エンティティを登録する.
     *
     * @param entity エンティティ
     * @return 登録済みのエンティティ
     */
    Sample register(Sample entity);

    /**
     * エンティティを更新する.
     *
     * @param entity エンティティ
     * @return 更新済みのエンティティ
     */
    Sample update(Sample entity);

    /**
     * 指定された{@code ID}でエンティティを削除する.
     *
     * @param id {@code ID}
     */
    void delete(Integer id);
}
