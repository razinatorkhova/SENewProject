import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.practikum.yandex.pageobject.MainPage;
import ru.practikum.yandex.pageobject.OrderPage;

import static org.junit.Assert.assertTrue;

@RunWith(Enclosed.class)
public class OrderAScooterTest {

    @RunWith(Parameterized.class)
    public static class OrderFormTest extends BaseUITest{
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

        @Test
         public void orderAScooterUsingTheTopButtonTest () {
            MainPage mainPage = new MainPage(driver);
            mainPage.orderTopButtonClick();

            OrderPage orderPage = new OrderPage(driver);
            orderPage.fillOrderFormOnFirstPage(firstName, lastName, address, subway, phone);
            orderPage.clickNextButton();
            orderPage.fillOrderFormOnSecondPage(deliveryDate, rentPeriod, scooterColor, commentToCourier);
            orderPage.clickOrderButton();
            orderPage.clickApproveButton();
            assertTrue("Заказ не подтвержден", orderPage.isOrderConfirmed());

        }

        @Test
        public void checkTheDownButtonOrderTest () {
            MainPage mainPage = new MainPage(driver);
            mainPage.orderDownButtonClick();

            String currentUrl = driver.getCurrentUrl();

            Assert.assertEquals(OrderPage.ORDER_PAGE_URL, currentUrl);
                }
              }
        }
