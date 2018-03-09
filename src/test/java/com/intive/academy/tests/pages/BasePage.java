package com.intive.academy.tests.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    WebDriver driver;

    protected static Logger log;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log = Logger.getLogger(BasePage.class);
    }

}
