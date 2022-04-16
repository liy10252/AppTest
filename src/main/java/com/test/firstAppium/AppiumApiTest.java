package com.test.firstAppium;


import com.test.listener.ElementListener;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.GsmCallActions;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.events.EventFiringWebDriverFactory;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppiumApiTest {

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
        desiredCapabilities.setCapability("appPackage","com.handsgo.jiakao.android");

        //automationName:uiautomator2来解决输入框输入不了数据
        //自动化引擎
        //noReset:不清除掉应用的数据启动测试true:不清除 false:清除
        desiredCapabilities.setCapability("noReset","true");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
//        desiredCapabilities.setCapability("automationName","uiautomator2");
        //appActivity:测试App启动入口
//        desiredCapabilities.setCapability("appActivity","com.handsgo.jiakao.android.splash.Login");
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
        androidDriver = EventFiringWebDriverFactory.getEventFiringWebDriver(androidDriver,new ElementListener());
        //隐式等待
        androidDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        Thread.sleep(10000);
    }

   @Test
   public void testApi() throws InterruptedException, IOException {

        Thread.sleep(8000);
        //1.页面跳转--APP内部页面的跳转
//       Activity activity = new Activity("com.handsgo.jiakao.android",
//               "com.baojiazhijia.qichebaojia.lib.app.rank.SalesRankingActivity");
//       androidDriver.startActivity(activity);
//       Thread.sleep(5000);
//        //2.app相互跳转--跳转到其他app页面
//        Activity activity = new Activity("com.baidu.BaiduMap","com.baidu.baidumaps.MapsActivity");
//        androidDriver.startActivity(activity);
//        Thread.sleep(5000);

       //getPageSource的使用
//      String pageSource = androidDriver.getPageSource();
//      System.out.println(pageSource);
      //获取当前activity
//      System.out.println(androidDriver.currentActivity());
       //清楚app数据
//       androidDriver.resetApp();
//       //获取到app是否安装
//       System.out.println(androidDriver.isAppInstalled("com.android.browser"));

       //pressKey模拟按键 android独有
//       KeyEvent keyEvent = new KeyEvent();
//       keyEvent.withKey(AndroidKey.HOME);
//       androidDriver.pressKey(keyEvent);

//       //getScreenShotAs截图
//       File file = androidDriver.getScreenshotAs(OutputType.FILE);
//       FileUtils.copyFile(file,new File("C:\\Users\\lee\\Desktop\\test.png"));

         //获取当前设备的系统时间
//         System.out.println(androidDriver.getDeviceTime());
//
//         //获取设备DPI，不是分辨率
//         System.out.println(androidDriver.getDisplayDensity());
//
//         //获取设备的自动化引擎名字
//         System.out.println(androidDriver.getAutomationName());
//
//         //获取设备横竖屏状态LANDSCAPE:横屏显示 PORTAIT:竖屏显示
//        System.out.println(androidDriver.getOrientation().value());


   }

    @AfterTest
    public void tearDown(){
        //当测试用例运行完毕，销毁驱动
        androidDriver.quit();
    }
}
