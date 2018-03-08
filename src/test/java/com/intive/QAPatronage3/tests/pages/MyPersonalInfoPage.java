package com.intive.QAPatronage3.tests.pages;

import cucumber.api.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class MyPersonalInfoPage {
    private WebDriver driver;

    @FindBy(how = How.ID, using = "firstname")
    private WebElement FirstName;
    @FindBy(how = How.ID, using = "lastname")
    private WebElement LastName;
    @FindBy(how = How.ID, using = "email")
    private WebElement Email;
    @FindBy(how = How.ID, using = "days")
    private WebElement Days;
    @FindBy(how = How.ID, using = "months")
    private WebElement Months;
    @FindBy(how = How.ID, using = "years")
    private WebElement Years;

    public MyPersonalInfoPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void checkFirstName(DataTable myLogin) {
        List<List<String>> data = myLogin.raw();
        String Login = FirstName.getAttribute("value");
        assertEquals(data.get(0).get(0), Login);
    }

    public void checkLastName(DataTable myLogin) {
        List<List<String>> data = myLogin.raw();
        String last = LastName.getAttribute("value");
        assertEquals(data.get(0).get(1), last);
       // String valueLast = LastName.getAttribute("value");
       // System.out.println(valueLast);
       // assertEquals(lastname, valueLast);
    }

    public void checkEmail(DataTable myLogin) {
        List<List<String>> data = myLogin.raw();
        String mail = Email.getAttribute("value");
        assertEquals(data.get(0).get(2), mail);
    }

    public void checkDay(DataTable myLogin) {
        List<List<String>> data = myLogin.raw();
        String day = Days.getAttribute("value");
        assertEquals(data.get(0).get(3), day);
    }

    public void checkMonth(DataTable myLogin) {
        List<List<String>> data = myLogin.raw();
        String month = Months.getAttribute("value");
        assertEquals(data.get(0).get(4), month);
    }

    public void checkYear(DataTable myLogin) {
        List<List<String>> data = myLogin.raw();
        String year = Years.getAttribute("value");
        assertEquals(data.get(0).get(5), year);
    }

}
