package com.appium.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetByLocation {

    private static Logger log = LoggerFactory.getLogger(GetByLocation.class);

    private Properties properties;

    /**
     * 构造方法：
     * 读取测试用例到文件流中并将其加载到properties对象中
     * @param filename
     */
    public GetByLocation(String filename) {
        properties = new Properties();
        try {
            InputStream in = this.getClass().getClassLoader().getResourceAsStream("./uiElement/"+filename);
            properties.load(in);
            in.close();
        } catch (IOException e) {
            log.info("找不到文件：{}",filename);
            e.printStackTrace();
        }
    }

    /**
     * 获取By对象，用于代码中引用
     * @param keyword
     * @return
     */
    public By getLocatorApp(String keyword){
        String value = properties.getProperty(keyword.trim());
        String locatorType = value.split(">>")[0].toLowerCase();
        String locatorValue = value.split(">>")[1];
        if (locatorType != null && locatorValue != null){
            return getByElement(locatorType,locatorValue);
        }else {
            new RuntimeException("元素没有获取到！");
            return null;
        }
    }

    /**
     * 传入数据分离文件中的keyword关键字获取MobileElement对象
     * @param keyword
     * @param driver
     * @return
     */
    public MobileElement getElementObj(String keyword,AppiumDriver<MobileElement> driver){
        String value = properties.getProperty(keyword.trim());
        String locatorType = value.split(">>")[0].toLowerCase();
        String locatorValue = value.split(">>")[1];
        if (locatorType != null && locatorValue != null){
            return getElementObj(locatorType,locatorValue,driver);
        }else {
            new RuntimeException("元素没有获取到！");
            return null;
        }
    }

    /**
     * 返回By对象
     * @param locatorType 传入元素定位方式
     * @param locatorValue 传入元素定位值
     * @return
     */
    public By getByElement(String locatorType, String locatorValue){
        if ("id".equals(locatorType)){
            return By.id(locatorValue);
        } else if ("name".equals(locatorType)){
            return By.name(locatorValue);
        } else if ("xpath".equals(locatorType)){
            return By.xpath(locatorValue);
        } else if ("classname".equals(locatorType)){
            return By.className(locatorValue);
        } else if ("tagname".equals(locatorType)){
            return By.tagName(locatorValue);
        } else if ("linktext".equals(locatorType)){
            return By.linkText(locatorValue);
        } else if ("cssselector".equals(locatorType)){
            return By.cssSelector(locatorValue);
        } else if ("partiallinktext".equals(locatorType)){
            return By.partialLinkText(locatorValue);
        }else {
            log.info("定位类型为：{}的元素不存在",locatorType);
            return null;
        }
    }

    /**
     *返回MobileElement对象
     * @param locatorType
     * @param locatorValue
     * @param driver
     * @return
     */
    public MobileElement getElementObj(String locatorType, String locatorValue,AppiumDriver<MobileElement> driver){
        if ("id".equals(locatorType)){
            return driver.findElementById(locatorValue);
        } else if ("name".equals(locatorType)){
            return driver.findElementByName(locatorValue);
        } else if ("xpath".equals(locatorType)){
            return driver.findElementByXPath(locatorValue);
        } else if ("classname".equals(locatorType)){
            return driver.findElementByClassName(locatorValue);
        } else if ("tagname".equals(locatorType)){
            return driver.findElementByTagName(locatorValue);
        } else if ("linktext".equals(locatorType)){
            return driver.findElementByLinkText(locatorValue);
        } else if ("cssselector".equals(locatorType)){
            return driver.findElementByCssSelector(locatorValue);
        } else if ("partiallinktext".equals(locatorType)){
            return driver.findElementByPartialLinkText(locatorValue);
        }else {
            log.info("定位类型为：{}的元素不存在",locatorType);
            return null;
        }
    }
}
