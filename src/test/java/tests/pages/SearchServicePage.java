package tests.pages;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.assertFalse;
import static tests.Hooks.driver;
import static tests.Hooks.wait;

public class SearchServicePage {

    String mainPageUrl = "https://patronage2018.intive-projects.com/";

    @FindBy(how = How.ID, using = "search__input")
    public WebElement searchField;

    @FindBy(how = How.CLASS_NAME, using = "form__button--submit")
    public WebElement searchSubmit;

    @FindBy(how = How.XPATH, using = "//a[@href='/add/form']")
    public WebElement newServiceButton;

    @FindBy(how = How.XPATH, using = "//a[@href='/login']")
    public WebElement loginButton;

    @FindBy(how = How.CSS, using = "#root > div > header > div > a")
    public WebElement logoShopMe;

    public SearchServicePage() {
        PageFactory.initElements(driver, this);
    }

    public String goToMainPage() {
        driver.get(mainPageUrl);
        return driver.getCurrentUrl();
    }

    public void getSearchResult(String searchPhrase) {
        searchField.clear();
        searchField.sendKeys(searchPhrase);
    }

    public void submitMySearch() {
        searchSubmit.click();
    }

    public void submitByEnter() {
        searchSubmit.sendKeys(Keys.ENTER);
    }

    public String generateString(int phraseLength) {
        return StringUtils.leftPad("", phraseLength, 'a');
    }

    public void sendSearchPhrase(int phraseLength) {
        searchField.sendKeys(generateString(phraseLength));
    }

    public int getSearchPhraseLength() {
        return searchField.getAttribute("value").length();
    }

    public void searchBtnIsNotClickable() {
        assertFalse(searchSubmit.isEnabled());
    }

    public void pushNewServiceButton() {
        wait.until(ExpectedConditions.elementToBeClickable(newServiceButton)).click();
    }

    public void pushLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public void verifyIfMainPageIsVisible() {
        wait.until(ExpectedConditions.urlToBe(mainPageUrl));
        String url = driver.getCurrentUrl();
        Assert.assertEquals(mainPageUrl, url);
    }

    public void pushShopMeButton() {
        logoShopMe.click();
    }
}
