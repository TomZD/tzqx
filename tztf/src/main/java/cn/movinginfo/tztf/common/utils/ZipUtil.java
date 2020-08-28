package cn.movinginfo.tztf.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FilenameUtils;

/**
 * zip压缩文件工具类
 * @author Nirvana.Titan
 *
 */
public class ZipUtil {
    /**
     * 压缩单一文件
     * @param src 被压缩文件路径
     * @param output 压缩后的文件路径
     * @throws IOException
     */
    public static void zipSingleFile(String src, String output) throws IOException{
        FileInputStream in = new FileInputStream(src);

        // out put file 
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(output));
        
        String innerFileName = FilenameUtils.getName(src);
        // name the file inside the zip  file 
        out.putNextEntry(new ZipEntry(innerFileName)); 

        // buffer size
        byte[] b = new byte[1024*1024];
        int count;

        while ((count = in.read(b)) > 0) {
            out.write(b, 0, count);
        }
        out.close();
        in.close();
    }
    
    public static void zipSingleFile(File src, File output) throws IOException {
        FileInputStream in = new FileInputStream(src);

        // out put file 
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(output));
        
        String innerFileName = FilenameUtils.getName(src.getPath());
        out.putNextEntry(new ZipEntry(innerFileName)); 

        // buffer size
        byte[] b = new byte[1024*1024];
        int count;

        while ((count = in.read(b)) > 0) {
            out.write(b, 0, count);
        }
        out.close();
        in.close();
    }
    
    public static void zipSingleFile( File src
                                    , File output
                                    , String innerFileName ) 
                                    throws IOException {
        
        FileInputStream in = new FileInputStream(src);

        // out put file 
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(output));
        out.putNextEntry(new ZipEntry(innerFileName)); 

        // buffer size
        byte[] b = new byte[1024*1024];
        int count;

        while ((count = in.read(b)) > 0) {
            out.write(b, 0, count);
        }
        out.close();
        in.close();
    }
    
}
