package cn.movinginfo.tztf.sys.shiro;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import cn.movinginfo.tztf.sys.action.DeptAction;



public class FormAuthenticationCaptchaFilter extends FormAuthenticationFilter {
	
	private static final Logger log = LoggerFactory.getLogger(FormAuthenticationFilter.class);
	 
	public static final String DEFAULT_CAPTCHA_PARAM = "captcha";
	
	public static final String DEFAULT_DEPART_NAME = "departName";
	
	private String captchaParam = DEFAULT_CAPTCHA_PARAM;
	
	private String departName = DEFAULT_DEPART_NAME;
	
	private String loginTypeParam = "loginType";
	
	private Map<String, Object> map = new HashMap<String, Object>();
	
	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

 
	public String getLoginTypeParam() {
		 
		return loginTypeParam;
 
	}
 
	public String getCaptchaParam() {
 
		return captchaParam;
 
	}
	
	public String getDepartName() {
		 
		return departName;
 
	}
 
	protected String getCaptcha(ServletRequest request) {
 
		return WebUtils.getCleanParam(request, getCaptchaParam());
 
	}
	
	protected String getLonginType(ServletRequest request) {
		 
		return WebUtils.getCleanParam(request, getLoginTypeParam());
 
	}
	
	/*protected String getDepart(ServletRequest request) {
		 
		return WebUtils.getCleanParam(request, getDepartName());
 
	}*/
 
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
 
		String username = getUsername(request);
 
		String password = getPassword(request);
 
		String captcha = getCaptcha(request);
		
		String loginType = getLonginType(request);
		
	//	String departName = getDepart(request);
 
		boolean rememberMe = isRememberMe(request);
 
		String host = getHost(request);
		
		if(password==null){
			password = "111111";
		}
 
		return new UsernamePasswordCaptchaToken(username,
				password.toCharArray(),rememberMe, host, captcha,loginType);
 
	}
	
	 /**
     * 表示当访问拒绝时
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	/*@Override
	protected boolean onLoginSuccess(AuthenticationToken token,
			Subject subject, ServletRequest request, ServletResponse response)
			throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		if (!"XMLHttpRequest".equalsIgnoreCase(httpServletRequest
				.getHeader("X-Requested-With"))) {// 不是ajax请求
			issueSuccessRedirect(request, response);
		} else {
			httpServletResponse.setCharacterEncoding("UTF-8");
			PrintWriter out = httpServletResponse.getWriter();
			out.println("{success:true,message:'登入成功'}");
			out.flush();
			out.close();
		}
		return false;
	}

	*//**
	 * 主要是处理登入失败的方法
	 *//*
	@Override
	protected boolean onLoginFailure(AuthenticationToken token,
			AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		if (!"XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request)
				.getHeader("X-Requested-With"))) {// 不是ajax请求
			setFailureAttribute(request, e);
			return true;
		}
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			String message = e.getClass().getSimpleName();
			if ("IncorrectCredentialsException".equals(message)) {
				out.println("{success:false,message:'密码错误'}");
			} else if ("UnknownAccountException".equals(message)) {
				out.println("{success:false,message:'账号不存在'}");
			} else if ("LockedAccountException".equals(message)) {
				out.println("{success:false,message:'账号被锁定'}");
			} else {
				out.println("{success:false,message:'未知错误'}");
			}
			out.flush();
			out.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

	*//**
	 * 所有请求都会经过的方法。
	 *//*
	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {

		if (isLoginRequest(request, response)) {
			if (isLoginSubmission(request, response)) {
				if (log.isTraceEnabled()) {
					log.trace("Login submission detected.  Attempting to execute login.");
				}
				if ("XMLHttpRequest"
						.equalsIgnoreCase(((HttpServletRequest) request)
								.getHeader("X-Requested-With"))) {// 不是ajax请求
					String vcode = request.getParameter("captcha");
					String vvcode = (String) SecurityUtils.getSubject().getSession()
							.getAttribute(DeptAction.KEY_CAPTCHA);
					if (vvcode == null || "".equals(vvcode)
							|| !vvcode.equals(vcode)) {
						response.setCharacterEncoding("UTF-8");
						PrintWriter out = response.getWriter();
						out.println("{success:false,message:'验证码错误'}");
						out.flush();
						out.close();
						return false;
					}
				}
				return executeLogin(request, response);
			} else {
				if (log.isTraceEnabled()) {
					log.trace("Login page view.");
				}
				// allow them to see the login page ;)
				return true;
			}
		} else {
			if (log.isTraceEnabled()) {
				log.trace("Attempting to access a path which requires authentication.  Forwarding to the "
						+ "Authentication url [" + getLoginUrl() + "]");
			}
			if (!"XMLHttpRequest"
					.equalsIgnoreCase(((HttpServletRequest) request)
							.getHeader("X-Requested-With"))) {// 不是ajax请求
				saveRequestAndRedirectToLogin(request, response);
			} else {
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.println("{message:'login'}");
				out.flush();
				out.close();
			}
			return false;
		}
	}*/
}
