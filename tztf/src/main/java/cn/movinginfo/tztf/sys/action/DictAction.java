package cn.movinginfo.tztf.sys.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.sys.domain.Dict;
import cn.movinginfo.tztf.sys.service.DictService;

@SuppressWarnings("serial")
@Controller
@RequestMapping("/sys/dict")
public class DictAction extends MagicAction<Dict, DictService> {


}
