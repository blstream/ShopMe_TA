package com.intive.QAPatronage3.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage {
    private WebDriver driver;

    public ProductsPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.CLASS_NAME, using = "product_img_link")
    private WebElement BlousePicture;
    @FindBy(how = How.NAME, using = "Submit")
    private WebElement AddToCartButton;


    public void chooseBlouse() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(BlousePicture));
        BlousePicture.click();
    }

    public void clickAddToCart() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(AddToCartButton));
        AddToCartButton.click();
    }


}
