package cn.movinginfo.tztf.sen.action;



import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.sen.domain.AreaAlarm;
import cn.movinginfo.tztf.sen.domain.DangerPoint;
import cn.movinginfo.tztf.sen.domain.Tabstation;
import cn.movinginfo.tztf.sen.service.AreaalarmService;
import cn.movinginfo.tztf.sen.service.DangerPointService;
import cn.movinginfo.tztf.sen.service.TabstationService;
import cn.movinginfo.tztf.sys.utils.json.DictFilter;
import net.ryian.commons.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
*
* @description:Tabstation action
* @author: autoCode
* @history:
*/
@Controller
@RequestMapping("/yzt/tabStation")
@SuppressWarnings("serial")
public class TabstationAction extends MagicAction<Tabstation,TabstationService> {
	
	@Autowired
	private AreaalarmService areaalarmService;
	
	@Autowired
	private DangerPointService dangerPointService;
	
	/**
	 * 自动站管理页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("tabStationQuery")
	public String indexTabStationinfoQuery(HttpServletRequest request, Model model){
		model.addAttribute("currentPage", 1);
		model.addAttribute("pageSize", 10);
		return "/yzt/tabStation/tabStationQuery";
	}
	
	/**
	 * 获取列表数据
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/tabStationData")
	public String tabStationData(HttpServletRequest request, Model model)
			throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map=getParameterMap(request);
		PageInfo<?> pageInfo = entityService.queryPaged(map);
		JSONObject page = (JSONObject) JSONObject.parse(JSONObject
				.toJSONString(pageInfo, new DictFilter())); 
		model.addAttribute("page", page);
		 return getNameSpace() + "tabStationData";
	}
	
	
	/**
	 * 向编辑下拉框传值
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "editTabStation")
	public void editTabStation(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Tabstation tab = entityService.get(Long.parseLong(id));
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(tab));
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
		Tabstation tabStationinfo = bindEntity(request, entityClass);
		String iiiii = tabStationinfo.getIiiii();
		Tabstation tab = entityService.findTabStationinfoByIiiii(iiiii);
		if( tabStationinfo.getId()== null) {
			if(tab != null) {
				printJson(response, com.alibaba.fastjson.JSON.toJSONString("该站号已存在!"));
				return;
			}
		}else {
			if(tab != null &&!tab.getId().equals(tabStationinfo.getId()) ) {
				printJson(response, com.alibaba.fastjson.JSON.toJSONString("该站号已存在!"));
				return;
			}
		}
		entityService.saveOrUpdate(tabStationinfo);
	}
	
	/**
	 * 删除
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteTabStation")
	public String delete(HttpServletRequest request, Model model) throws Exception {
		String id = request.getParameter("id");
		if (!StringUtils.isEmpty(id)) {
			try {
				entityService.logicRemove(Long.parseLong(id));
				List<AreaAlarm> areaList = areaalarmService.getAreaAlarmByRelStation(id);//获取区域报警的有关该站点的数据
				for(AreaAlarm area :areaList) {
					String relStation = area.getRelative();
					String nowRelStation = (","+relStation+",").replaceAll(","+id+",", ",");
					String regex = "^,*|,*$";//规则字符串前后去逗号
					String str = nowRelStation.replaceAll(regex, "");
					area.setRelStation(str);
					areaalarmService.update(area);
				}
				List<DangerPoint> pointList = dangerPointService.getDangerPointByRelStation(id);//获取隐患点有关站点的数据
				for(DangerPoint point :pointList) {
					String relStation = point.getRelative();
					String nowRelStation = (","+relStation+",").replaceAll(","+id+",", ",");
					String regex = "^,*|,*$";//规则字符串前后去逗号
					String str = nowRelStation.replaceAll(regex, "");
					point.setRelStation(str);
					dangerPointService.update(point);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		return "redirect:/yzt/tabStation/tabStationQuery";
	}


}
