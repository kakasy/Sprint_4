import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import ru.yandex.praktikum.objects.OrderPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(Parameterized.class)
public class OrderPageTest {

    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String station;
    private final String number;

    private final String date;
    private final String lease;
    private final String[] colors;

    public OrderPageTest(String name, String surname, String address, String station, String number, String date,
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


    @Before
    public void startUp() {
        //WebDriverManager.chromedriver().setup();
        //WebDriverManager.firefoxdriver().setup();
        WebDriverManager.edgedriver().setup();

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
    public void testOrderPage() {
        //driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        driver = new EdgeDriver();

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

        //проверяем что после нажатия на кнопку появилось окно с информацией о заказе
        assertTrue(driver.findElement(orderPageObj.getConfirmWindow()).isDisplayed());

        //смотрим статус заказа
        orderPageObj.clickSeeStatusButton();

        //проверяем что после нажатия на лого самоката нас перекинет на главную страницу проката самокатов
        assertEquals("https://qa-scooter.praktikum-services.ru/", orderPageObj.checkUrlScooterLogoClick());

        //проверяем что при нажатии на лого Яндекс нас перекинет на новую страницу с нужным адресом
        assertEquals("https://dzen.ru/?yredirect=true", orderPageObj.checkUrlYandexLogoClick());

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
