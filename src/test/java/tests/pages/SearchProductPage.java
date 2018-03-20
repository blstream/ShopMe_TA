package tests.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import static tests.Hooks.driver;

@Deprecated
public class SearchProductPage {

    @FindBy(how = How.NAME, using = "search_query")
    public WebElement searchField;

    @FindBy(how = How.NAME, using = "submit_search")
    public WebElement searchSubmit;

    public SearchProductPage() {
        PageFactory.initElements(driver, this);
    }

    public void getSearchResult(String searchPhrase) {
        searchField.sendKeys(searchPhrase);
        searchSubmit.click();
    }
}
