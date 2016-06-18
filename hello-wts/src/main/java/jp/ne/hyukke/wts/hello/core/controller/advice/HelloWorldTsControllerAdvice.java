package jp.ne.hyukke.wts.hello.core.controller.advice;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * @author hyukke
 */
@ControllerAdvice
public class HelloWorldTsControllerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // TODO ログ出力に変更
        System.out.println("Init binder!!");
    }

    /**
     * @param e 例外
     * @return 権限なしエラー
     */
    @ExceptionHandler(AccessDeniedException.class)
    public String accessDeniedException(AccessDeniedException e) {
        return "errors/403";
    }
}
