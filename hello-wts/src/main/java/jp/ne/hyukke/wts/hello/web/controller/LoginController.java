package jp.ne.hyukke.wts.hello.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ログインを行うコントローラクラス.
 *
 * @author hyukke
 */
@Controller
@RequestMapping("login")
public class LoginController {

    /**
     * ビューを表示する.
     *
     * @return ビュー
     */
    @RequestMapping(method = RequestMethod.GET)
    public String login() {

        return "login";
    }
}
