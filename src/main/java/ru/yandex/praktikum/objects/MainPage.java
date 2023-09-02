package ru.yandex.praktikum.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

import static org.junit.Assert.assertEquals;


//класс для описания главной страницы
public class MainPage {

    private WebDriver driver;

    //локатор для верхней кнопки "Заказать"
    private final By topOrderButton = By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/button[1]");

    //локатор для нижней кнопки "Заказать"
    private final By bottomOrderButton = By.xpath("//*[@id=\"root\"]/div/div/div[4]/div[2]/div[5]/button");

    //общий вид локатора для кнопок-стрелок
    private final String arrowButton = "//*[@id='accordion__heading-']";

    // строка для использования в цикле для нажатия на кнопки по шаблону локатора
    private final String mod = arrowButton.substring(0, arrowButton.length() - 2);

    //общий вид локатора для текста под кнопками-стрелками
    private final String textLocator = "//*[@id='accordion__panel-']";

    //строка для использования в цикле для нахождения текста определенной кнопки
    private final String modText = textLocator.substring(0, textLocator.length() - 2);

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkArrowsText() {
        for (int i = 0; i < 8; i++) {
            // подставляем в строку для выражения локатора номер кнопки-стрелки и закрываем выражение
            WebElement element = driver.findElement(By.xpath(mod + i + "']"));
            //скроллим до нужного элемента
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
            element.click();
            //ждем пока появится текст
            new WebDriverWait(driver, Duration.ofSeconds(1))
                    .until(ExpectedConditions
                            .visibilityOf(driver.findElement(By.xpath(modText + i + "']"))));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);


            if (i == 0) {
                assertEquals("Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
                        driver.findElement(By.xpath(modText + i + "']" )).getText());
            } else if (i == 1) {
                assertEquals("Пока что у нас так: один заказ — один самокат. " +
                                "Если хотите покататься с друзьями, " +
                                "можете просто сделать несколько заказов — один за другим.",
                        driver.findElement(By.xpath(modText + i + "']" )).getText());
            } else if (i == 2) {
                assertEquals("Допустим, вы оформляете заказ на 8 мая. " +
                                "Мы привозим самокат 8 мая в течение дня. " +
                                "Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. " +
                                "Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
                        driver.findElement(By.xpath(modText + i + "']" )).getText());
            } else if (i == 3) {
                assertEquals("Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
                        driver.findElement(By.xpath(modText + i + "']" )).getText());
            } else if (i == 4) {
                assertEquals("Пока что нет! " +
                                "Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
                        driver.findElement(By.xpath(modText + i + "']" )).getText());
            } else if (i == 5) {
                assertEquals("Самокат приезжает к вам с полной зарядкой. " +
                                "Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. " +
                                "Зарядка не понадобится.",
                        driver.findElement(By.xpath(modText + i + "']" )).getText());
            } else if (i == 6) {
                assertEquals("Да, пока самокат не привезли. " +
                                "Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
                        driver.findElement(By.xpath(modText + i + "']" )).getText());
            } else {
                assertEquals("Да, обязательно. Всем самокатов! И Москве, и Московской области.",
                        driver.findElement(By.xpath(modText + i + "']" )).getText());
            }


        }

    }

    public By getTopOrderButton() {
        return topOrderButton;
    }

    public By getBottomOrderButton() {
        return bottomOrderButton;
    }
}
