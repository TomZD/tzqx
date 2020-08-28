package cn.movinginfo.tztf.sev.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import cn.movinginfo.tztf.common.constants.DictKeys;
import cn.movinginfo.tztf.common.domain.BaseDomain;
import cn.movinginfo.tztf.sys.utils.json.Dict;

@SuppressWarnings("serial")
@MappedSuperclass
public class MiddleObject extends BaseDomain {

//    @Column(name = "lot")
//	protected Double lot; // 经度
//	
//	@Column(name = "lat")
//    protected Double lat; // 纬度
	
//	@Column(name = "TASK_STATE")
//	private String taskState; // 由任务驱动的状态，与其他状态都无关
//	
//	@Column(name = "TASK_STATE_DETAIL")
//	private String taskStateDetail; // 任务状态明细，由任务驱动的状态，与其他状态都无关（新添加）

//	@Dict(key = DictKeys.PUB_STATE)
//	@Column(name = "PUBLISH_STATE")
//	private String publishState; // 发布状态
	@Column(name = "points")
	private String points;
	
	@Column(name = "check_content")
	private String checkContent; // 审核意见、审核不通过的原因
    
	@Column(name = "NUMBER")
	private String number;
    
//	@Column(name = "VERSION")
	@Column(name = "version")
	private Integer version; // 版本
    
	@Column(name="MSG_ID")
	private String msgId;
	@Column(name="pub_date")
	private Date pubDate;
	
	
	
	@Column(name="title")
	private String title;
	
	
	
	@Transient
	private String refMsgId;//解除相关原来的MsgId
	
	
	@Dict(key = DictKeys.DURATION)
	@Column(name = "duration")
	private String duration;


  
	@Column(name = "alarm_type_name")
	private String alarmTypeName;

	public String getAlarmTypeName() {
		return alarmTypeName;
	}

	public void setAlarmTypeName(String alarmTypeName) {
		this.alarmTypeName = alarmTypeName;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getRefMsgId() {
		return refMsgId;
	}

	public void setRefMsgId(String refMsgId) {
		this.refMsgId = refMsgId;
	}
//    
//	@Column(name = "LOCKED", columnDefinition="bit(1) default '0'")
//	private Boolean locked; // 操作锁定

//    public Double getLot() {
//        return lot;
//    }
//    public void setLot(Double lot) {
//        this.lot = lot;
//    }
//    
//    public Double getLat() {
//    	return lat;
//    }
//    public void setLat(Double lat) {
//    	this.lat = lat;
//    }
	
//    public String getTaskState() {
//        return taskState;
//    }
//    public void setTaskState(String taskState) {
//        this.taskState = taskState;
//    }
//
//    public String getTaskStateDetail() {
//        return taskStateDetail;
//    }
//    public void setTaskStateDetail(String taskStateDetail) {
//        this.taskStateDetail = taskStateDetail;
//    }

	public String getCheckContent() {
		return checkContent;
	}
	public void setCheckContent(String checkContent) {
		this.checkContent = checkContent;
	}
	
//    public String getPublishState() {
//        return publishState;
//    }
//    public void setPublishState(String publishState) {
//        this.publishState = publishState;
//    }
    
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getVersion() {
        return version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }
    
    public String getMsgId() {
        return msgId;
    }
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
//    
//    public Boolean getLocked() {
//        return locked;
//    }
//    public void setLocked(Boolean locked) {
//        this.locked = locked;
//    }
//    
//    @Transient
//    public void lock(){
//        setLocked(Boolean.TRUE);
//    }
//    @Transient
//    public void unlock(){
//        setLocked(Boolean.FALSE);
//    }

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}
    
}
