package cn.movinginfo.tztf.sys.action;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.movinginfo.tztf.sys.domain.Record;
import cn.movinginfo.tztf.sys.service.RecordService;

@Controller
@RequestMapping("/sys/record")
public class RecordAction {
	
	private RecordService recordService;
	
	@RequestMapping("getRecord")
	public List<Record> getRecordList() {
		List<Record> result = recordService.getRecordList();
		return result;
	}
}
