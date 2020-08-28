package cn.movinginfo.tztf.sys.shiro;

import net.ryian.commons.DigestUtils;
import net.ryian.commons.EncodeUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import cn.movinginfo.tztf.sys.action.DeptAction;
import cn.movinginfo.tztf.sys.domain.Permission;
import cn.movinginfo.tztf.sys.domain.User;
import cn.movinginfo.tztf.sys.service.DeptService;
import cn.movinginfo.tztf.sys.service.PermissionService;
import cn.movinginfo.tztf.sys.service.UserService;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by allenwc on 14/11/12.
 */
public class ShiroDBRealm extends AuthorizingRealm
{

    @Autowired
    protected UserService userService;
    @Autowired
    protected DeptService deptService;
    @Autowired
    protected PermissionService permissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        ShiroUser shiroUser = (ShiroUser) principalCollection.getPrimaryPrincipal();
        List<String> permissions = new ArrayList<String>();
            for (Permission permission : permissionService.getPermissionsByUser(shiroUser.id)) {
                String code = permission.getCode();
                    if (!StringUtils.isEmpty(code)) {
                            permissions.add(code);
                    }
                }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);


        return info;
    }

    @Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authenticationToken) throws AuthenticationException {
    	UsernamePasswordCaptchaToken token = (UsernamePasswordCaptchaToken) authenticationToken;
    	String departName = token.getUsername();
    	if(!"1".equals(token.getLoginType())){
    		if (departName == null) {
    			throw new AccountException(
    					"Null usernames are not allowed by this realm.");
    		}
    		// 增加判断验证码逻辑
    		String captcha = token.getCaptcha();
    		String exitCode = (String) SecurityUtils.getSubject().getSession()
    				.getAttribute(DeptAction.KEY_CAPTCHA);
    		if (null == captcha || !captcha.equalsIgnoreCase(exitCode)) {
    			throw new CaptchaException("验证码错误");
    		}
    	}else{
    		
    	}
    	User user = userService.findUserByUserName(departName);
		/*Depart dept = deptService.getByDepartmentId(user1.getDepartmentId());
		User user = userService.findUserByDepartment(dept.getId());*/
	        if (user != null) {
	            byte[] salt = Hex.decode(user.getSalt());
	            return new SimpleAuthenticationInfo(new ShiroUser(user.getId(), user.getUserName(), user.getName()),
	                    user.getPassword(), ByteSource.Util.bytes(salt), getName());
	        } else {
	        	
	            return null;
	        }
		
	}

    /**
     * 设定Password校验的Hash算法与迭代次数.
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        MyHashedCredentialsMatcher matcher = new MyHashedCredentialsMatcher(User.HASH_ALGORITHM);
        matcher.setHashIterations(User.HASH_INTERATIONS);
        setCredentialsMatcher(matcher);
    }

    public AuthorizationInfo getAuthorizationInfo(Object principal) {
        PrincipalCollection principalCollection = new SimplePrincipalCollection(principal,getName());
        return this.getAuthorizationInfo(principalCollection);
    }

    public void clearCachedAuthorizationInfo(Object principal) {
        PrincipalCollection principalCollection = new SimplePrincipalCollection(principal,getName());
        this.clearCachedAuthorizationInfo(principalCollection);
    }



        /**
         * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
         */
    public static class ShiroUser implements Serializable {
        private static final long serialVersionUID = -1373760761780840081L;
        public Long id;
        public String userName;
        public String name;
        public List<Permission> permissions;

        public ShiroUser(Long id, String userName, String name) {
            this.id = id;
            this.userName = userName;
            this.name = name;
        }

            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }

            public String getName() {
            return name;
        }

        /**
         * 本函数输出将作为默认的<shiro:principal/>输出.
         */
        @Override
        public String toString() {
            return userName;
        }

        /**
         * 重载hashCode,只计算loginName;
         */
        @Override
        public int hashCode() {
            return Objects.hashCode(userName);
        }

        /**
         * 重载equals,只计算loginName;
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            ShiroUser other = (ShiroUser) obj;
            if (userName == null) {
                if (other.userName != null) {
                    return false;
                }
            } else if (!userName.equals(other.userName)) {
                return false;
            }
            return true;
        }
    }
    
    public static void main(String[] args) {
		String salt= "145ffa652e4adab2";
		byte[] hashPassword = DigestUtils.sha1("111111".getBytes(), Hex.decode(salt), 1024);
		System.out.println(EncodeUtils.encodeHex(hashPassword));
	}

}
