package cn.movinginfo.tztf.common.action;

import net.ryian.core.action.BaseAction;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.movinginfo.tztf.sys.shiro.ShiroDBRealm;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Action基类，系统中所有基类均继承自该类
 * 
 * @author cheng.wang
 */
@SuppressWarnings("serial")
public abstract class BaseMagicAction extends BaseAction{

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	public ShiroDBRealm.ShiroUser user;

	protected ShiroDBRealm.ShiroUser getCurrentUser() {
		if(SecurityUtils.getSubject().getPrincipal()==null){
			return user;
		}else{
			return (ShiroDBRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
		}
		

	}
	
	protected void setCurrentUser(ShiroDBRealm.ShiroUser user) {
		this.user=user;

	}

	protected Map<String,String> getParameterMap(HttpServletRequest request) {
		Map<String,String> map = new HashMap<String,String>();
		Enumeration<String> enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements()) {
			String propertyName = enumeration.nextElement();
			String propertyValue = request.getParameter(propertyName.trim());
			map.put(propertyName,propertyValue);
		}
		return map;
	}

}
