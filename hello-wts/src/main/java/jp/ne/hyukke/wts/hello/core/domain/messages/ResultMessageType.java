package jp.ne.hyukke.wts.hello.core.domain.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 結果メッセージの種別を表す列挙.
 *
 * @author hyukke
 */
@AllArgsConstructor
public enum ResultMessageType {

    SUCCESS("success"),
    WARNING("warning"),
    ERROR("error");

    @Getter
    private String type;
}
