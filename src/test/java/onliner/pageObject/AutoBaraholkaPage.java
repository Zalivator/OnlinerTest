package onliner.pageObject;

import framework.elements.Button;
import framework.elements.Input;
import framework.elements.Label;
import framework.elements.TextBox;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AutoBaraholkaPage {
    private static final TextBox PAGE_LOCATOR = new TextBox(By.xpath("//h1[contains(text(), 'Автобарахолка')]"));
    private static final Button BTN_CURRENCY = new Button(By.xpath("//a[text()='USD']"));
    private static final Input INPUT_CAR_PRICE = new Input(By.xpath("//div[@class='vehicle-form__row vehicle-form__row_condensed-alter']//input[@placeholder='до']"));
    private static final Input CHECKBOX_BODY_TYPE = new Input(By.xpath("//div[@class='vehicle-form__checkbox-sign' and contains(text(),'Седан')]/../../input"));
    private static final Input CHECKBOX_GEARBOX = new Input(By.xpath("//div[@class='vehicle-form__checkbox-sign' and contains(text(),'Автоматическая')]/../../div[@class='i-checkbox__faux']"));
    private static final TextBox NO_AUTO_INFO = new TextBox(By.xpath("//span[contains(text(), 'Найдено')]"));

 /*   private static final TextBox AUTO_GOOD_FILTRATION = new TextBox(By.xpath("//div[@class='vehicle-form__offers-item']//div[contains(text(), 'Audi A6 (C7) Рестайлинг ULTRA')]/../../../../../.."));
    private static final TextBox AUTO_GOOD_FILTRATION_COST = new TextBox(By.xpath("//div[@class='vehicle-form__offers-item']//div[contains(text(), 'Audi A6 (C7) Рестайлинг ULTRA')]/../../../../..//div[@class='vehicle-form__description vehicle-form__description_tiny vehicle-form__description_other']"));
    private static final TextBox AUTO_GOOD_FILTRATION_TRANSMISSION = new TextBox(By.xpath("//div[@class='vehicle-form__offers-item']//div[contains(text(), 'Audi A6 (C7) Рестайлинг ULTRA')]/../../../../..//div[@class='vehicle-form__description vehicle-form__description_base vehicle-form__description_primary vehicle-form__description_transmission vehicle-form__description_condensed-other']"));
    private static final TextBox AUTO_GOOD_FILTRATION_BODY_TYPE = new TextBox(By.xpath("//div[@class='vehicle-form__offers-item']//div[contains(text(), 'Audi A6 (C7) Рестайлинг ULTRA')]/../../../../..//div[@class='vehicle-form__description vehicle-form__description_base vehicle-form__description_primary vehicle-form__description_car vehicle-form__description_condensed-other']"));
*/

    private static final Label CARS_AFTER_FILTRATION = new Label(By.xpath("//div[@class='vehicle-form__offers-item']"));
    private static final Label NEXT_ITEMS = new Label(By.xpath("//a[@class='vehicle-pagination__main']"));
    private static final TextBox CARS_EQUALS_BODY_TYPE = new TextBox(By.xpath("//div[@class='vehicle-form__offers-flex']//div[contains(text(), 'Седан')]"));

    private static final Input NO_BUTTON = new Input(By.xpath("//a[@class='vehicle-pagination__main vehicle-pagination__main_disabled']"));
    List<WebElement> gg = new ArrayList<>();

    private int costValue = 5000;

    @Step("Проверка загрузившейся страницы.")
    public void isPageOpened() {
        Assert.assertTrue(PAGE_LOCATOR.isDisplayed(), "ERROR: Страница 'Автобарахолка' не была загружена!");
    }

    @Step("Выбор валюты 'BYN' для фильтрации авто по цене.")
    public void choiceCurrency() {
        BTN_CURRENCY.click();
    }

    @Step("Фильтрация авто по цене до 5000$.")
    public void entryCarPrice() {
        INPUT_CAR_PRICE.scrollIntoView();
        INPUT_CAR_PRICE.sendKeys(Integer.toString(costValue));
    }

    @Step("Фильтрация авто по типу кузова: седан.")
    public void choiceOfBodyType() {
        CHECKBOX_BODY_TYPE.clickViaJS();
    }

    @Step("Фильтрация авто по типу коробки передач: автоматическая.")
    public void choiceGearbox() {
        CHECKBOX_GEARBOX.scrollIntoView();
        CHECKBOX_GEARBOX.clickViaJS();
    }

    /*@Step("Проверка на найденные авто с учетом фильтрации.")
    public void isAutoVisible() {
        Assert.assertTrue(NO_AUTO_INFO.isDisplayed(), "Объявлений с учетом указанных фильтров найдено не было!");
    }*/

    /*private int equalsCost() {
        String autoCost = AUTO_GOOD_FILTRATION_COST.getText().substring(0, AUTO_GOOD_FILTRATION_COST.getText().indexOf("$")).trim().replace(" ", "");
        int intCost = Integer.parseInt(autoCost);

        return intCost;
    }*/

    public void viewFiltrationAuto() {
        do {
            if (NEXT_ITEMS.isDisplayed()) {
                NEXT_ITEMS.click();
            }
            //NEXT_ITEMS.click();
            for (int i = 0; i < CARS_AFTER_FILTRATION.getElements().size(); i++) {
                Assert.assertTrue(CARS_AFTER_FILTRATION.isDisplayed());
                //Assert.assertEquals(CARS_EQUALS_BODY_TYPE.getText(), "Седан");
            }
            //NEXT_ITEMS.click();
            } while (CARS_AFTER_FILTRATION.isDisplayed());
        /*try {
            Assert.assertTrue(AUTO_GOOD_FILTRATION.isDisplayed(), "Объявлений с учетом указанных фильтров найдено не было!");
            Assert.assertTrue(equalsCost() < costValue, "Объявлений с учетом указанных фильтров найдено не было!");
            Assert.assertEquals(AUTO_GOOD_FILTRATION_TRANSMISSION.getText(), "Автоматическая");
            Assert.assertEquals(AUTO_GOOD_FILTRATION_BODY_TYPE.getText(), "Седан");
        } catch (Exception e) {
            Assert.assertTrue(AUTO_GOOD_FILTRATION.isDisplayed(), "Объявлений с учетом указанных фильтров найдено не было!");
            Assert.assertTrue(equalsCost() < costValue, "Объявлений с учетом указанных фильтров найдено не было!");
            Assert.assertEquals(AUTO_GOOD_FILTRATION_TRANSMISSION.getText(), "Автоматическая");
            Assert.assertEquals(AUTO_GOOD_FILTRATION_BODY_TYPE.getText(), "Седан");
        }*/

    }
}
