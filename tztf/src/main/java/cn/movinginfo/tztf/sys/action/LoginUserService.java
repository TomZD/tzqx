package cn.movinginfo.tztf.sys.action;

import java.util.List;

import net.ryian.commons.CollectionUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.movinginfo.tztf.sem.domain.LoginUser;
import cn.movinginfo.tztf.sem.domain.LoginUserExample;
import cn.movinginfo.tztf.sem.mapper.LoginUserMapper;

@Service
public class LoginUserService {
	@Autowired
	private LoginUserMapper loginUserMapper;

	public LoginUser selectUserByName(String name) {
		LoginUserExample example = new LoginUserExample();
		example.createCriteria().andUserNameEqualTo(name);
		List<LoginUser> list = loginUserMapper.selectByExample(example);
		return list.size() == 0 ? null : list.get(0);
	}
	
	
}
