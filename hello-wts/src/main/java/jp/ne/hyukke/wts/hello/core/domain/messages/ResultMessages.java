package jp.ne.hyukke.wts.hello.core.domain.messages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 複数の結果メッセージを保持するクラス.
 *
 * @author hyukke
 */
public class ResultMessages implements Serializable, Iterable<ResultMessage> {

    private static final long serialVersionUID = -2734694231168426029L;

    private final ResultMessageType type;

    private final List<ResultMessage> list = new ArrayList<>();

    /**
     * @param type 結果メッセージの種別
     */
    public ResultMessages(ResultMessageType type) {
        this.type = type;
    }

    @Override
    public Iterator<ResultMessage> iterator() {
        return this.list.iterator();
    }

    /**
     * @return 正常の結果メッセージ
     */
    public static ResultMessages success() {
        return new ResultMessages(ResultMessageType.SUCCESS);
    }

    /**
     * @return エラーの結果メッセージ
     */
    public static ResultMessages error() {
        return new ResultMessages(ResultMessageType.ERROR);
    }

    /**
     * @return 警告の結果メッセージ
     */
    public static ResultMessages warning() {
        return new ResultMessages(ResultMessageType.WARNING);
    }

    /**
     * 指定されたコードのメッセージを追加する.
     *
     * @param code コード
     * @return この結果メッセージ
     */
    public ResultMessages add(String code) {
        this.list.add(new ResultMessage(code));
        return this;
    }

    /**
     * 指定されたコードのメッセージを追加する.
     *
     * @param code コード
     * @param args パラメータ
     * @return この結果メッセージ
     */
    public ResultMessages add(String code, Object... args) {
        this.list.add(new ResultMessage(code, args));
        return this;
    }

    /**
     * @return 結果メッセージの種別
     */
    public ResultMessageType getType() {
        return this.type;
    }

    /**
     * @return メッセージ
     */
    public List<ResultMessage> getList() {
        return Collections.unmodifiableList(this.list);
    }
}
