package cn.movinginfo.tztf.sem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.movinginfo.tztf.dd.domain.DisasterExample.Criteria;
import cn.movinginfo.tztf.sem.domain.Communication;
import cn.movinginfo.tztf.sem.domain.CommunicationExample;
import cn.movinginfo.tztf.sem.domain.Danger;
import cn.movinginfo.tztf.sem.domain.DangerExample;
import cn.movinginfo.tztf.sem.domain.EmergencyProduct;
import cn.movinginfo.tztf.sem.domain.EmergencyProductExample;
import cn.movinginfo.tztf.sem.domain.Expert;
import cn.movinginfo.tztf.sem.domain.ExpertExample;
import cn.movinginfo.tztf.sem.domain.ExpertLaLo;
import cn.movinginfo.tztf.sem.domain.ExpertLaLoExample;
import cn.movinginfo.tztf.sem.domain.Government;
import cn.movinginfo.tztf.sem.domain.GovernmentExample;
import cn.movinginfo.tztf.sem.domain.Medical;
import cn.movinginfo.tztf.sem.domain.MedicalExample;
import cn.movinginfo.tztf.sem.domain.Refuge;
import cn.movinginfo.tztf.sem.domain.RefugeExample;
import cn.movinginfo.tztf.sem.domain.RescueTeam;
import cn.movinginfo.tztf.sem.domain.RescueTeamExample;
import cn.movinginfo.tztf.sem.domain.Storage;
import cn.movinginfo.tztf.sem.domain.StorageExample;
import cn.movinginfo.tztf.sem.domain.Target;
import cn.movinginfo.tztf.sem.domain.TargetExample;
import cn.movinginfo.tztf.sem.domain.ThousandGovernment;
import cn.movinginfo.tztf.sem.domain.Transport;
import cn.movinginfo.tztf.sem.domain.TransportExample;
import cn.movinginfo.tztf.sem.mapper.CommunicationMapper;
import cn.movinginfo.tztf.sem.mapper.DangerMapper;
import cn.movinginfo.tztf.sem.mapper.EmergencyProductMapper;
import cn.movinginfo.tztf.sem.mapper.ExpertLaLoMapper;
import cn.movinginfo.tztf.sem.mapper.ExpertMapper;
import cn.movinginfo.tztf.sem.mapper.GovernmentMapper;
import cn.movinginfo.tztf.sem.mapper.MedicalMapper;
import cn.movinginfo.tztf.sem.mapper.RefugeMapper;
import cn.movinginfo.tztf.sem.mapper.RescueTeamMapper;
import cn.movinginfo.tztf.sem.mapper.StorageMapper;
import cn.movinginfo.tztf.sem.mapper.TargetMapper;
import cn.movinginfo.tztf.sem.mapper.ThousandGovernmentMapper;
import cn.movinginfo.tztf.sem.mapper.TransportMapper;

@Service
public class EmergencyService {
	
	@Autowired
	private CommunicationMapper semTcommunicationMapper;

	@Autowired
	private DangerMapper semTdangerMapper;
	
	@Autowired
	private EmergencyProductMapper semTEmergencyMappey;
	
	@Autowired
	private ExpertMapper semTExpertMapper;
	
	@Autowired
	private MedicalMapper semTMedicalMapper;
	
	@Autowired
	private RefugeMapper semTRefugeMapper;
	
	@Autowired
	private RescueTeamMapper semTSaveMapper;
	
	@Autowired
	private StorageMapper semTStorageMapper;
	
	@Autowired
	private TransportMapper semTTransportMapper;
	
	@Autowired
	private TargetMapper semTTargetMapper;
	
	@Autowired
	private GovernmentMapper governmentMapper;
	
	@Autowired
	private ThousandGovernmentMapper thousandGovernmentMapper;
	
	@Autowired
	private ExpertLaLoMapper expertLaLoMapper;
	
	public List<Communication> getSemTcommunications() {
		CommunicationExample example = new CommunicationExample();
		return semTcommunicationMapper.selectByExample(example);
	}
	
	public Communication findCommunicationById(Long id) {
		return semTcommunicationMapper.findCommunicationById(id);
	}

	public List<Danger> getSemTdangers() {
		DangerExample example = new DangerExample();
		return semTdangerMapper.selectByExample(example);
	}
	
	public Danger findDangerById(Long id) {
		return semTdangerMapper.findDangerById(id);
	}

	public List<EmergencyProduct> getSemTEmergency() {
		EmergencyProductExample example = new EmergencyProductExample();
		return semTEmergencyMappey.selectByExample(example);
	}
	
	public EmergencyProduct findProductById(Long id) {
		return semTEmergencyMappey.findProductById(id);
	}

	public List<Expert> getSemTExpert() {
		ExpertExample example = new ExpertExample();
		return semTExpertMapper.selectByExample(example);
	}
	
	

	public List<Medical> getSemTMedical() {
		MedicalExample example = new MedicalExample();
		return semTMedicalMapper.selectByExample(example);
	}
	
	public Medical findMedicalById(Long id) {
		return semTMedicalMapper.findMedicalById(id);
	}

	public List<Refuge> getSemTRefuge() {
		RefugeExample example = new RefugeExample();
		return semTRefugeMapper.selectByExample(example);
	}
	
	public Refuge findRefugeById(Long id) {
		return semTRefugeMapper.findRefugeById(id);
	}

	public List<RescueTeam> getSemTSave() {
		RescueTeamExample example = new RescueTeamExample();
		return semTSaveMapper.selectByExample(example);
	}
	
	public RescueTeam findRescueTeamById(Long id) {
		return semTSaveMapper.findRescueTeamById(id);
	}

	public List<Storage> getSemTStorage() {
		StorageExample example = new StorageExample();
		return semTStorageMapper.selectByExample(example);
	}
	
	public Storage findStorageById(Long id) {
		return semTStorageMapper.findStorageById(id);
	}

	public List<Transport> getSemTTransport() {
		TransportExample example = new TransportExample();
		return semTTransportMapper.selectByExample(example);
	}
	
	public Transport findTransportById(Long id) {
		return semTTransportMapper.findTransportById(id);
	}

	public List<Target> getSemTTarget() {
		TargetExample example = new TargetExample();
		return semTTargetMapper.selectByExample(example);
	}
	
	public Target findTargetById(Long id) {
		return semTTargetMapper.findTargetById(id);
	}

	public List<Government> getSemTVideo() {
		return governmentMapper.getOneThousandData();
	}
	
	public List<Government> getVideo(String lon1,String lat1,String lon2,String lat2 ) {
//		return governmentMapper.getVideos( lon1, lat1, lon2,lat2);
		GovernmentExample example  = new GovernmentExample();
		cn.movinginfo.tztf.sem.domain.GovernmentExample.Criteria criteria = example.createCriteria();
		criteria.andLatitudeGreaterThan(lat2).andLatitudeLessThan(lat1).andLongitudeGreaterThan(lon1).andLongitudeLessThan(lon2);
		return governmentMapper.selectByExample(example);
		
		
	}
	
	public Government findGovernmentById(Long id) {
		return governmentMapper.selectByPrimaryKey(id.intValue());
	}
	
	public List<Government> getThousandVideo() {
		GovernmentExample example = new GovernmentExample();
		example.createCriteria().andLongitudeNotEqualTo("0");
		return governmentMapper.selectByExample(example);
	}
	
	public ThousandGovernment findThousandGovernmentById(Long id) {
		return thousandGovernmentMapper.findThousandGovernmentById(id);
	}

	public List<ExpertLaLo> getSemTExpertLaLo() {
		ExpertLaLoExample example = new ExpertLaLoExample();
		return expertLaLoMapper.selectByExample(example);
	}
	
	public ExpertLaLo findExpertById(Long id) {
		return expertLaLoMapper.findExpertById(id);
	}

	public List<Refuge> townByreFuge(String town) {
		RefugeExample example = new RefugeExample();
		example.createCriteria().andAddressLike("%" + town + "%");
		return semTRefugeMapper.selectByExample(example);
	}
	
}







