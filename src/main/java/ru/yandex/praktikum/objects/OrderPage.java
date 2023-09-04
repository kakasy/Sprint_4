package ru.yandex.praktikum.objects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

//страница заказа самоката
public class OrderPage {
    private WebDriver driver;

    //локатор для верхней кнопки "Заказать"
    private final By topOrderButton = By.xpath("//*[@id='root']/div/div/div[1]/div[2]/button[1]");

    //локатор для нижней кнопки "Заказать"
    private final By bottomOrderButton = By.xpath("//*[@id='root']/div/div/div[4]/div[2]/div[5]/button");

    //локатор поля с именем
    private final By firstName = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[1]/input");

    //локатор поля с фамилией
    private final By lastName = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[2]/input");

    //локатор поля с адресом
    private final By address = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[3]/input");

    //локатор поля со станцией метро
    private final By metro = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[4]/div/div/input");

    //локатор поля номера телефона
    private final By phoneNumber = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[5]/input");

    //локатор кнопки "Далее"
    private final By nextButton = By.xpath("//*[@id='root']/div/div[2]/div[3]/button");

    //локатор поля даты привоза самоката
    private final By dateDelivery = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[1]/div[1]/div/input");

    //локатор кнопки с выпадающим списком
    private final By listButton = By.xpath("//*[@class='Dropdown-control']");

    //локатор для значения выпадающего списка
    private final String dropDownValue = "//*[@class='Dropdown-menu']/div[text()='%s']";

    //локатор для поля с чекбоксами для выбора цвета
    private final String checkBoxes = "//label[@for='%s']";

    //локатор кнопки "Заказать"
    private final By orderButton = By.xpath("//*[@id='root']/div/div[2]/div[3]/button[2]");

    //локатор кнопки "Да" для окна оформления заказа
    private final By orderYesButton = By.xpath("//*[@id='root']/div/div[2]/div[5]/div[2]/button[2]");

    //локатор окна с подтвержденным заказом
    private final By confirmWindow = By.xpath("//*[@class='Order_Modal__YZ-d3']");

    //локатор кнопки "Посмотреть статус"
    private final By seeStatus = By.xpath("//*[@id='root']/div/div[2]/div[5]/div[2]/button");

    //локатор для лого Яндекс
    private final By yandexLogo = By.xpath("//*[@class='Header_LogoYandex__3TSOI']");

    //локатор для лого Самокат
    private  final By scooterLogo = By.xpath("//*[@class='Header_LogoScooter__3lsAR']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //проверка наличия кнопки "Далее" и нажатие на нее
    public void checkNClickNextButton() {
        assertTrue(driver.findElement(nextButton).isDisplayed());
        driver.findElement(nextButton).click();
    }

    //нажатие верхней кнопки "Заказать"
    public void clickTopButton() {
        driver.findElement(topOrderButton).click();
    }

    //скролл и нажатие нижней кнопки "Заказать"
    public void clickBottomButton() {
        WebElement element = driver.findElement(bottomOrderButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    //случайный выбор кнопки для заказа
    public void clickRandomButton() {
        int rand = (int) (Math.random() * 2);
        if (rand == 1) {
            clickTopButton();
            System.out.println("Нажали верхнюю кнопку");
        } else {
            clickBottomButton();
            System.out.println("Нажали нижнюю кнопку");
        }
    }

    public void setFirstName(String name) {
        driver.findElement(firstName).sendKeys(name);
    }

    public void setLastName(String surname) {
        driver.findElement(lastName).sendKeys(surname);
    }

    public void setAddress(String addr) {
        driver.findElement(address).sendKeys(addr);
    }

    public void setStation(String station) {
        driver.findElement(metro).sendKeys(station, Keys.ARROW_DOWN, Keys.ENTER);
    }

    public void setPhoneNumber(String number) {
        driver.findElement(phoneNumber).sendKeys(number);
    }

    //заполнение полей заказа
    public void fillOrderFields(String name, String surname, String addr, String station, String number) {
        setFirstName(name);
        setLastName(surname);
        setAddress(addr);
        setStation(station);
        setPhoneNumber(number);

    }

    public void setDateDelivery(String date) {
        driver.findElement(dateDelivery).sendKeys(date, Keys.ENTER);
    }

    //нажатие на поле выбора времени аренды и выбор значения
    public void setLeaseTime(String value) {
        driver.findElement(listButton).click();
        driver.findElement(By.xpath(String.format(dropDownValue, value))).click();
    }

    //выбор цвета самоката
    public void setCheckBoxes(String[] values) {
        for (int i = 0; i < values.length; i++) {
            driver.findElement(By.xpath(String.format(checkBoxes, values[i]))).click();
        }

    }

    //метод для заполнения полей 2 части заказа (без комментария)
    public void setLease(String date, String leaseValue, String[] values) {
        setDateDelivery(date);
        setLeaseTime(leaseValue);
        setCheckBoxes(values);

    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    public void clickYesOrderButton() {
        driver.findElement(orderYesButton).click();
    }

    public void clickSeeStatusButton() {
        WebElement element = driver.findElement(confirmWindow);
        //скроллим до нужного элемента
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
        //ждем пока появится текст
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions
                        .visibilityOf(driver.findElement(confirmWindow)));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(seeStatus).click();
    }


    //метод для проверки урла при нажатии на лого Самокат
    public String checkUrlScooterLogoClick() {
        driver.findElement(scooterLogo).click();
        return driver.getCurrentUrl();
    }

    //метод проверки урла при нажатии на лого Яндекс
    public String checkUrlYandexLogoClick() {

        String originalWindow = driver.getWindowHandle();
        //Check we don't have other windows open already
        assert driver.getWindowHandles().size() == 1;

        //Click the link which opens in a new window
        driver.findElement(yandexLogo).click();

        //Wait for the new window or tab
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(numberOfWindowsToBe(2));

        //Loop through until we find a new window handle
        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        //Wait for the new tab to finish loading content
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(titleIs("Дзен"));

        new WebDriverWait(driver, Duration.ofSeconds(3));
        return driver.getCurrentUrl();

    }

    public By getTopOrderButton() {
        return topOrderButton;
    }

    public By getBottomOrderButton() {
        return bottomOrderButton;
    }

    public By getConfirmWindow() {
        return confirmWindow;
    }
}
