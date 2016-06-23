package jp.ne.hyukke.wts.hello.core.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 結果ページを保持するクラス.
 *
 * @param <E> エンティティクラス
 * @author hyukke
 */
public class ResultPage<E> implements Serializable {

    private static final long serialVersionUID = 7991494034315077385L;

    private final List<E> content = new ArrayList<>();

    private final long total;

    /**
     * @param content 中身
     * @param total 総件数
     */
    public ResultPage(long total, List<E> content) {
        this.total = total;
        this.content.addAll(content);
    }

    /**
     * @return 総件数
     */
    public long getTotal() {
        return this.total;
    }

    /**
     * @return 中身
     */
    public List<E> getContent() {
        return Collections.unmodifiableList(this.content);
    }
}
