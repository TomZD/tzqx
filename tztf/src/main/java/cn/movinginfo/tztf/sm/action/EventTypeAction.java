package cn.movinginfo.tztf.sm.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.sm.domain.EventType;
import cn.movinginfo.tztf.sm.service.EventTypeService;

/**
*
* @description:EventType action
* @author: autoCode
* @history:
*/
@Controller
@RequestMapping("/sm/event-type")
@SuppressWarnings("serial")
public class EventTypeAction extends MagicAction<EventType,EventTypeService> {


}
