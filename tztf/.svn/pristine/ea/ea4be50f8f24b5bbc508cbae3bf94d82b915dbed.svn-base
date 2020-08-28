package cn.movinginfo.tztf.sem.domain;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class SevAlarm implements Serializable {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Long id;

    private Long deptId;

    private String pubNo;

    private String title;

    @Override
	public String toString() {
		return "SevAlarm [sdf=" + sdf + ", id=" + id + ", deptId=" + deptId
				+ ", pubNo=" + pubNo + ", title=" + title + ", pubRange="
				+ pubRange + ", pubRangeName=" + pubRangeName + ", content="
				+ content + ", pubState=" + pubState + ", duration=" + duration
				+ ", pubDate=" + pubDate + ", cancelDate=" + cancelDate
				+ ", pubChannel=" + pubChannel + ", imagePath=" + imagePath
				+ ", createDate=" + createDate + ", creator=" + creator
				+ ", updateDate=" + updateDate + ", updator=" + updator
				+ ", valid=" + valid + ", checkContent=" + checkContent
				+ ", alarmTypeName=" + alarmTypeName + ", auditDate="
				+ auditDate + ", releaseDate=" + releaseDate
				+ ", completeDate=" + completeDate + ", version=" + version
				+ ", type=" + type + ", typeId=" + typeId + ", pid=" + pid
				+ ", issuer=" + issuer + ", publisher=" + publisher
				+ ", lastAction=" + lastAction + ", number=" + number
				+ ", msgId=" + msgId + ", isSend=" + isSend + ", noticeFile="
				+ noticeFile + ", sendDate=" + sendDate + ", publishtypename="
				+ publishtypename + ", channelName=" + channelName
				+ ", kettle=" + kettle + ", areaId=" + areaId + ", points="
				+ points + ", images=" + images + ", sendMsg=" + sendMsg
				+ ", pubDateStr=" + pubDateStr + ", releaseDateStr="
				+ releaseDateStr + "]";
	}

	private String pubRange;


	private String pubRangeName;

    private String content;

    private String pubState;

    private String duration;

    private Date pubDate;

    private Date cancelDate;

    private Integer pubChannel;

    private String imagePath;

    private Date createDate;

    private Long creator;

    private Date updateDate;

    private Long updator;

    private String valid;

    private String checkContent;

    private String alarmTypeName;

    private Date auditDate;

    private Date releaseDate;

    private Date completeDate;

    private Integer version;

    private String type;

    private Integer typeId;

    private Integer pid;

    private String issuer;

    private String publisher;

    private Byte lastAction;

    private String number;

    private String msgId;

    private Integer isSend;

    private String noticeFile;

    private Date sendDate;

    private String publishtypename;

    private String channelName;

    private String kettle;

    private String areaId;

    private String points;

    private String images;

    private String sendMsg;

	private String pubDateStr;
	
	private String releaseDateStr;
	
    public String getPubDateStr() {
		return pubDateStr;
	}

	public void setPubDateStr(String pubDateStr) {
		this.pubDateStr = pubDateStr;
	}

	public String getReleaseDateStr() {
		return releaseDateStr;
	}

	public void setReleaseDateStr(String releaseDateStr) {
		this.releaseDateStr = releaseDateStr;
	}

	private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getPubNo() {
        return pubNo;
    }

    public void setPubNo(String pubNo) {
        this.pubNo = pubNo == null ? null : pubNo.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getPubRange() {
        return pubRange;
    }

    public void setPubRange(String pubRange) {
        this.pubRange = pubRange == null ? null : pubRange.trim();
    }

    public String getPubRangeName() {
        return pubRangeName;
    }

    public void setPubRangeName(String pubRangeName) {
        this.pubRangeName = pubRangeName == null ? null : pubRangeName.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getPubState() {
        return pubState;
    }

    public void setPubState(String pubState) {
        this.pubState = pubState == null ? null : pubState.trim();
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration == null ? null : duration.trim();
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) throws ParseException {
    	Date date = null;
    	if(StringUtils.isNotEmpty(pubDateStr)){
    		 date = sdf.parse(pubDateStr);
    	}
        this.pubDate = date;
    }

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    public Integer getPubChannel() {
        return pubChannel;
    }

    public void setPubChannel(Integer pubChannel) {
        this.pubChannel = pubChannel;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath == null ? null : imagePath.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getUpdator() {
        return updator;
    }

    public void setUpdator(Long updator) {
        this.updator = updator;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid == null ? null : valid.trim();
    }

    public String getCheckContent() {
        return checkContent;
    }

    public void setCheckContent(String checkContent) {
        this.checkContent = checkContent == null ? null : checkContent.trim();
    }

    public String getAlarmTypeName() {
        return alarmTypeName;
    }

    public void setAlarmTypeName(String alarmTypeName) {
        this.alarmTypeName = alarmTypeName == null ? null : alarmTypeName.trim();
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) throws ParseException {
    	Date date = null;
    	if(StringUtils.isNotEmpty(releaseDateStr)) {
    		date = sdf.parse(releaseDateStr);
    		System.out.println("setReleaseDate method--------");
    	}
        this.releaseDate = date;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer == null ? null : issuer.trim();
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher == null ? null : publisher.trim();
    }

    public Byte getLastAction() {
        return lastAction;
    }

    public void setLastAction(Byte lastAction) {
        this.lastAction = lastAction;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId == null ? null : msgId.trim();
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
        this.noticeFile = noticeFile == null ? null : noticeFile.trim();
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getPublishtypename() {
        return publishtypename;
    }

    public void setPublishtypename(String publishtypename) {
        this.publishtypename = publishtypename == null ? null : publishtypename.trim();
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public String getKettle() {
        return kettle;
    }

    public void setKettle(String kettle) {
        this.kettle = kettle == null ? null : kettle.trim();
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points == null ? null : points.trim();
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    public String getSendMsg() {
        return sendMsg;
    }

    public void setSendMsg(String sendMsg) {
        this.sendMsg = sendMsg == null ? null : sendMsg.trim();
    }
}