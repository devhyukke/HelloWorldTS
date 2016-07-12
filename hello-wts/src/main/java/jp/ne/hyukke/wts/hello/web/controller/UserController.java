package jp.ne.hyukke.wts.hello.web.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.ne.hyukke.wts.hello.core.domain.messages.ResultMessages;
import jp.ne.hyukke.wts.hello.domain.dto.UserDto;
import jp.ne.hyukke.wts.hello.domain.entity.User;
import jp.ne.hyukke.wts.hello.domain.service.UserService;
import jp.ne.hyukke.wts.hello.domain.vo.UserConditionVo;
import jp.ne.hyukke.wts.hello.web.WebMvcConfig;
import jp.ne.hyukke.wts.hello.web.form.SampleForm;
import jp.ne.hyukke.wts.hello.web.form.UserForm;
import jp.ne.hyukke.wts.hello.web.form.UserSearchForm;

/**
 * ユーザーを操作するコントローラクラス.
 *
 * @author hyukke
 */
@Controller
@PreAuthorize("hasRole('SYSTEM_ADMIN', 'USER_MANAGER')")
@RequestMapping("users")
@SessionAttributes(value = WebMvcConfig.SEARCH_CONDITION_QUERY_KEY)
public class UserController {

    private static final String FORM_KEY = "userForm";

    @Autowired
    private UserService userService;

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
     * ビューを表示する.
     *
     * @param model モデル
     * @return ビュー
     */
    @RequestMapping(method = RequestMethod.GET)
    public String show(
            @ModelAttribute("userSearchForm") UserSearchForm form, Model model, SessionStatus status,
            HttpServletRequest request) {

        // 初期表示時の検索の場合はセッションをクリア
        if (StringUtils.isEmpty(request.getQueryString())) {
            status.setComplete();
        }

        UserConditionVo condition = UserConditionVo
                .valueOf(form.getId(), form.getUsername(), form.getDisplayName(), form.getRoleId());
        model.addAttribute("page", this.userService.findByCondition(condition));

        Optional.ofNullable(request.getQueryString())
                .filter(StringUtils::hasText)
                .ifPresent(query -> model.addAttribute(WebMvcConfig.SEARCH_CONDITION_QUERY_KEY, query));

        return "users/list";
    }

    /**
     * 新規作成のビューを表示する.
     *
     * @param model モデル
     * @return ビュー
     */
    @RequestMapping(value = "editor", method = RequestMethod.GET)
    public String showCreateNew(Model model) {

        model.addAttribute(FORM_KEY, new SampleForm());

        return "users/create";
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

        model.addAttribute("user", this.userService.findById(id));

        return "users/detail";
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

        User user = this.userService.findById(id);
        UserForm form = new UserForm();
        BeanUtils.copyProperties(user, form);

        model.addAttribute("user", user);
        model.addAttribute(FORM_KEY, form);

        return "users/edit";
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
            @Valid @ModelAttribute(FORM_KEY) UserForm form,
            BindingResult bindingResult, Model model, RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute(FORM_KEY, form);
            return "users/create";
        }

        UserDto dto = new UserDto();
        dto.setUsername(form.getUsername());
        dto.setPassword(form.getPassword());
        dto.setDisplayName(form.getDisplayName());
        dto.setRoleId(form.getRoleId());
        User registerd = this.userService.register(dto);

        attributes.addFlashAttribute(ResultMessages.success().add("message.info.common.register.success"));
        attributes.addFlashAttribute("newCreation", registerd);

        return "redirect:/users".concat(this.queryString(model));
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
            @Valid @ModelAttribute(FORM_KEY) UserForm form, BindingResult bindingResult,
            Model model, RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("user", this.userService.findById(id));
            model.addAttribute(FORM_KEY, form);
            return "users/edit";
        }

        UserDto dto = new UserDto();
        dto.setId(id);
        dto.setUsername(form.getUsername());
        dto.setPassword(form.getPassword());
        dto.setDisplayName(form.getDisplayName());
        dto.setRoleId(form.getRoleId());
        this.userService.update(dto);

        attributes.addFlashAttribute(ResultMessages.success().add("message.info.common.update.success"));

        return "redirect:/users/".concat(String.valueOf(id));
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

        UserDto dto = new UserDto();
        dto.setId(id);
        this.userService.delete(dto);

        attributes.addFlashAttribute(ResultMessages.success().add("message.info.common.delete.success"));

        return "redirect:/users".concat(this.queryString(model));
    }
}
