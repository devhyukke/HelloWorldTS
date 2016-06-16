package jp.ne.hyukke.wts.hello.core.domain.messages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 複数の結果メッセージを保持するクラス.
 *
 * @author hyukke
 */
@Getter
@RequiredArgsConstructor
public class ResultMessages implements Serializable, Iterable<ResultMessage> {

    private final ResultMessageType type;

    private final List<ResultMessage> messages = new ArrayList<>();

    @Override
    public Iterator<ResultMessage> iterator() {
        return this.messages.iterator();
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
        this.messages.add(new ResultMessage(code));
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
        this.messages.add(new ResultMessage(code, args));
        return this;
    }
}
