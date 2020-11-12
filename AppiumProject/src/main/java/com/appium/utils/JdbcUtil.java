package com.appium.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {

    //声明四个变量并进行初始化；
    private static String driver=null;
    private static String username=null;
    private static String password=null;
    private static String url=null;

    //从database.properties配置文件中读取数据并赋予声明的变量
    static{
        try {
            Properties pros = new Properties();
            InputStream ins = JdbcUtil.class.getClassLoader().getResourceAsStream("database.properties");
            pros.load(ins);
            driver = pros.getProperty("driver");
            url = pros.getProperty("url");
            username = pros.getProperty("username");
            password = pros.getProperty("password");
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //创建Connection对象用于java与数据库的交互
    public static Connection getConnection() throws SQLException {
       return DriverManager.getConnection(url,username,password);
    }
}
