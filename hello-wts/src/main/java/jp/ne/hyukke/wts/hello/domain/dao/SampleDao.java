package jp.ne.hyukke.wts.hello.domain.dao;

import java.util.List;

import jp.ne.hyukke.wts.hello.core.domain.model.ResultPage;
import jp.ne.hyukke.wts.hello.domain.model.Sample;
import jp.ne.hyukke.wts.hello.domain.vo.SampleConditionVo;

/**
 * サンプルのデータにアクセスするインタフェース.
 *
 * @author hyukke
 */
public interface SampleDao {

    /**
     * 指定された{@code ID}でデータを検索する.
     *
     * @param id {@code ID}
     * @return ドメインモデル
     */
    Sample findById(Integer id);

    /**
     * すべてのデータを検索する.
     *
     * @return ドメインモデル
     */
    List<Sample> findAll();

    /**
     * 指定された条件でデータを検索する.
     *
     * @param condition 条件
     * @return 結果ページ
     */
    ResultPage<Sample> findByCondition(SampleConditionVo condition);

    /**
     * データを登録する.
     *
     * @param model ドメインモデル
     * @return 登録済みのドメインモデル
     */
    Sample register(Sample model);

    /**
     * データを更新する.
     *
     * @param model ドメインモデル
     * @return 更新済みのドメインモデル
     */
    Sample update(Sample model);

    /**
     * 指定された{@code ID}でデータを削除する.
     *
     * @param id {@code ID}
     */
    void delete(Integer id);
}
