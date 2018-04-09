package tests.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import static tests.Hooks.driver;

public class SearchServicePage {

    String mainPageUrl = "https://patronage2018.intive-projects.com/";

    @FindBy(how = How.ID, using = "search__input")
    public WebElement searchField;

    @FindBy(how = How.CLASS_NAME, using = "form__button--submit")
    public WebElement searchSubmit;

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
}
