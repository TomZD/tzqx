package cn.movinginfo.tztf.common.utils;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import cn.movinginfo.tztf.common.utils.ConfigHelper;

/**
 * Author: ZhangKX
 * Created by Administrator on 2019/11/1.
 * Reamrk:
 */

import java.io.IOException;

/**
 * @author  2019/6/23 15:32
 */
public class MergeImg {
    private BufferedImage getImageFromLocal(String path) throws IOException {
        return ImageIO.read(new File(path));

    }

    private BufferedImage mergeImage(BufferedImage img1, BufferedImage img2) {

            int width = img1.getWidth();
            int height = img1.getHeight();

            Graphics2D graphics2D = img2.createGraphics();
            graphics2D.drawImage(img1, 0, 0, width, height, null);
            graphics2D.dispose();

        return img2;
    }

    private void writeImageToLocal(String newPath, BufferedImage img) throws IOException {

            ImageIO.write(img, "png", new File(newPath));

    }

    public void saveMergeImage(String path1, String path2, String newPath) {


        try {
            writeImageToLocal(newPath, mergeImage(getImageFromLocal(path1), getImageFromLocal(path2)));

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MergeImg img = new MergeImg();
        String path1 = "C:\\Users\\Administrator\\Desktop\\save.png";
        String path2 = "C:\\Users\\Administrator\\Desktop\\3.png";
        String newPath = "C:\\Users\\Administrator\\Desktop\\" + System.currentTimeMillis() + ".png";
        img.saveMergeImage(path1, path2, newPath);

    }
}

