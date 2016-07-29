package jp.ne.hyukke.wts.hello.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.ne.hyukke.wts.hello.core.domain.messages.ResultMessages;
import jp.ne.hyukke.wts.hello.domain.constants.SampleOption;
import jp.ne.hyukke.wts.hello.domain.constants.SampleType;
import jp.ne.hyukke.wts.hello.domain.dto.SampleRegisterDto;
import jp.ne.hyukke.wts.hello.domain.dto.SampleUpdateDto;
import jp.ne.hyukke.wts.hello.domain.model.Sample;
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
@PreAuthorize("hasAnyRole('ROLE_SYSTEM_ADMIN', 'ROLE_USER_MANAGER', 'ROLE_USER')")
@RequestMapping("samples")
public class SampleController extends SearchableControllerBase {

    private static final String FORM_KEY = "sampleForm";

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
     * @param form フォーム
     * @param model モデル
     * @param status ステータス
     * @param request リクエスト
     * @return ビュー
     */
    @RequestMapping(method = RequestMethod.GET)
    public String show(
            @ModelAttribute("sampleSearchForm") SampleSearchForm form, Model model, SessionStatus status,
            HttpServletRequest request) {

        SampleConditionVo condition = SampleConditionVo.valueOf(form.getId(), form.getName(), form.getType());
        model.addAttribute("page", this.sampleService.findByCondition(condition));

        this.addSearchConditionToSession(model, status, request);

        return "samples/list";
    }

    /**
     * 新規作成のビューを表示する.
     *
     * @param model モデル
     * @return ビュー
     */
    @RequestMapping(value = "editor", method = RequestMethod.GET)
    public String showCreateNew(Model model) {

        SampleForm form = new SampleForm();
        form.setOption(SampleOption.NONE);
        model.addAttribute(FORM_KEY, form);

        return "samples/create";
    }

    /**
     * 詳細のビューを表示する.
     *
     * @param id {@code ID}
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
     * @param id {@code ID}
     * @param model モデル
     * @return ビュー
     */
    @RequestMapping(value = "{id}/editor", method = RequestMethod.GET)
    public String showEdit(@PathVariable Integer id, Model model) {

        Sample sample = this.sampleService.findById(id);
        SampleForm form = new SampleForm();
        BeanUtils.copyProperties(sample, form);

        model.addAttribute("sample", sample);
        model.addAttribute(FORM_KEY, form);

        return "samples/edit";
    }

    /**
     * 新規作成する.
     *
     * @param form フォーム
     * @param bindingResult バインド結果
     * @param model モデル
     * @param attributes リダイレクト属性
     * @return ビュー
     */
    @RequestMapping(method = RequestMethod.POST)
    public String create(
            @Valid @ModelAttribute(FORM_KEY) SampleForm form,
            BindingResult bindingResult, Model model, RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute(FORM_KEY, form);
            return "samples/create";
        }

        SampleRegisterDto dto = new SampleRegisterDto();
        dto.setName(form.getName());
        dto.setType(form.getType());
        dto.setEmail(form.getEmail());
        dto.setPassword(form.getPassword());
        dto.setChecked(form.isChecked());
        dto.setOption(form.getOption());
        dto.setRemark(form.getRemark());
        Sample registerd = this.sampleService.register(dto);

        attributes.addFlashAttribute(ResultMessages.success().add("message.info.common.register.success"));
        attributes.addFlashAttribute("newCreation", registerd);

        return "redirect:/samples".concat(this.queryString(model));
    }

    /**
     * 更新する.
     *
     * @param id {@code ID}
     * @param form フォーム
     * @param bindingResult バインド結果
     * @param model モデル
     * @param attributes リダイレクト属性
     * @return ビュー
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public String update(
            @PathVariable Integer id,
            @Valid @ModelAttribute(FORM_KEY) SampleForm form, BindingResult bindingResult,
            Model model, RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("sample", this.sampleService.findById(id));
            model.addAttribute(FORM_KEY, form);
            return "samples/edit";
        }

        SampleUpdateDto dto = new SampleUpdateDto();
        dto.setId(id);
        dto.setName(form.getName());
        dto.setType(form.getType());
        dto.setEmail(form.getEmail());
        dto.setPassword(form.getPassword());
        dto.setChecked(form.isChecked());
        dto.setOption(form.getOption());
        dto.setRemark(form.getRemark());
        this.sampleService.update(dto);

        attributes.addFlashAttribute(ResultMessages.success().add("message.info.common.update.success"));

        return "redirect:/samples/".concat(String.valueOf(id));
    }

    /**
     * 削除する.
     *
     * @param id {@code ID}
     * @param model モデル
     * @param attributes リダイレクト属性
     * @return ビュー
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Integer id, Model model, RedirectAttributes attributes) {

        this.sampleService.delete(id);

        attributes.addFlashAttribute(ResultMessages.success().add("message.info.common.delete.success"));

        return "redirect:/samples".concat(this.queryString(model));
    }
}
