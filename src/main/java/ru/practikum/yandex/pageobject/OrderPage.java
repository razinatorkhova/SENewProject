package ru.practikum.yandex.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {

//   шаги:
//            1. Нажать на кнопку "Заказать"
//            2. Заполнить форму "Для кого самокат"
//            3. Заполнить форму "Про аренду"
//            4. Нажать на кнопку "Заказать"
//            5. На вопрос "Хотите оформить заказ" нужно ответить "Да"
//            6. Проверить, что появилось модальное окно "Заказ оформлен" -- проверить, что текст в окне содержит текст "Заказ оформлен"

    public static final String ORDER_PAGE_URL = "https://qa-scooter.praktikum-services.ru/order";

    private By firstNameInput = By.xpath("//input[@placeholder='* Имя']");
    private By lastNameInput = By.xpath("//input[@placeholder='* Фамилия']");
    private By addressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private By subwayCheck = By.xpath("//input[@placeholder='* Станция метро']");
    private By phoneInput = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By metroStationInput = By.xpath(".//button[@value='1']");

    private By nextButton = By.xpath("//button[text()='Далее']");

    private By deliveryDateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private By rentPeriodInput = By.xpath(".//div[@class='Dropdown-placeholder']");
    private By scooterColorCheck = By.xpath(".//input[@id='black']");
    private By commentToCourierInput = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private By oneDayRent = By.xpath(".//div[@role='option' and text()='сутки']");
    private By chooseDate = By.xpath(".//div[@aria-label='Choose суббота, 16-е ноября 2024 г.']");

    private By orderButton = By.xpath("//*[@class='Order_Buttons__1xGrp']//button[text()='Заказать']");

    private By approveButton = By.xpath("//*[@class='Order_Buttons__1xGrp']//button[text()='Да']");

    private By orderConfirmed = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and text()='Заказ оформлен']");

    protected WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillOrderFormOnFirstPage(String firstName, String lastName, String address, String subway, String phone) {
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(addressInput).sendKeys(address);
        driver.findElement(subwayCheck).click();
        metroStationInput();
        driver.findElement(phoneInput).sendKeys(phone);
    }

    public void fillOrderFormOnSecondPage(String deliveryDate, String rentPeriod, String scooterColor, String commentToCourier) {
        driver.findElement(deliveryDateInput).click();
        deliveryDateInput();
        driver.findElement(rentPeriodInput).click();
        rentPeriodInput();
        driver.findElement(scooterColorCheck).click();
        driver.findElement(commentToCourierInput).sendKeys(commentToCourier);
    }

    public void metroStationInput(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(metroStationInput));
        driver.findElement(metroStationInput).click();
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public void deliveryDateInput(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(chooseDate));
        driver.findElement(chooseDate).click();
    }

    public void rentPeriodInput(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(oneDayRent));
        driver.findElement(oneDayRent).click();
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    public void clickApproveButton() {
        driver.findElement(approveButton).click();
    }

    public boolean isOrderConfirmed() {
        WebElement orderStatusElement = driver.findElement(orderConfirmed);
        String orderStatusText = orderStatusElement.getText();
        return orderStatusText.contains("Заказ оформлен");
    }
}
