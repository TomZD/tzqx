package cn.movinginfo.tztf.sys.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.sys.domain.FooterContact;
import cn.movinginfo.tztf.sys.service.FooterContactService;

@Controller
@RequestMapping("/sys/footer")
public class FooterContactAction extends MagicAction<FooterContact, FooterContactService> {
	
}
