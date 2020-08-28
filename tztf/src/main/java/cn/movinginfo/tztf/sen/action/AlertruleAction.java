package cn.movinginfo.tztf.sen.action;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
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
import cn.movinginfo.tztf.sen.service.AlertruleService;
import cn.movinginfo.tztf.sen.service.ElementinfoService;
import cn.movinginfo.tztf.sys.utils.json.DictFilter;
import net.ryian.commons.StringUtils;

/**
 *
 * @description:Alertrule action
 * @author: autoCode
 * @history:
 */
@Controller
@RequestMapping("/yzt/alertRule")
@SuppressWarnings("serial")
public class AlertruleAction extends MagicAction<Alertrule, AlertruleService> {
	@Autowired
	private ElementinfoService elementinfoService;
	
	/**
	 * 报警规则管理页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("alertRuleQuery")
	public String indexAlertRuleQuery(HttpServletRequest request, Model model){
		model.addAttribute("currentPage", 1);
		model.addAttribute("pageSize", 10);
		return "/yzt/alertRule/alertRuleQuery";
	}

	/**
	 * 获取列表数据
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/alertRuleData")
	public String alertRuleData(HttpServletRequest request, Model model) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map = getParameterMap(request);
		PageInfo<?> pageInfo = entityService.queryPaged(map);
		JSONObject page = (JSONObject) JSONObject.parse(JSONObject.toJSONString(pageInfo, new DictFilter()));
		model.addAttribute("page", page);
		return getNameSpace() + "alertRuleData";
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteAlertRule")
	public String delete(HttpServletRequest request, Model model) throws Exception {
		String id = request.getParameter("id");
		if (!StringUtils.isEmpty(id)) {
			entityService.logicRemove(Long.parseLong(id));
		}
		return "redirect:/yzt/alertRule/alertRuleQuery";
	}

	/**
	 * 获取气象因子列表
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getAllElement")
	@ResponseBody
	public void getAllMeteorological(HttpServletRequest request, HttpServletResponse response) {
		List<Elementinfo> list = elementinfoService.getAll();
		JSONArray json = new JSONArray();
		for (Elementinfo e : list) {
			JSONObject jo = new JSONObject();
			jo.put("id", e.getId());
			jo.put("text", e.getElementName());
			json.put(jo);
		}
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(json.toString()));
	}

	/**
	 * 新增保存和修改
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveOrUpdate")
	public void suerEdit(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Alertrule alertrule = bindEntity(request, entityClass);
		String elementIds = alertrule.getElementIds();
		String relative = alertrule.getRelative();
		String logic = alertrule.getLogic();
		String thresholdValue = alertrule.getThresholdValue();
		String[] meteorologicalIds = elementIds.split(",");
		HashSet<String> hashSet = new HashSet<String>();
		for (int i = 0; i < meteorologicalIds.length; i++) {// 判断气象因子是否重复
			hashSet.add(meteorologicalIds[i]);
		}
		if (hashSet.size() != meteorologicalIds.length) {
			printJson(response, com.alibaba.fastjson.JSON.toJSONString("该气象因子已存在！"));
			return;
		}
		String[] elements = elementIds.split(",");
		String[] values = thresholdValue.split(",");
		String[] logics = logic.split(",");
		String[] relatives = relative.split(",");
		String ruleEpr = "";
		for (int x = 0; x < elements.length; x++) {
			Elementinfo ele = elementinfoService.get(Long.parseLong(elements[x]));
			String eleId = ele.getElementId();
			if (x > 0) {
				ruleEpr += relatives[x - 1];
			}
			ruleEpr += eleId + logics[x] + values[x];
			
		}
		alertrule.setRuleExpr(ruleEpr);

		entityService.saveOrUpdate(alertrule);
	}

	/**
	 * 向编辑框传值
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "editAlertRule")
	public void edit(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Alertrule alert = entityService.get(Long.parseLong(id));
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(alert));
	}

}
