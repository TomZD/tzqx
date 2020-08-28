package cn.movinginfo.tztf.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jcifs.UniAddress;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFileOutputStream;
import jcifs.smb.SmbSession;

/** 
 * @author  作者 E-mail: yougq
 * @version 创建时间：2018年4月9日 上午12:11:46 
 * 类说明         保存至共享目录
 */
public class FilesUtils {
    
    /**
     * @param localDir   上传文件本地路径
     * @param proTemplate 
     * @return
     * @throws Exception
     */
	public static boolean smbUploading(String localDir) throws Exception {
		boolean flag = true;
		String allAddress = "\\\\172.41.129.111\\modle\\Outputs\\RealTime\\Data\\";
		Pattern p = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
		Matcher m = p.matcher(allAddress);

		String removeIp = "";
		if (m.find()) {
			removeIp = m.group();
		}
		String removeDir = allAddress.replaceAll("\\\\", "/");
		String removeLoginUser = "yb";
		String removeLoginPass = "1212";
		// String removeLoginUser = "jinhua";
		// String removeLoginPass = "xC123456";

		NtlmPasswordAuthentication auth = null;
		OutputStream out = null;
		SmbFileOutputStream smb = null;
		File file = new File(allAddress);
		File[] files = file.listFiles();
		Date time = new Date();
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd:HH");
		  String t = sdf.format(time);
		  String[] times = t.split(":");
		  String ts = times[0]+times[1]+times[2]+times[3];
		for (int a = 0; a < files.length; a++) {
		  if (files[a].isDirectory()) {
              //确认目标文件的需要被复制到的位置,它肯定是在目标文件夹下面
			  if(files[a].getName().equals(ts)) {
			  String source1 = allAddress+File.separator+files[a].getName();
			  String target1 = localDir+File.separator+files[a].getName();
			  copyDir(source1,target1);
              break;
			  }
          }
		  
		}
//        InputStream in = null;
		try {
			File dir = new File(localDir);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			InetAddress ip = InetAddress.getByName(removeIp);
			UniAddress address = new UniAddress(ip);
			// 权限验证
			auth = new NtlmPasswordAuthentication(removeIp, removeLoginUser, removeLoginPass);
			SmbSession.logon(address, auth);
			// 根据文件创建文件的输入流
//            in = new FileInputStream(file);
//            // 创建字节数组
//            byte[] data = new byte[1024];
//            // 读取内容，放到字节数组里面
//            in.read(data);
//            System.out.println(new String(data));
			
			// 文件路径是否合法
			/*String url = "http:" + removeDir + dir.getName();
			SmbFile remoteFile = new SmbFile(url, auth);
			remoteFile.connect();
			if (remoteFile.isDirectory()) {
				flag = false;
			}
			smb = new SmbFileOutputStream(remoteFile);
			// 向远程共享目录写入文件
			out = new BufferedOutputStream(smb);
			out.write(toByteArray(dir));*/
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		} finally {
			if (smb != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.gc();
		return flag;
	}
    @SuppressWarnings("resource")
    public static byte[] toByteArray(File file) throws IOException {
        FileChannel fc = null;
        try {
            fc = new RandomAccessFile(file, "r").getChannel();
            MappedByteBuffer byteBuffer = fc.map(MapMode.READ_ONLY, 0,
                    fc.size()).load();
            
            byte[] result = new byte[(int) fc.size()];
            if (byteBuffer.remaining() > 0) {
                byteBuffer.get(result, 0, byteBuffer.remaining());
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                fc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static void copyDir(String source1, String target1) throws IOException {
        File source = new File(source1);
        File target = new File(target1);
        target.mkdirs();
        File[] files = source.listFiles();
        for(int a=0;a<files.length;a++){
            if(files[a].isFile()){
                if(files[a].getName().endsWith(".000")) {
                	System.out.println(files[a].getName());
                	 File target2 = new File(target,files[a].getName());
                	copyFile(files[a], target2);
                }
            }
        }
}
    
    private static void copyFile(File file, File target)
            throws IOException {
        BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(file));
        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(target));
        byte[] buf = new byte[1024];
        int len = 0;
        while((len=bis.read(buf))!= -1){
            bos.write(buf, 0,len);
        }
        bis.close();
        bos.close();
    }
    
   public static void main(String[] args) throws Exception {
	   FilesUtils.smbUploading("F:/csjl");
	   
}
}


