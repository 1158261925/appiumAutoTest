package com.appium.test;

import com.appium.utils.GetByLocation;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestNGDemo {

    /**
    @DataProvider(name = "provider")
    public Object[][] getData(){
        Object[][] arr = {{"正确的用户名","错误的密码","请重新输入密码"},
                {"正确的用户名","正确的密码","登录成功"},
                {"错误的用户名","正确的密码","请重新输入用户名"}
        };
        return arr;
    }
     */

    @Parameters({"myparam"})
    @Test
    public void test(String param){
        System.out.println("输出的结果为："+param);
    }

    @Test
    public void demo(){
        Properties pro = new Properties();
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("./uiElement/login.properties");
        try {
            pro.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String flag = pro.getProperty("mBtAgree4logSuccess");
        System.out.println(flag);
    }
}
