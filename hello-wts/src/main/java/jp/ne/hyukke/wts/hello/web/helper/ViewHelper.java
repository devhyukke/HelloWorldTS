package jp.ne.hyukke.wts.hello.web.helper;

import org.springframework.stereotype.Component;

/**
 * ビューの構築を補助するクラス.
 *
 * @author hyukke
 */
@Component
public class ViewHelper {

    public String toMasked(String target) {
        if (target == null) {
            return "";
        }
        StringBuilder masked = new StringBuilder(target.length());
        for (int i = 0; i < target.length(); i++) {
            masked.append("●");
        }
        return masked.toString();
    }
}
