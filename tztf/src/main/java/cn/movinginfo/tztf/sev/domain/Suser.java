package cn.movinginfo.tztf.sev.domain;
import javax.persistence.Column;
import javax.persistence.Table;

import cn.movinginfo.tztf.common.domain.BaseDomain;


/**
* @description:
* @author: autoCode
* @history:
*/
@Table(name = "sev_t_suser")
public class Suser extends BaseDomain {

    private static final long serialVersionUID = 1L;


    //服务类型(1:高速公路建设)
    @Column(name = "service_type")
    private String serviceType;

    //名称
    @Column(name = "name")
    private String name;

    public void setServiceType(String serviceType){
        this.serviceType = serviceType;
    }

    public String getServiceType(){
        return serviceType;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
