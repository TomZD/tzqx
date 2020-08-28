package cn.movinginfo.tztf.sev.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.sev.domain.ReleaseChannelInstance;
import cn.movinginfo.tztf.sev.service.ReleaseChannelInstanceService;
@Controller
@RequestMapping("/sev/releaseChannelInstance")
public class ReleaseChannelInstanceAction extends MagicAction<ReleaseChannelInstance,ReleaseChannelInstanceService> {

}
