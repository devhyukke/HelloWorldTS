package jp.ne.hyukke.wts.hello.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import jp.ne.hyukke.wts.hello.web.form.AccountSettingForm;

/**
 * アカウント設定を操作するコントローラクラス.
 *
 * @author hyukke
 */
@Controller
@PreAuthorize("hasAnyRole('ROLE_SYSTEM_ADMIN', 'ROLE_USER_MANAGER', 'ROLE_USER')")
@RequestMapping("settings/account")
public class AccountSettingController extends SearchableControllerBase {

    private static final String FORM_KEY = "accountSettingForm";

    /**
     * ビューを表示する.
     *
     * @param model モデル
     * @param status ステータス
     * @param request リクエスト
     * @return ビュー
     */
    @RequestMapping(method = RequestMethod.GET)
    public String show(Model model, SessionStatus status, HttpServletRequest request) {

        // TODO 実装
        AccountSettingForm form = new AccountSettingForm();

        model.addAttribute(FORM_KEY, form);

        return "settings/account";
    }
}
