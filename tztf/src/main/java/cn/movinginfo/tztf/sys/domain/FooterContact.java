package cn.movinginfo.tztf.sys.domain;

import javax.persistence.Table;
import javax.persistence.Transient;

import cn.movinginfo.tztf.common.constants.DictKeys;
import cn.movinginfo.tztf.common.domain.BaseDomain;

/**
 * 底部联系人
 * @author caixj
 *
 */
@Table(name="sys_footer_contact")
public class FooterContact extends BaseDomain{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String contactName;				//联系人
	private String contactPhone;
	private String technicalSupport;		//技术支持
	private String supportPhone;
	private String areaId;
	private String version;                //版本
	
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getTechnicalSupport() {
		return technicalSupport;
	}
	public void setTechnicalSupport(String technicalSupport) {
		this.technicalSupport = technicalSupport;
	}
	public String getSupportPhone() {
		return supportPhone;
	}
	public void setSupportPhone(String supportPhone) {
		this.supportPhone = supportPhone;
	}
	
	
	
	
}
