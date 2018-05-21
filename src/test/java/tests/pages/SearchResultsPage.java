package tests.pages;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tests.helpers.RestAssuredMethods;
import tests.objects.MyService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertTrue;
import static tests.Hooks.driver;
import static tests.Hooks.wait;

public class SearchResultsPage extends SearchServicePage {
    RestAssuredMethods restAssuredMethods = new RestAssuredMethods("https://patronage2018.intive-projects.com/api");

    @FindBy(how = How.CLASS_NAME, using = "search-results__list")
    public WebElement resultsList;

    @FindBy(how = How.CLASS_NAME, using = "search__message-error")
    public WebElement errorMessageField;

    @FindBy(how = How.CLASS_NAME, using = "no-search-results__paragraph")
    public WebElement noResultsField;

    @FindBy(how = How.CLASS_NAME, using = "services-item")
    public WebElement firstService;

    @FindBy(how = How.CLASS_NAME, using = "services-item")
    public List<WebElement> serviceList;

    @FindBy(how = How.CLASS_NAME, using = "pagination__button--last")
    public WebElement lastPageButton;

    @FindBy(how = How.CLASS_NAME, using = "pagination__button--next")
    public WebElement nextButton;

    @FindBy(how = How.CLASS_NAME, using = "pagination__list")
    public WebElement paginationList;

    @FindBy(how = How.CLASS_NAME, using = "pagination__button")
    public List<WebElement> pageButtonsList;

    @FindBy(how = How.CLASS_NAME, using = "pagination__button--active")
    public WebElement activePageButton;

    @FindBy(how = How.CLASS_NAME, using = "pagination__button--previous")
    public WebElement previousButton;

    @FindBy(how = How.CLASS_NAME, using = "services-item__title")
    public List<WebElement> servicesTitles;

    @FindBy(how = How.CLASS_NAME, using = "services-item__span")
    public List<WebElement> servicesCategoryPriceAndDate;

    private WebElement getServiceRowElement(int line) {
        return serviceList.get(line);
    }

    private WebElement getNumberOfPage(int pageNumber) {
        return pageButtonsList.get(pageNumber);
    }

    public void pushPageNumberButton(int number, int page) {
        getNumberOfPage(number).click();
        wait.until(ExpectedConditions.textToBePresentInElement(activePageButton, String.valueOf(page)));
    }

    public void pushNextPageNumberButton(int number) {
        if (number == 0)
            getNumberOfPage(number).click();
        else try {
            if (getNumberOfPage(number + 1).getText().equals(String.valueOf(number + 2)))
                getNumberOfPage(number + 1).click();
            else
                getNumberOfPage(4).click();
        } catch (IndexOutOfBoundsException e) {
            getNumberOfPage(4).click();
        }
    }

    public void pushNextButton() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        nextButton.click();
    }

    public void returnToFirstPage() {
        int lastPage = Integer.valueOf(activePageButton.getText());
        int actualPage = lastPage;
        wait.until(ExpectedConditions.textToBePresentInElement(pageButtonsList.get(0), "<"));
        for (int i = 1; i < lastPage; i++) {
            wait.until(ExpectedConditions.textToBePresentInElement(activePageButton, String.valueOf(actualPage)));
            pageButtonsList.get(0).click();
            --actualPage;
        }
        assertTrue(activePageButton.getText().equals("1"));
    }

    public void areLastServicesVisible() {
        wait.until(ExpectedConditions.visibilityOf(previousButton));
        int lastPage = Integer.valueOf(activePageButton.getText());
        if (lastPage < 10) {
            assertTrue(getServicesTitles().get(0).substring(0, 2).equals(String.valueOf((lastPage - 1) * 10 + 1)));
        } else {
            assertTrue(getServicesTitles().get(0).substring(0, 3).equals(String.valueOf((lastPage - 1) * 10 + 1)));
        }
    }

    public String getLastPageNumberVisible() {
        wait.until(ExpectedConditions.visibilityOf(lastPageButton));
        return lastPageButton.getText();
    }

    public Integer getNumberOfPagesFromFE() {
        Integer pageNumberInFE;
        if (pageButtonsList.size() <= 3) {
            pageNumberInFE = pageButtonsList.size();
        } else {
            pageNumberInFE = Integer.valueOf(getLastPageNumberVisible());
        }
        return pageNumberInFE;
    }

    public void isCorrectNumberOfResultsOnEachPage(int numberOfResults) {
        wait.until(ExpectedConditions.visibilityOf(nextButton));
        int k = getNumberOfPagesFromFE();
        for (int i = 0; i < k - 1; i++) {
            wait.until(ExpectedConditions.textToBePresentInElement(activePageButton, String.valueOf(i + 1)));
            isNumberOfServicesCorrect(numberOfResults);
            pushNextPageNumberButton(i);
        }
        wait.until(ExpectedConditions.textToBePresentInElement(activePageButton, String.valueOf(k)));
    }

    public void areAllDatabaseServicesPresent(String titlePhrase) {
        wait.until(ExpectedConditions.visibilityOf(resultsList));
        Integer pageNumberInFE = getNumberOfPagesFromFE();

        int last = servicesTitles.size();
        for (int j = 0; j < pageNumberInFE; j++) {
            for (int i = 0; i < last; i++) {
                int b = getServiceRowElement(i).getText().length();
                MyService service = restAssuredMethods.searchForServices(titlePhrase).get(10 * j + i);
                assertTrue(String.valueOf((10 * j + i + 1) + ". " + service.title + "\n" + service.category.getName() + "\n" + service.basePrice)
                        .contains(getServiceRowElement(i).getText().substring(0, b - 14)));
            }
            if (j + 1 < pageNumberInFE) {
                pushNextPageNumberButton(j);
                wait.until(ExpectedConditions.textToBePresentInElement(activePageButton, String.valueOf(j + 2)));
                last = servicesTitles.size();
            }
        }
    }

    public void isNumberOfServicesCorrect(int numberPerPage) {
        Assert.assertEquals(numberPerPage, servicesTitles.size());
    }

    public void isNumberOfLastServicesUnderLimit(int limit) {
        assertTrue(servicesTitles.size() <= limit);
    }

    public void isNumberOfAllPagesVisible() {
        if (pageButtonsList.size() == 0)
            assertTrue(getNumberOfPage(0).isDisplayed());
        else if (pageButtonsList.size() == 1)
            assertTrue(getNumberOfPage(1).isDisplayed());
        else if (pageButtonsList.size() == 2)
            assertTrue(getNumberOfPage(2).isDisplayed());
        else if (pageButtonsList.size() == 3)
            assertTrue(getNumberOfPage(3).isDisplayed());
        else
            assertTrue(lastPageButton.isDisplayed());
    }

    public void deleteServicesFromPage(int numberOfElements, int pageNumber, String title) {
        List<MyService> deleteList = restAssuredMethods.searchForServicesOnPage(pageNumber, 10, title).content;
        for (int i = 0; i < numberOfElements; ++i) {
            restAssuredMethods.deleteServiceById(deleteList.get(i).id);
        }
    }

    public void pushLastPageButton() {
        if (pageButtonsList.size() <= 3)
            pushPageNumberButton(pageButtonsList.size() - 1, pageButtonsList.size());
        else
            lastPageButton.click();
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

    public void paginationButtonsAreVisible() {
        wait.until(ExpectedConditions.visibilityOf(paginationList));
    }

    public void isActualPageBold(int numberOfPage) {
        wait.until(ExpectedConditions.textToBePresentInElement(activePageButton, String.valueOf(numberOfPage)));
    }

    public void previousButtonIsVisible() {
        assertTrue(pageButtonsList.get(0).getText().equals("<"));
    }

    public void firstPageButtonIsVisible() {
        String firstPage = "1";
        if (!activePageButton.getText().equals("1")) {
            firstPage = pageButtonsList.get(1).getText();
        }
        assertTrue(firstPage.equals("1"));
    }

    public boolean isNextButtonInvisible() {
        try {
            nextButton.isDisplayed();
            return false;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return true;
        }
    }

    public boolean isFirstPageButtonClickable() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(getNumberOfPage(1)));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public List<String> getServicesTitles() {
        ArrayList<String> titles = new ArrayList<>();
        for (int i = 0; i < servicesTitles.size(); i++) {
            titles.add(servicesTitles.get(i).getText());
        }
        return titles;
    }

    public List<String> getServicesCategories() {
        return getSpanValuesByIndex(0);
    }

    public List<String> getServicesPrices() {
        return getSpanValuesByIndex(1);
    }

    public List<String> getServicesDates() {
        return getSpanValuesByIndex(2);
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

    private List<String> getSpanValuesByIndex(int spanIndex) {
        ArrayList<String> spanValuesList = new ArrayList<>();
        int sizeOfCategoryPriceAndDateArray = servicesCategoryPriceAndDate.size();
        for (int i = 0; i < sizeOfCategoryPriceAndDateArray; i++) {
            spanValuesList.add(servicesCategoryPriceAndDate.get(i).getText());
        }
        List<List<String>> categoryPriceAndDateSubsets = Lists.partition(spanValuesList, 3);
        ArrayList<String> chosenSpanValues = new ArrayList<>();
        for (int i = 0; i < categoryPriceAndDateSubsets.size(); i++) {
            chosenSpanValues.add(categoryPriceAndDateSubsets.get(i).get(spanIndex));
        }
        return chosenSpanValues;
    }
}
