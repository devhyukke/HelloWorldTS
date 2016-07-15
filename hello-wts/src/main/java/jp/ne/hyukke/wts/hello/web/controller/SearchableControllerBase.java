package jp.ne.hyukke.wts.hello.web.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jp.ne.hyukke.wts.hello.web.WebMvcConfig;

/**
 * 検索可能なコントローラの基底クラス.
 *
 * @author hyukke
 */
@Controller
@SessionAttributes(value = WebMvcConfig.SEARCH_CONDITION_QUERY_KEY)
public class SearchableControllerBase {

    /**
     * @param model モデル
     * @return クエリ文字列
     */
    @ModelAttribute("queryString")
    public String queryString(Model model) {

        Object query = model.asMap().get(WebMvcConfig.SEARCH_CONDITION_QUERY_KEY);
        if (query == null) {
            return "";
        }
        return "?".concat(String.class.cast(query));
    }

    /**
     * 検索条件をセッションに追加する.
     *
     * @param model モデル
     * @param status ステータス
     * @param request リクエスト
     */
    protected void addSearchConditionToSession(Model model, SessionStatus status, HttpServletRequest request) {

        // 初期表示時の検索の場合はセッションをクリア
        if (StringUtils.isEmpty(request.getQueryString())) {
            status.setComplete();
        }

        Optional.ofNullable(request.getQueryString())
                .filter(StringUtils::hasText)
                .ifPresent(query -> model.addAttribute(WebMvcConfig.SEARCH_CONDITION_QUERY_KEY, query));
    }
}
