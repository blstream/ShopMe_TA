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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public List<WebElement> ServiceList;

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

    private WebElement getServiceRowElement(int line) {
        return ServiceList.get(line);
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
                nextButton.click();
            }

            public void returnToFirstPage() {
                int lastPage = Integer.valueOf(activePageButton.getText());
                int actualPage = lastPage;
                wait.until(ExpectedConditions.textToBePresentInElement(pageButtonsList.get(0), "<"));
                for (int i = 1; i <= lastPage; i++) {
                        pageButtonsList.get(0).click();
                        wait.until(ExpectedConditions.textToBePresentInElement(activePageButton, String.valueOf(actualPage)));
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

            public String getLastPage() {
                wait.until(ExpectedConditions.visibilityOf(lastPageButton));
                return lastPageButton.getText();
            }

            public Integer getNumberOfPagesFromFE() {
                Integer pageNumberInFE;
                if (pageButtonsList.size() <= 3) {
                        pageNumberInFE = pageButtonsList.size();
                    } else {
                        pageNumberInFE = Integer.valueOf(getLastPage());
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
                wait.until(ExpectedConditions.textToBePresentInElement(activePageButton, String.valueOf(k - 1)));
            }

            public void areAllDatabaseServicesPresent(String titlePhrase) {
                wait.until(ExpectedConditions.visibilityOf(resultsList));
                Integer pageNumberInFE = getNumberOfPagesFromFE();

                        List<WebElement> elementsPerPage = resultsList.findElements(By.className("services-item__title"));
                int last = elementsPerPage.size();
                for (int j = 0; j < pageNumberInFE; j++) {
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
                                String dateFE;
                                if (getServicesDates().get(i).length() == 9) {
                                        dateFE = "0" + getServicesDates().get(i);
                                    } else dateFE = getServicesDates().get(i);
                                assertTrue(dateText.equals(dateFE));
                            }
                        if (j + 1 < pageNumberInFE) {
                                pushNextPageNumberButton(j);
                                System.out.println(j+"active:"+activePageButton.getText());
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
                List<WebElement> titlesPerPage = resultsList.findElements(By.className("services-item__title"));
                assertTrue(titlesPerPage.size() <= limit);
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
                List<MyService> deleteList = restAssuredMethods.searchForServicesOnPage(pageNumber, 10,title).content;
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

