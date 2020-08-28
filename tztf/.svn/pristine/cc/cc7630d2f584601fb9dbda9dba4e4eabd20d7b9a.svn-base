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
@Table(name = "sen_t_equipment")
public class Equipment extends BaseDomain {

    private static final long serialVersionUID = 1L;


    //
    @Column(name = "name")
    private String name;
    
    @Column(name = "icon")
    private String icon;
    
    @Transient
    private List<Equipment> equipmentList;
    
    

    public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<Equipment> getEquipmentList() {
		return equipmentList;
	}

	public void setEquipmentList(List<Equipment> equipmentList) {
		this.equipmentList = equipmentList;
	}

	public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
