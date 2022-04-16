package com.test.firstAppium;

//import com.test.listener.ElementListener;
import com.test.listener.ElementListener;
import com.test.po.Register;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
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

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MultiTouchTest{

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
        desiredCapabilities.setCapability("appPackage","com.baidu.BaiduMap");

        //automationName:uiautomator2来解决输入框输入不了数据
        //自动化引擎
        //noReset:不清除掉应用的数据启动测试true:不清除 false:清除
        desiredCapabilities.setCapability("noReset","true");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
//        desiredCapabilities.setCapability("automationName","uiautomator2");
        //appActivity:测试App启动入口
//        desiredCapabilities.setCapability("appActivity","com.handsgo.jiakao.android.splash.Login");
        desiredCapabilities.setCapability("appActivity","com.baidu.baidumaps.WelcomeScreen");

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
   public void testZoom() throws InterruptedException {

//       WebDriverWait webDriverWait = new WebDriverWait(androidDriver,10);

//       WebElement webElement = webDriverWait.until(new ExpectedCondition<WebElement>() {
//           @Override
//           public WebElement apply( WebDriver webDriver) {
//               return androidDriver.findElementByXPath("//android.widget.TextView[@text='出行']");
//           }
//       });

       androidDriver.findElementById("com.baidu.BaiduMap:id/tv_searchbox_home_text_switcher").click();
       androidDriver.findElementById("com.baidu.BaiduMap:id/tvSearchBoxInput").sendKeys("北京");
       //模拟键盘按键
       androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));

       WebDriverWait webDriverWait = new WebDriverWait(androidDriver,10);

       WebElement webElement = webDriverWait.until(new ExpectedCondition<WebElement>() {
           @Override
           public WebElement apply( WebDriver webDriver) {
               return androidDriver.findElementById("com.baidu.BaiduMap:id/llt_poidetail_header_own");
           }
       });


       //实例化TouchAction对象
       TouchAction touchAction = new TouchAction(androidDriver);

       PointOption startPointOption = PointOption.point(360,200);
       PointOption endPointOption = PointOption.point(360,1000);
       //把原始时间转换成Duration类型的
       //把Duration类型转换成WaitOptions类型
       Duration duration = Duration.ofMillis(800);
       WaitOptions waitOptions = WaitOptions.waitOptions(duration);
       touchAction.press(startPointOption).waitAction(waitOptions).moveTo(endPointOption).release();
       //让滑动生效
       touchAction.perform();

       androidDriver.findElementById("com.baidu.BaiduMap:id/zoom_in").click();
       Thread.sleep(2000);
       androidDriver.findElementById("com.baidu.BaiduMap:id/zoom_out").click();

       Thread.sleep(2000);


        //1.实例化MultiTouchAction对象
       MultiTouchAction multiTouchAction = new MultiTouchAction(androidDriver);
       //2.实例化两个TouchAction(因为需要两根手指进行放大操作)
       TouchAction touchAction1 = new TouchAction(androidDriver);
       TouchAction touchAction2 = new TouchAction(androidDriver);

       //得到当前屏幕的宽度和高度
       int x = androidDriver.manage().window().getSize().getWidth();
       int y = androidDriver.manage().window().getSize().getHeight();

       //第一根手指从B点滑动到A点
       touchAction1.press(PointOption.point(x*4/10,y*4/10))
               .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
               .moveTo(PointOption.point(x*2/10,y*2/10)).release();
       //第二根手指从C点滑动到D点
       touchAction2.press(PointOption.point(x*6/10,y*6/10))
               .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
               .moveTo(PointOption.point(x*8/10,y*8/10)).release();
       //把两根手指的操作添加到MultiTouchAction里
       multiTouchAction.add(touchAction1).add(touchAction2);
       multiTouchAction.perform();

       Thread.sleep(5000);


       TouchAction touchAction3 = new TouchAction(androidDriver);
       TouchAction touchAction4 = new TouchAction(androidDriver);
       //第一根手指从A点滑动到B点
       touchAction3.press(PointOption.point(x*2/10,y*2/10))
               .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
               .moveTo(PointOption.point(x*4/10,y*4/10)).release();
       //第二根手指从D点滑动到C点
       touchAction4.press(PointOption.point(x*8/10,y*8/10))
               .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
               .moveTo(PointOption.point(x*6/10,y*6/10)).release();
       //把两根手指的操作添加到MultiTouchAction里
       multiTouchAction.add(touchAction3).add(touchAction4);
       multiTouchAction.perform();

       Thread.sleep(5000);
   }

    @AfterTest
    public void tearDown(){
        //当测试用例运行完毕，销毁驱动
        androidDriver.quit();
    }
}
