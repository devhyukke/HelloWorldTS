package jp.ne.hyukke.wts.hello.web.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.ne.hyukke.wts.hello.core.domain.messages.ResultMessages;
import jp.ne.hyukke.wts.hello.domain.constants.SampleType;
import jp.ne.hyukke.wts.hello.domain.dto.SampleDto;
import jp.ne.hyukke.wts.hello.domain.entity.Sample;
import jp.ne.hyukke.wts.hello.domain.service.SampleService;
import jp.ne.hyukke.wts.hello.domain.vo.SampleConditionVo;
import jp.ne.hyukke.wts.hello.web.form.SampleForm;
import jp.ne.hyukke.wts.hello.web.form.SampleSearchForm;

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
    public String show(@ModelAttribute("sampleSearchForm") SampleSearchForm form, Model model) {

        SampleConditionVo condition = SampleConditionVo.valueOf(form.getId(), form.getName(), form.getType());
        model.addAttribute("page", this.sampleService.findByCondition(condition));

        return "samples/search";
    }

    /**
     * クリアする.
     *
     * @param model モデル
     * @return ビュー
     */
    @RequestMapping(method = RequestMethod.GET, params = "clear")
    public String clear(Model model) {

        return "redirect:/samples";
    }

    /**
     * 新規作成のビューを表示する.
     *
     * @param model モデル
     * @return ビュー
     */
    @RequestMapping(value = "editor", method = RequestMethod.GET)
    public String showCreateNew(Model model) {

        model.addAttribute("sampleForm", new SampleForm());

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
        model.addAttribute("sampleForm", form);

        return "samples/edit";
    }

    /**
     * 作成する.
     *
     * @param form フォーム
     * @param bindingResult バインド結果
     * @param model モデル
     * @param attributes リダイレクト属性
     * @return ビュー
     */
    @RequestMapping(method = RequestMethod.POST)
    public String create(
            @Valid @ModelAttribute("sampleForm") SampleForm form,
            BindingResult bindingResult, Model model, RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("sampleForm", form);
            return "samples/create";
        }

        SampleDto dto = new SampleDto();
        dto.setName(form.getName());
        dto.setType(form.getType());
        this.sampleService.register(dto);

        attributes.addFlashAttribute(ResultMessages.success().add("message.info.common.register.success"));

        return "redirect:/samples";
    }

    /**
     * 更新する.
     *
     * @param id ID
     * @param form フォーム
     * @param bindingResult バインド結果
     * @param model モデル
     * @param attributes リダイレクト属性
     * @return ビュー
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public String update(
            @PathVariable Integer id,
            @Valid @ModelAttribute("sampleForm") SampleForm form, BindingResult bindingResult,
            Model model, RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("sample", this.sampleService.findById(id));
            model.addAttribute("sampleForm", form);
            return "samples/edit";
        }

        SampleDto dto = new SampleDto();
        dto.setId(id);
        dto.setName(form.getName());
        dto.setType(form.getType());
        this.sampleService.update(dto);

        attributes.addFlashAttribute(ResultMessages.success().add("message.info.common.update.success"));

        return "redirect:/samples/".concat(String.valueOf(id));
    }

    /**
     * 削除する.
     *
     * @param id ID
     * @param model モデル
     * @param attributes リダイレクト属性
     * @return ビュー
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Integer id, Model model, RedirectAttributes attributes) {

        SampleDto dto = new SampleDto();
        dto.setId(id);
        this.sampleService.delete(dto);

        attributes.addFlashAttribute(ResultMessages.success().add("message.info.common.delete.success"));

        return "redirect:/samples";
    }
}
