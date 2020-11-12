package com.appium.init;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * ios初始化driver并返回driver对象
 */
public class IOSInitDriver {

    //公共参数封装，并返回caps参数
    public static DesiredCapabilities getCommons(String udid){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.IOS);
        caps.setCapability(MobileCapabilityType.DEVICE_NAME,"iPhone X");
        caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,600);
        caps.setCapability(MobileCapabilityType.UDID,udid);
        return caps;
    }

    /**
     *
     * @param udid:设备唯一标志
     * @param appPath：app路径
     * @return
     * @throws MalformedURLException
     */
    public AppiumDriver<MobileElement> getIosDriverWithNotInstall(String udid,String appPath) throws MalformedURLException {
        DesiredCapabilities caps = getCommons(udid);
        caps.setCapability(MobileCapabilityType.APP,appPath);
        caps.setCapability(MobileCapabilityType.NO_RESET,false);
        caps.setCapability(IOSMobileCapabilityType.USE_NEW_WDA,false);
        //ios真机的webview页面执行appium自动化需要使用ios_webkit_debug_proxy，需要先安装，然后在这里配置true
        caps.setCapability(IOSMobileCapabilityType.START_IWDP,true);
        AppiumDriver<MobileElement> driver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),caps);
        return driver;
    }

}
