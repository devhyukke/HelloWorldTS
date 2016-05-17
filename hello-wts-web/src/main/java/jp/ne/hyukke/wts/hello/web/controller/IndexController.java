package jp.ne.hyukke.wts.hello.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.ne.hyukke.wts.hello.domain.service.SampleService;

/**
 * Index Controller.
 *
 * @author hyukke
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private SampleService sampleService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute(this.sampleService.findById(Integer.valueOf(1)));
        return "index";
    }
}
