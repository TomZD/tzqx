package cn.movinginfo.tztf.common.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;


public class StaticStation {
	public static final Map<String,String> CACHE=new HashMap<String,String>();//站号站名键值对
	
	// MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
//    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
//    static final String DB_URL = "jdbc:mysql://localhost:3306/tztf?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
    
    static final String JDBC_DRIVER = ConfigHelper.getProperty("JDBC_DRIVER");  
    static final String DB_URL = ConfigHelper.getProperty("DB_URL");
 
    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    //static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    //static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&serverTimezone=UTC";
 
 
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = ConfigHelper.getProperty("USER");
    static final String PASS = ConfigHelper.getProperty("PASS");
 
    static {
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
        
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT iiiii, station_name FROM sen_t_tabstation";
            ResultSet rs = stmt.executeQuery(sql);
        
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                String iiiii = rs.getString("iiiii");
                String name = rs.getString("station_name");
    
                // 输出数据
                System.out.print("ID: " + iiiii);
                System.out.print(", 站点名称: " + name);
                System.out.print("\n");
                CACHE.put(iiiii, name);
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        //System.out.println("Goodbye!");
    }
    public static void main(String[] args) {
		String a = CACHE.get("K8201");
		System.out.println(a);
	}
}
