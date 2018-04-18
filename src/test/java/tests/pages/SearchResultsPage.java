package tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;
import java.util.List;

import static tests.Hooks.driver;
import static tests.Hooks.wait;

public class SearchResultsPage {

    @FindBy(how = How.CLASS_NAME, using = "search-results__list")
    public WebElement resultsList;

    @FindBy(how = How.CLASS_NAME, using = "search__message-error")
    public WebElement errorMessageField;

    @FindBy(how = How.CLASS_NAME, using = "no-search-results__paragraph")
    public WebElement noResultsField;

    @FindBy(how = How.CLASS_NAME, using = "services-item")
    public WebElement firstService;

    @FindBy(how = How.CLASS_NAME, using = "services-item")
    public List<WebElement> ServiceList;

    private WebElement getServiceRowElement(int line){
        return ServiceList.get(line);
    }


    public SearchResultsPage() {
        PageFactory.initElements(driver, this);
    }

    public void waitForNewResults() {
        wait.until(ExpectedConditions.visibilityOf(resultsList));
    }

    public void areNewResultsPresent(String expectedService) {
        wait.until(ExpectedConditions.textToBePresentInElement(firstService, expectedService));
    }

    public List<String> getServicesTitles() {
        List<WebElement> titlesWebElements = resultsList.findElements(By.className("services-item__title"));
        ArrayList<String> titles = new ArrayList<String>();
        for (int i = 0; i < titlesWebElements.size(); i++) {
            titles.add(titlesWebElements.get(i).getText());
        }
        return titles;
    }

    public List<String> getServicesPrices() {
        List<WebElement> pricesWebElements = resultsList.findElements(By.className("services-item__price"));
        ArrayList<String> prices = new ArrayList<String>();
        for (int i = 0; i < pricesWebElements.size(); i++) {
            prices.add(pricesWebElements.get(i).getText());
        }
        return prices;
    }

    public List<String> getServicesDates() {
        List<WebElement> datesWebElements = resultsList.findElements(By.className("services-item__date"));
        ArrayList<String> dates = new ArrayList<String>();
        for (int i = 0; i < datesWebElements.size(); i++) {
            dates.add(datesWebElements.get(i).getText());
        }
        return dates;
    }

    public String getErrorMessage() {
        return errorMessageField.getText();
    }

    public String getNoResultsMessage() {
        wait.until(ExpectedConditions.visibilityOf(noResultsField));
        return noResultsField.getText();
    }

    public String getTitle(int line) {
         return getServiceRowElement(line).getText();
    }

    public void openServiceFromResults(int line) {
        getServiceRowElement(line).click();
    }
}
