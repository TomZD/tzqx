package cn.movinginfo.tztf.sys.domain;

import javax.persistence.Column;
import javax.persistence.Table;

import cn.movinginfo.tztf.common.domain.BaseDomain;

@Table(name ="sys_people_group")
public class PeopleGroup extends BaseDomain {
    private static final long serialVersionUID = 1L;

    @Column(name="name")//短信组名字
    private String name;

    @Column(name="department")//单位
    private String department;

    @Column(name="phone")//电话
    private String phone;

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
