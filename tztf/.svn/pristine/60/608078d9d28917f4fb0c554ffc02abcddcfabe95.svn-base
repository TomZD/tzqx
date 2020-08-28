package cn.movinginfo.tztf.sev.domain;
import java.util.Date;

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
@Table(name = "sev_t_reminder")
public class Reminder extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @Transient
    private Suser Suser;

    //所属服务用户
    @Column(name = "suid")
    private Long suid;
    
    @Column(name = "type")
    @Dict(key=DictKeys.STRONG_WEATHER) 
    private Long type;

  

	//标题
    @Column(name = "title")
    private String title;

    //内容
    @Column(name = "content")
    private String content;
    
    //发布状态
    @Column(name = "is_pub")
    @Dict(key=DictKeys.BOOL_TYPE)  //2016-08-28 # 2366 # 韩明睿 - 前台取不到isPubStr，检查后发现少了这句与数据字典关联的代码
    private Integer isPub = 0;
    
    //发布时间
    @Column(name = "pub_date")
    private Date pubDate;

    /**
	 * @return the pubDate
	 */
	public Date getPubDate() {
		return pubDate;
	}

	/**
	 * @param pubDate the pubDate to set
	 */
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	/**
	 * @return the Suser
	 */
	public Suser getSuser() {
		return Suser;
	}

	/**
	 * @param Suser the Suser to set
	 */
	public void setSuser(Suser Suser) {
		this.Suser = Suser;
	}

	/**
	 * @return the isPub
	 */
	public Integer getIsPub() {
		return isPub;
	}

	/**
	 * @param isPub the isPub to set
	 */
	public void setIsPub(Integer isPub) {
		this.isPub = isPub;
	}

	public void setSuid(Long suid){
        this.suid = suid;
    }

    public Long getSuid(){
        return suid;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getContent(){
        return content;
    }
    
    public Long getType() {
  		return type;
  	}

  	public void setType(Long type) {
  		this.type = type;
  	}
}
