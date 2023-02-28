package onliner.pageObject;

import framework.elements.Button;
import framework.elements.Input;
import framework.elements.TextBox;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class AutoBaraholkaPage {
    private static final TextBox PAGE_LOCATOR = new TextBox(By.xpath("//h1[contains(text(), 'Автобарахолка')]"));
    private static final Button BTN_CURRENCY = new Button(By.xpath("//a[text()='USD']"));
    private static final Input INPUT_CAR_PRICE = new Input(By.xpath("//div[@class='vehicle-form__row vehicle-form__row_condensed-alter']//input[@placeholder='до']"));
    private static final Input CHECKBOX_BODY_TYPE = new Input(By.xpath("//div[@class='vehicle-form__checkbox-sign' and contains(text(),'Седан')]/../../input"));
    private static final Input CHECKBOX_GEARBOX = new Input(By.xpath("//div[@class='vehicle-form__checkbox-sign' and contains(text(),'Автоматическая')]/../../input"));
    private static final TextBox NO_AUTO_INFO = new TextBox(By.xpath("//span[contains(text(), 'Найдено')]"));

    //private static final TextBox GG = new TextBox(By.xpath("//div[@class='input-style__faux' and contains(text(), 'от')]"));
    @Step("Проверка загрузившейся страницы.")
    public void isPageOpened() {
        Assert.assertTrue(PAGE_LOCATOR.isDisplayed(), "ERROR: Страница 'Автобарахолка' не была загружена!");
    }

    @Step("Выбор валюты 'BYN' для фильтрации авто по цене.")
    public void choiceCurrency() {
        BTN_CURRENCY.click();
    }

    @Step("Фильтрация авто по цене до 100000$.")
    public void entryCarPrice() {
        INPUT_CAR_PRICE.scrollIntoView();
        INPUT_CAR_PRICE.sendKeys("100000");
    }

    @Step("Фильтрация авто по типу кузова: седан.")
    public void choiceOfBodyType() {
        CHECKBOX_BODY_TYPE.moveAndClickByAction();
    }

    @Step("Фильтрация авто по типу коробки передач: автоматическая.")
    public void choiceGearbox() {
        //GG.scrollIntoView();
        CHECKBOX_GEARBOX.moveAndClickByAction();
    }

    @Step("Проверка на найденные авто с учетом фильтрации.")
    public void isAutoVisible() {
        //Assert softAssert = new Assert();
        Assert.assertTrue(NO_AUTO_INFO.isDisplayed(), "Объявлений с учетом указанных фильтров найдено не было!");
        //softAssert.assertAll();
    }
}
