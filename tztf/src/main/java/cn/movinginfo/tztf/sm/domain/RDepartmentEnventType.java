package cn.movinginfo.tztf.sm.domain;
import javax.persistence.Column;
import javax.persistence.Table;

import cn.movinginfo.tztf.common.domain.BaseDomain;


/**
* @description:
* @author: autoCode
* @history:
*/
@Table(name = "sm_r_department_envent_type")
public class RDepartmentEnventType extends BaseDomain {

    private static final long serialVersionUID = 1L;


    //部门id
    @Column(name = "department_id")
    private Long departmentId;

    //事件类型id
    @Column(name = "event_type_id")
    private Long eventTypeId;

    public void setDepartmentId(Long departmentId){
        this.departmentId = departmentId;
    }

    public Long getDepartmentId(){
        return departmentId;
    }

    public void setEventTypeId(Long eventTypeId){
        this.eventTypeId = eventTypeId;
    }

    public Long getEventTypeId(){
        return eventTypeId;
    }
}
