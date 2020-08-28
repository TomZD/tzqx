package cn.movinginfo.tztf.sen.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.movinginfo.tztf.common.constants.DictKeys;
import cn.movinginfo.tztf.common.domain.BaseDomain;
import cn.movinginfo.tztf.sys.utils.json.Dict;

@Table(name ="sen_t_menu")
public class Menu extends BaseDomain{
	private static final long serialVersionUID = 1L;
	
	@Column(name="name")//菜单名称
	private String name;  
	
	@Column(name="menu_id")//菜单级别   0为标题，1为一级菜单，2为二级菜单
	private Long menuId;
	
	@Column(name="type")//类型
	private String type;
	
	@Column(name="value")//前端的value值
	private String value;
	
	@Column(name="url")//路径
	private String url;
	
	@Column(name="pid")//所属id
	private Long pid;
	
	@Column(name="icon")
	private String icon;
	
	@Column(name="file_url")
	private String fileURL;
	
	@Column(name="show_time")
	private String showTime;
	
	@Transient
	private String pidName;
	
	@Column(name="data_type")//数据类型
	private String dataType;
	
	@Column(name="type_id")
	private Long typeId;
	
	@Transient
	private List<Menu> liMenu;//二级菜单
	
	@Transient
	private List<Menu> threeMenu;//三级菜单
	
	
	

	public String getFileURL() {
		return fileURL;
	}

	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public List<Menu> getThreeMenu() {
		return threeMenu;
	}

	public void setThreeMenu(List<Menu> threeMenu) {
		this.threeMenu = threeMenu;
	}

	public String getPidName() {
		return pidName;
	}

	public void setPidName(String pidName) {
		this.pidName = pidName;
	}

	public List<Menu> getLiMenu() {
		return liMenu;
	}

	public void setLiMenu(List<Menu> liMenu) {
		this.liMenu = liMenu;
	}

	public Menu() {
		super();
	}

	public Menu(String name, Long menuId, String type, String value, String url, Long pid, String dataType,
			List<Menu> liMenu) {
		super();
		this.name = name;
		this.menuId = menuId;
		this.type = type;
		this.value = value;
		this.url = url;
		this.pid = pid;
		this.dataType = dataType;
		this.liMenu = liMenu;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	@Override
	public String toString() {
		return "Menu [name=" + name + ", menuId=" + menuId + ", type=" + type + ", value=" + value + ", url=" + url
				+ ", pid=" + pid + ", dataType=" + dataType + ", liMenu=" + liMenu + "]";
	}

	

	

	
	
	
}
