package ru.practikum.yandex.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private WebDriver driver;

    //кнопка для принятия куки "Да, все привыкли"
    private By cookiesButton = By.xpath(".//button[@id='rcc-confirm-button']");

    //кнопка "Заказать" в верхней части главной страницы
    private By orderTopButton = By.xpath (".//button[@class='Button_Button__ra12g']");

    //кнопка "Заказать" в верхней части главной страницы
    private By orderDownButton = By.xpath (".//button[contains(@class, 'Button_Middle')]");

    //первый вопрос FAQ
    private By firstQuestion = By.xpath (".//div[@id='accordion__heading-0']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public static final String MAIN_PAGE_URL = "https://qa-scooter.praktikum-services.ru/";

    public void openMainPage() {
        driver.get(MAIN_PAGE_URL);
    }

    public void cookiesButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(cookiesButton));
        driver.findElement(cookiesButton).click();
    }

    public WebElement getQuestion(int index) {
        return driver.findElement(By.id("accordion__heading-" + index));
    }

    public WebElement getAnswer(int index) {
        return driver.findElement(By.id("accordion__panel-" + index));
    }

    public void orderTopButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(orderTopButton));
        driver.findElement(orderTopButton).click();
    }

    public void orderDownButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(orderDownButton));
        driver.findElement(orderDownButton).click();
    }

    public void scrollToElement() {WebElement faqElement = driver.findElement(firstQuestion);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", faqElement);
    }
}
