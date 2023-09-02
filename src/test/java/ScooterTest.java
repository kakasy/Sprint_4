import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverInfo;
import ru.yandex.praktikum.objects.MainPage;
import ru.yandex.praktikum.objects.OrderPage;

import static org.junit.Assert.assertTrue;


@RunWith(Parameterized.class)
public class ScooterTest {

    private final String name;
    private final String surname;
    private final String address;
    private final String station;
    private final String number;

    private final String date;
    private final String lease;
    private final String[] colors;

    public ScooterTest(String name, String surname, String address, String station, String number, String date,
                       String lease, String[] colors) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.station = station;
        this.number = number;
        this.date = date;
        this.lease = lease;
        this.colors = colors;
    }

    private WebDriver driver;


    @Before
    public void startUp() {
        //WebDriverManager.chromedriver().setup();
        driver = WebDriverManager.firefoxdriver().create();

    }


    @Parameterized.Parameters
    public static Object[][] getCreds() {
        return new Object[][] {
                {"Билли", "Херрингтон", "Подвал кожевенного ремесла", "Черниговская", "88005553535", "05.11.2023"
                        , "сутки", new String[] {"black"}},
                {"Наруто", "Узумаки", "11-1 Камитоба-Хокотатэ-чо Минами-Кю Киото", "Ломоносова", "89003001000"
                        , "04,12,20", "двое суток", new String[] {"black", "grey"}},
        };
    }


    @Test
    public void testMainPage() {
        //driver = new ChromeDriver();
        //driver = new FirefoxDriver();

        //открываем страницу
        driver.get("https://qa-scooter.praktikum-services.ru/");

        //создаем экземпляр главной страницы
        MainPage mainPageObj = new MainPage(driver);

        //проверяем наличие двух кнопок заказа
        assertTrue(driver.findElement(mainPageObj.getTopOrderButton()).isDisplayed()
                && driver.findElement(mainPageObj.getBottomOrderButton()).isDisplayed());

        //проверяем что текст в стрелочках соответствует верному
        mainPageObj.checkArrowsText();

    }


    @Test
    public void testOrderPage() {
        //driver = new ChromeDriver();
        //driver = new FirefoxDriver();


        //открываем страницу
        driver.get("https://qa-scooter.praktikum-services.ru/");

        //создаем экземпляр страницы с заказом
        OrderPage orderPageObj = new OrderPage(driver);

        //проверяем наличие двух кнопок заказа
        assertTrue(driver.findElement(orderPageObj.getTopOrderButton()).isDisplayed()
                && driver.findElement(orderPageObj.getBottomOrderButton()).isDisplayed());

        //нажимаем случайную кнопку заказа
        orderPageObj.clickRandomButton();

        //заполняем поля заказа
        orderPageObj.fillOrderFields(name, surname, address, station, number);

        //нажимаем кнопку далее
        orderPageObj.checkNClickNextButton();

        //заполняем вторую часть заказа
        orderPageObj.setLease(date, lease, colors);

        //нажимаем заказать
        orderPageObj.clickOrderButton();

        //подтверждаем заказ, нажав на кнопку "Да"
        orderPageObj.clickYesOrderButton();

    }


//    @After
//    public void tearDown() {
//        driver.quit();
//    }

}
