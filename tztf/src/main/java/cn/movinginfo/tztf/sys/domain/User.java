package cn.movinginfo.tztf.sys.domain;

import net.ryian.commons.DigestUtils;
import net.ryian.commons.EncodeUtils;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.movinginfo.tztf.common.constants.DictKeys;
import cn.movinginfo.tztf.common.domain.BaseDomain;
import cn.movinginfo.tztf.sys.utils.json.Dict;


/**
 * @description:
 * @author: autoCode
 * @history:
 */
@Table(name = "sys_user")
public class User extends BaseDomain {

    private static final long serialVersionUID = 1L;

    public static final String HASH_ALGORITHM = "SHA-1"; // 使用的加密算法
    public static final int HASH_INTERATIONS = 1024; // 加密迭代次数
    private static final int SALT_SIZE = 8; // 加密盐的大小

    /**
     * 引入公司单位
     */
    @Transient
    private Depart depart;
    
    @Transient
    private RUserRole rUserRole;

    /**
     * 用户名
     **/
    @Column(name="user_name")
    private String userName;

    /**
     * 联系方式
     */
    @Column(name="phone")
    private String phone;
    
    //是否登录人员
    @Column(name="is_use")
    private String isUse;
    
    /**
     * 是否接受短信
     */
    @Column(name="is_receive_massages")
    @Dict(key = DictKeys.BOOL_TYPE)
    private String isReceiveMassages;
    
    /**
     * 部门单位编号
     */
    @Column(name="department_id")
    private Long departmentId;

    /**
     * 密码
     **/
    @Column(name="password")
    private String password;
    
    /**
     * 真实姓名
     */
    @Column(name="name")
    private String name;

    /**
     * 盐值
     **/
    private String salt;
    
    /**
     * 区域ID
     */
    @Dict(key = DictKeys.AREA_ID)
    @Column(name="area_id")
    private String areaId;
    
    /**
     * 是否应急平台用户
     */
    @Column(name="is_yjpt")
    @Dict(key = DictKeys.BOOL_TYPE)
    private String isYjpt;
    
    
    
    
    public String getIsYjpt() {
		return isYjpt;
	}

	public void setIsYjpt(String isYjpt) {
		this.isYjpt = isYjpt;
	}

	public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
    
	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIsReceiveMassages() {
		return isReceiveMassages;
	}

	public void setIsReceiveMassages(String isReceiveMassages) {
		this.isReceiveMassages = isReceiveMassages;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
            

	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	public Depart getDepart() {
		return depart;
	}

	public void setDepart(Depart depart) {
		this.depart = depart;
	}

	public RUserRole getrUserRole() {
		return rUserRole;
	}

	public void setrUserRole(RUserRole rUserRole) {
		this.rUserRole = rUserRole;
	}

	
	public void encryptUserPassword(String password) {
        byte[] salt = DigestUtils.generateSalt(SALT_SIZE);
        this.setSalt(EncodeUtils.encodeHex(salt));

        byte[] hashPassword = DigestUtils.sha1(password.getBytes(), salt, HASH_INTERATIONS);
        this.setPassword(EncodeUtils.encodeHex(hashPassword));
    }

	@Override
	public String toString() {
		return "User [depart=" + depart + ", rUserRole=" + rUserRole + ", userName=" + userName + ", phone=" + phone
				+ ", isUse=" + isUse + ", isReceiveMassages=" + isReceiveMassages + ", departmentId=" + departmentId
				+ ", password=" + password + ", name=" + name + ", salt=" + salt + "]";
	}
	
	

}
