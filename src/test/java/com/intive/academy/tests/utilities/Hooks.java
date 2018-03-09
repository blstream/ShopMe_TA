package com.intive.academy.tests.utilities;

import com.intive.academy.tests.factories.DriverFactory;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends DriverFactory {

    @Before
    public void initDriver(){
        initChromeDriver();
        System.out.println("TEST_START");
    }

    @After
    public void destroyDriver(){
        if(driver != null){
            driver.quit();
            driver = null;
        }
        System.out.println("TEST_END");
    }
}
