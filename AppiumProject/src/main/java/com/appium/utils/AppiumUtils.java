package com.appium.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AppiumUtils {
    /**
     * 判断元素是否存在，若存在则返回true；若不存在则返回false
     * @param driver
     * @param by
     * @return
     */
        public static Boolean isElementExits(AndroidDriver<AndroidElement> driver, By by) {
            try {
                driver.findElement(by);
                System.out.println("元素存在");
                return true;
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("元素不存在");

            }
            return false;
        }

    /**
     * 获取当前页面的所有context，并将包含webview的context进行遍历，取最新的webview的context赋予driver；
     * @param driver
     */
    public static void setContextToWebview(AppiumDriver<MobileElement> driver){
            Set<String> contextNames = driver.getContextHandles();
            List<String> webViewContextNames =  contextNames
                    .stream()
                    .filter(contextName -> contextName.contains("WEBVIEW_"))
                    .collect(Collectors.toList());
            String currentContextView = "";

            if (webViewContextNames.size() > 0){
                currentContextView = (String) webViewContextNames
                        .toArray()[webViewContextNames.size()-1];
                driver.context(currentContextView);
            }
        }

     public static void swipeToUp(AppiumDriver<MobileElement> driver){

       int width = driver.manage().window().getSize().getWidth();
       int height = driver.manage().window().getSize().getHeight();

       TouchAction touch = new TouchAction(driver);

       touch.press(PointOption.point(width/2,height*7/8)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).moveTo(PointOption.point(width/2,height/8)).release().perform();
     }

}
