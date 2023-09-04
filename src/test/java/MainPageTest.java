import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import ru.yandex.praktikum.objects.MainPage;
import ru.yandex.praktikum.objects.OrderPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(Parameterized.class)
public class MainPageTest {

    private WebDriver driver;

    private final int number;



    @Before
    public void startUp() {
        //WebDriverManager.chromedriver().setup();
        //WebDriverManager.firefoxdriver().setup();
        WebDriverManager.edgedriver().setup();

    }

    public MainPageTest(int number) {
        this.number = number;
    }

    @Parameterized.Parameters
    public static Object[][] getCreds() {
        return new Object[][] {
                {0},
                {1},
                {2},
                {3},
                {4},
                {5},
                {6},
                {7},
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
        assertTrue(driver.findElement(mainPageObj.getTopOrderButton()).isDisplayed()
                && driver.findElement(mainPageObj.getBottomOrderButton()).isDisplayed());

        //проверяем что текст в стрелочках соответствует верному
        mainPageObj.checkArrowsText(number);

    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
