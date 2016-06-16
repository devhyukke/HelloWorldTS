package jp.ne.hyukke.wts.hello.core.domain.exception;

import jp.ne.hyukke.wts.hello.core.domain.messages.ResultMessages;

import lombok.Getter;

/**
 * 業務エラーが発生した際に投げられる例外クラス.
 *
 * @author hyukke
 */
public class BusinessException extends Exception {

    @Getter
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
}
