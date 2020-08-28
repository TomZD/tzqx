package cn.movinginfo.tztf.sev.action;

import java.util.List;

import cn.movinginfo.tztf.sev.mapper.AreaResult;
import cn.movinginfo.tztf.sys.domain.Depart;

public class AreaData {

	private AreaResult result;
	private List<Depart> depart;
	public AreaResult getResult() {
		return result;
	}
	public void setResult(AreaResult result) {
		this.result = result;
	}
	public List<Depart> getDepart() {
		return depart;
	}
	public void setDepart(List<Depart> depart) {
		this.depart = depart;
	}
}
