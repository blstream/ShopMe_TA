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
import static org.junit.Assert.assertTrue;
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

    @FindBy(how = How.CLASS_NAME, using = "login-button")
    public WebElement loginButton;

    @FindBy(how = How.CLASS_NAME, using = "logo__link")
    public WebElement logoShopMe;

    @FindBy(how = How.CLASS_NAME, using = "user-name")
    public WebElement loggedUserName;

    @FindBy(how = How.XPATH, using = "//a[@href='/']")
    public WebElement logoutButton;

    @FindBy(how = How.CLASS_NAME, using = "user-name")
    public WebElement loggedInUserName;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'zalogowano:')]")
    public WebElement signInInfo;

    public SearchServicePage() {
        PageFactory.initElements(driver, this);
    }

    public String goToMainPage() {
        driver.get(mainPageUrl);
        return driver.getCurrentUrl();
    }

    public void waitForSearchFieldVisibility() {
        wait.until(ExpectedConditions.visibilityOf(searchField));
    }

    public void getSearchResult(String searchPhrase) {
        waitForSearchFieldVisibility();
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
        waitForSearchFieldVisibility();
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

    public void loginButtonIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        assertTrue(loginButton.isDisplayed());
    }

    public void logoutButtonIsDisplayed() {
        assertTrue(logoutButton.isDisplayed());
    }

    public String getUserNameFieldText() {
        return loggedUserName.getText();
    }

    public void iSeeAuthenticationInfo(String myName) {
        Assert.assertTrue(myName.equals(loggedInUserName.getText()));
        Assert.assertTrue(signInInfo.isDisplayed());
    }

    public void pushLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void navigateToPreviousPage(){
        driver.navigate().back();
    }
}
