package cn.movinginfo.tztf.releasechannel.support;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;



public class FtpHelper {
    
    private static final Logger log = Logger.getLogger(FtpHelper.class);
    
    private FTPClient mFtp = new FTPClient();
    
    public void init() throws NumberFormatException, IOException, SocketException {
        InputStream inputstream = null;
        try {
            inputstream = this.getClass().getResourceAsStream("/channel/12379-tlq.properties");
            Properties prop = new Properties();
            prop.load(inputstream);
            
            String userName = prop.getProperty("ftpUsername").trim();
            String password = prop.getProperty("ftpPassword").trim();
            String host = prop.getProperty("ftpAddr").trim();
            String port = prop.getProperty("ftpPort").trim();
            
            int portNum = -1;
            try {
                portNum = Integer.parseInt(port);
            } catch (NumberFormatException e){
                log.warn("12379-tlq.properties 配置文件中port配置项无法转换为整数类型");
                log.debug(e.getStackTrace());
                throw new NumberFormatException("12379-tlq.properties 配置文件中port配置项无法转换为整数类型");
            }
            
            mFtp.connect(host);
            mFtp.setDefaultPort(portNum);
            
            mFtp.login(userName, password);
            mFtp.setFileType(FTP.BINARY_FILE_TYPE);
            //mFtp.setFileTransferMode(FTP.BINARY_FILE_TYPE);
        } finally {
            if(inputstream!=null)
                inputstream.close();
        }
    }
    
    public void close() {
        if (mFtp.isConnected()) {
            try {
                mFtp.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
                log.info("ftp关闭异常！" + e.getMessage());
                log.debug(e.getStackTrace());
            }
        }
    }
    
    public String getRecvFile(String fileStartName) throws IOException {
        mFtp.changeWorkingDirectory("rcv");
        
        FTPFile[] ftpFiles = mFtp.listFiles();
        for(FTPFile file: ftpFiles){
            if(file.getName().startsWith(fileStartName) && file.getName().endsWith(".xml")) {
                File tmp = File.createTempFile("ne-feedback-" + FilenameUtils.getBaseName(file.getName()) + "_", ".xml");
                FileOutputStream outputStream = new FileOutputStream(tmp);
                boolean ok = mFtp.retrieveFile(file.getName(), outputStream);
                log.info("ftp下载文件是否成功：" + ok);
                log.info("ftp状态：" + mFtp.getReplyString());
                log.info("ftp获取了回执文件：" + file.getName() + "，并且复制为临时文件：" + tmp.getAbsolutePath());
                return tmp.getAbsolutePath();
            }
        }
        
        return StringUtils.EMPTY;
    }
    
    public void upload(String fileName) throws Exception {
        
        InputStream inputstream = this.getClass().getResourceAsStream("/channel/12379-tlq.properties");
        
        Properties prop = new Properties();
        prop.load(inputstream);
        
        String userName = prop.getProperty("ftpUsername").trim();
        String password = prop.getProperty("ftpPassword").trim();
        String host = prop.getProperty("ftpAddr").trim();
        String port = prop.getProperty("ftpPort").trim();
        int portNum = -1;
        try {
            portNum = Integer.parseInt(port);
        } catch (Exception e){
            
            log.warn("12379-tlq.properties 配置文件中port配置项无法转换为整数类型");
            log.debug(e.getStackTrace());
            
            throw new Exception("12379-tlq.properties 配置文件中port配置项无法转换为整数类型");
        }
        
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            
            ftp.connect(host);
            ftp.setDefaultPort(portNum);

            ftp.login(userName, password);
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            //ftp.setFileType(FTP.BINARY_FILE_TYPE, FTP.BINARY_FILE_TYPE);
            //ftp.setFileTransferMode(FTP.BINARY_FILE_TYPE);
            String fileNameShort = FilenameUtils.getName(fileName);
            File f = new File(fileName);
            FileInputStream inputStream = new FileInputStream(f);
            ftp.changeWorkingDirectory("alert");
            boolean ok = false;
            try{
                ok = ftp.storeFile(fileNameShort, inputStream);
                log.info("ftp上传文件成功！文件名：" + fileNameShort);
                reply = ftp.getReplyCode();
                System.out.println("ftp replyCode:" + reply);
                System.out.println("ftp replyMSG:" + ftp.getReplyString());
                log.info("ftp replyMSG:" + ftp.getReplyString());
            } catch (Exception e) { 
                log.warn("ftp上传文件出错！" + e.getMessage());
                log.debug(e.getStackTrace());
                throw new Exception("ftp上传文件出错！" + e.getMessage());
            } finally {
                inputStream.close();
            }
            
            reply = ftp.getReplyCode();
            System.out.println("ftp replyCode:" + reply);
            System.out.println("ftp replyMSG:" + ftp.getReplyString());
            log.info("ftp replyMSG:" + ftp.getReplyString());
            String replyStr = ftp.getReplyString();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
            }
            
            if(!ok) {
                throw new Exception("FTP上传失败！replayCode：" + reply + ", replyMsg:" + replyStr);
            }
           
        } catch (IOException e) {
            log.debug("ftp fail!", e);
            throw new Exception("FTP上传失败！" + e.getMessage());
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                    log.info("ftp关闭异常！" + e.getMessage());
                    log.debug(e.getStackTrace());
                }
            }
        }
    }
    
    public String download(String fileName){
        
        return "";
    }
    
    public boolean checkExists(String fileName) throws Exception {

        InputStream inputstream = this.getClass().getResourceAsStream("/channel/12379-tlq.properties");

        Properties prop = new Properties();
        prop.load(inputstream);

        String userName = prop.getProperty("ftpUsername").trim();
        String password = prop.getProperty("ftpPassword").trim();
        String host = prop.getProperty("ftpAddr").trim();
        String port = prop.getProperty("ftpPort").trim();
        int portNum = -1;
        try {
            portNum = Integer.parseInt(port);
        } catch (Exception e) {

            log.warn("12379-tlq.properties 配置文件中port配置项无法转换为整数类型");
            log.debug(e.getStackTrace());

            throw new Exception("12379-tlq.properties 配置文件中port配置项无法转换为整数类型");
        }

        FTPClient ftp = new FTPClient();
        try {

            ftp.connect(host);
            ftp.setDefaultPort(portNum);

            ftp.login(userName, password);
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
//            ftp.setFileType(FTP.BINARY_FILE_TYPE, FTP.BINARY_FILE_TYPE);
//            ftp.setFileTransferMode(FTP.BINARY_FILE_TYPE);
            String fileNameShort = FilenameUtils.getName(fileName);
            FTPFile[] ftpFiles = ftp.listFiles();
            for(FTPFile f2: ftpFiles){
                if(f2.isFile() &&  f2.getName().equals(fileNameShort)) {
                    return true;
                }
            }
            return false;

        } catch (IOException e) {
            log.debug(e.getStackTrace());
        } finally {
            int reply = ftp.getReplyCode();

            if (!FTPReply.isPositiveCompletion(reply)) {
                log.info("ftp回应了消极信息！");
                log.info("ftp reply msg:" + ftp.getReplyString());
                ftp.disconnect();
            }
            
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                    log.info("ftp关闭异常！" + e.getMessage());
                    log.debug(e.getStackTrace());
                }
            }
        }

        return true;
    }
    
    public String getLatestRecvFile(String fileName) throws Exception {
        InputStream inputstream = this.getClass().getResourceAsStream("/channel/12379-tlq.properties");
        
        Properties prop = new Properties();
        prop.load(inputstream);
        
        String userName = prop.getProperty("ftpUsername").trim();
        String password = prop.getProperty("ftpPassword").trim();
        String host = prop.getProperty("ftpAddr").trim();
        String port = prop.getProperty("ftpPort").trim();
        int portNum = -1;
        try {
            portNum = Integer.parseInt(port);
        } catch (Exception e){
            log.warn("12379-tlq.properties 配置文件中port配置项无法转换为整数类型");
            log.debug(e.getStackTrace());
            throw new Exception("12379-tlq.properties 配置文件中port配置项无法转换为整数类型");
        }
        
        FTPClient ftp = new FTPClient();
        FTPClientConfig config = new FTPClientConfig();
        try {
            int reply;
            
            ftp.connect(host);
            ftp.setDefaultPort(portNum);

            ftp.login(userName, password);
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            //ftp.setFileTransferMode(FTP.BINARY_FILE_TYPE);
            ftp.changeWorkingDirectory("rcv");
//            try{
//                FTPFile[] ftpFiles = ftp.listFiles();
//                for(FTPFile ftpFile: ftpFiles) {
//                    String ftpFileName = ftpFile.getName();
//                    
//                    String modTime = ftp.getModificationTime(ftpFile.getName());
//                    
//                }
//                ftp.retrieveFile(remote, local)(fileNameShort, outputStream);
//                log.info("ftp上传文件成功！文件名：" + fileNameShort);
//            } catch (Exception e) { 
//                log.warn("ftp上传文件出错！" + e.getMessage());
//                log.debug(e.getStackTrace());
//                throw new Exception("ftp上传文件出错！" + e.getMessage());
//            } finally {
//                inputStream.close();
//            }
            
            reply = ftp.getReplyCode();

            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
            }
           
        } catch (IOException e) {
            log.debug(e.getStackTrace());
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                    log.info("ftp关闭异常！" + e.getMessage());
                    log.debug(e.getStackTrace());
                }
            }
        }
        
        return "";
    }
    
    private boolean compareFileName(String x, String y) {
        
        return false;
    }
}
