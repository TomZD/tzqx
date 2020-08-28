package cn.movinginfo.tztf.sev.domain;
import org.springframework.stereotype.Component;

import cn.movinginfo.tztf.common.constants.DictKeys;
import cn.movinginfo.tztf.sys.utils.json.Dict;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.Set;


/**
* @description:
* @author: autoCode
* @history:
*/
@Table(name = "sev_t_alarm")
//public class Alarm extends BaseDomain {
public class Alarm extends MiddleObject {

    private static final long serialVersionUID = 1L;
    
   @Dict(key = DictKeys.AREA_ID)
    @Column(name="area_id")
    private String areaId;
    
    
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	
	//渠道id
	@Transient
	private String channelId;

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	//经纬度
	@Transient
	private String lonLat;
	
	@Transient
	private String centrePoint;
	
	@Transient
	private String lon;
	
	@Transient
	private String lat;
	
	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getCentrePoint() {
		return centrePoint;
	}

	public void setCentrePoint(String centrePoint) {
		this.centrePoint = centrePoint;
	}

	public String getLonLat() {
		return lonLat;
	}

	public void setLonLat(String lonLat) {
		this.lonLat = lonLat;
	}

	//发布状态
	@Transient
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	// 部门ID
	@Column(name = "dept_id")
	private Long deptId;

	// 发布编号
	@Column(name = "pub_no")
	private String pubNo;
	
	@Column(name = "kettle")
	private String kettle;
	
	@Column(name = "alarm_id")
	private Long alarmId;

	//是否转发0：待转发 1：转发
	@Column(name = "is_relay")
	private Integer isRelay;

	//反馈信息
	@Column(name = "message")
	private String message;

	@Column(name = "town_id")
	private String townId;

	public String getTownId() {
		return townId;
	}

	public void setTownId(String townId) {
		this.townId = townId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getIsRelay() {
		return isRelay;
	}

	public void setIsRelay(Integer isRelay) {
		this.isRelay = isRelay;
	}

	public Long getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(Long alarmId) {
		this.alarmId = alarmId;
	}

	public String getKettle() {
		return kettle;
	}

	public void setKettle(String kettle) {
		this.kettle = kettle;
	}
	@Column(name = "sms_group")
	private String smsGroup;
	
	@Column(name = "depart_fax")
	private String departFax;
		
	

	public String getDepartFax() {
		return departFax;
	}

	public void setDepartFax(String departFax) {
		this.departFax = departFax;
	}

	public String getSmsGroup() {
		return smsGroup;
	}

	public void setSmsGroup(String smsGroup) {
		this.smsGroup = smsGroup;
	}

	// 预警标题
	@Column(name = "title")
	private String title;

	@Column(name = "pub_date")
	private Date pubDate;

	// 发布范围
	@Column(name = "pub_range")
	private String pubRange;
	
	@Column(name = "points")
	private String points;
	// 发布范围名称
	@Column(name = "pub_range_name")
	private String pubRangeName;

	// 预警内容
	@Column(name = "content")
	private String content;

	@Column(name = "is_feedback")
	private Integer isFeedback;
	
	@Column(name="alarm_type")
	private Integer alarmType;
	
	public Integer getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(Integer alarmType) {
		this.alarmType = alarmType;
	}

	public Integer getIsFeedback() {
		return isFeedback;
	}

	public void setIsFeedback(Integer isFeedback) {
		this.isFeedback = isFeedback;
	}

	//	// 审核原因
//	@Column(name = "check_content")
//	private String checkContent;

	// 发布状态
	@Dict(key = DictKeys.PUB_STATE)
	@Column(name = "pub_state")
	private String pubState;
    
	@Transient
	private String pubTime;

	@Override
	public Date getPubDate() {
		return pubDate;
	}

	@Override
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public String getPubTime() {
		return pubTime;
	}

	public void setPubTime(String pubTime) {
		this.pubTime = pubTime;
	}
	// 发布方式（钉钉/微信/邮箱）
	@Column(name = "publishTypeName")
	private String publishTypeName;

	//审核时间
	@Column(name = "audit_date")
	private java.util.Date auditDate;
	
	public String getPublishTypeName() {
		return publishTypeName;
	}

	public void setPublishTypeName(String publishTypeName) {
		this.publishTypeName = publishTypeName;
	}



	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}



	//渠道时间
	@Column(name = "release_date")
	private java.util.Date releaseDate;
	
	//完成时间
	@Column(name = "complete_date")
	private java.util.Date completeDate;
	
	//解除时间
	@Column(name = "cancel_date")
	private java.util.Date cancelDate;
	
//	//版本
//	@Column(name = "version")
//	private Integer version;
	
	@Transient
    private String time;
	
	@Transient
    private String deptName;
	
	public String getRealTime() {
		return realTime;
	}

	public void setRealTime(String realTime) {
		this.realTime = realTime;
	}



	@Transient
    private String num;
	
	@Transient
    private String realTime;
    
	@Transient
    private String reallyTime;
    
	public String getReallyTime() {
		return reallyTime;
	}

	public void setReallyTime(String reallyTime) {
		this.reallyTime = reallyTime;
	}



	@Column(name = "LAST_ACTION")
	private Integer operationAction;
	
	// 发布渠道
	@Column(name = "pub_channel")
	private String pubChannel;

	// 图片路径
	@Column(name = "image_path")
	private String imagePath;

	 //预警信息名称
	@Column(name = "alarm_type_name")
	private String alarmTypeName;
	
	// 类型，区别事件还是预警
	@Column(name = "type")
	private String type;
	
	// 预警信息名称
	@Column(name = "type_id")
	private String typeId;
	
	//事件专用版本
	@Column(name = "pid")
	private Long pid;
	
	//发布人
	@Column(name = "publisher")
	private String publisher;
	
	//签发人
	@Column(name = "issuer")
	private String issuer;
	//是否发送
	@Column(name = "is_send")
	private Integer isSend;
	//通知单的文件路径
	@Column(name = "notice_file")
	private String noticeFile;
	//发送时间
	@Column(name = "send_date")
	private Date sendDate;
	/**
     * 图片路径
     */
    @Column(name="images")
    private String images;

    @Transient
	private Integer relay;

    @Transient
	private Integer feedback;

    @Transient
	private String msg;

	public Integer getRelay() {
		return relay;
	}

	public void setRelay(Integer relay) {
		this.relay = relay;
	}

	public Integer getFeedback() {
		return feedback;
	}

	public void setFeedback(Integer feedback) {
		this.feedback = feedback;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Transient
    private String alarmContent;
    
	public String getAlarmContent() {
		return alarmContent;
	}

	public void setAlarmContent(String alarmContent) {
		this.alarmContent = alarmContent;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	


	public String getAlarmTypeName() {
		return alarmTypeName;
	}

	public void setAlarmTypeName(String alarmTypeName) {
		this.alarmTypeName = alarmTypeName;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public void setPubNo(String pubNo){
        this.pubNo = pubNo;
    }

    public String getPubNo(){
        return pubNo;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setPubRange(String pubRange){
        this.pubRange = pubRange;
    }

    public String getPubRange(){
        return pubRange;
    }

    /**
	 * @return the pubRangeName
	 */
	public String getPubRangeName() {
		return pubRangeName;
	}

	/**
	 * @param pubRangeName the pubRangeName to set
	 */
	public void setPubRangeName(String pubRangeName) {
		this.pubRangeName = pubRangeName;
	}

	public void setContent(String content){
        this.content = content;
    }

    public String getContent(){
        return content;
    }

    public void setPubState(String pubState){
        this.pubState = pubState;
    }

    public String getPubState(){
        return pubState;
    }
    

    

   

    public String getPubChannel() {
		return pubChannel;
	}

	public void setPubChannel(String pubChannel) {
		this.pubChannel = pubChannel;
	}

	public void setImagePath(String imagePath){
        this.imagePath = imagePath;
    }

    public String getImagePath(){
        return imagePath;
    }

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public java.util.Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(java.util.Date cancelDate) {
		this.cancelDate = cancelDate;
	}

//	public String getCheckContent() {
//		return checkContent;
//	}
//	public void setCheckContent(String checkContent) {
//		this.checkContent = checkContent;
//	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	public java.util.Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(java.util.Date auditDate) {
		this.auditDate = auditDate;
	}

	public java.util.Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(java.util.Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public java.util.Date getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(java.util.Date completeDate) {
		this.completeDate = completeDate;
	}
	
	
    
	public Integer getOperationAction() {
		return operationAction;
	}

	public void setOperationAction(Integer operationAction) {
		this.operationAction = operationAction;
	}



	public Integer getIsSend() {
		return isSend;
	}

	public void setIsSend(Integer isSend) {
		this.isSend = isSend;
	}

	public String getNoticeFile() {
		return noticeFile;
	}

	public void setNoticeFile(String noticeFile) {
		this.noticeFile = noticeFile;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	
	



	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}



	// 发布渠道内容
	@Transient
	private Set<ReleaseChannelInstance> channelInstances;
	public Set<ReleaseChannelInstance> getChannelInstances() {
		return channelInstances;
	}
	public void setChannelInstances(Set<ReleaseChannelInstance> channelInstances) {
		this.channelInstances = channelInstances;
	}
	
}
