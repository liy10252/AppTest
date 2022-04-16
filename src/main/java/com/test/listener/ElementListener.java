package com.test.listener;

import io.appium.java_client.events.api.general.ElementEventListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class ElementListener implements ElementEventListener {

    static Logger log = LoggerFactory.getLogger(ElementListener.class);
    @Override
    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
        log.info("点击:"+splitElement(webElement));
        System.out.println("点击:"+splitElement(webElement));
    }

    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {

    }

    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver) {
        log.info("点击:"+splitElement(webElement));
        System.out.println("改变数值:"+splitElement(webElement));
    }

    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
    }

    //获取操作的控件字符串
    private String splitElement(WebElement element) {
        String str = element.toString().split("-> ")[1];
        return str.substring(0, str.length() - 1);
    }

}
