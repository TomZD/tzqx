package cn.movinginfo.tztf.sen.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.movinginfo.tztf.common.domain.BaseDomain;

@Table(name ="sen_t_permission")
public class SenPermission extends BaseDomain{
	private static final long serialVersionUID = 1L;
	
	@Column(name="name")
	private String name;
	
	@Column(name="code")
	private String code;
	
	@Column(name="url")
	private String url;
	
	@Column(name="pid")
	private Integer pid;
	
	@Transient
	private List<SenPermission> twoPermissionList;

	public SenPermission() {
		super();
	}

	public SenPermission(String name, String code, String url, Integer pid) {
		super();
		this.name = name;
		this.code = code;
		this.url = url;
		this.pid = pid;
	}
	

	public List<SenPermission> getTwoPermissionList() {
		return twoPermissionList;
	}

	public void setTwoPermissionList(List<SenPermission> twoPermissionList) {
		this.twoPermissionList = twoPermissionList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	@Override
	public String toString() {
		return "SenPermission [name=" + name + ", code=" + code + ", url=" + url + ", pid=" + pid
				+ ", twoPermissionList=" + twoPermissionList + "]";
	}

	

	
}
