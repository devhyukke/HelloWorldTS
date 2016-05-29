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
}
