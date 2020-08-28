package cn.movinginfo.tztf.sys.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.sys.domain.RUserRole;
import cn.movinginfo.tztf.sys.service.RUserRoleService;

/**
*
* @description:RUserRole action
* @author: autoCode
* @history:
*/
@Controller
@RequestMapping("/sys/r-user-role")
@SuppressWarnings("serial")
public class RUserRoleAction extends MagicAction<RUserRole,RUserRoleService> {


}
