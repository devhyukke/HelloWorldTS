package jp.ne.hyukke.wts.hello.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import jp.ne.hyukke.wts.hello.domain.service.UserService;
import jp.ne.hyukke.wts.hello.web.form.SettingAccountPasswordForm;
import jp.ne.hyukke.wts.hello.web.form.SettingAccountUsernameForm;
import jp.ne.hyukke.wts.hello.web.security.LoginUser;

/**
 * アカウント設定を操作するコントローラクラス.
 *
 * @author hyukke
 */
@Controller
@PreAuthorize("hasAnyRole('ROLE_SYSTEM_ADMIN', 'ROLE_USER_MANAGER', 'ROLE_USER')")
@RequestMapping("settings/account")
public class SettingAccountController extends SearchableControllerBase {

    private static final String PASSWORD_FORM_KEY = "settingAccountPasswordForm";
    private static final String USERNAME_FORM_KEY = "settingAccountUsernameForm";

    @Autowired
    private UserService userService;

    /**
     * ビューを表示する.
     *
     * @param loginUser ログインユーザー
     * @param model モデル
     * @param status ステータス
     * @param request リクエスト
     * @return ビュー
     */
    @RequestMapping(method = RequestMethod.GET)
    public String show(
            @AuthenticationPrincipal LoginUser loginUser,
            Model model, SessionStatus status, HttpServletRequest request) {

        model.addAttribute(PASSWORD_FORM_KEY, new SettingAccountPasswordForm());

        SettingAccountUsernameForm usernameForm = new SettingAccountUsernameForm();
        usernameForm.setUsername(loginUser.getUsername());
        model.addAttribute(USERNAME_FORM_KEY, usernameForm);

        return "settings/account";
    }

    // TODO 実装
}
