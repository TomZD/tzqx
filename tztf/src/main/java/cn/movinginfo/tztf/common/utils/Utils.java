package cn.movinginfo.tztf.common.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
	
	private static Logger logger = LoggerFactory.getLogger(Utils.class);
	
	//2016-09-05 #无 by 朱潜  添加ISO_DATE_FORMAT作为通用日期格式
	public static final SimpleDateFormat ISO_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static final SimpleDateFormat DATE_MIN_CN_FORMAT = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
	
	public static boolean isEmpty(Enumeration e) {
		return e.hasMoreElements();
	}

	public static boolean isEmpty(String str) {
		return StringUtils.isEmpty(str);
	}

	public static boolean isEmpty(Collection c) {
		return c == null || c.isEmpty();
	}

	public static boolean isEmpty(Object[] objects) {
		return objects == null || objects.length == 0;
	}

	public static String join(Object[] objects, String split) {
		StringBuffer buf = new StringBuffer();
		if (!isEmpty(objects)) {
			for (Object obj : objects) {
				buf.append(obj.toString()).append(split);
			}
			buf.delete(buf.lastIndexOf(split), buf.lastIndexOf(split) + 1);
		}
		return buf.toString();
	}

	public static String join(Collection c, String split) {
		StringBuffer buf = new StringBuffer();
		if (!isEmpty(c)) {
			for (Object obj : c) {
				buf.append(obj.toString()).append(split);
			}
			buf.delete(buf.lastIndexOf(split), buf.lastIndexOf(split) + 1);
		}
		return buf.toString();
	}

	public static String emptySubstitute(String str, String str2) {
		if (isEmpty(str))
			return str2;
		return str;
	}

	public static InputStream loadResource(String name) {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		InputStream is = null;
		try {
			is = classLoader.getResourceAsStream(name);
		} catch (Exception e) {
			logger.error("loadResource", e);
		}

		if (is == null) {
			try {
				is = classLoader.getResourceAsStream("/" + name);
			} catch (Exception e) {
				logger.error("loadResource", e);
			}
		}
		return is;
	}

	public static int getInt(String str) {
		if (!Utils.isEmpty(str))
			return Integer.valueOf(str);
		return 0;
	}
	
	public String getString(String str){
		return str == null ? "" : str;
	}

	public static BufferedImage createImage(BufferedImage img, int width,
											int height, Color bgcolor) {
		int type = BufferedImage.TYPE_INT_ARGB;
		BufferedImage newImage = new BufferedImage(width, height, type);

		Graphics2D g = newImage.createGraphics();
		setRenderingHint(g);
		if (bgcolor != null) {
			g.setPaint(bgcolor);
			g.fillRect(0, 0, width, width);
		}
		g.drawImage(img, 0, 0, width, height, null);
		g.dispose();
		return newImage;
	}

	public static void setRenderingHint(Graphics2D g){
		Map<RenderingHints.Key, Object> m = new HashMap<RenderingHints.Key, Object>();
		m.put(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		m.put(RenderingHints.KEY_ALPHA_INTERPOLATION ,
				RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		m.put(RenderingHints.KEY_COLOR_RENDERING , RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		m.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		m.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		m.put(RenderingHints.KEY_DITHERING , RenderingHints.VALUE_DITHER_ENABLE);
		/*
		m.put(RenderingHints. , RenderingHints.);
		*/
		g.setRenderingHints(m);
	}

}
