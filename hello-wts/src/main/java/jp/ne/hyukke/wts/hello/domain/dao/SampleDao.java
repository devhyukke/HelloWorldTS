package jp.ne.hyukke.wts.hello.domain.dao;

import java.util.List;

import jp.ne.hyukke.wts.hello.domain.entity.Sample;

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
     * エンティティを登録する.
     *
     * @param entity エンティティ
     */
    void register(Sample entity);

    /**
     * エンティティを更新する.
     *
     * @param entity エンティティ
     */
    void update(Sample entity);

    /**
     * 指定された{@code ID}でエンティティを削除する.
     *
     * @param id {@code ID}
     */
    void delete(Integer id);
}
