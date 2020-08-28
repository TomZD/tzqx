package cn.movinginfo.tztf.sys.action;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.sys.domain.PeopleGroup;
import cn.movinginfo.tztf.sys.service.PeopleGroupService;
import cn.movinginfo.tztf.sys.utils.json.DictFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sys/peoplegroup")
public class PeopleGroupAction extends MagicAction<PeopleGroup, PeopleGroupService> {

    @Autowired
    private PeopleGroupService peopleGroupService;
    @RequestMapping("peoplegroupQuery")
    public String indexMenu(HttpServletRequest request, Model model){
        model.addAttribute("currentPage", 1);
        model.addAttribute("pageSize", 10);
        return "/sys/peoplegroup/peoplegroupQuery";
    }


    /**
     * 获取列表数据
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("peoplegroupData")
    public String departfaxData(HttpServletRequest request, Model model){
        model.addAttribute("currentPage",1);
        model.addAttribute("pageSize", 10);
        Map<String,String> map = new HashMap<String,String>();
        map=getParameterMap(request);
        List<PeopleGroup> list = entityService.getListGroupAll();

        PageInfo<PeopleGroup> pageinfo = new PageInfo<>(list);
        JSONObject page = (JSONObject) JSONObject.parse(JSONObject
                .toJSONString(pageinfo, new DictFilter()));
        model.addAttribute("page", page);
        return getNameSpace() + "peoplegroupData";
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
        PeopleGroup peopleGroup = bindEntity(request, entityClass);
        String name =peopleGroup.getName();
      PeopleGroup pg =  peopleGroupService.getPeopleGroupBy(name);
     //   DepartFax df = entityService.getDepartFaxBydepart(depart);
        if(peopleGroup.getId() == null) {
            if(pg != null) {
                printJson(response, com.alibaba.fastjson.JSON.toJSONString("该群组名已存在!"));
                return;
            }
        }else {
            if(pg != null && !peopleGroup.getId().equals(pg.getId()) ) {
                printJson(response, com.alibaba.fastjson.JSON.toJSONString("该群组名已存在!"));
                return;
            }
        }
        entityService.saveOrUpdate(peopleGroup);
    }


    /**
     * 向编辑框传值
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "editPeopleGroup")
    public void editPeopleGroup(HttpServletRequest request, HttpServletResponse response) {
         String id = request.getParameter("id");
        PeopleGroup peopleGroup = entityService.get(Long.parseLong(id));
        printJson(response, com.alibaba.fastjson.JSON.toJSONString(peopleGroup));
    }

    /**
     * 删除
     *
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deletePeopleGroup")
    public String deletePeopleGroup(HttpServletRequest request, Model model) throws Exception {
        String id = request.getParameter("id");
        if (!StringUtils.isEmpty(id)) {
            entityService.logicRemove(Long.parseLong(id));
        }
        return "redirect:/sys/peoplegroup/peoplegroupQuery";
    }



}
