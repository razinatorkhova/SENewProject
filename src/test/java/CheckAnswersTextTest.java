import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.practikum.yandex.pageobject.MainPage;

import java.time.Duration;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CheckAnswersTextTest extends BaseUITest {

    private final int questionIndex;
    private MainPage mainPage;

    private static final String[] EXPECTED_ANSWERS = {
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."
    };

    public CheckAnswersTextTest(int questionIndex) {
        this.questionIndex = questionIndex;
    }

    @Parameterized.Parameters
    public static Object[] data() {
        return new Object[]{0, 1, 2, 3, 4, 5, 6, 7};
    }

    @Test
    public void questionsAndAnswersTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.сookiesButtonClick();
        mainPage.scrollToElement();
        mainPage.getQuestion(questionIndex).click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(mainPage.getAnswer(questionIndex)));

        String answerText = mainPage.getAnswer(questionIndex).getText();

        String expectedAnswerText = EXPECTED_ANSWERS[questionIndex];

        assertEquals("Ответ не соответствует ожидаемому", answerText, expectedAnswerText);
    }
}
