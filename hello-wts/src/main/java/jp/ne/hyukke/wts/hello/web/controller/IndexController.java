package jp.ne.hyukke.wts.hello.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 見出しを表示するコントローラクラス.
 *
 * @author hyukke
 */
@Controller
@RequestMapping("/")
public class IndexController {

    /**
     * ビューを表示する.
     *
     * @param model モデル
     * @return ビュー
     */
    @RequestMapping(method = RequestMethod.GET)
    public String show(Model model) {
        return "index";
    }
}
