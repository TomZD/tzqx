/**
 * 
 */
package cn.movinginfo.tztf.common;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

/**************************************
 * @author  yougq
 * @version 创建时间：2018年10月17日 下午18:18:19 类说明: mybatis逆向工程函数
 ***************************************
 */

public class GenMain {
	public static void main(String[] args) throws UnsupportedEncodingException{
		System.out.println("mybatis逆向开始.......");
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		String genCfg = GenMain.class.getResource("/config/MySqlConfiguration.xml").getFile();
		genCfg = java.net.URLDecoder.decode(genCfg, "utf-8");
		File configFile = new File(genCfg);
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = null;
		try {
			config = cp.parseConfiguration(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XMLParserException e) {
			e.printStackTrace();
		}
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = null;
		try {
			myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
		try {
			myBatisGenerator.generate(null);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}