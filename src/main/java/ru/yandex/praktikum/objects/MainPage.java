package ru.yandex.praktikum.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.*;


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

    public void checkArrowsText(String text, int index) {
            // подставляем в строку для выражения локатора номер кнопки-стрелки и закрываем выражение
            WebElement element = driver.findElement(By.xpath(mod + index + "']"));
            //скроллим до нужного элемента
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
            element.click();
            //ждем пока появится текст
            new WebDriverWait(driver, Duration.ofSeconds(1))
                    .until(ExpectedConditions
                            .visibilityOf(driver.findElement(By.xpath(modText + index + "']"))));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
            assertEquals(text, driver.findElement(By.xpath(modText + index  + "']")).getText());
    }

    public void checkOrderButtonsExists() {
        assertTrue(driver.findElement(topOrderButton).isDisplayed()
                && driver.findElement(bottomOrderButton).isDisplayed());
    }
}
