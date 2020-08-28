package cn.movinginfo.tztf.sen.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.sen.domain.Equipment;
import cn.movinginfo.tztf.sen.service.EquipmentService;

/**
*
* @description:Equipment action
* @author: autoCode
* @history:
*/
@Controller
@RequestMapping("/sen/equipment")
@SuppressWarnings("serial")
public class EquipmentAction extends MagicAction<Equipment,EquipmentService> {


}
