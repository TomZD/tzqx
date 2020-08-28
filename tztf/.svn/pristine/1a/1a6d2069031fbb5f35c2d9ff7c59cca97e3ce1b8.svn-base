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
import cn.movinginfo.tztf.sev.service.DingDingGroupService;
import cn.movinginfo.tztf.sys.utils.json.DictFilter;

@Controller
@RequestMapping("/sev/dingdingGroup")
public class DingDingGroupAction extends MagicAction<DingDingGroup, DingDingGroupService> {
    @RequestMapping(value = "dingdingGroupQuery")
    public String indexMenu(HttpServletRequest request, Model model){
        model.addAttribute("currentPage", 1);
        model.addAttribute("pageSize", 10);
        return "/sev/dingdingGroup/dingdingGroupQuery";
    }


    /**
     * 获取列表数据
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("dingdingGroupData")
    public String DingDingData(HttpServletRequest request, Model model){
        model.addAttribute("currentPage", 1);
        model.addAttribute("pageSize", 10);
        Map<String,String> map = new HashMap<String,String>();
        map=getParameterMap(request);
        PageInfo<?> pageInfo =entityService.queryPaged(map);
        JSONObject page = (JSONObject) JSONObject.parse(JSONObject
                .toJSONString(pageInfo, new DictFilter()));
        model.addAttribute("page", page);
        return getNameSpace() + "dingdingGroupData";
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
    	DingDingGroup DingDing = bindEntity(request, entityClass);
        String groupName = DingDing.getGroupName();
        DingDingGroup df = entityService.getGroupByGroupName(groupName);
        if(DingDing.getId() == null) {
        	if(df != null) {
        		printJson(response, com.alibaba.fastjson.JSON.toJSONString("该群已存在!"));
                return;
        	}
        }else {
        	if(df != null && !DingDing.getId().equals(df.getId()) ) {
        		printJson(response, com.alibaba.fastjson.JSON.toJSONString("该群已存在!"));
                return;
        	}
        }
        entityService.saveOrUpdate(DingDing);
    }

    /**
     * 向编辑框传值
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "editDingDingGroup")
    public void editDingDing(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        DingDingGroup DingDing = entityService.get(Long.parseLong(id));
        printJson(response, com.alibaba.fastjson.JSON.toJSONString(DingDing));
    }

    /**
     * 删除
     *
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteDingDingGroup")
    public String deleteDecision(HttpServletRequest request, Model model) throws Exception {
        String id = request.getParameter("id");
        if (!StringUtils.isEmpty(id)) {
            entityService.logicRemove(Long.parseLong(id));
        }
        return "redirect:/sev/dingdingGroup/dingdingGroupQuery";
    }
}
