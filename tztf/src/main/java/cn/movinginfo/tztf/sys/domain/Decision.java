package cn.movinginfo.tztf.sys.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.movinginfo.tztf.common.constants.DictKeys;
import cn.movinginfo.tztf.common.domain.BaseDomain;
import cn.movinginfo.tztf.sys.utils.json.Dict;

@Table(name ="sys_decision")
public class Decision extends BaseDomain{
	private static final long serialVersionUID = 1L;
	
	@Column(name="name")//姓名
	private String name;
	
	@Column(name="department")//单位
	private String department;
	
	@Column(name="phone")//电话
	private String phone;
	
	@Dict(key =DictKeys.AREA_ID)
	@Column(name="area_id")//区域Id
    private String areaId;
	
	@Dict(key = DictKeys.DEPT_TYPE)
    @Column(name="dept_type")
    private String deptType;
	
	@Column(name="user_id")
	private String userId;
	
	@Column(name="dept_id")
	private String deptId;
	
	@Transient
	private String deptName;
	
	@Transient
	private List<Depart> deptList;
	
	
	public List<Depart> getDeptList() {
		return deptList;
	}



	public void setDeptList(List<Depart> deptList) {
		this.deptList = deptList;
	}



	public String getDeptName() {
		return deptName;
	}



	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}



	public Decision() {
		super();
	}
	

	
	public String getDeptType() {
		return deptType;
	}



	public String getDeptId() {
		return deptId;
	}



	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}



	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public Decision(String name, String department, String phone, String areaId, String deptType, String userId) {
		super();
		this.name = name;
		this.department = department;
		this.phone = phone;
		this.areaId = areaId;
		this.deptType = deptType;
		this.userId = userId;
	}



	public String getAreaId() {
		return areaId;
	}


	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
