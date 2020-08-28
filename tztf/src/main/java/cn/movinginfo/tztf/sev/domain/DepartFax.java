package cn.movinginfo.tztf.sev.domain;

import javax.persistence.Column;
import javax.persistence.Table;

import cn.movinginfo.tztf.common.domain.BaseDomain;

@Table(name = "sev_t_depart_fax")
public class DepartFax extends BaseDomain {

    @Column(name = "depart")
    private String depart;

    @Column(name = "fax_num")
    private String  fax_num;

	

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public String getFax_num() {
		return fax_num;
	}

	public void setFax_num(String fax_num) {
		this.fax_num = fax_num;
	}


}
