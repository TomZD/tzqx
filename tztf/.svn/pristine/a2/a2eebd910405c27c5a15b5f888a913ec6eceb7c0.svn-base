package cn.movinginfo.tztf.sev.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.sev.domain.DingDingGroup;
import cn.movinginfo.tztf.sev.domain.Share;
import cn.movinginfo.tztf.sev.service.DingDingGroupService;
import cn.movinginfo.tztf.sev.service.ShareService;
import cn.movinginfo.tztf.sys.utils.json.DictFilter;

@Controller
@RequestMapping("/sev/share")
public class ShareAction extends MagicAction<Share, ShareService> {
    @RequestMapping(value = "shareQuery")
    public String indexMenu(HttpServletRequest request, Model model){
        model.addAttribute("currentPage", 1);
        model.addAttribute("pageSize", 10);
        return "/sev/share/shareQuery";
    }


    /**
     * 获取列表数据
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("shareData")
    public String DingDingData(HttpServletRequest request, Model model){
        model.addAttribute("currentPage", 1);
        model.addAttribute("pageSize", 10);
        Map<String,String> map = new HashMap<String,String>();
        map=getParameterMap(request);
        PageInfo<?> pageInfo =entityService.queryPaged(map);
        JSONObject page = (JSONObject) JSONObject.parse(JSONObject
                .toJSONString(pageInfo, new DictFilter()));
        model.addAttribute("page", page);
        return getNameSpace() + "shareData";
    }

    /**
     * 新增修改保存
     * @param request
     * @param response
     * @param model
     * @throws Exception
     */
    @RequestMapping(value = "/saveOrUpdate")
    public void saveOrUpdate(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
    	Share share = bindEntity(request, entityClass);
        entityService.saveOrUpdate(share);
    }

    /**
     * 向编辑框传值
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "editShare")
    public void editDingDing(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        Share share = entityService.get(Long.parseLong(id));
        printJson(response, com.alibaba.fastjson.JSON.toJSONString(share));
    }

    /**
     * 删除
     *
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteShare")
    public String deleteDecision(HttpServletRequest request, Model model) throws Exception {
        String id = request.getParameter("id");
        if (!StringUtils.isEmpty(id)) {
            entityService.logicRemove(Long.parseLong(id));
        }
        return "redirect:/sev/dingdingGroup/shareQuery";
    }
}
