package com.basiq.ui.tests.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitHandler {

    public static void waitForTextToAppear(WebDriver newDriver, String textToAppear, WebElement element) {
        WebDriverWait wait = new WebDriverWait(newDriver, Duration.ofMillis(60000));
        wait.until(ExpectedConditions.textToBePresentInElement(element, textToAppear));
    }

}
