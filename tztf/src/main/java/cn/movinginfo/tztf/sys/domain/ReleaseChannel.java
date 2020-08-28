package cn.movinginfo.tztf.sys.domain;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.movinginfo.tztf.common.domain.BaseDomain;


@Table(name = "sys_release_channel")
public class ReleaseChannel extends BaseDomain implements Comparable<ReleaseChannel> {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "NAME_EN")
	private String nameEn;
	
	@Column(name = "SORT_NUMBER")
	private Integer sortNumber;
	@Transient
	private String content;
	@Column(name = "NEED_FEEDBACK")
	private Integer needFeedback;//是否需要反馈


	@Column(name = "HANDLER_CLAZZ")
	private String handlerClazz; // 处理类的全名

    @Column(name="IS_IMPORTANT_EVENT")
	private Boolean isImportantEvent; // 是否需要重大流程

	@Column(name = "TEMPLATE_LENGTH")
	private Integer templateLength; // 模板长度

	@Column(name = "TEMPLATE_TYPE")
	private String templateType; // 模板类型

	@Column(name = "MESSAGE_TEMPLATE")
	private String messageTemplate; // 修改过后的模板

    @Column(name="RELEASE_ACTION")
	private Short releaseAction; // 

    @Column(name="RELEASE_ACTION_WHEN_TERMINATE")
	private Short releaseActionWhenTerminate; // 

    @Column(name="MAX_LENGTH")
	private Short maxLength; // 

    @Column(name="FORCE_UPDATE")
	private Boolean forceUpdate; // 

    @Column(name="FORCE_CANCEL")
	private Boolean forceCancel; // 
    


    @Column(name ="PARENT_ID")
	private Long parentId;
	
	@Column(name ="id")
	private Long id;
	
	@Column(name ="kettle")
	private Long kettle;
	

	public Long getKettle() {
		return kettle;
	}
	public void setKettle(Long kettle) {
		this.kettle = kettle;
	}

	@Transient
	public static final short RELEASE_ACTION_CONTENT = 1;
	@Transient
	public static final short RELEASE_ACTION_FILE = 2;
	@Transient
	public static final short RELEASE_ACTION_BOTH = 3;

	@Transient
	public static final int MESSAGE_TYPE_LONG = 0; // 模板类型长度：长
	@Transient
	public static final int MESSAGE_TYPE_MIDDLE = 1;// 模板类型长度：中
	@Transient
	public static final int MESSAGE_TYPE_SHORT = 2;// 模板类型长度：段

	@Transient
	private Boolean checked = false;

	@Transient
	private Boolean useDefaultRemoveMode;
    public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getHandlerClazz() {
        return handlerClazz;
    }
    public void setHandlerClazz(String handlerClazz) {
        this.handlerClazz = handlerClazz;
    }
    
    public Boolean getIsImportantEvent() {
        return isImportantEvent;
    }
    public void setIsImportantEvent(Boolean isImportantEvent) {
        this.isImportantEvent = isImportantEvent;
    }
    
	public Integer getTemplateLength() {
		return templateLength;
	}
	public void setTemplateLength(Integer templateLength) {
		this.templateLength = templateLength;
	}
	
	public String getTemplateType() {
		return templateType;
	}
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}
	
	public String getMessageTemplate() {
		return messageTemplate;
	}
	public void setMessageTemplate(String messageTemplate) {
		this.messageTemplate = messageTemplate;
	}
	
    @Transient
    public Boolean isChecked() {
        return checked;
    }
    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
    
    public Short getReleaseAction() {
        return releaseAction;
    }
    public void setReleaseAction(Short releaseAction) {
        this.releaseAction = releaseAction;
    }
    
    public Short getReleaseActionWhenTerminate() {
        return releaseActionWhenTerminate;
    }
    public void setReleaseActionWhenTerminate(Short releaseActionWhenTerminate) {
        this.releaseActionWhenTerminate = releaseActionWhenTerminate;
    }
    
    public Short getMaxLength() {
        return maxLength;
    }
    public void setMaxLength(Short maxLength) {
        this.maxLength = maxLength;
    }
    
    public Boolean getForceUpdate() {
        return forceUpdate;
    }
    public void setForceUpdate(Boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
    }
    
    public Boolean getForceCancel() {
        return forceCancel;
    }
    public void setForceCancel(Boolean forceCancel) {
        this.forceCancel = forceCancel;
    }
    
    @Transient
    public Boolean getUseDefaultRemoveMode() {
        return useDefaultRemoveMode;
    }
    public void setUseDefaultRemoveMode(Boolean useDefaultRemoveMode) {
        this.useDefaultRemoveMode = useDefaultRemoveMode;
    }
    
    public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getNameEn() {
		return nameEn;
	}
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public Integer getSortNumber() {
		return sortNumber;
	}
	public void setSortNumber(Integer sortNumber) {
		this.sortNumber = sortNumber;
	}
	
	public Integer getNeedFeedback() {
		return needFeedback;
	}
	public void setNeedFeedback(Integer needFeedback) {
		this.needFeedback = needFeedback;
	}
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((checked == null) ? 0 : checked.hashCode());
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((handlerClazz == null) ? 0 : handlerClazz.hashCode());
        result = prime * result + ((maxLength == null) ? 0 : maxLength.hashCode());
        result = prime * result + ((messageTemplate == null) ? 0 : messageTemplate.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((nameEn == null) ? 0 : nameEn.hashCode());
        result = prime * result + ((releaseAction == null) ? 0 : releaseAction.hashCode());
        result = prime * result + ((releaseActionWhenTerminate == null) ? 0 : releaseActionWhenTerminate.hashCode());
        result = prime * result + ((sortNumber == null) ? 0 : sortNumber.hashCode());
        result = prime * result + ((templateLength == null) ? 0 : templateLength.hashCode());
        result = prime * result + ((templateType == null) ? 0 : templateType.hashCode());
        return result;
    }
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ReleaseChannel other = (ReleaseChannel) obj;
        if (checked == null) {
            if (other.checked != null)
                return false;
        }
        else if (!checked.equals(other.checked))
            return false;
        if (content == null) {
            if (other.content != null)
                return false;
        }
        else if (!content.equals(other.content))
            return false;
        if (handlerClazz == null) {
            if (other.handlerClazz != null)
                return false;
        }
        else if (!handlerClazz.equals(other.handlerClazz))
            return false;
        if (maxLength == null) {
            if (other.maxLength != null)
                return false;
        }
        else if (!maxLength.equals(other.maxLength))
            return false;
        if (messageTemplate == null) {
            if (other.messageTemplate != null)
                return false;
        }
        else if (!messageTemplate.equals(other.messageTemplate))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        }
        else if (!name.equals(other.name))
            return false;
        if (nameEn == null) {
            if (other.nameEn != null)
                return false;
        }
        else if (!nameEn.equals(other.nameEn))
            return false;
        if (releaseAction == null) {
            if (other.releaseAction != null)
                return false;
        }
        else if (!releaseAction.equals(other.releaseAction))
            return false;
        if (releaseActionWhenTerminate == null) {
            if (other.releaseActionWhenTerminate != null)
                return false;
        }
        else if (!releaseActionWhenTerminate.equals(other.releaseActionWhenTerminate))
            return false;
        if (sortNumber == null) {
            if (other.sortNumber != null)
                return false;
        }
        else if (!sortNumber.equals(other.sortNumber))
            return false;
        if (templateLength == null) {
            if (other.templateLength != null)
                return false;
        }
        else if (!templateLength.equals(other.templateLength))
            return false;
        if (templateType == null) {
            if (other.templateType != null)
                return false;
        }
        else if (!templateType.equals(other.templateType))
            return false;
        return true;
    }
	
	@Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("发布渠道 [");
        if (name != null)
            builder.append("名称=").append(name).append(", ");
        if (nameEn != null)
            builder.append("英文名=").append(nameEn).append(", ");
        if (handlerClazz != null)
            builder.append("处理类=").append(handlerClazz).append(", ");
        if (sortNumber != null)
            builder.append("排序=").append(sortNumber).append(", ");
        if (maxLength != null)
            builder.append("最大长度=").append(maxLength).append(", ");
        if (content != null)
            builder.append("内容=").append(content);
        builder.append("]");
        return builder.toString();
    }
	
	@Override
    public int compareTo(ReleaseChannel arg0) {
        int result = this.sortNumber - arg0.sortNumber;
        if(result>0) return 1;
        if(result<0) return -1;
        return 0;
    }
	

}
