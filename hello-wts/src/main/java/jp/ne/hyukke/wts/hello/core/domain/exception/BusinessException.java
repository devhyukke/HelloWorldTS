package jp.ne.hyukke.wts.hello.core.domain.exception;

import jp.ne.hyukke.wts.hello.core.domain.messages.ResultMessages;

/**
 * 業務エラーが発生した際に投げられる例外クラス.
 *
 * @author hyukke
 */
public class BusinessException extends Exception {

    private static final long serialVersionUID = 8872864080368180445L;

    private ResultMessages messages;

    /**
     * デフォルトコンストラクタ.
     */
    public BusinessException() {
        super();
    }

    /**
     * コンストラクタ.
     *
     * @param messages メッセージ
     */
    public BusinessException(ResultMessages messages) {
        this.messages = messages;
    }

    /**
     * @return 結果メッセージ
     */
    public ResultMessages getMessages() {
        return this.messages;
    }
}
