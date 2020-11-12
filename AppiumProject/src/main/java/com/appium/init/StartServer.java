package com.appium.init;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;

/**
 * 代码启动或关闭appium服务
 */
public class StartServer {
    AppiumDriverLocalService appiumDriverLocalService;
    //启动appium服务
    public void startService(){
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        //指定appium生成日志的路径
        builder.withLogFile(new File("src/main/resources/logs/appium.log"));
        appiumDriverLocalService = builder.build();
        appiumDriverLocalService.start();
    }
    //关闭appium服务
    public void stopService(){
        appiumDriverLocalService.stop();
    }

}
