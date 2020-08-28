package cn.movinginfo.tztf.sys.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

/**
 * 实现不需要密码登录
 * @author fl
 *
 */
public class MyHashedCredentialsMatcher extends HashedCredentialsMatcher {
	
	public MyHashedCredentialsMatcher(String hashAlgorithmName){
		super(hashAlgorithmName);
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		// TODO Auto-generated method stub
		UsernamePasswordCaptchaToken usernamePasswordCaptchaToken = (UsernamePasswordCaptchaToken) token;
		if("1".equals(usernamePasswordCaptchaToken.getLoginType())){//登录方式为免密码登录直接返回true
			return true;
		}
		return super.doCredentialsMatch(token, info);
	}

	
	
}
