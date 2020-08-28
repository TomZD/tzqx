package cn.movinginfo.tztf.sen.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.sen.domain.DangerType;
import cn.movinginfo.tztf.sen.service.DangerTypeService;

/**
*
* @description:DangerType action
* @author: autoCode
* @history:
*/
@Controller
@RequestMapping("/sen/dangerType")
@SuppressWarnings("serial")
public class DangerTypeAction extends MagicAction<DangerType,DangerTypeService> {


}
