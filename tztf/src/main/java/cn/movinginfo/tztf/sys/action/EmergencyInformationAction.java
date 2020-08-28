package cn.movinginfo.tztf.sys.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.common.utils.ConfigExportHelper;
import cn.movinginfo.tztf.sen.domain.KeyPeople;
import cn.movinginfo.tztf.sen.domain.PersonType;
import cn.movinginfo.tztf.sen.service.KeyPeopleService;
import cn.movinginfo.tztf.sen.service.PersonTypeService;
import cn.movinginfo.tztf.sys.domain.Area;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.service.AreaService;
import cn.movinginfo.tztf.sys.service.DeptService;
import cn.movinginfo.tztf.sys.service.PeopleGroupService;
import cn.movinginfo.tztf.sys.service.UserService;
import cn.movinginfo.tztf.sys.utils.json.DictFilter;

/**
*
* @description:EmergencyInformation action
* @author: autoCode
* @history:
*/
@Controller
@RequestMapping("/sys/emergencyinfo")
@SuppressWarnings("serial")
public class EmergencyInformationAction extends MagicAction<KeyPeople,KeyPeopleService> {

    @Autowired
    private DeptService deptService;
   @Resource
   private PeopleGroupService peopleGroupService;
   @Autowired
   private UserService userService;
   @Autowired
   private AreaService areaService;
   
   @Autowired
   private PersonTypeService personTypeService;

    @RequestMapping(value = "emergencyinfoQuery")
    public String indexMenu(HttpServletRequest request, Model model){
        model.addAttribute("currentPage", 1);
        model.addAttribute("pageSize", 10);
        return "/sys/emergencyinfo/emergencyinfoQuery";
    }

    /**
     * 获取列表数据
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("emergencyinfoData")
    public String departfaxData(HttpServletRequest request, Model model){
        Long userId = userService.get(this.getCurrentUser().id).getId();
        Long DepartmentId = userService.selectDepartmentId(userId);
        Depart depart = deptService.get(DepartmentId);
    	String typeIds = ConfigExportHelper.getProperty("typeIds");
    	String town = "";
    	String townId = depart.getTownId();
    	if(!StringUtils.isEmpty(townId)) {
    		town = areaService.get(Long.parseLong(townId)).getTown();
    	}
        Map<String,String> map = new HashMap<String,String>();
        map=getParameterMap(request);
        map.put("town", town);
        map.put("typeIds", typeIds);//信息员、网格责任人
        PageInfo<?> pageinfo =entityService.queryPaged(map);
        JSONObject page = (JSONObject) JSONObject.parse(JSONObject
                .toJSONString(pageinfo, new DictFilter()));
        model.addAttribute("page", page);
        return getNameSpace() + "emergencyinfoData";
    }



    /**
     * 获取信息员与网格责任人下拉框
     * @param request
     * @param response
     * @param model
     */
    @RequestMapping(value="getTypes")
    @ResponseBody
    public void getTypes(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<PersonType> list = personTypeService.getTypeInId();
        printJson(response, com.alibaba.fastjson.JSON.toJSONString(list));
    }

    /**
     * 向编辑框传值
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "editEmergencyInfo")
    public void uoeditDepartFax(HttpServletRequest request, HttpServletResponse response,Model model) {
        String id = request.getParameter("id");
        KeyPeople kp = entityService.get(Long.parseLong(id));
        Long personTypeId = kp.getPersonTypeId();
    	String personType = personTypeService.get(personTypeId).getName();
    	kp.setPersonType(personType);
    	kp.setPersonTypeList(personTypeService.getTypeInIdNotId(String.valueOf(personTypeId)));
        printJson(response, com.alibaba.fastjson.JSON.toJSONString(kp));
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
    	KeyPeople people = bindEntity(request,entityClass);
    	Long userId = userService.get(this.getCurrentUser().id).getId();
        Long DepartmentId = userService.selectDepartmentId(userId);
        Depart depart = deptService.get(DepartmentId);
        String townId = depart.getTownId();
        if(!StringUtils.isEmpty(townId)) {
        	Area area = areaService.get(Long.parseLong(townId));
        	people.setTown(area.getTown());
        }
        people.setCity("台州市");
        people.setCounty("黄岩区");
        Long personTypeId = people.getPersonTypeId();
        String name = people.getName();
        KeyPeople kp = entityService.getKeyPeopleByPhoneAndType(personTypeId, name);
        if(people.getId() == null) {
            if(kp != null) {
                printJson(response, com.alibaba.fastjson.JSON.toJSONString("该姓名已存在!"));
                return;
            }
        }else {
            if(kp != null && !people.getId().equals(kp.getId()) ) {
                printJson(response, com.alibaba.fastjson.JSON.toJSONString("该姓名已存在!"));
                return;
            }
        }
        entityService.saveOrUpdate(people);
    }


    /**
     * 删除
     *
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteEmergencyInfo")
    public String deletePeoPle(HttpServletRequest request, Model model) throws Exception {
        String id = request.getParameter("id");
        if (!StringUtils.isEmpty(id)) {
            entityService.logicRemove(Long.parseLong(id));
        }
        return "redirect:/sys/emergencyinfo/emergencyinfoQuery";
    }

    //获取群组group的值，通过id


}
