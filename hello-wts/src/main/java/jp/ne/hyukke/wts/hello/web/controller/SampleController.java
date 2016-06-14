package jp.ne.hyukke.wts.hello.web.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.ne.hyukke.wts.hello.domain.constants.SampleType;
import jp.ne.hyukke.wts.hello.domain.dto.SampleDto;
import jp.ne.hyukke.wts.hello.domain.entity.Sample;
import jp.ne.hyukke.wts.hello.domain.service.SampleService;
import jp.ne.hyukke.wts.hello.web.form.SampleForm;

/**
 * サンプルを操作するコントローラクラス.
 *
 * @author hyukke
 */
@Controller
@RequestMapping("/samples")
public class SampleController {

    @Autowired
    private SampleService sampleService;

    /**
     * @return 種別
     */
    @ModelAttribute("sampleTypes")
    public SampleType[] sampleTypes() {

        return SampleType.values();
    }

    /**
     * ビューを表示する.
     *
     * @param model モデル
     * @return ビュー
     */
    @RequestMapping(method = RequestMethod.GET)
    public String show(Model model) {

        model.addAttribute("samples", this.sampleService.findAll());

        return "samples/search";
    }

    /**
     * 新規作成のビューを表示する.
     *
     * @param model モデル
     * @return ビュー
     */
    @RequestMapping(value = "editor", method = RequestMethod.GET)
    public String showCreateNew(Model model) {

        model.addAttribute("form", new SampleForm());

        return "samples/create";
    }

    /**
     * 詳細のビューを表示する.
     *
     * @param id ID
     * @param model モデル
     * @return ビュー
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String showDetail(@PathVariable Integer id, Model model) {

        model.addAttribute("sample", this.sampleService.findById(id));

        return "samples/detail";
    }

    /**
     * 編集のビューを表示する.
     *
     * @param id ID
     * @param model モデル
     * @return ビュー
     */
    @RequestMapping(value = "{id}/editor", method = RequestMethod.GET)
    public String showEdit(@PathVariable Integer id, Model model) {

        Sample sample = this.sampleService.findById(id);
        SampleForm form = new SampleForm();
        BeanUtils.copyProperties(sample, form);

        model.addAttribute("sample", sample);
        model.addAttribute("form", form);

        return "samples/edit";
    }

    /**
     * 作成する.
     *
     * @param form フォーム
     * @param model モデル
     * @return ビュー
     */
    @RequestMapping(method = RequestMethod.POST)
    public String create(@ModelAttribute SampleForm form, Model model) {

        // TODO now developing...
        System.out.println("created!!");
        return "redirect:/samples";
    }

    /**
     * 更新する.
     *
     * @param id ID
     * @param form フォーム
     * @param model モデル
     * @return ビュー
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public String update(@PathVariable Integer id, @ModelAttribute SampleForm form, Model model) {

        // TODO now developing...
        System.out.println("updated!!");
        return "redirect:/samples/".concat(String.valueOf(id));
    }

    /**
     * 削除する.
     *
     * @param id ID
     * @param model モデル
     * @return ビュー
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Integer id, Model model) {

        SampleDto dto = new SampleDto();
        dto.setId(id);
        this.sampleService.delete(dto);

        return "redirect:/samples";
    }
}
