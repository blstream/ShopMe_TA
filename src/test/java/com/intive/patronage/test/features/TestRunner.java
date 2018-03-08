package com.intive.patronage.test.features;

import com.intive.patronage.testAuthentication.TestAuthenticationStepDefs;
import com.intive.patronage.testMakeOrder.TestMakeOrderStepDefs;
import com.intive.patronage.testMyPersonalInformation.TestMyPersonalInformationStepDefs;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestAuthenticationStepDefs.class,
        TestMakeOrderStepDefs.class,
        TestMyPersonalInformationStepDefs.class
})
public class TestRunner {
}
