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

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.sen.domain.Alertrule;
import cn.movinginfo.tztf.sen.domain.AreaAlarm;
import cn.movinginfo.tztf.sen.domain.Elementinfo;
import cn.movinginfo.tztf.sen.domain.Tabstation;
import cn.movinginfo.tztf.sen.service.AlertruleService;
import cn.movinginfo.tztf.sen.service.AreaalarmService;
import cn.movinginfo.tztf.sen.service.ElementinfoService;
import cn.movinginfo.tztf.sen.service.TabstationService;
import cn.movinginfo.tztf.sys.utils.json.DictFilter;
import net.ryian.commons.StringUtils;

/**
 * @description:Areaalarm action
 * @author: autoCode
 * @history:
 */
@Controller
@RequestMapping("/yzt/areaAlarm")
@SuppressWarnings("serial")
public class AreaalarmAction extends MagicAction<AreaAlarm, AreaalarmService> {

	@Autowired
	private AlertruleService alertruleService;

	@Autowired
	private ElementinfoService elementinfoService;

	@Autowired
	private TabstationService tabstationService;
	
	/**
	 * 区域报警管理页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("areaAlarmQuery")
	public String areaAlarmQuery(HttpServletRequest request, Model model){
		model.addAttribute("currentPage", 1);
		model.addAttribute("pageSize", 10);
		return "/yzt/areaAlarm/areaAlarmQuery";
	}

	/**
	 * 获取列表数据
	 *
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/areaAlarmData")
	public String areaAlarmData(HttpServletRequest request, Model model) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map = getParameterMap(request);
		PageInfo<?> pageInfo = entityService.queryPaged(map);
		JSONObject page = (JSONObject) JSONObject.parse(JSONObject.toJSONString(pageInfo, new DictFilter()));
		model.addAttribute("page", page);
		return getNameSpace() + "areaAlarmData";
	}

	/**
	 * 获取气象因子列表
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getAllMeteorological")
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
	 * 获取所有的站点
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getRelStation")
	public void getRelStation(HttpServletRequest request, HttpServletResponse response) {
		List<Tabstation> list = tabstationService.getAll();
		JSONArray json = new JSONArray();
		for (Tabstation tab : list) {
			JSONObject jo = new JSONObject();
			jo.put("id", tab.getId());
			jo.put("text", tab.getIiiii());
			json.put(jo);
		}
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(json.toString()));
	}

	/**
	 * 向编辑框传值
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "editAreaAlarm")
	public void edit(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		AreaAlarm areaAlarm = entityService.get(Long.parseLong(id));
		String alertId = areaAlarm.getAlertruleId();
		Alertrule alert = alertruleService.get(Long.parseLong(alertId));
		String logic = alert.getLogic();
		String thresholdValue = alert.getThresholdValue();
		String relative = alert.getRelative();
		String ElementIds = alert.getElementIds();

		String thresholdValueTwo = "0";
		String relativeTwo = "0";
		String ElementIdsTwo = "0";
		String logicTwo = "0";
		String alertTwoId = areaAlarm.getAlertruleTwoId();
		if (!StringUtils.isEmpty(alertTwoId)) {
			Alertrule alertTwo = alertruleService.get(Long.parseLong(alertTwoId));
			thresholdValueTwo = alertTwo.getThresholdValue();
			relativeTwo = alertTwo.getRelative();
			ElementIdsTwo = alertTwo.getElementIds();
			logicTwo = alertTwo.getLogic();
		}
		String thresholdValueThree = "0";
		String relativeThree = "0";
		String ElementIdsThree = "0";
		String logicThree = "0";
		String alertThreeId = areaAlarm.getAlertruleThreeId();
		if (!StringUtils.isEmpty(alertThreeId)) {
			Alertrule alertThree = alertruleService.get(Long.parseLong(alertThreeId));
			thresholdValueThree = alertThree.getThresholdValue();
			relativeThree = alertThree.getRelative();
			ElementIdsThree = alertThree.getElementIds();
			logicThree = alertThree.getLogic();
		}
		areaAlarm.setLogic(logic + "@" + logicTwo + "@" + logicThree);
		areaAlarm.setThresholdValue(thresholdValue + "@" + thresholdValueTwo + "@" + thresholdValueThree);
		areaAlarm.setRelative(relative + "@" + relativeTwo + "@" + relativeThree);
		areaAlarm.setMeteorologicalId(ElementIds + "@" + ElementIdsTwo + "@" + ElementIdsThree);
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(areaAlarm));
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
	public void suerEdit(HttpServletRequest request, HttpServletResponse response, Model model, String logic,
			String thresholdValue, String meteorologicalId) throws Exception {
		AreaAlarm areaAlarm = bindEntity(request, entityClass);
		String[] meIds = meteorologicalId.split("@");
		String[] vals = thresholdValue.split("@");
		String[] logs = logic.split("@");
		String[] rels = areaAlarm.getRelative().split("@");
		for (String me : meIds) {
			String[] meteorologicalIds = me.split(",");
			HashSet<String> hashSet = new HashSet<String>();
			for (int i = 0; i < meteorologicalIds.length; i++) {// 判断气象因子是否重复
				hashSet.add(meteorologicalIds[i]);
			}
			if (hashSet.size() != meteorologicalIds.length) {
				printJson(response, com.alibaba.fastjson.JSON.toJSONString("同一阈值条件不能存在相同的气象因子!"));
				return;
			}
		}
		Alertrule alert = null;
		String alertId = null;
		for (int i = 0; i < meIds.length; i++) {
			if (areaAlarm.getId() != null) {
				AreaAlarm area = entityService.get(areaAlarm.getId());
				if (i == 0) {
					if (!StringUtils.isEmpty(area.getAlertruleId())) {
						alert = alertruleService.get(Long.parseLong(area.getAlertruleId()));
					} else {
						alert = new Alertrule();
						alert.setAlertLevel(i + 1);
					}
				} else if (i == 1) {
					if (!StringUtils.isEmpty(area.getAlertruleTwoId())) {
						alert = alertruleService.get(Long.parseLong(area.getAlertruleTwoId()));
					} else {
						alert = new Alertrule();
						alert.setAlertLevel(i + 1);
					}
				} else if (i == 2) {
					if (!StringUtils.isEmpty(area.getAlertruleThreeId())) {
						alert = alertruleService.get(Long.parseLong(area.getAlertruleThreeId()));
					} else {
						alert = new Alertrule();
						alert.setAlertLevel(i + 1);
					}

				}

			} else {
				alert = new Alertrule();
				alert.setAlertLevel(i + 1);
			}

			if (meIds[i].equals("0")) {
				alertId = "";
			} else {
				String[] elements = meIds[i].split(",");
				String[] values = vals[i].split(",");
				String[] logics = logs[i].split(",");
				String[] relatives = rels[i].split(",");
				alert.setLogic(logs[i]);
				alert.setElementIds(meIds[i]);
				alert.setThresholdValue(vals[i]);
				alert.setRelative(rels[i]);
				alert.setType("区域");
				String ruleEpr = "";
				for (int x = 0; x < elements.length; x++) {
					Elementinfo ele = elementinfoService.get(Long.parseLong(elements[x]));
					String eleId = ele.getElementId();
					if (x > 0) {
						ruleEpr += relatives[x - 1];
					}
					ruleEpr += eleId + logics[x] + values[x];

				}
				alert.setRuleExpr(ruleEpr);
				alertId = String.valueOf(alertruleService.saveOrUpdate(alert));
			}

				if (i == 0) {
					areaAlarm.setAlertruleId(String.valueOf(alertId));
				} else if (i == 1) {
					areaAlarm.setAlertruleTwoId(String.valueOf(alertId));
				} else if (i == 2) {
					areaAlarm.setAlertruleThreeId(String.valueOf(alertId));
				}
		}

		try {
			if (areaAlarm.getId() == null) {
				entityService.insertSelective(areaAlarm);
			} else {
				entityService.update(areaAlarm);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// alertruleService.delete(Long.parseLong(alertId));

		}

	}

	/**
	 * 删除
	 *
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteAreaAlarm")
	public String delete(HttpServletRequest request, Model model) throws Exception {
		String id = request.getParameter("id");
		if (!StringUtils.isEmpty(id)) {
			entityService.logicRemove(Long.parseLong(id));
		}
		return "redirect:/yzt/areaAlarm/areaAlarmQuery";
	}

}
