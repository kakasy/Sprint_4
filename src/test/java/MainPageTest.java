import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import ru.yandex.praktikum.objects.MainPage;


@RunWith(Parameterized.class)
public class MainPageTest {

    private WebDriver driver;

    private final int index;
    private final String text;



    @Before
    public void startUp() {
        //WebDriverManager.chromedriver().setup();
        //WebDriverManager.firefoxdriver().setup();
        WebDriverManager.edgedriver().setup();

    }

    public MainPageTest(String text, int index) {
        this.text = text;
        this.index = index;
    }

    @Parameterized.Parameters
    public static Object[][] getCreds() {
        return new Object[][] {
                {"Сутки — 400 рублей. Оплата курьеру — наличными или картой.", 0},
                {"Пока что у нас так: один заказ — один самокат. " +
                        "Если хотите покататься с друзьями, " +
                        "можете просто сделать несколько заказов — один за другим.", 1},
                {"Допустим, вы оформляете заказ на 8 мая. " +
                        "Мы привозим самокат 8 мая в течение дня. " +
                        "Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. " +
                        "Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", 2},
                {"Только начиная с завтрашнего дня. Но скоро станем расторопнее.", 3},
                {"Пока что нет! " +
                        "Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", 4},
                {"Самокат приезжает к вам с полной зарядкой. " +
                        "Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. " +
                        "Зарядка не понадобится.", 5},
                {"Да, пока самокат не привезли. " +
                        "Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", 6},
                {"Да, обязательно. Всем самокатов! И Москве, и Московской области.", 7},
        };
    }


    @Test
    public void testMainPage() {

        driver = new EdgeDriver();

        //открываем страницу
        driver.get("https://qa-scooter.praktikum-services.ru/");

        //создаем экземпляр главной страницы
        MainPage mainPageObj = new MainPage(driver);

        //проверяем наличие двух кнопок заказа
        mainPageObj.checkOrderButtonsExists();

        //проверяем что текст в стрелочках соответствует верному
        mainPageObj.checkArrowsText(text, index);

    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
