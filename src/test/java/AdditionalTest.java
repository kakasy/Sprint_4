import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import ru.yandex.praktikum.objects.OrderPage;

public class AdditionalTest {
    private WebDriver driver;

    @Before
    public void startUp() {
        //WebDriverManager.chromedriver().setup();
        //WebDriverManager.firefoxdriver().setup();
        WebDriverManager.edgedriver().setup();
    }

    @Test
    public void clickScooterLogo() {
        //driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        driver = new EdgeDriver();

        //открываем страницу
        driver.get("https://qa-scooter.praktikum-services.ru/");

        //создаем экземпляр страницы с заказом
        OrderPage orderPageObj = new OrderPage(driver);


        //проверяем наличие двух кнопок заказа
        orderPageObj.checkOrderButtonsExists();

        //нажимаем кнопку заказа
        orderPageObj.clickOrderButtonMain(true);

        //проверяем что после нажатия на лого самоката нас перекинет на главную страницу проката самокатов
        orderPageObj.scooterLogoCheck();
    }

    @Test
    public void clickYandexLogo() {
        //driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        driver = new EdgeDriver();

        //открываем страницу
        driver.get("https://qa-scooter.praktikum-services.ru/");

        //создаем экземпляр страницы с заказом
        OrderPage orderPageObj = new OrderPage(driver);

        //проверяем что при нажатии на лого Яндекс нас перекинет на новую страницу с нужным адресом
        orderPageObj.yandexLogoCheck();
    }


    @After
    public void teadDown() {
        driver.quit();
    }
}
