package cn.movinginfo.tztf.sev.domain;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.movinginfo.tztf.common.constants.DictKeys;
import cn.movinginfo.tztf.common.domain.BaseDomain;
import cn.movinginfo.tztf.sys.domain.AlarmType;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.utils.json.Dict;

@Table(name = "sev_t_emergency_plan")
public class EmergencyPlan extends BaseDomain {

	private String path;//存储相对路径
	private String title;//标题
	/**
	 * 区域ID
	 */
	
	 @Dict(key = DictKeys.AREA_ID)
	    @Column(name="area_id")
	    private String areaId;
	    
	    
		public String getAreaId() {
			return areaId;
		}

		public void setAreaId(String areaId) {
			this.areaId = areaId;
		}
		/**
		 * 部门类型
		 */
		
		 @Dict(key = DictKeys.DEPT_TYPE)
		    @Column(name="dept_type")
		    private String deptType;
		    
		    
			public String getDeptType() {
				return deptType;
			}

			public void setDeptType(String deptType) {
				this.deptType = deptType;
			}
	/**
     * 部门ID
     */
    @Column(name="department_id")
    private Long departmentId;
    /**
     * 类型ID
     */
    @Column(name="alarm_type_id")
	private Long alarmTypeId;
	
	@Transient
	private Depart depart;
	@Transient
	private AlarmType alarmType;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public Long getAlarmTypeId() {
		return alarmTypeId;
	}
	public void setAlarmTypeId(Long alarmTypeId) {
		this.alarmTypeId = alarmTypeId;
	}
	public Depart getDepart() {
		return depart;
	}
	public void setDepart(Depart depart) {
		this.depart = depart;
	}
	public AlarmType getAlarmType() {
		return alarmType;
	}
	public void setAlarmType(AlarmType alarmType) {
		this.alarmType = alarmType;
	}
	
	
}
