package com.test.firstAppium;


import com.test.listener.ElementListener;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.events.EventFiringWebDriverFactory;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.TestNGException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ToastTest {

    public static AndroidDriver<WebElement> androidDriver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        //创建配置对象
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        //添加配置
        //deviceName:可以找到我们测试的设备
        desiredCapabilities.setCapability("deviceName","127.0.0.1:62001");
        //platformName：测试平台 Android or IOS
        desiredCapabilities.setCapability("platformName","android");
        //appPackage:找到要测试的App
//        desiredCapabilities.setCapability("appPackage","com.handsgo.jiakao.android");
        desiredCapabilities.setCapability("appPackage","com.tencent.mm");

        //automationName:uiautomator2来解决输入框输入不了数据
        //自动化引擎
        //noReset:不清除掉应用的数据启动测试true:不清除 false:清除
        desiredCapabilities.setCapability("noReset","true");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
//        desiredCapabilities.setCapability("automationName","uiautomator2");
        //appActivity:测试App启动入口
//        desiredCapabilities.setCapability("appActivity","com.handsgo.jiakao.android.splash.Login");
        desiredCapabilities.setCapability("appActivity","com.tencent.mm.ui.LauncherUI");

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
    public void getToast() throws InterruptedException {

        try {
            androidDriver.findElementByXPath("//*[@text='登录']").click();
            androidDriver.findElement(By.id("com.tencent.mm:id/erw")).click();


            androidDriver.findElementByXPath("//*[@text='请填写微信号/QQ号/邮箱']").sendKeys("123");
            androidDriver.findElementByXPath("//*[@text='密码']/following-sibling::android.widget.EditText").sendKeys("321");

            androidDriver.findElement(By.id("com.tencent.mm:id/erp")).click();

            WebElement element = new WebDriverWait(androidDriver, 10).until(new ExpectedCondition<WebElement>() {
                public WebElement apply(WebDriver driver) {
                    return driver.findElement(By.xpath("//*[contains(@text, '正在')]"));
                }
            });
            String toast = element.getText();
            System.out.println("toast : " + toast);
        } catch (AssertionError e) {
            Assert.fail(getClass().getName() + " >> " + e.getMessage());
        } catch (Exception e) {
            throw new TestNGException(getClass().getName() + e.getMessage(), e);
        }
    }

    @AfterTest
    public void tearDown(){
        //当测试用例运行完毕，销毁驱动
        androidDriver.quit();
    }
}
