package com.appium.init;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

/**
 * driver初始化方法的封装，并返回driver
 */

public class InitDriver {
	
	//获取公共参数caps并返回
	public static DesiredCapabilities getCommons(String udid) {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.UDID, udid);
		caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 600);
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "任意安卓设备");
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD, true);
		caps.setCapability(AndroidMobileCapabilityType.RESET_KEYBOARD, true);
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
		caps.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS,true);
		caps.setCapability(AndroidMobileCapabilityType.NO_SIGN, true);
	  //caps.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE, "/usr/local/bin/chromedriver");
	  //caps.setCapability(AndroidMobileCapabilityType.RECREATE_CHROME_DRIVER_SESSIONS, true);
		caps.setCapability("chromedriverExecutableDir", "/usr/local/lib/node_modules/appium/node_modules/appium-chromedriver/chromedriver/mac");
		caps.setCapability("chromedriverChromeMappingFile", "/usr/local/lib/node_modules/appium/node_modules/appium-chromedriver/chromedriver/chromedriver_support.json");
		return caps;
	}
	//安装app并打开,每次打开都重置数据
	public static AppiumDriver<MobileElement> initDriverWithoutInstall(String udid, String appPath) throws Exception{
		DesiredCapabilities caps = getCommons(udid);
		File file = new File(appPath);
		caps.setCapability(MobileCapabilityType.APP, file.getAbsolutePath());
		caps.setCapability(MobileCapabilityType.NO_RESET, false);
		AppiumDriver<MobileElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		return driver;
	}
	/**
	 * 打开已安装的app,每次打开都重置数据（起始activity与等待后的activity一致的情况下）
	 * packageName=com.renmai.easymoney
	 * activity=packageName=com.renmai.easymoney.MainActivity
	 * deviceName=emulator-5554
	 */
	public static AppiumDriver<MobileElement> initDriverWithInstall(String udid,String appPackage,String activity) throws Exception{
		DesiredCapabilities caps = getCommons(udid);
		caps.setCapability(MobileCapabilityType.NO_RESET, false);
		caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appPackage);
		caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,activity);
		caps.setCapability(AndroidMobileCapabilityType.RECREATE_CHROME_DRIVER_SESSIONS,true);
		caps.setCapability(AndroidMobileCapabilityType.NATIVE_WEB_SCREENSHOT,true);
		AppiumDriver<MobileElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		return driver;
	}
	//打开已安装的app,每次打开都不重置数据（起始activity与等待后的activity不一致的情况下）
	public static AppiumDriver<MobileElement> initDriverWithInstall(String udid,String appPackage,String appActivity,String appWaitActivity) throws Exception{
		DesiredCapabilities caps = getCommons(udid);
		caps.setCapability(MobileCapabilityType.NO_RESET, true);
		caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appPackage);
		caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appActivity);
		caps.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, appWaitActivity);
		AppiumDriver<MobileElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		return driver;
	}
	

}
