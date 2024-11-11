import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import ru.practikum.yandex.pageobject.MainPage;
import ru.practikum.yandex.pageobject.OrderPage;

import java.time.Duration;

import static org.junit.Assert.assertTrue;
import static ru.practikum.yandex.pageobject.OrderPage.ORDER_PAGE_URL;

@RunWith(Enclosed.class)
public class OrderAScooterTest extends BaseUITest {

    @RunWith(Parameterized.class)
    public static class OrderFormTest {
        private WebDriver driver;
        private String firstName;
        private String lastName;
        private String address;
        private String subway;
        private String phone;
        private String deliveryDate;
        private String rentPeriod;
        private String scooterColor;
        private String commentToCourier;

        @Parameterized.Parameters
        public static Object[][] testData() {
            return new Object[][]{
                    {"Алина", "Иванова", "Ленина 5", "Бульвар Рокоссовского", "89829966246", "16.11.2024", "сутки", "черный жемчуг", "комментарий"},
                    {"Балерина", "Идет", "Домой", "Бульвар Рокоссовского", "89829966246", "16.11.2024", "сутки", "черный жемчуг", "комментарий"}
            };
        }
        public OrderFormTest(String firstName, String lastName, String address, String subway, String phone, String deliveryDate, String rentPeriod, String scooterColor, String commentToCourier) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
            this.subway = subway;
            this.phone = phone;
            this.deliveryDate = deliveryDate;
            this.rentPeriod = rentPeriod;
            this.scooterColor = scooterColor;
            this.commentToCourier = commentToCourier;
        }
        @Before
        public void setUp() {
            String browser = "chrome";
            if (browser.equals("chrome")) {
                ChromeOptions options = new ChromeOptions();
                driver = new ChromeDriver(options);
            } else if (browser.equals("firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                driver = new FirefoxDriver(options);
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }

        @After
        public void tearDown(){
            driver.quit();
        }

        @Test
                public void OrderAScooterUsingTheTopButtonTest () {
                    MainPage mainPage = new MainPage(driver);
                    mainPage.openMainPage();
                    mainPage.сookiesButtonClick();
                    mainPage.orderTopButtonClick();

                    OrderPage orderPage = new OrderPage(driver);
                    orderPage.fillOrderFormOnFirstPage(firstName, lastName, address, subway, phone);
                    orderPage.clickNextButton();
                    orderPage.fillOrderFormOnSecondPage(deliveryDate, rentPeriod, scooterColor, commentToCourier);
                    orderPage.clickOrderButton();
                    orderPage.clickApproveButton();
                    assertTrue ("Заказ не подтвержден", orderPage.isOrderConfirmed());


                }

                @Test
                public void CheckTheDownButtonOrderTest () {
                    MainPage mainPage = new MainPage(driver);
                    mainPage.openMainPage();
                    mainPage.сookiesButtonClick();
                    mainPage.orderDownButtonClick();

                    String currentUrl = driver.getCurrentUrl();

                    Assert.assertEquals(ORDER_PAGE_URL, currentUrl);
                }
            }
        }
