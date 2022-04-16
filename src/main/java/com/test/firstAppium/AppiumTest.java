package com.test.firstAppium;

import com.test.listener.ElementListener;
import com.test.po.Register;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.events.EventFiringWebDriverFactory;
import io.appium.java_client.touch.offset.PointOption;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AppiumTest {

    public static AndroidDriver<WebElement> androidDriver;

    @BeforeTest
    public void setUp() throws MalformedURLException, InterruptedException {
        //创建配置对象
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        //添加配置
        //deviceName:可以找到我们测试的设备
        desiredCapabilities.setCapability("deviceName","127.0.0.1:62001");
        //platformName：测试平台 Android or IOS
        desiredCapabilities.setCapability("platformName","android");
        //appPackage:找到要测试的App
        desiredCapabilities.setCapability("appPackage","com.handsgo.jiakao.android");
        //automationName:uiautomator2来解决输入框输入不了数据
        //自动化引擎
        //noReset:不清除掉应用的数据启动测试true:不清除 false:清除
//        desiredCapabilities.setCapability("noReset","true");
        desiredCapabilities.setCapability("automationName","uiautomator2");
        //appActivity:测试App启动入口
        desiredCapabilities.setCapability("appActivity","com.handsgo.jiakao.android.splash.Login");
//        //解决不能输入中文问题
//        desiredCapabilities.setCapability("unicodeKeyboard", "True");
//        desiredCapabilities.setCapability("resetKeyboard", "True");

        //创建驱动
        //传入两个参数
        //第一个参数：Appium通讯地址
        //第二个参数: 配置对象
        androidDriver = new AndroidDriver<WebElement>
                (new URL("http://127.0.0.1:4723/wd/hub"),desiredCapabilities);
//        androidDriver = EventFiringWebDriverFactory.getEventFiringWebDriver(androidDriver,new ElementListener());
        //隐式等待
        androidDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        Thread.sleep(10000);
    }

    @Test
    public void testJiakao() throws InterruptedException {

        Register register = new Register(androidDriver);
//        androidDriver.findElementById("com.handsgo.jiakao.android:id/btn_agree").click();

        //显示等待
        WebDriverWait webDriverWait = new WebDriverWait(androidDriver,10);

        WebElement webElement = webDriverWait.until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply( WebDriver webDriver) {
                return androidDriver.findElementById("com.handsgo.jiakao.android:id/btn_agree");
            }
        });
        webElement.click();

//        register.getAgree().click();
//        Thread.sleep(10000);
        //找到定位城市文本,并且点击
//        androidDriver.findElementById("com.handsgo.jiakao.android:id/cityTv").click();


        register.getCityTv().click();
        //找到城市搜索框,输入"北京"
//        androidDriver.findElementById("com.handsgo.jiakao.android:id/edt_search_q").sendKeys("北京");
//        register.getEdt_search().sendKeys("北京");
        register.getEdt_search().sendKeys("长");
        //找到搜索结果中的北京点击
//        androidDriver.findElementById("com.handsgo.jiakao.android:id/item_title").click();
//         List<WebElement> elementList = androidDriver.findElementsById("com.handsgo.jiakao.android:id/line");
//         elementList.get(2).click();

//        register.getItem_title().click();
        //根据文本值来找元素
//        androidDriver.findElementByAndroidUIAutomator("new UiSelector().text(\"长沙\")").click();
        //根据xpath定位
        androidDriver.findElementByXPath("//android.widget.TextView[@text='长沙']").click();
        //找到性别男点击
//        androidDriver.findElementById("com.handsgo.jiakao.android:id/maleTv").click();

        register.getMaleTv().click();
        //找到小车，点击
//        androidDriver.findElementById("com.handsgo.jiakao.android:id/carNameTv").click();
        register.getCarNameTv().click();
        //找到下一步，点击
//        androidDriver.findElementById("com.handsgo.jiakao.android:id/okBtn").click();

//        //坐标定位
//        TouchAction action = new TouchAction(androidDriver);
//        PointOption pointOption = new PointOption();
//        pointOption.withCoordinates(364,1232);
//        action.tap(pointOption).perform().release();

        register.getOkBtn().click();

//        androidDriver.findElementByXPath("//*[@text='未报名']").click();
        register.getBaoming().click();
//        Thread.sleep(10000);


//        androidDriver.findElementById("com.handsgo.jiakao.android:id/closeIv").click();

        //通过content-desc的值定位元素
        //androidDriver.findElementByAccessibilityId("content-desc的值");
        //断言
        String expected = "cn.mucang.android.core.webview.HTML5Activity";
        String actual = androidDriver.currentActivity();
        Assert.assertEquals(actual,expected);
    }

    @AfterTest
    public void tearDown(){
        //当测试用例运行完毕，销毁驱动
        androidDriver.quit();
    }
}
