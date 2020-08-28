package cn.movinginfo.tztf.sen.domain;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.movinginfo.tztf.common.domain.BaseDomain;


/**
* @description:
* @author: autoCode
* @history:
*/
@Table(name = "sen_t_defense_department")
public class DefenseDepartment extends BaseDomain {

    private static final long serialVersionUID = 1L;


    //
    @Column(name = "Name")
    private String name;

    //
    @Column(name = "longitude")
    private String longitude;

    //
    @Column(name = "latitude")
    private String latitude;
    
    @Transient
    private List<PersonType> personTypeList;
    
   

    public List<PersonType> getPersonTypeList() {
		return personTypeList;
	}

	public void setPersonTypeList(List<PersonType> personTypeList) {
		this.personTypeList = personTypeList;
	}

	public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setLongitude(String longitude){
        this.longitude = longitude;
    }

    public String getLongitude(){
        return longitude;
    }

    public void setLatitude(String latitude){
        this.latitude = latitude;
    }

    public String getLatitude(){
        return latitude;
    }
}
