package cn.movinginfo.tztf.common.utils;

import java.io.File;

public class Test2 {

///**
//     *
//     * @param content html body里面需要填充的内容
//     * @param fileName 文件名
//     * @param path 路径
//     * @return
//     */
//    public static boolean htmlToWord(){
//        try {
//            //模板
//            InputStream html=new FileInputStream("F:\\form.html");
//            String conte=getContent(html);
//            Document document=Jsoup.parse(conte);
////            org.jsoup.nodes.Element body=document.body();
////            body.html(content);
//            File file=new File("F:\\form.html");
//            FileWriter fileWriter=new FileWriter(file);
//            fileWriter.write(document.html());
//            fileWriter.close();
//            html.close();
//            File file1=new File("F:\\form.doc");
//            if(file.renameTo(file1)){
//                return true;
//            }else {
//                return false;
//            }
// 
//        } catch (Exception e) {
// 
//            e.printStackTrace();
//            return false;
//        }
//    }
// 
//    /**
//     * 把输入流里面的内容以UTF-8编码当文本取出。
//     * 不考虑异常，直接抛出
//     * @param ises
//     * @return
//     * @throws IOException
//     */
//    private static String getContent(InputStream... ises) throws IOException {
//        if (ises != null) {
//            StringBuilder result = new StringBuilder();
//            BufferedReader br;
//            String line;
//            for (InputStream is : ises) {
//                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//                while ((line=br.readLine()) != null) {
//                    result.append(line);
//                }
//            }
//            return result.toString();
//        }
//        return null;
//    }
//    
//    public static void main(String[] args) {
//    	htmlToWord();
//	}
	

public static void main(String[] args) {
		
		//要改的文件夹路径
		String path= "F:\\";
		getNew(path);
		
	}
	private static void getNew(String path) {
		File file = new File(path);
		//得到文件夹下的所有文件和文件夹
		String[] list = file.list();
		
		if(list!=null && list.length>0){
			for (String oldName : list) {
				File oldFile = new File(path,oldName);
				//判断出文件和文件夹
				if(!oldFile.isDirectory()){
					//文件则判断是不是要修改的
					if(oldName.contains(".html")){
						System.out.println(oldName);
						String newoldName = oldName.substring(0, oldName.lastIndexOf("."))+".doc";
						System.out.println(newoldName);
						File newFile = new File(path,newoldName);
						boolean flag = oldFile.renameTo(newFile);
						System.out.println(flag);
					}
				}
			}
		}
	}
}
