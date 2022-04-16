package com.test.po;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Data;
import org.openqa.selenium.support.PageFactory;


@Data
public class Register {

    AndroidDriver driver;

    @AndroidFindBy(id = "com.handsgo.jiakao.android:id/btn_agree")
    AndroidElement agree;

    @AndroidFindBy(id = "com.handsgo.jiakao.android:id/cityTv")
    AndroidElement cityTv;

    @AndroidFindBy(id = "com.handsgo.jiakao.android:id/edt_search_q")
    AndroidElement edt_search;

    @AndroidFindBy(id = "com.handsgo.jiakao.android:id/item_title")
    AndroidElement item_title;

    @AndroidFindBy(id = "com.handsgo.jiakao.android:id/maleTv")
    AndroidElement maleTv;

    @AndroidFindBy(id = "com.handsgo.jiakao.android:id/carNameTv")
    AndroidElement carNameTv;

    @AndroidFindBy(id = "com.handsgo.jiakao.android:id/okBtn")
    AndroidElement okBtn;

    @AndroidFindBy(xpath = "//*[@text='未报名']")
    AndroidElement baoming;

    public Register(AndroidDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }



}
