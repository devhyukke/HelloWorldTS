package jp.ne.hyukke.wts.hello.core.domain.messages;

import java.io.Serializable;

/**
 * 結果メッセージを保持するクラス.
 *
 * @author hyukke
 */
public class ResultMessage implements Serializable {

    private static final long serialVersionUID = -3159496917623794657L;

    private final String code;

    private final Object[] args;

    /**
     * @param code メッセージコード
     */
    public ResultMessage(String code) {
        this(code, new Object[] {});
    }

    /**
     * @param code メッセージコード
     * @param args パラメータ
     */
    public ResultMessage(String code, Object...args) {
        this.code = code;
        this.args = args;
    }

    /**
     * @return メッセージコード
     */
    public String getCode() {
        return this.code;
    }

    /**
     * @return パラメータ
     */
    public Object[] getArgs() {
        return this.args;
    }
}
