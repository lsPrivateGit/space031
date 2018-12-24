package com.springmvc.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


/**
 * 自定义异常处理器类
 * @author ex-sunjiamin
 *
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		
		CustomException customException = null;
		
		
		//如果抛出的是系统自定义异常则直接转换
		if(ex instanceof CustomException ){
			customException = (CustomException) ex;
		}else{
			customException = new CustomException("系统错误，请与管理员联系！");
		}
		
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.addObject("message",customException.getMessage());
		modelAndView.setViewName("error");
				
		
		
		return modelAndView;
	}

}
