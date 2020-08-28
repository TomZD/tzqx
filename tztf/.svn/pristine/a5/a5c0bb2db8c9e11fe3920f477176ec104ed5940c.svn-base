package cn.movinginfo.tztf.sev.domain;

import javax.persistence.Column;
import javax.persistence.Table;

import cn.movinginfo.tztf.common.constants.DictKeys;
import cn.movinginfo.tztf.common.domain.BaseDomain;
import cn.movinginfo.tztf.sys.utils.json.Dict;

@Table(name = "sev_t_temperson")
public class Temperson extends BaseDomain{
	private static final long serialVersionUID = 1L;
	
	@Column(name="name")
	private String name;
	
	@Column(name="phone")
	private String phone;
	
	@Dict(key = DictKeys.AREA_ID)
	@Column(name="area_id")
	private String areaId;
	
	@Column(name="ps")
	private String ps;
	
	@Column(name="department_id")
	private Long departmentId;
    
	@Column(name="dept_name")
	private String deptName;
	
	@Column(name="area_name")
	private String areaName;

	public Temperson() {
		super();
	}

	public Temperson(String name, String phone, String areaId, String ps, Long departmentId,
			String deptName, String areaName) {
		super();
		this.name = name;
		this.phone = phone;
		this.areaId = areaId;
		this.ps = ps;
		this.departmentId = departmentId;
		this.deptName = deptName;
		this.areaName = areaName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	
	
}
