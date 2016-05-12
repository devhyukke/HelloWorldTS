package jp.ne.hyukke.wts.hello.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Index Controller.
 *
 * @author hyukke
 */
@RequestMapping("/")
@Controller
public class IndexController {

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "index";
    }
}
