package cn.movinginfo.tztf.common.utils;

import java.util.List;

import cn.movinginfo.tztf.webserviceClient.dingding.DingDingService;
import cn.movinginfo.tztf.webserviceClient.dingding.DingDingServiceService;

public class DingDingUtil {
	public static String getReadState(String chatIds, String msgIds) {
		String result = "";
		try {
			String[] chatIdArrays=chatIds.split(",");
			String[] msgIdArrays=msgIds.split(",");
			int numOfGroup = 0;
			int numOfRead = 0;
			DingDingServiceService dingdingService = new DingDingServiceService();
			DingDingService port = dingdingService.getDingDingServicePort();
			// 先获取token值
			String token = port.getToken();
			for (String chatId : chatIdArrays) {
				List<String> memberIdList = port.getGroupMembers(token, chatId);
				if (memberIdList != null) {
					numOfGroup+=memberIdList.size();
				}
			}
			for(String msgId:msgIdArrays)
			{
				List<String> msgIdList = port.getReadList(token, msgId);
				if(msgIdList!=null)
				{
					numOfRead+=msgIdList.size();
				}
			}
			result = "(发送:" + numOfGroup + "已读:" + numOfRead + ")";
		} catch (Exception ex) {
			result = "(获取发送和已读失败)";
		}
		return result;
	}
}
