package com.test.listener;

import io.appium.java_client.events.api.general.AlertEventListener;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class AlertListener implements AlertEventListener {
    @Override
    public void beforeAlertAccept(WebDriver webDriver, Alert alert) {

    }

    @Override
    public void afterAlertAccept(WebDriver webDriver, Alert alert) {

    }

    @Override
    public void afterAlertDismiss(WebDriver webDriver, Alert alert) {

    }

    @Override
    public void beforeAlertDismiss(WebDriver webDriver, Alert alert) {

    }

    @Override
    public void beforeAlertSendKeys(WebDriver webDriver, Alert alert, String s) {

    }

    @Override
    public void afterAlertSendKeys(WebDriver webDriver, Alert alert, String s) {

    }
}
