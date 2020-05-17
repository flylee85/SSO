package com.sso.server.config;

import com.github.mjd507.util.exception.BusinessException;
import com.github.mjd507.util.http.ApiCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by mjd on 2020/4/17 14:12
 */
@ControllerAdvice
@Slf4j
public class GlobalException {

    @ExceptionHandler({BusinessException.class})
    public ModelAndView handleException(BusinessException e) {
        log.error("捕获全局异常:", e);
        ModelAndView mv = new ModelAndView();
        mv.addObject("errorCode", e.getCode());
        mv.addObject("errorMsg", e.getMsg());
        mv.setViewName("common/error");
        return mv;
    }

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ModelAndView handleException(Exception e) {
        log.error("捕获全局异常:", e);
        ModelAndView mv = new ModelAndView();
        mv.addObject("errorCode", ApiCode.INTERNAL_ERROR.getCode());
        mv.addObject("errorMsg", e);
        mv.setViewName("common/error");
        return mv;
    }

}
