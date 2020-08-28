package cn.movinginfo.tztf.sys.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.sys.domain.ReleaseChannel;
import cn.movinginfo.tztf.sys.service.ReleaseChannelService;

@Controller
@RequestMapping("/sys/releseChannel")
@SuppressWarnings("serial")
public class ReleaseChannelAction extends MagicAction<ReleaseChannel, ReleaseChannelService> {

}
