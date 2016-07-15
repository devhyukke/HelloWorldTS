package jp.ne.hyukke.wts.hello.web.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.groups.Default;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.ne.hyukke.wts.hello.core.domain.messages.ResultMessages;
import jp.ne.hyukke.wts.hello.core.validation.groups.Registration;
import jp.ne.hyukke.wts.hello.domain.dto.UserRegisterDto;
import jp.ne.hyukke.wts.hello.domain.dto.UserUpdateDto;
import jp.ne.hyukke.wts.hello.domain.model.Role;
import jp.ne.hyukke.wts.hello.domain.model.User;
import jp.ne.hyukke.wts.hello.domain.service.RoleService;
import jp.ne.hyukke.wts.hello.domain.service.UserService;
import jp.ne.hyukke.wts.hello.domain.vo.UserConditionVo;
import jp.ne.hyukke.wts.hello.web.form.UserForm;
import jp.ne.hyukke.wts.hello.web.form.UserSearchForm;

/**
 * ユーザーを操作するコントローラクラス.
 *
 * @author hyukke
 */
@Controller
@PreAuthorize("hasAnyRole('ROLE_SYSTEM_ADMIN', 'ROLE_USER_MANAGER')")
@RequestMapping("users")
public class UserController extends SearchableControllerBase {

    private static final String FORM_KEY = "userForm";

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    // ロールを複数まとめて取り扱うために定義
    public static class Roles extends ArrayList<Role> {

        private static final long serialVersionUID = -4477568201034633868L;

        public Roles(java.util.Collection<Role> col) {
            super(col);
        }

        public String nameAt(Integer id) {

            return this.stream()
                    .filter(role -> role.getId().equals(id))
                    .map(Role::getName)
                    .findFirst().orElse("");
        }
    }

    /**
     * @return ロール
     */
    @ModelAttribute("roles")
    public Roles roles() {

        return new Roles(this.roleService.findAll());
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
            @ModelAttribute("userSearchForm") UserSearchForm form, Model model, SessionStatus status,
            HttpServletRequest request) {

        UserConditionVo condition = UserConditionVo
                .valueOf(form.getId(), form.getUsername(), form.getDisplayName(), form.getRoleId());
        model.addAttribute("page", this.userService.findByCondition(condition));

        this.addSearchConditionToSession(model, status, request);

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

        model.addAttribute(FORM_KEY, new UserForm());

        return "users/create";
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

        model.addAttribute("user", this.userService.findById(id));

        return "users/detail";
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

        User user = this.userService.findById(id);
        UserForm form = new UserForm();
        BeanUtils.copyProperties(user, form);
        form.setRoleId(user.getRole().getId());

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
            @Validated({Default.class, Registration.class}) @ModelAttribute(FORM_KEY) UserForm form,
            BindingResult bindingResult, Model model, RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute(FORM_KEY, form);
            return "users/create";
        }

        UserRegisterDto dto = new UserRegisterDto();
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
            @Valid @ModelAttribute(FORM_KEY) UserForm form, BindingResult bindingResult,
            Model model, RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("user", this.userService.findById(id));
            model.addAttribute(FORM_KEY, form);
            return "users/edit";
        }

        UserUpdateDto dto = new UserUpdateDto();
        dto.setId(id);
        dto.setUsername(form.getUsername());
        dto.setDisplayName(form.getDisplayName());
        dto.setRoleId(form.getRoleId());
        this.userService.update(dto);

        attributes.addFlashAttribute(ResultMessages.success().add("message.info.common.update.success"));

        return "redirect:/users/".concat(String.valueOf(id));
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

        this.userService.delete(id);

        attributes.addFlashAttribute(ResultMessages.success().add("message.info.common.delete.success"));

        return "redirect:/users".concat(this.queryString(model));
    }
}
