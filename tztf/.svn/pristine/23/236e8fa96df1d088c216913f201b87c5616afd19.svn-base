package cn.movinginfo.tztf.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.fit.cssbox.demo.ImageRenderer;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Html2ImgUtil {
	public static String getHtmlContent(String content){
		String result = "";
		return result;
	}
	
	public static String getHnrb(String url){
		String result = null;
		WebClient client = new WebClient(BrowserVersion.CHROME);
		client.getOptions().setJavaScriptEnabled(false); // 默认执行js，如果不执行js，则可能会登录失败，因为用户名密码框需要js来绘制。
		HtmlPage page;
		try {
			page = client.getPage(url);
			HtmlAnchor htmlAnchor = null;
			try{
				htmlAnchor =  page.getAnchorByText("天气预报");
			}catch(ElementNotFoundException e){
				e.printStackTrace();
			}
			if(htmlAnchor!=null){
				String contenturl = url.replace("node_1.htm", htmlAnchor.getHrefAttribute()) ;
				page = client.getPage(contenturl);
				result = page.asText();
				int startIndex = result.indexOf("天气预报")+1;
				int endIndex = result.indexOf("全市动员");
				result = result.substring(startIndex, endIndex).trim();
			}
			client.close();
			//client.closeAllWindows();
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String generateHtmlImg(String url) throws Exception{
		String result = null;
		ImageRenderer render = new ImageRenderer();
        System.out.println("start...");
        url = "http://www.hnqxw.com/?m=forecast";
        result = "D:" + File.separator + "html.png";
        FileOutputStream out = new FileOutputStream(new File(result));
        render.renderURL(url, out, ImageRenderer.Type.PNG);
        out.close();
        System.out.println("OK");
        return result;
	}
	public static void main(String[] args) throws Exception {
		getHnrb("http://hnrb.zjol.com.cn/hnrb/html/2018-05/08/node_1.htm");
    }

}
