package cn.movinginfo.tztf.sys.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.sys.domain.Area;
import cn.movinginfo.tztf.sys.service.AreaService;

@Controller
@RequestMapping("/sys/area")
@SuppressWarnings("serial")
public class AreaAction extends MagicAction<Area, AreaService> {

}
