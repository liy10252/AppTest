package com.test.firstAppium;

//import com.test.listener.ElementListener;
import com.test.listener.ElementListener;
import com.test.po.Register;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.events.EventFiringWebDriverFactory;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
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
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SwipeTest {

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
//        desiredCapabilities.setCapability("appPackage","com.handsgo.jiakao.android");
        desiredCapabilities.setCapability("appPackage","com.only.main");

        //automationName:uiautomator2来解决输入框输入不了数据
        //自动化引擎
        //noReset:不清除掉应用的数据启动测试true:不清除 false:清除
        desiredCapabilities.setCapability("noReset","true");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
//        desiredCapabilities.setCapability("automationName","uiautomator2");
        //appActivity:测试App启动入口
//        desiredCapabilities.setCapability("appActivity","com.handsgo.jiakao.android.splash.Login");
        desiredCapabilities.setCapability("appActivity","com.yy.only.activity.SplashActivity");

//        //解决不能输入中文问题
//        desiredCapabilities.setCapability("unicodeKeyboard", "True");
//        desiredCapabilities.setCapability("resetKeyboard", "True");

        //创建驱动
        //传入两个参数
        //第一个参数：Appium通讯地址
        //第二个参数: 配置对象
        androidDriver = new AndroidDriver<WebElement>
                (new URL("http://127.0.0.1:4723/wd/hub"),desiredCapabilities);
        androidDriver = EventFiringWebDriverFactory.getEventFiringWebDriver(androidDriver,new ElementListener());
        //隐式等待
        androidDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        Thread.sleep(10000);
    }

    @Test
    public void testRefresh() throws InterruptedException {
        //下拉刷新
        //java-client 4.1.2
//        Thread.sleep(15000);
//        androidDriver.swipe(360,580,360,990,800);
        //java-client 6.1.0
        androidDriver.findElementByXPath("android.widget.TextView").click();
        TouchAction touchAction = new TouchAction(androidDriver);
        PointOption startPointOption = PointOption.point(360,580);
        PointOption endPointOption = PointOption.point(360,990);
        //把原始时间转换成Duration类型的
        //把Duration类型转换成WaitOptions类型
        Duration duration = Duration.ofMillis(800);
        WaitOptions waitOptions = WaitOptions.waitOptions(duration);
        touchAction.press(startPointOption).waitAction(waitOptions).moveTo(endPointOption).release();
        //让滑动生效
        touchAction.perform();
    }

    @Test
    public void testMultiSwipe() throws InterruptedException {

//        androidDriver.findElementById("com.only.main:id/btn_user_agreement_ok").click();
        androidDriver.findElementByXPath("//android.widget.TextView[@text='密码']/..").click();

        WebDriverWait webDriverWait = new WebDriverWait(androidDriver,10);

        WebElement webElement = webDriverWait.until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply( WebDriver webDriver) {
                return androidDriver.findElementByXPath("//android.widget.TextView[@text='滑动锁']");
            }
        });
        //实例化TouchAction对象
        TouchAction touchAction = new TouchAction(androidDriver);

        PointOption startPointOption = PointOption.point(370,1080);
        PointOption endPointOption = PointOption.point(370,200);
        //把原始时间转换成Duration类型的
        //把Duration类型转换成WaitOptions类型
        Duration duration = Duration.ofMillis(800);
        WaitOptions waitOptions = WaitOptions.waitOptions(duration);
        touchAction.press(startPointOption).waitAction(waitOptions).moveTo(endPointOption).release();
        //让滑动生效
        touchAction.perform();
        Thread.sleep(3000);

        androidDriver.findElementByXPath("//android.widget.TextView[@text='贴纸图案锁']/..").click();

        TouchAction touch = new TouchAction(androidDriver);

        //九宫格操作
        //第一个点坐标:160 540 第二个点坐标:360 540 第三个点坐标:560 540
        //第四个点坐标:360 710 第五个点坐标:160 880 第六点坐标:360 880 第七个点坐标:560 880
        PointOption pointOption1 = PointOption.point(160,540);
        PointOption pointOption2 = PointOption.point(360,540);
        PointOption pointOption3 = PointOption.point(560,540);
        PointOption pointOption4 = PointOption.point(360,710);
        PointOption pointOption5 = PointOption.point(160,880);
        PointOption pointOption6 = PointOption.point(360,880);
        PointOption pointOption7 = PointOption.point(560,880);
        touch.press(pointOption1).moveTo(pointOption2).moveTo(pointOption3)
                .moveTo(pointOption4).moveTo(pointOption5).moveTo(pointOption6).moveTo(pointOption7).release();
        touch.perform();
    }

    @AfterTest
    public void tearDown(){
        //当测试用例运行完毕，销毁驱动
        androidDriver.quit();
    }
}
