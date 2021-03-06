package cn.movinginfo.tztf.sev.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.movinginfo.tztf.common.domain.BaseDomain;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.domain.ReleaseChannel;

@Table(name = "sev_t_publish")
public class Publish extends BaseDomain{
	
	@Transient
	private Alarm alarm;
	@Transient
	private Depart depart;
	@Transient
	private ReleaseChannelInstance releaseChannelInstance;
	@Transient
	private ReleaseChannel channel;
	/**
     * 预警ID
     */
    @Column(name="alarm_id")
	private Long alarmId;
	/**
     * 部门ID
     */
    @Column(name="department_id")
    private Long departmentId;
    /**
     * 预警渠道发布ID
     */
    @Column(name="instance_id")
    private Long releaseChannelInstanceId;
    /**
     * 部门接收时间
     */
    @Column(name="receive_date")
    private Date receiveDate;
    /**
     * 部门反馈时间
     */
    @Column(name="reply_date")
    private Date replyDate;
    /**
     * 是否发布
     */
    @Column(name="is_publish")
    private Integer isPublish;
    /**
     * 发布详情
     */
    @Column(name="publish_detail")
    private String publishDetail;
    /**
     * 图片路径
     */
    @Column(name="images")
    private String images;
    /**
     * 
     */
    @Column(name="publish_date")
    private String publishDate;
    
    
 // 发布方式（钉钉/微信/邮箱）
 	@Column(name = "publishTypeName")
 	private String publishTypeName;
 	
 	
 	public String getPublishTypeName() {
		return publishTypeName;
	}
	public void setPublishTypeName(String publishTypeName) {
		this.publishTypeName = publishTypeName;
	}
	public Long getAlarmId() {
		return alarmId;
	}
	public void setAlarmId(Long alarmId) {
		this.alarmId = alarmId;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public Date getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}
	public Date getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}
	public Integer getIsPublish() {
		return isPublish;
	}
	public void setIsPublish(Integer isPublish) {
		this.isPublish = isPublish;
	}
	public String getPublishDetail() {
		return publishDetail;
	}
	public void setPublishDetail(String publishDetail) {
		this.publishDetail = publishDetail;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public Alarm getAlarm() {
		return alarm;
	}
	public void setAlarm(Alarm alarm) {
		this.alarm = alarm;
	}
	public Depart getDepart() {
		return depart;
	}
	public void setDepart(Depart depart) {
		this.depart = depart;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public ReleaseChannelInstance getReleaseChannelInstance() {
		return releaseChannelInstance;
	}
	public void setReleaseChannelInstance(ReleaseChannelInstance releaseChannelInstance) {
		this.releaseChannelInstance = releaseChannelInstance;
	}
	public Long getReleaseChannelInstanceId() {
		return releaseChannelInstanceId;
	}
	public void setReleaseChannelInstanceId(Long releaseChannelInstanceId) {
		this.releaseChannelInstanceId = releaseChannelInstanceId;
	}
	public ReleaseChannel getChannel() {
		return channel;
	}
	public void setChannel(ReleaseChannel channel) {
		this.channel = channel;
	}
	@Override
	public String toString() {
		return "Publish [alarm=" + alarm + ", depart=" + depart + ", releaseChannelInstance=" + releaseChannelInstance
				+ ", channel=" + channel + ", alarmId=" + alarmId + ", departmentId=" + departmentId
				+ ", releaseChannelInstanceId=" + releaseChannelInstanceId + ", receiveDate=" + receiveDate
				+ ", replyDate=" + replyDate + ", isPublish=" + isPublish + ", publishDetail=" + publishDetail
				+ ", images=" + images + ", publishDate=" + publishDate + ", publishTypeName=" + publishTypeName + "]";
	}
	
	
    
    
    
}
