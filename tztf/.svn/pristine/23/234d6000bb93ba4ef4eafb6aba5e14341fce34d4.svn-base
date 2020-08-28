package cn.movinginfo.tztf.dd.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.movinginfo.tztf.common.constants.DictKeys;
import cn.movinginfo.tztf.sys.utils.json.Dict;

public class Disaster implements Serializable {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private Long id;

    private Long deptId;

    private String pubNo;

    private String title;
    
    private String name;

    private String pubAdd;
    
    private String address;

    private String content;
    
    private Date pubDate;

    private String imagePath;

    private String valid;

    private String checkContent;

    private String type;

    private String publisher;
    
    private String chargeMan;
    
    private String dataType;

    public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getChargeMan() {
		return chargeMan;
	}

	public void setChargeMan(String chargeMan) {
		this.chargeMan = chargeMan;
	}

	private String areaId;

    private Integer typeId;

    private Integer userId;
    
    @Dict(key = DictKeys.PUB_STATE)
    private String pubState;

    private Date auditDate;

    private Date releaseDate;

    private Date completeDate;
    
    private String lon;
    
    private String longitude;
    
    private String lat;
    
    private String latitude;

    private String date;
    
    private String reallyTime;
    
    private String[] images; 
    
	public String[] getImages() {
		return images;
	}

	public void setImages(String[] images) {
		this.images = images;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getReallyTime() {
		return reallyTime;
	}

	public void setReallyTime(String reallyTime) {
		this.reallyTime = reallyTime;
	}

	public String getDate() {
		return sdf.format(pubDate);
	}

	public void setDate( ) {
		
		this.date = sdf.format(pubDate);
	}

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

    public String getPubAdd() {
        return pubAdd;
    }

    public void setPubAdd(String pubAdd) {
        this.pubAdd = pubAdd == null ? null : pubAdd.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath == null ? null : imagePath.trim();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher == null ? null : publisher.trim();
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPubState() {
        return pubState;
    }

    public void setPubState(String pubState) {
        this.pubState = pubState == null ? null : pubState.trim();
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

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }
}