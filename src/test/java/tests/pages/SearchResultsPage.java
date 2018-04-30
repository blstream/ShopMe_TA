package tests.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tests.helpers.RestAssuredMethods;
import tests.objects.MyService;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import static tests.Hooks.driver;
import static tests.Hooks.wait;

public class SearchResultsPage {
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

    @FindBy(how = How.CLASS_NAME, using = "pagination__button-previous")
    public WebElement previousButton;

    @FindBy(how = How.CLASS_NAME, using = "pagination__list")
    public WebElement pageButtons;

    @FindBy(how = How.CLASS_NAME, using = "pagination__button")
    public List<WebElement> paginationList;

    @FindBy(how = How.CLASS_NAME, using = "pagination__button--active")
    public WebElement activePageButton;


    private WebElement getNumberOfPage(int pageNumber) {
        return paginationList.get(pageNumber);
    }

    public void pushPageNumberButton(int number, int page) {//tu podajemy indeks, króry chcemy klinąć, page to numer strony do porównania
        getNumberOfPage(number).click();
        wait.until(ExpectedConditions.textToBePresentInElement(activePageButton, String.valueOf(page)));
    }

    public void pushNextPageNumberButton(int number) {//do iteracji, number to numer indexu, od 0
        if (number == 0)
            getNumberOfPage(number).click();
        else if (getNumberOfPage(number + 1).getText().equals(String.valueOf(number + 2)))
            getNumberOfPage(number + 1).click();
        else
            getNumberOfPage(5).click();
    }

    public void pushNextButton() {
        nextButton.click();
    }

    public void returnToFirstPage() {
        int lastPage = Integer.valueOf(activePageButton.getText());
        int actualPage = lastPage;
        for (int i = 1; i < lastPage; i++) ;
        {
            wait.until(ExpectedConditions.textToBePresentInElement(activePageButton, String.valueOf(actualPage)));
            previousButton.click();
            --actualPage;
        }
        Assert.assertTrue(activePageButton.getText() == "1");
    }
    public void areLastServicesVisible(){
        int lastPage= Integer.valueOf(activePageButton.getText());
        Assert.assertTrue(getServicesTitles().get(0).substring(0, 2).equals(String.valueOf(lastPage*10+1)));
    }

    public String getLastPage() {
        wait.until(ExpectedConditions.visibilityOf(lastPageButton));
        return lastPageButton.getText();
    }

    public Integer getNumberOfPagesFromFE() {
        Integer pageNumberInFE = (paginationList.size() <= 3) ? paginationList.size() : Integer.valueOf(getLastPage());
        return pageNumberInFE;
    }

    public void isCorrectNumberOfResultsOnEachPage(int numberOfResults) {
        wait.until(ExpectedConditions.visibilityOf(nextButton));
        System.out.println("getNumberfrom FE" + getNumberOfPagesFromFE());
        for (int i = 0; i < getNumberOfPagesFromFE() - 1; i++) {
            waitForNewResults();
            isNumberOfServicesCorrect(numberOfResults);
            pushNextPageNumberButton(i);
        }
    }

    public void areAllDatabaseServicesPresent(String titlePhrase) {
        Integer pageNumberInFE = getNumberOfPagesFromFE() + 1;
        wait.until(ExpectedConditions.visibilityOf(resultsList));
        List<WebElement> elementsPerPage = resultsList.findElements(By.className("services-item__title"));
        int last = elementsPerPage.size();
        for (int j = 0; j <= pageNumberInFE; j++) {
            for (int i = 0; i < last; i++) {
                MyService service = restAssuredMethods.searchForServices(titlePhrase).get(10 * j + i);
                //TITLE:
                assertTrue(String.valueOf((10 * j + i + 1) + ". " + service.title).equals(getServicesTitles().get(i)));
                //PRICE:
                int a = String.valueOf(getServicesPrices().get(i)).length();
                assertTrue((Float.valueOf(service.basePrice)).equals(Float.valueOf(getServicesPrices().get(i).substring(0, a - 2))));
                //DATE:
                Date date = new Date(service.date);
                SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
                String dateText = df.format(date);
                assertTrue(dateText.equals(getServicesDates().get(i)));
            }
            if (j + 1 <= pageNumberInFE) {
                pushNextPageNumberButton(j);
                wait.until(ExpectedConditions.textToBePresentInElement(activePageButton, String.valueOf(j + 2)));
                elementsPerPage = resultsList.findElements(By.className("services-item__title"));
                last = elementsPerPage.size();
            }
        }

    }

    public void isNumberOfServicesCorrect(int numberPerPage) {
        List<WebElement> titlesPerPage = resultsList.findElements(By.className("services-item__title"));
        Assert.assertEquals(numberPerPage, titlesPerPage.size());
    }

    public void isNumberOfLastServicesUnderLimit(int limit) {
        waitForNewResults();
        List<WebElement> titlesPerPage = resultsList.findElements(By.className("services-item__title"));
        Assert.assertTrue(titlesPerPage.size() < limit);
    }


    public void isNumberOfAllPagesVisible() {
        if (paginationList.size() == 0)
            Assert.assertTrue(getNumberOfPage(0).isDisplayed());
        else if (paginationList.size() == 1)
            Assert.assertTrue(getNumberOfPage(1).isDisplayed());
        else if (paginationList.size() == 2)
            Assert.assertTrue(getNumberOfPage(2).isDisplayed());
        else if (paginationList.size() == 3)
            Assert.assertTrue(getNumberOfPage(3).isDisplayed());
        else
            Assert.assertTrue(lastPageButton.isDisplayed());
    }

    public void deleteServicesFromPage(int numberOfElements, int pageNumber) {
        for (int i = 0; i < numberOfElements; ++i) {
            List<MyService> deleteList = restAssuredMethods.getServices(pageNumber, 10).content;
            restAssuredMethods.deleteService(deleteList.get(i).id);
        }
    }

    public void pushLastPageButton() {//jeśli zaczynamy z pozycji strony 1(prev-butt jest niewidoczny)
        if (paginationList.size() <= 3)
            pushPageNumberButton(paginationList.size() - 1, paginationList.size());
        else
            lastPageButton.click();
    }


    private WebElement getServiceRowElement(int line) {
        return serviceList.get(line);
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
        wait.until(ExpectedConditions.visibilityOf(pageButtons));
    }

    public void isActualPageBold(int numberOfPage) {
        Assert.assertEquals(activePageButton.getText(), String.valueOf(numberOfPage));
    }

    public void previousButtonIsVisible() {
        Assert.assertTrue(paginationList.get(0).getText().equals("<"));
    }

    public void firstPageButtonIsVisible() {//bierzemy pierwszy wyraz po "<". Oczywiście jesteśmy na ostatniej stronie, więc istnieje, chyba, że 1, stąd if
        String firstPage = "1";
        if (!activePageButton.getText().equals("1")) {
            firstPage = paginationList.get(1).getText();
        }
        Assert.assertTrue(firstPage.equals("1"));
    }

    public void areLastPageBold() {
        List<String> pageOneValues = new ArrayList<>();
        pageOneValues.add(String.valueOf(getNumberOfPagesFromFE()));
        wait.until(ExpectedConditions.textToBePresentInElement(activePageButton, "6"));
        System.out.println("button " + activePageButton.getText());
        Assert.assertEquals(activePageButton.getText(), pageOneValues.get(0));
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
        List<WebElement> titlesWebElements = resultsList.findElements(By.className("services-item__title"));
        ArrayList<String> titles = new ArrayList<>();
        for (int i = 0; i < titlesWebElements.size(); i++) {
            titles.add(titlesWebElements.get(i).getText());
        }
        return titles;
    }

    public List<String> getServicesPrices() {
        List<WebElement> pricesWebElements = resultsList.findElements(By.className("services-item__price"));
        ArrayList<String> prices = new ArrayList<>();
        for (int i = 0; i < pricesWebElements.size(); i++) {
            prices.add(pricesWebElements.get(i).getText());
        }
        return prices;
    }

    public List<String> getServicesDates() {
        List<WebElement> datesWebElements = resultsList.findElements(By.className("services-item__date"));
        ArrayList<String> dates = new ArrayList<>();
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
