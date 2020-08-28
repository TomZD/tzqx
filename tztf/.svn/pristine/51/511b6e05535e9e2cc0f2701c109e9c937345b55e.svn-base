package cn.movinginfo.tztf.sev.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.movinginfo.tztf.common.constants.DictKeys;
import cn.movinginfo.tztf.common.domain.BaseDomain;
import cn.movinginfo.tztf.sys.domain.ReleaseChannel;
import cn.movinginfo.tztf.sys.utils.json.Dict;

@Table(name = "sev_t_release_channel_instance")
public class ReleaseChannelInstance extends BaseDomain implements Comparable<ReleaseChannelInstance>, Cloneable {
	
	@Override
	public String toString() {
		return "ReleaseChannelInstance [channelId=" + channelId + ", content=" + content + ", senderNumber="
				+ senderNumber + ", senderType=" + senderType + ", version=" + version + ", arriveTime=" + arriveTime
				+ ", resultId=" + resultId + ", channel=" + channel + ", releaseState=" + releaseState
				+ ", releaseTime=" + releaseTime + ", feedbackMessage=" + feedbackMessage + ", data=" + data
				+ ", purpose=" + purpose + ", removeContent=" + removeContent + ", removeReleaseTime="
				+ removeReleaseTime + ", removeArriveTime=" + removeArriveTime + ", removeFeedbackMessage="
				+ removeFeedbackMessage + ", useDefaultRemoveMode=" + useDefaultRemoveMode + ", memo=" + memo
				+ ", alarmId=" + alarmId + ", instanceIds=" + instanceIds + ", channelIds=" + channelIds + "]";
	}

	private static final long serialVersionUID = 1L;
	
	@Column(name = "CHANNEL_ID")
	private Long channelId;
	
	@Column(name = "CONTENT")
	private String content;
	
	@Column(name = "SENDER_NUMBER")
	private String senderNumber;
	
	@Dict(key = DictKeys.SENDER_TYPE)
	@Column(name = "SENDER_TYPE")
	private String senderType; // 渠道发送的类型
	
	@Column(name = "VERSION")
	private Integer version;
	
	@Column(name = "ARRIVE_TIME")
	private Date arriveTime;
   
	@Column(name = "RESULT_ID")
	private String resultId;//传真id
    
	@Transient
	private ReleaseChannel channel; // 使用的发布渠道

	@Dict(key = DictKeys.CHANNEL_STATE)
	@Column(name = "CHANNEL_STATE")
	private String releaseState; // 渠道发布状态

	@Column(name = "RELASE_TIME")
	private Date releaseTime; // 发布时间

	@Column(name = "FEED_BACK_MESSAGE")
	private String feedbackMessage; // 反馈消息

	@Column(name = "data")
	private String data; // 额外数据

	@Column(name = "SEND_PURPOSE")
	private String purpose; // 发送的数据的状态

	@Column(name = "REMOVE_CONTENT")
	private String removeContent; // 解除时发送的内容

	@Column(name = "REMOVE_RELEASE_TIME")
	private Date removeReleaseTime; // 解除消息发出的时间

	@Column(name = "REMOVE_ARRIVE_TIME")
	private Date removeArriveTime; // 解除消息送达时间

	@Column(name = "REMOVE_FEED_BACK_MESSAGE")
	private String removeFeedbackMessage; // 解除的反馈信息

	@Column(name = "USE_DEFAULT_REMOVE_MODE")
	private Boolean useDefaultRemoveMode; // 使用默认解除模式

	@Column(name = "MEMO")
	private String memo; // 未知字段
	
	@Column(name = "alarm_id")
	private Long alarmId;//预警ID
	
	@Transient
	private String instanceIds;//相同类型的发布渠道实例ID
	
	@Transient
	private String channelIds;////相同类型的发布渠道ID

	public ReleaseChannel getChannel() {
		return channel;
	}
	public void setChannel(ReleaseChannel channel) {
		this.channel = channel;
	}
	
	public String getReleaseState() {
		return releaseState;
	}
	public void setReleaseState(String releaseState) {
		this.releaseState = releaseState;
	}
	
	public Date getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	
    public String getFeedbackMessage() {
        return feedbackMessage;
    }
    public void setFeedbackMessage(String feedbackMessage) {
        this.feedbackMessage = feedbackMessage;
    }

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    
    public String getRemoveContent() {
        return removeContent;
    }
    public void setRemoveContent(String removeContent) {
        this.removeContent = removeContent;
    }
    
    public Date getRemoveReleaseTime() {
        return removeReleaseTime;
    }
    public void setRemoveReleaseTime(Date removeReleaseTime) {
        this.removeReleaseTime = removeReleaseTime;
    }
    
    public Date getRemoveArriveTime() {
        return removeArriveTime;
    }
    public void setRemoveArriveTime(Date removeArriveTime) {
        this.removeArriveTime = removeArriveTime;
    }
    
    public String getRemoveFeedbackMessage() {
        return removeFeedbackMessage;
    }
    public void setRemoveFeedbackMessage(String removeFeedbackMessage) {
        this.removeFeedbackMessage = removeFeedbackMessage;
    }
    
    public Boolean getUseDefaultRemoveMode() {
        return useDefaultRemoveMode;
    }
    public void setUseDefaultRemoveMode(Boolean useDefaultRemoveMode) {
        this.useDefaultRemoveMode = useDefaultRemoveMode;
    }
    
    public String getPurpose() {
        return purpose;
    }
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
    
    public String getMemo() {
    	return memo;
    }
    public void setMemo(String memo) {
    	this.memo = memo;
    }
    
    public String getSenderType() {
		return senderType;
	}
	public void setSenderType(String senderType) {
		this.senderType = senderType;
	}

	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}

	public Long getChannelId() {
		return channelId;
	}
	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getSenderNumber() {
		return senderNumber;
	}
	public void setSenderNumber(String senderNumber) {
		this.senderNumber = senderNumber;
	}

	public Date getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(Date arriveTime) {
		this.arriveTime = arriveTime;
	}
    
    @Override
	public int compareTo(ReleaseChannelInstance arg0) {
		if (channel != null && arg0.getChannel() != null) {
			return channel.compareTo(arg0.getChannel());
		}
		return 0;
	}

    @Override
    public ReleaseChannelInstance clone() throws CloneNotSupportedException{
        ReleaseChannelInstance newChannel = new ReleaseChannelInstance();
        org.springframework.beans.BeanUtils.copyProperties(this, newChannel);
        return newChannel;
    }
	public Long getAlarmId() {
		return alarmId;
	}
	public void setAlarmId(Long alarmId) {
		this.alarmId = alarmId;
	}
	public String getInstanceIds() {
		return instanceIds;
	}
	public void setInstanceIds(String instanceIds) {
		this.instanceIds = instanceIds;
	}
	public String getResultId() {
		return resultId;
	}
	public void setResultId(String resultId) {
		this.resultId = resultId;
	}
	public String getChannelIds() {
		return channelIds;
	}
	public void setChannelIds(String channelIds) {
		this.channelIds = channelIds;
	}
    
	
}
