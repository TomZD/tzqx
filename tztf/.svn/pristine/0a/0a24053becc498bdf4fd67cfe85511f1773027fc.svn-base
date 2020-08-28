package cn.movinginfo.tztf.sen.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.movinginfo.tztf.sys.domain.UserXt;



public class LoginInterceptor implements HandlerInterceptor {

    @Override
    //登录拦截验证
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      //  System.out.println("url:" +request.getRequestURI()+" :            -------登录拦截验证----");
    	UserXt user = (UserXt)request.getSession().getAttribute("yztUser");
        String url = request.getRequestURI();
        if(user == null) {	//未登录拦截 跳转登录页面
//        	if(url.contains("Index")) {
        		response.sendRedirect(request.getContextPath()+"/fzjzLogin");
            	return false;
//        	}else if(url.contains("yzt")){
//        		response.sendRedirect(request.getContextPath()+"/returnLogin");
//            	return false;
//        	}
        	
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}
