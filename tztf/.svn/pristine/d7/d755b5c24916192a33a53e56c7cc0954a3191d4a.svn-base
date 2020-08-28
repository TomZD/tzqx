package cn.movinginfo.tztf.sen.action;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.sen.domain.Alertrule;
import cn.movinginfo.tztf.sen.domain.Elementinfo;
import cn.movinginfo.tztf.sen.domain.KeyPlace;
import cn.movinginfo.tztf.sen.domain.PlaceType;
import cn.movinginfo.tztf.sen.service.AlertruleService;
import cn.movinginfo.tztf.sen.service.ElementinfoService;
import cn.movinginfo.tztf.sys.domain.Dict;
import cn.movinginfo.tztf.sys.service.DictService;
import cn.movinginfo.tztf.sys.utils.json.DictFilter;
import net.ryian.commons.StringUtils;


/**
*
* @description:Elementinfo action
* @author: autoCode
* @history:
*/
@Controller
@RequestMapping("/yzt/elementInfo")
@SuppressWarnings("serial")
public class ElementinfoAction extends MagicAction<Elementinfo,ElementinfoService> {
	
	@Autowired
	private DictService dictService;

	@Autowired
	private AlertruleService alertruleService;
	
	
	/**
	 * 气象要素管理页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("elementInfoQuery")
	public String indexElementInfoQuery(HttpServletRequest request, Model model){
		model.addAttribute("currentPage", 1);
		model.addAttribute("pageSize", 10);
		return "/yzt/elementInfo/elementInfoQuery";
	}
	
	/**
	 * 获取列表数据
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/elementInfoData")
	public String elementInfoData(HttpServletRequest request, Model model)
			throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map=getParameterMap(request);
		PageInfo<?> pageInfo = entityService.queryPaged(map);
		JSONObject page = (JSONObject) JSONObject.parse(JSONObject
				.toJSONString(pageInfo, new DictFilter())); 
		model.addAttribute("page", page);
		 return getNameSpace() + "elementInfoData";
	}
	
	
	/**
	 * 删除
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteElementInfo")
	public String delete(HttpServletRequest request, Model model) throws Exception {
		String id = request.getParameter("id");
		if (!StringUtils.isEmpty(id)) {
			entityService.logicRemove(Long.parseLong(id));
		}
		return "redirect:/yzt/elementInfo/elementInfoQuery";
	}
	
	/**
	 * 获取所有气象类型
	 * @return
	 */
	@RequestMapping(value ="getAllType")
	@ResponseBody
	public List<Dict> getAllType() {
		List<Dict> list = dictService.getDicList("element_type");
		return list;
	}
	
	/**
	 * 向编辑框传值
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "editElementInfo")
	public void edit(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Elementinfo	 elementInfo = entityService.get(Long.parseLong(id));
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(elementInfo));
	}
	
	/**
	 * 新增保存和修改
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveOrUpdate")
	public void suerEdit(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Elementinfo elementinfo = bindEntity(request, entityClass);
		String elementId = elementinfo.getElementId();
		String elementName = elementinfo.getElementName();
		Long id = elementinfo.getId();
		if(id ==null) {
		Elementinfo ele = entityService.findByElementId(elementId);
		if(ele !=null) {
	      printJson(response, com.alibaba.fastjson.JSON.toJSONString("该要素名已存在！"));
		   return;
		  }
		Elementinfo element = entityService.findByElementName(elementName);
		if(element !=null) {
		      printJson(response, com.alibaba.fastjson.JSON.toJSONString("该要素中文名已存在！"));
			   return;
			  }
		entityService.saveOrUpdate(elementinfo);
		}else {
			Elementinfo ele = entityService.findByElementId(elementId);
			if(ele !=null&&!ele.getId().equals(id)) {
				printJson(response, com.alibaba.fastjson.JSON.toJSONString("该要素名已存在！"));
				   return;
			}
			Elementinfo element = entityService.findByElementName(elementName);
			if(element !=null&&!element.getId().equals(id)) {
			      printJson(response, com.alibaba.fastjson.JSON.toJSONString("该要素中文名已存在！"));
				   return;
			   }
			try {
				entityService.saveOrUpdate(elementinfo);
				List<Alertrule> list = alertruleService.getAlertruleByElementId(String.valueOf(id));
				for(Alertrule alert :list) {
					String eleId=alert.getElementIds();
					String logic = alert.getLogic();
					String thresholdValue = alert.getThresholdValue();
					String relative = alert.getRelative();
					String[] elementIds = eleId.split(",");
					String[] logics = logic.split(",");
					String[] thresholdValues = thresholdValue.split(",");
					String[] relatives = relative.split(",");
					String ruleExpr="";
					for(int x=0;x<elementIds.length;x++) {
						Elementinfo eleInfo = entityService.get(Long.parseLong(elementIds[x]));
						String eleName = eleInfo.getElementId();
						if(x>0) {
							ruleExpr += relatives[x-1];
						}
						ruleExpr += eleName + logics[x]+thresholdValues[x];
					}
					alert.setRuleExpr(ruleExpr);
					alertruleService.saveOrUpdate(alert);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
				
			
		}
		
	}
	
	/**
	 * 向编辑下拉框传值
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "editElementType")
	public void editPlaceType(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Elementinfo elementInfo = entityService.get(Long.parseLong(id));
    	String type=elementInfo.getElementType();
    	String typeName=dictService.getContent("element_type", type);
    	elementInfo.setTypeName(typeName);
    	List<Dict> typeList = dictService.getElementTypeByNotValue(type);
    	elementInfo.setTypeList(typeList);
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(elementInfo));
	}

}
