package com.appium.testng;

import com.appium.init.InitDriver;
import com.appium.init.StartServer;
import com.appium.testcase.DemoCase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RyhApp {

    DemoCase ryhcase;

    StartServer startServer;

    AppiumDriver<MobileElement> driver;

    @BeforeClass
    public void init() throws Exception {
        //启动appium服务
        startServer = new StartServer();
        startServer.startService();
        Thread.sleep(5000);
        driver = InitDriver.initDriverWithInstall("emulator-5554","com.renmai.easymoney","com.renmai.easymoney.MainActivity");
        ryhcase = new DemoCase(driver);
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    }
    @Test
    public void test001_login() {
        try {
            ryhcase.login();
            Assert.assertEquals(driver.getPageSource().contains("有问题找客服："),true);
        } catch (Exception e) {
            File file = driver.getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(file,new File("src/main/resources/images/login.png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Assert.fail("登录失败！");
        }
    }
    @Test(enabled = true)
    public void test002_idoAuthAndRisk(){
        try {
            ryhcase.idoAuthAndSupplementInfo();
        } catch (Exception e) {
            File file = driver.getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(file,new File("src/main/resources/images/idnoAndRisk.png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Assert.fail("实名或授信风控失败！");
        }

    }
    @Test(enabled = true)
    public void test003_withdrawApply(){
        try {
            ryhcase.withdrawApply();
            Assert.assertEquals(driver.getPageSource().contains("额度已用完"),true);
        } catch (Exception e) {
            File file = driver.getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(file,new File("src/main/resources/images/withdrawApply.png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Assert.fail("提现失败！");
        }
    }

    @Test
    public void test004_goRepay(){
        try {
            ryhcase.goRepay();
            Assert.assertEquals(driver.getPageSource().contains("借款列表"),true);
        } catch (InterruptedException e) {
            File file = driver.getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(file,new File("src/main/resources/images/repay.png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Assert.fail("还款失败！");
        }
    }

    @AfterClass
    public void destroy(){
        driver.quit();
        startServer.stopService();
    }

}
