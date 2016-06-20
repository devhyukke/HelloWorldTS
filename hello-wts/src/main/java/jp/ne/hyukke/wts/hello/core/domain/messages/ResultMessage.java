package jp.ne.hyukke.wts.hello.core.domain.messages;

import java.io.Serializable;

import lombok.Getter;

/**
 * 結果メッセージを保持するクラス.
 *
 * @author hyukke
 */
@Getter
public class ResultMessage implements Serializable {

    private static final long serialVersionUID = -3159496917623794657L;

    private final String code;

    private final Object[] args;

    /**
     * コンストラクタ.
     *
     * @param code メッセージコード
     */
    public ResultMessage(String code) {
        this(code, new Object[] {});
    }

    /**
     * コンストラクタ.
     *
     * @param key キー
     * @param args パラメータ
     */
    public ResultMessage(String code, Object...args) {
        this.code = code;
        this.args = args;
    }
}
