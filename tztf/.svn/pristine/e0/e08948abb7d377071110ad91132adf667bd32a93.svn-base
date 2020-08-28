package cn.movinginfo.tztf.common.fax;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.RequestProcessor;

public class MyRequestProcessor extends RequestProcessor {

	protected boolean processPreprocess(HttpServletRequest req, HttpServletResponse res) {
		try {
			req.setCharacterEncoding("GB2312");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return true;
	}
	
}
