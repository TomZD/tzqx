/**
 * 
 */
package cn.movinginfo.tztf.common.utils;

import java.util.List;

/**
 *  @author yougq
 *	2019年3月27日下午3:04:25
 */
public class MsgDomain {
	private String uuid;
	private List<String> tels;
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public List<String> getTels() {
		return tels;
	}
	public void setTels(List<String> tels) {
		this.tels = tels;
	}

}
