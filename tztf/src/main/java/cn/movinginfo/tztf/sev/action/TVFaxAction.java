package cn.movinginfo.tztf.sev.action;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.sev.domain.TVFax;
import cn.movinginfo.tztf.sev.service.TVFaxService;
import cn.movinginfo.tztf.sys.utils.json.DictFilter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/sev/tvfax")
public class TVFaxAction extends MagicAction<TVFax, TVFaxService> {
    @RequestMapping(value = "tvfaxQuery")
    public String indexMenu(HttpServletRequest request, Model model){
        model.addAttribute("currentPage", 1);
        model.addAttribute("pageSize", 10);
        return "/sev/tvfax/tvfaxQuery";
    }


    /**
     * 获取列表数据
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("tvfaxData")
    public String tvfaxData(HttpServletRequest request, Model model){
        model.addAttribute("currentPage", 1);
        model.addAttribute("pageSize", 10);
        Map<String,String> map = new HashMap<String,String>();
        map=getParameterMap(request);
        PageInfo<?> pageInfo =entityService.queryPaged(map);
        JSONObject page = (JSONObject) JSONObject.parse(JSONObject
                .toJSONString(pageInfo, new DictFilter()));
        model.addAttribute("page", page);
        return getNameSpace() + "tvfaxData";
    }

    /**
     * 新增修改保存
     * @param request
     * @param response
     * @param model
     * @throws Exception
     */
    @RequestMapping(value = "/saveOrUpdate")
    public void suerEdit(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
    	TVFax tvfax = bindEntity(request, entityClass);
        String tvName = tvfax.getTvName();
        TVFax tv = entityService.getDepartFaxByName(tvName);
        if(tvfax.getId() == null) {
        	if(tv != null) {
        		printJson(response, com.alibaba.fastjson.JSON.toJSONString("该部门已存在!"));
                return;
        	}
        }else {
        	if(tv != null && !tvfax.getId().equals(tv.getId()) ) {
        		printJson(response, com.alibaba.fastjson.JSON.toJSONString("该部门已存在!"));
                return;
        	}
        }
        entityService.saveOrUpdate(tvfax);
    }

    /**
     * 向编辑框传值
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "edittvfax")
    public void edittvfax(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        TVFax tvfax = entityService.get(Long.parseLong(id));
        printJson(response, com.alibaba.fastjson.JSON.toJSONString(tvfax));
    }

    /**
     * 删除
     *
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deletetvfax")
    public String deleteDecision(HttpServletRequest request, Model model) throws Exception {
        String id = request.getParameter("id");
        if (!StringUtils.isEmpty(id)) {
            entityService.logicRemove(Long.parseLong(id));
        }
        return "redirect:/sev/tvfax/tvfaxQuery";
    }

}
