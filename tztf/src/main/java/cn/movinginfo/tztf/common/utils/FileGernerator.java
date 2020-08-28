package cn.movinginfo.tztf.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.FieldMergingArgs;
import com.aspose.words.Font;
import com.aspose.words.IFieldMergingCallback;
import com.aspose.words.ImageFieldMergingArgs;
import com.aspose.words.License;
import com.aspose.words.RelativeHorizontalPosition;
import com.aspose.words.RelativeVerticalPosition;
import com.aspose.words.SaveFormat;
import com.aspose.words.SaveOptions;
import com.aspose.words.TextFormFieldType;
import com.aspose.words.WrapType;

import cn.movinginfo.tztf.common.constants.SystemProperties;
import cn.movinginfo.tztf.sev.domain.Alarm;
import cn.movinginfo.tztf.sys.domain.Decision;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.domain.ReleaseChannel;
import cn.movinginfo.tztf.sys.service.ReleaseChannelService;


public class FileGernerator {
	
	public static String creatFile(String file, List<String[]> lists, String downFileName, List<ReplaceData> rls,
			int column, String fileTemplPath, String head, ServletContext context, String image, List<ReleaseChannel> allReleaseChannel, List<Decision> decisions) throws Exception {
		String template = "";
		try {
			// 复制文件模版
			template = copyFile(file, downFileName, fileTemplPath, context);
			// 替换模板内容
			if (StringUtils.isBlank(template)) {
				throw new Exception("无法复制文件模板！");
			}

			if (!getLicense()) {
				return "";
			}

			// ---------------------------------------------
			Document doc = new Document(template);
			DocumentBuilder builder = new DocumentBuilder(doc);
			if(!image.equals("")){
				File imagefile=new File(image);
				if(imagefile.exists()){
					if (!Utils.isEmpty(image)) {// 插入图片
						builder.moveToBookmark("image");
						builder.insertImage(image, 100, 100, 100, 5, 150,
								128, WrapType.TIGHT);
						
					}
				}
			}

			builder.moveToBookmark("begin");
			    if(allReleaseChannel!=null){
			    	for(ReleaseChannel r:allReleaseChannel){

				    	if(r.getValid()==1){
				    		builder.insertImage("D:\\hytfsource\\word\\true.png");
				    	}else{
				    		builder.insertImage("D:\\hytfsource\\word\\false.png");
				    	}
				    	builder.write(r.getName());
						
					}	
			    }
			    
			builder.moveToBookmark("smsgroup");
			if(allReleaseChannel!=null){
		    	for(Decision d:decisions){
			    	if(d.getValid()==1){
			    		builder.insertImage("D:\\hytfsource\\word\\true.png");
			    	}else{
			    		builder.insertImage("D:\\hytfsource\\word\\false.png");
			    	}
			    	builder.write(d.getName());	
					
				}	
		    }
			// 替换表头
			if (!Utils.isEmpty(rls)) {
				String[] fieldNames = new String[rls.size()];
				Object[] fieldValues = new Object[rls.size()];
				for (int i = 0; i < rls.size(); i++) {
					if (!Utils.isEmpty(rls.get(i).getName())) {
						fieldNames[i] = rls.get(i).getName();
						if (!Utils.isEmpty(rls.get(i).getContent())) {
							fieldValues[i] = rls.get(i).getContent();
						} else {
							fieldValues[i] = "";
						}
					}
				}
				doc.getMailMerge().setFieldMergingCallback(new HandleMergeField());
				doc.getMailMerge().execute(fieldNames, fieldValues);
			}
			try {
				doc.save(template, SaveOptions.createSaveOptions(SaveFormat.DOC));
			} catch (Exception e) {
				// TODO: handle exception
				if (e.getMessage().indexOf("拒绝访问") > -1) {
					Thread.sleep(100);
					doc.save(template, SaveOptions.createSaveOptions(SaveFormat.DOC));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("操作文件出错");
		}

		return template;
	}
	
	public static String creatFile2(Alarm alarm,Depart dept,String downFileName) throws Exception {
		SimpleDateFormat sdf2 =   new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar c = Calendar.getInstance();
		String pdate = sdf2.format(alarm.getPubDate());
		Integer duration = Integer.valueOf(
				alarm.getDuration());
		Date date = sdf2.parse(pdate);
		c.setTime(date);
		c.add(Calendar.HOUR, duration);
		Date enddate = c.getTime();
		String edate=sdf2.format(enddate);
		String pedate=pdate+"  至  "+edate;
		String pubRange=alarm.getPubRange();
		JSONObject o=new JSONObject();
		o.put("status",alarm.getStatus());
		o.put("signal",alarm.getAlarmTypeName());
		o.put("warning",alarm.getContent());
		o.put("startStopTime",pedate);
		o.put("distribution",pubRange);
		o.put("depart",dept.getName());
		o.put("phone", dept.getPhone());
		String path = SystemProperties.APP_PATH +"/WEB-INF/content/sev/alarm/form.vm";
		String newPath = SystemProperties.APP_PATH+"/WEB-INF/content/sev/alarm/"+downFileName+".html";
		VmToHtml.doRelease(path,newPath,o);
		String nPath =SystemProperties.APP_PATH+"/WEB-INF/content/sev/alarm";
		String loadPath = SystemProperties.APP_PATH + "/fileDownload/";
		System.out.println(loadPath);
		VmToHtml.getNew(nPath,loadPath);

		return downFileName;
	}

	public static String copyFile(String file, String fileName, String fileTemplPath, ServletContext context) {
		try {
			String newPath = "";
			int bytesum = 0;
			int byteread = 0;
			file = context.getRealPath("/fileModel") + File.separator + file;
			File oldfile = new File(file);
			boolean s = oldfile.exists();
			if (oldfile.exists()) { // 文件存在时
				createFiledir(fileTemplPath);
				newPath = fileTemplPath + fileName + ".doc";
				InputStream inStream = new FileInputStream(file); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					fs.write(buffer, 0, byteread);
				}
				fs.close();
				inStream.close();
				return newPath;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}
	
	public static String creatHtmlFile(String path,String file, List<String[]> lists, String downFileName, List<ReplaceData> rls,
			int column, String fileTemplPath, String head, ServletContext context, String image) throws Exception {

		String template = "";
		try {
			// 替换模板内容
			if (StringUtils.isBlank(path)) {
				throw new Exception("无法复制文件模板！");
			}

			if (!getLicense()) {
				return "";
			}

			// ---------------------------------------------
			Document doc = new Document(path);
			DocumentBuilder builder = new DocumentBuilder(doc);

			if (!Utils.isEmpty(image)) {// 插入图片
				builder.moveToBookmark("image");
				builder.insertImage(image, RelativeHorizontalPosition.MARGIN, 0, RelativeVerticalPosition.MARGIN, 5, 50,
						50, WrapType.TIGHT);
			}

			// 替换表头
			if (!Utils.isEmpty(rls)) {
				String[] fieldNames = new String[rls.size()];
				Object[] fieldValues = new Object[rls.size()];
				for (int i = 0; i < rls.size(); i++) {
					if (!Utils.isEmpty(rls.get(i).getName())) {
						fieldNames[i] = rls.get(i).getName();
						if (!Utils.isEmpty(rls.get(i).getContent())) {
							fieldValues[i] = rls.get(i).getContent();
						} else {
							fieldValues[i] = "";
						}
					}
				}
				doc.getMailMerge().setFieldMergingCallback(new HandleMergeField());
				doc.getMailMerge().execute(fieldNames, fieldValues);
			}
			try {
				doc.save(path, SaveOptions.createSaveOptions(SaveFormat.DOC));
				String newPath = fileTemplPath + downFileName + ".html";
				HtmlFileUtil.convert2Html(path, newPath);
			} catch (Exception e) {
				// TODO: handle exception
				if (e.getMessage().indexOf("拒绝访问") > -1) {
					Thread.sleep(100);
					doc.save(path, SaveOptions.createSaveOptions(SaveFormat.DOC));
					String newPath = fileTemplPath + downFileName + ".html";
					HtmlFileUtil.convert2Html(path, newPath);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("操作文件出错");
		}

		return template;
	}

	public static void createFiledir(String file) {
		try {
			File f = new File(file);
			if (f.exists()) {

			} else {
				f.mkdir();// 不存在则创建
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ReplaceData getReplaceData(String name, String content) {
		ReplaceData r = new ReplaceData();
		r.setName(name);
		r.setContent(content);
		return r;
	}

	public static boolean getLicense() {
		boolean result = false;
		try {
			InputStream is = FileGernerator.class.getClassLoader().getResourceAsStream("\\license.xml");
			License aposeLic = new License();
			aposeLic.setLicense(is);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private static class HandleMergeField implements IFieldMergingCallback {
		/**
		 * This handler is called for every mail merge field found in the
		 * document, for every record found in the data source.
		 */
		public void fieldMerging(FieldMergingArgs e) throws Exception {
			if (mBuilder == null)
				mBuilder = new DocumentBuilder(e.getDocument());

			// We decided that we want all boolean values to be output as check
			// box form fields.
			if (e.getFieldValue() instanceof Boolean) {
				// Move the "cursor" to the current merge field.
				mBuilder.moveToMergeField(e.getFieldName());

				// It is nice to give names to check boxes. Lets generate a name
				// such as MyField21 or so.
				String checkBoxName = java.text.MessageFormat.format("{0}{1}", e.getFieldName(), e.getRecordIndex());

				// Insert a check box.
				mBuilder.insertCheckBox(checkBoxName, (Boolean) e.getFieldValue(), 0);

				// Nothing else to do for this field.
				return;
			}

			// We want to insert html during mail merge.
			// if ("forecast1".equals(e.getFieldName()) ||
			// "content".equals(e.getFieldName()))
			// {
			// mBuilder.moveToMergeField(e.getFieldName());
			// mBuilder.insertHtml((String)e.getFieldValue(), true);
			// //mBuilder.pushFont();
			// }

			// Another example, we want the Subject field to come out as text
			// input form field.
			if ("Subject".equals(e.getFieldName())) {
				mBuilder.moveToMergeField(e.getFieldName());
				String textInputName = java.text.MessageFormat.format("{0}{1}", e.getFieldName(), e.getRecordIndex());
				mBuilder.insertTextInput(textInputName, TextFormFieldType.REGULAR, "", (String) e.getFieldValue(), 0);
			}
		}

		public void imageFieldMerging(ImageFieldMergingArgs args) throws Exception {
			// Do nothing.
		}

		private DocumentBuilder mBuilder;
	}
	
	public static String generateAlarmNoticeFile(List<ReplaceData> rls,String image,String templateFile,String modelFile ){
		String result = "";
		try {
			// 复制文件模版
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			result = templateFile;
			FileUtils.copyFile(new File(modelFile),
					new File(result));
			// 替换模板内容
			if (StringUtils.isBlank(result)) {
				throw new Exception("无法复制文件模板！");
			}

			if (!getLicense()) {
				return "";
			}
			Document doc = new Document(result);
			DocumentBuilder builder = new DocumentBuilder(doc);

			if (!Utils.isEmpty(image)) {// 插入图片
				builder.moveToBookmark("image");
				builder.insertImage(image, RelativeHorizontalPosition.MARGIN, 0, RelativeVerticalPosition.MARGIN, 5, 50,
						42, WrapType.TIGHT);
			}
			// 替换表头
			if (!Utils.isEmpty(rls)) {
				String[] fieldNames = new String[rls.size()];
				Object[] fieldValues = new Object[rls.size()];
				for (int i = 0; i < rls.size(); i++) {
					if (!Utils.isEmpty(rls.get(i).getName())) {
						fieldNames[i] = rls.get(i).getName();
						if (!Utils.isEmpty(rls.get(i).getContent())) {
							fieldValues[i] = rls.get(i).getContent();
						} else {
							fieldValues[i] = "";
						}
					}
				}
				doc.getMailMerge().setFieldMergingCallback(new HandleMergeField());
				doc.getMailMerge().execute(fieldNames, fieldValues);
			}
			try {
				doc.save(result, SaveOptions.createSaveOptions(SaveFormat.DOC));
				doc.save(result.replaceAll("doc", "pdf"), SaveOptions.createSaveOptions(SaveFormat.PDF));//另存为PDF
			} catch (Exception e) {
				// TODO: handle exception
				if (e.getMessage().indexOf("拒绝访问") > -1) {
					Thread.sleep(100);
					doc.save(result, SaveOptions.createSaveOptions(SaveFormat.DOC));
					doc.save(result.replaceAll("doc", "pdf"), SaveOptions.createSaveOptions(SaveFormat.PDF));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	

	public static void main(String[] args) {
		String inPath="D://20190902104244.doc";
		String outPath="D://20190902104244.tiff";
		
	         if (!getLicense()) { // 验证License 若不验证则转化出的pdf文档会有水印产生
	           
	                 return;
	         }
	        try {
	            long old = System.currentTimeMillis();
	            File file = new File(outPath); // 新建一个pdf文档
	            FileOutputStream os = new FileOutputStream(file);
	            Document doc = new Document(inPath); // Address是将要被转化的word文档
	            
	           
	            
	            doc.save(outPath, SaveFormat.PDF);// 全面支持DOC, DOCX,
	                                                            // OOXML, RTF HTML,
	                                                            // OpenDocument,
	                                                            // PDF, EPUB, XPS,
	                                                            // SWF 相互转换
	            long now = System.currentTimeMillis();
	            os.close();
	            
	           
	        } catch (Exception e) {
	            
	            e.printStackTrace();
	        }
	    }

	public static void getTifByDoc(String path) {
		String inPath=path+".doc";
		String outPath=path+".pdf";
		if (!getLicense()) { // 验证License 若不验证则转化出的pdf文档会有水印产生
	           
            return;
    }
   try {
       long old = System.currentTimeMillis();
       File file = new File(outPath); // 新建一个pdf文档
       FileOutputStream os = new FileOutputStream(file);
       Document doc = new Document(inPath); // Address是将要被转化的word文档
       
      
       
       doc.save(os, com.aspose.words.SaveFormat.PDF);// 全面支持DOC, DOCX,
                                                       // OOXML, RTF HTML,
                                                       // OpenDocument,
                                                       // PDF, EPUB, XPS,
                                                       // SWF 相互转换
       long now = System.currentTimeMillis();
       os.close();
       
      
   } catch (Exception e) {
       
       e.printStackTrace();
   }
}
		
	
	
	
}
