package cn.movinginfo.tztf.sem.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.movinginfo.tztf.releasechannel.ChannelReactorImpl;
import cn.movinginfo.tztf.releasechannel.support.NationalEmergencySupport;
import cn.movinginfo.tztf.sem.domain.Communication;
import cn.movinginfo.tztf.sem.domain.Danger;
import cn.movinginfo.tztf.sem.domain.EmergencyProduct;
import cn.movinginfo.tztf.sem.domain.ExpertLaLo;
import cn.movinginfo.tztf.sem.domain.Government;
import cn.movinginfo.tztf.sem.domain.Medical;
import cn.movinginfo.tztf.sem.domain.Refuge;
import cn.movinginfo.tztf.sem.domain.RescueTeam;
import cn.movinginfo.tztf.sem.domain.Storage;
import cn.movinginfo.tztf.sem.domain.Target;
import cn.movinginfo.tztf.sem.domain.ThousandGovernment;
import cn.movinginfo.tztf.sem.domain.Transport;
import cn.movinginfo.tztf.sem.service.EmergencyService;
import cn.movinginfo.tztf.sev.domain.Alarm;
import cn.movinginfo.tztf.sev.service.AlarmService;


/**
 * @author yougq
 * 2018/10/17 19:09
 * 应急办相关材料控制层
 *
 */
@Controller
@RequestMapping("/sem/emergency")
public class EmergencyController {

	@Autowired
	private EmergencyService emergencyService;
	
	@Autowired
	private AlarmService alarmService;
	
	@Autowired
	private ChannelReactorImpl channelReactor;
	/**
	 * 通讯保障列表
	 * @return
	 */
	@RequestMapping("communction")
	@ResponseBody
	public  List<Communication> getCommunction(){
		List<Communication> result = emergencyService.getSemTcommunications();
		for(Communication com : result) {
			com.setDataType("contact");
		}
		return result;
		
	}
	@RequestMapping("findCommunction")
	@ResponseBody
	public Communication findCommunction(Long id) {
		return emergencyService.findCommunicationById(id);
	}
	
	/**
	 * 危险源列表1545
	 * @return
	 */
	@RequestMapping("danger")
	@ResponseBody
	public  List<Danger> getDanger(){
		List<Danger> result = emergencyService.getSemTdangers();
		for(Danger da : result) {
			da.setDataType("danger");
		}
		return result;
	}
	@RequestMapping("findDanger")
	@ResponseBody
	public Danger findDanger(Long id) {
		return emergencyService.findDangerById(id);
	}
	
	
	/**
	 * 应急物资列表
	 * @return
	 */
	@RequestMapping("product")
	@ResponseBody
	public  List<EmergencyProduct> getEemergency(){
		List<EmergencyProduct> result = emergencyService.getSemTEmergency();
		for(EmergencyProduct em : result) {
			  em.setDataType("stuff");
		}
		return result;
	}
	@RequestMapping("findProduct")
	@ResponseBody
	public EmergencyProduct findProduct(Long id) {
		return emergencyService.findProductById(id);
	}
	
	/**
	 * 专家列表
	 * @return
	 */
	@RequestMapping("export")
	@ResponseBody
	public  List<ExpertLaLo> getSemTExpert(){
		List<ExpertLaLo> result = emergencyService.getSemTExpertLaLo();
		for(ExpertLaLo ex : result) {
			ex.setDataType("professional");
		}
		return result;
	}
	@RequestMapping("findExport")
	@ResponseBody
	public ExpertLaLo findExpertById(Long id) {
		ExpertLaLo expert = emergencyService.findExpertById(id);
		String name = expert.getName();
		expert.setChargeMan(name);
		String address = expert.getWorkCompany();
		expert.setAddress(address);
		return expert;
	}
	
	/**
	 * 医疗卫生表
	 * @return
	 */
	@RequestMapping("medic")
	@ResponseBody
	public  List<Medical> getSemTMedical(){
		List<Medical> result = emergencyService.getSemTMedical();
		for(Medical me : result) {
			me.setDataType("hygiene");
		}
		return result;
	}
	@RequestMapping("findMedic")
	@ResponseBody
	public Medical findMedic(Long id) {
		return emergencyService.findMedicalById(id);
	}
	
	
	/**
	 * 17
	 * 避难场所表 3856
	 * @return 
	 */
	@RequestMapping("refuge")
	@ResponseBody
	public  List<Refuge> getSemTRefuge(){
		List<Refuge> result = emergencyService.getSemTRefuge();
		for(Refuge re : result) {
			re.setDataType("shelter");
		}
		return result;
	}
	@RequestMapping("findRefuge")
	@ResponseBody
	public Refuge findRefuge(Long id) {
		return emergencyService.findRefugeById(id);
	}
	
	
	/**
	 * 救援队伍列表
	 * @return
	 */
	@RequestMapping("save")
	@ResponseBody
	public  List<RescueTeam> getSemTSave(){
		List<RescueTeam> result = emergencyService.getSemTSave();
		for(RescueTeam re : result) {
			re.setDataType("save");
		}
		return result;
	}
	@RequestMapping("findSave")
	@ResponseBody
	public RescueTeam findSave(Long id) {
		return emergencyService.findRescueTeamById(id);
	}
	
	
	/**
	 * 60
	 * 储备库列表137
	 * @return
	 */
	@RequestMapping("storage")
	@ResponseBody
	public  List<Storage> getSemTStorage(){
		List<Storage> result = emergencyService.getSemTStorage();
		for(Storage st : result) {
			st.setDataType("storage");
		}
		return result;
	}
	@RequestMapping("findStorage")
	@ResponseBody
	public Storage findStorage(Long id) {
		Storage sto = emergencyService.findStorageById(id);
		String name = sto.getPersonName();
		sto.setChargeMan(name);
		return sto;
	}
	
	
	/**
	 * 5
	 * 防护目标列表2513
	 * @return
	 */
	@RequestMapping("target")
	@ResponseBody
	public  List<Target> getSemTTarget(){
		List<Target> result = emergencyService.getSemTTarget();
		for(Target ta : result) {
			ta.setDataType("protect-target");
		}
		return result;
	}
	@RequestMapping("findTarget")
	@ResponseBody
	public Target findTarget(Long id) {
		return emergencyService.findTargetById(id);
	}
	
	
	/**
	 * 运输保障列表
	 * @return
	 */
	@RequestMapping("transport")
	@ResponseBody
	public  List<Transport> getSemTTransport(){
		List<Transport> result = emergencyService.getSemTTransport();
		for(Transport tr : result) {
			tr.setDataType("transform");
		}
		return result;
	}
	@RequestMapping("findTransport")
	@ResponseBody
	public Transport findTransport(Long id) {
		return emergencyService.findTransportById(id);
	}
	
	
	
	/**
	 * 视频监控列表
	 * @return
	 */
	@RequestMapping("video")
	@ResponseBody
	public  List<Government> getSemTVideo(String lonlat1 ,String lonlat2){
		String[] lonlatOne = lonlat1.split(",");
		String[] lonlatTwo = lonlat2.split(",");
		String lon1 = lonlatOne[0].substring(4);
		String lat1 = lonlatOne[1].substring(4);
		String lon2 = lonlatTwo[0].substring(4);
		String lat2 = lonlatTwo[1].substring(4);
		List<Government> result = emergencyService.getVideo(lon1,lat1,lon2,lat2);
		for(Government go : result) {
			go.setDataType("video-monitor");
		}

		return result;
	}
	
	
	@RequestMapping("findVideo")
	@ResponseBody
	public Government findGovernment(Long id) {
		return emergencyService.findGovernmentById(id);
	}
	
	@RequestMapping("thousandVideo")
	@ResponseBody
	public  List<Government> getThousandVideo(){
		List<Government> result = emergencyService.getThousandVideo();
		for(Government th : result) {
			th.setDataType("video-monitor");
		}
		return result;
	}
	
	@RequestMapping("findThousandVideo")
	@ResponseBody
	public ThousandGovernment findThousandGovernmentById(Long id) {
		return emergencyService.findThousandGovernmentById(id);
	}
	
	/**
	 * @param town  街道名
	 * @return
	 * 根据街道名返回避难场所
	 */
	@RequestMapping("townbyrefuge")
	@ResponseBody
	public List<Refuge> townByreFuge(String town){
		return emergencyService.townByreFuge(town);
	}
	
	
	/**
	 * @param id      主键
	 * @return
	 * 避难场所详细信息
	 */
	@RequestMapping("getrefugebyid")
	@ResponseBody
	public Refuge getrefugebyid(Long id){
		return emergencyService.findRefugeById(id);
	}
	
	/**
	 * @param id    主键
	 * @return
	 * 运输保障列表详细信息
	 */
	@RequestMapping("gettransportbyid")
	@ResponseBody
	public Transport gettransportbyid(Long id){
		return emergencyService.findTransportById(id);
	}
	
	
	/**
	 * @param id    主键
	 * @return
	 * 物资保障详细信息
	 */
	@RequestMapping("getproductbyid")
	@ResponseBody
	public EmergencyProduct getproductbyid(Long id){
		return emergencyService.findProductById(id);
	}
	
	
	
	// danger 危险源列表1545   refuge 避难场所表 3856   target  防护目标列表2513  storage  储备库列表137
	
	@RequestMapping(value = "/saveAlarmData",method = RequestMethod.POST)
	@ResponseBody
	public void saveAlarmData(@RequestBody Alarm a) throws ParseException{
		if(a.getStatus().equals("fb")) {
			     a.setOperationAction(0);
		         a.setVersion(0);
		         a.setPubState("5");
		         a.setPid(Long.parseLong("1"));
		}else if(a.getStatus().equals("gx")) {
			     Alarm oldAlarm = alarmService.getAlarmByPubNo(a.getPubNo());
			     if(oldAlarm != null) {
			     oldAlarm.setValid(0);
			     oldAlarm.setPubState("6");
			     alarmService.saveOrUpdate(oldAlarm);
			     }
			     a.setPubState("5");
			     a.setVersion(2);
			     a.setOperationAction(2);
			     a.setPid(oldAlarm.getId());
		   }else if(a.getStatus().equals("jc")) {
			   Alarm oldAlarm = alarmService.getAlarmByPubNo(a.getPubNo());
			   if(oldAlarm != null) {
				     oldAlarm.setValid(0);
				     oldAlarm.setPubState("6");
				     alarmService.saveOrUpdate(oldAlarm);
				     }
			     a.setVersion(1);
			     a.setOperationAction(2);
			     a.setPubState("12");
			     a.setPid(oldAlarm.getId());
		   }
		     a.setDeptId(Long.parseLong("1"));
		     a.setValid(1);
		     a.setKettle("1");
		     String time = a.getPubTime();
		     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		     Date d = sdf.parse(time);
		     a.setCreateDate(new Date());
		     a.setCreator(Long.parseLong("46"));
		     NationalEmergencySupport support = new NationalEmergencySupport();
			 String msgId = support.makeMsgId(a.getCreator(), a.getCreateDate());
		     a.setMsgId(msgId);
		     a.setPubDate(d);
		     alarmService.saveAlarmData(a);
		     Alarm nextAlarm = alarmService.passVersionAndCode(a.getVersion(), a.getPubNo());
		     if(nextAlarm != null) {
				if (nextAlarm.getType().equals("alarm")) {
					nextAlarm.setAuditDate(new Date());
					nextAlarm.setPubState("1");
					channelReactor.doRelease(nextAlarm.getChannelInstances(), nextAlarm);
					nextAlarm.setPubState("5");
					nextAlarm.setReleaseDate(new Date());
					nextAlarm.setCompleteDate(new Date());
					alarmService.saveOrUpdate(nextAlarm);
				}
		     }
	}
}





