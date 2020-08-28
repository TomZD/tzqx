package cn.movinginfo.tztf.sev.action;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.sev.domain.DepartFax;
import cn.movinginfo.tztf.sev.service.DepartFaxService;
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
@RequestMapping("/sev/departfax")
public class DepartFaxAction extends MagicAction<DepartFax, DepartFaxService> {
    @RequestMapping(value = "departfaxQuery")
    public String indexMenu(HttpServletRequest request, Model model){
        model.addAttribute("currentPage", 1);
        model.addAttribute("pageSize", 10);
        return "/sev/departfax/departfaxQuery";
    }


    /**
     * 获取列表数据
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("departfaxData")
    public String departfaxData(HttpServletRequest request, Model model){
        model.addAttribute("currentPage", 1);
        model.addAttribute("pageSize", 10);
        Map<String,String> map = new HashMap<String,String>();
        map=getParameterMap(request);
        PageInfo<?> pageInfo =entityService.queryPaged(map);
        JSONObject page = (JSONObject) JSONObject.parse(JSONObject
                .toJSONString(pageInfo, new DictFilter()));
        model.addAttribute("page", page);
        return getNameSpace() + "departfaxData";
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
        DepartFax departFax = bindEntity(request, entityClass);
        String depart = departFax.getDepart();
        DepartFax df = entityService.getDepartFaxBydepart(depart);
        if(departFax.getId() == null) {
        	if(df != null) {
        		printJson(response, com.alibaba.fastjson.JSON.toJSONString("该部门已存在!"));
                return;
        	}
        }else {
        	if(df != null && !departFax.getId().equals(df.getId()) ) {
        		printJson(response, com.alibaba.fastjson.JSON.toJSONString("该部门已存在!"));
                return;
        	}
        }
        entityService.saveOrUpdate(departFax);
    }

    /**
     * 向编辑框传值
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "editDepartFax")
    public void editDepartFax(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        DepartFax departFax = entityService.get(Long.parseLong(id));
        printJson(response, com.alibaba.fastjson.JSON.toJSONString(departFax));
    }

    /**
     * 删除
     *
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteDepartFax")
    public String deleteDecision(HttpServletRequest request, Model model) throws Exception {
        String id = request.getParameter("id");
        if (!StringUtils.isEmpty(id)) {
            entityService.logicRemove(Long.parseLong(id));
        }
        return "redirect:/sev/departfax/departfaxQuery";
    }

}
