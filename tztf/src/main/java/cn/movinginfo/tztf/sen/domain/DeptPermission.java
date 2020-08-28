package cn.movinginfo.tztf.sen.domain;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.movinginfo.tztf.common.domain.BaseDomain;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.domain.UserXt;


/**
* @description:
* @author: autoCode
* @history:
*/
@Table(name = "sen_t_dept_permission")
public class DeptPermission extends BaseDomain {

    private static final long serialVersionUID = 1L;


    //
    @Column(name = "permission_id")
    private Long permissionId;
    
    @Column(name = "two_permission_id")
    private String twoPermissionId;

    //
    @Column(name = "dept_id")
    private Long deptId;
    
    @Transient
    private String deptName;
    
    @Transient
    private String menuName;
    
    @Transient
    private String twoMenuName;
    
    @Transient
    private Depart dept;

	@Transient
	private UserXt userXt;
    
    @Transient
    private SenPermission senPermission;
    
    @Transient
    private List<Depart> deptList;

	@Transient
	private List<UserXt> userXtList;
    
    @Transient
    private List<SenPermission> permissionList;
    
    
    public String getTwoMenuName() {
		return twoMenuName;
	}

	public void setTwoMenuName(String twoMenuName) {
		this.twoMenuName = twoMenuName;
	}

	public String getTwoPermissionId() {
		return twoPermissionId;
	}

	public void setTwoPermissionId(String twoPermissionId) {
		this.twoPermissionId = twoPermissionId;
	}

	public Depart getDept() {
		return dept;
	}

	public void setDept(Depart dept) {
		this.dept = dept;
	}

	public SenPermission getSenPermission() {
		return senPermission;
	}

	public void setSenPermission(SenPermission senPermission) {
		this.senPermission = senPermission;
	}

	public List<Depart> getDeptList() {
		return deptList;
	}

	public void setDeptList(List<Depart> deptList) {
		this.deptList = deptList;
	}

	public List<SenPermission> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<SenPermission> permissionList) {
		this.permissionList = permissionList;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public void setPermissionId(Long permissionId){
        this.permissionId = permissionId;
    }

    public Long getPermissionId(){
        return permissionId;
    }

    public void setDeptId(Long deptId){
        this.deptId = deptId;
    }

    public Long getDeptId(){
        return deptId;
    }

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public UserXt getUserXt() {
		return userXt;
	}

	public void setUserXt(UserXt userXt) {
		this.userXt = userXt;
	}

	public List<UserXt> getUserXtList() {
		return userXtList;
	}

	public void setUserXtList(List<UserXt> userXtList) {
		this.userXtList = userXtList;
	}
}
