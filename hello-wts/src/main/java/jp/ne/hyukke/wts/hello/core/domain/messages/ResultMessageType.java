package jp.ne.hyukke.wts.hello.core.domain.messages;

/**
 * 結果メッセージの種別を表す列挙.
 *
 * @author hyukke
 */
public enum ResultMessageType {

    SUCCESS("success"),
    WARNING("warning"),
    ERROR("error");

    private String type;

    /**
     * @param type 種別
     */
    private ResultMessageType(String type) {
        this.type = type;
    }

    /**
     * @return 種別
     */
    public String getType() {
        return this.type;
    }
}
