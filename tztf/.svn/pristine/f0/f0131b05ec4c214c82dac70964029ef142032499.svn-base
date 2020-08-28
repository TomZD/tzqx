package cn.movinginfo.tztf.dd.action;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.movinginfo.tztf.common.action.BaseMagicAction;
import cn.movinginfo.tztf.dd.domain.Disaster;
import cn.movinginfo.tztf.dd.service.DisasterService;
import cn.movinginfo.tztf.sev.domain.Alarm;
import cn.movinginfo.tztf.sev.domain.ReleaseChannelInstance;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.domain.ReleaseChannel;
import cn.movinginfo.tztf.sys.domain.User;
import cn.movinginfo.tztf.sys.service.DeptService;
import cn.movinginfo.tztf.sys.service.DictService;
import cn.movinginfo.tztf.sys.service.UserService;

@Controller
@RequestMapping("/dd/disaCheck")
public class DisaCheckAction extends BaseMagicAction{
	
	@Autowired
	private DisasterService disasterService;
	@Autowired
	private UserService userService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private DictService dictService;
	
	
	@RequestMapping(value = "check")
	public String check(HttpServletRequest request, Model model) throws Exception {
		User user = userService.get(getCurrentUser().id);
		Long departId = user.getDepartmentId();
		Depart depart = deptService.findById(departId);
		String areaId = depart.getAreaId();
		//待办
		List<Disaster> listDisaster = disasterService.getListByAreaId(areaId);
		for (int i = 0; i < listDisaster.size(); i++) {
			Depart dept = deptService.get(listDisaster.get(i).getDeptId());
			//listDisaster.get(i).setDeptName(dept.getAddress());
		}
		// 审核通过
		List<Disaster> listNowDisaster = disasterService.getListIsPublishing(areaId);
		model.addAttribute("listNowDisaster", listNowDisaster);
		// 待办信息
		model.addAttribute("listDisaster", listDisaster);
			
	
		String deptNameS = "";
		Depart dept = deptService.get(departId);
		if(dept.getName().indexOf("应急办")>-1){
			deptNameS="yjb";
		}
		model.addAttribute("deptName", deptNameS);
		return "/dd/disaCheck/check";
	}
	
	
	//审核界面
		@RequestMapping(value = "doCheck")
		public String doCheck(HttpServletRequest request, Model model, String id) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Disaster disaster = disasterService.get(Long.parseLong(id));
				model.addAttribute("disaster", disaster);
				if(disaster.getImagePath()==null||"".equals(disaster.getImagePath())){
					
				}else{
					model.addAttribute("images", disaster.getImagePath().split(","));
				}
				model.addAttribute("id", id);
				
			
				
				return  "/dd/disaCheck/doCheck";
		}
		

		//灾情审核通过
		
		@RequestMapping(value = "confirm",method = RequestMethod.POST)
		public void confirm(HttpServletRequest request, HttpServletResponse response) {
			try {
				String id = request.getParameter("id");
				Disaster disaster = disasterService.get(Long.parseLong(id));
				disaster.setPubState("1");
				disaster.setAuditDate(new Date());
				//disaster.setReleaseDate(new Date());
				disaster.setCompleteDate(new Date());
				disasterService.saveOrUpdate(disaster);
				printJson(response, messageSuccuseWrap());
			} catch (Exception e) {
				logger.error("save", e);
				printJson(response, messageFailureWrap("发布失败！"));
			}
		}
		
		//灾情审核不通过

		@RequestMapping(value = "noConfirm",method = RequestMethod.POST)
		public void noConfirm(HttpServletRequest request, HttpServletResponse response) {
			try {
				String id = request.getParameter("id");
				Disaster disaster = disasterService.get(Long.parseLong(id));
				disaster.setPubState("2");
				disaster.setCheckContent(request.getParameter("checkContent"));
				disaster.setAuditDate(new Date());
				disasterService.saveOrUpdate(disaster);
				printJson(response, messageSuccuseWrap());
			} catch (Exception e) {
				logger.error("save", e);
				printJson(response, messageFailureWrap("发布失败！"));
			}
		}

}
