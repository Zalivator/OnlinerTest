package onliner.pageObject.pages;

import framework.BasePage;
import framework.elements.Button;
import framework.elements.Input;
import framework.elements.Label;
import framework.elements.TextBox;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class AutoBaraholkaPage extends BasePage {
    private static final By PAGE_LOCATOR = By.xpath("//h1[contains(text(), 'Автобарахолка')]");
    private static final String BTN_CURRENCY = "//a[text()='%s']";
    private static final String INPUT_CAR_PRICE = "//div[@class='vehicle-form__row vehicle-form__row_condensed-alter']//input[@placeholder='%s']";
    private static final String CHECKBOX_BODY_TYPE = "//div[@class='vehicle-form__checkbox-sign' and contains(text(),'%s')]/../../input";
    private static final String CHECKBOX_GEARBOX = "//div[@class='vehicle-form__checkbox-sign' and contains(text(),'%s')]/../../div[@class='i-checkbox__faux']";

    private static final Label CARS_AFTER_FILTRATION = new Label(By.xpath("//div[@class='vehicle-form__offers-item']"));
    private static final Label NEXT_ITEMS = new Label(By.xpath("//a[@class='vehicle-pagination__main']"));
    private static final String CARS_EQUALS_PARAMETERS = "//div[@class='vehicle-form__offers-flex']//div[contains(text(), '%s')]";
    private static final TextBox CARS_GOOD_COST = new TextBox(By.xpath("//div[@class='vehicle-form__offers-item']//div[contains(text(), '$')]"));
    private static final TextBox NO_BUTTON_FOR_NEXT_ITEMS = new TextBox(By.xpath("//a[@class='vehicle-pagination__main vehicle-pagination__main_disabled']"));
    private int costValue = 5000;

    public AutoBaraholkaPage() {
        super(PAGE_LOCATOR, "'Autobaraholka' page");
    }

    @Step("Выбор валюты 'USD' для фильтрации авто по цене.")
    public void choiceCurrency(String currency) {
        Button btnCurrency = new Button(By.xpath(String.format(BTN_CURRENCY, currency)));
        btnCurrency.click();
    }

    @Step("Фильтрация авто по цене до 5000$.")
    public void entryCarPrice(String carPriceFromTo) {
        Input inputCarPrice = new Input(By.xpath(String.format(INPUT_CAR_PRICE, carPriceFromTo)));
        inputCarPrice.scrollIntoView();
        inputCarPrice.sendKeys(Integer.toString(costValue));
    }

    @Step("Фильтрация авто по типу кузова: седан.")
    public void choiceOfBodyType(String carBodyType) {
        Input checkboxBodyType = new Input(By.xpath(String.format(CHECKBOX_BODY_TYPE, carBodyType)));
        checkboxBodyType.clickViaJS();
    }

    @Step("Фильтрация авто по типу коробки передач: автоматическая.")
    public void choiceGearbox(String carGearBox) {
        Input checkboxGearBox = new Input(By.xpath(String.format(CHECKBOX_GEARBOX, carGearBox)));
        checkboxGearBox.scrollIntoView();
        checkboxGearBox.clickViaJS();
    }

    @Step("Проверка отображения объявлений с указанными фильтрами.")
    public void viewFiltrationAuto() {
        TextBox carsEqualsBodyType = new TextBox(By.xpath(String.format(CARS_EQUALS_PARAMETERS, "Седан")));
        TextBox carsEqualsTransmission = new TextBox(By.xpath(String.format(CARS_EQUALS_PARAMETERS, "Автоматическая")));
        try {
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertTrue(CARS_AFTER_FILTRATION.isElementPresent(), "Объявлений с учетом указанных фильтров найдено не было!");
            do {
                for (int i = 0; i < CARS_AFTER_FILTRATION.getElements().size(); i++) {
                    Assert.assertTrue(CARS_AFTER_FILTRATION.isDisplayed());
                    Assert.assertEquals(carsEqualsBodyType.getText(), "Седан");
                    Assert.assertEquals(carsEqualsTransmission.getText(), "Автоматическая");
                    Assert.assertTrue(equalsCost() < costValue);

                }
                if (NEXT_ITEMS.isElementPresent()) {
                    NEXT_ITEMS.click();
                }
                else if (NO_BUTTON_FOR_NEXT_ITEMS.isElementPresent()){
                    break;
                }
                else {
                    break;
                }
            } while (CARS_AFTER_FILTRATION.isDisplayed());
            softAssert.assertAll();
        }catch (StaleElementReferenceException e) {
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertTrue(CARS_AFTER_FILTRATION.isElementPresent(), "Объявлений с учетом указанных фильтров найдено не было!");
            do {
                for (int i = 0; i < CARS_AFTER_FILTRATION.getElements().size(); i++) {
                    Assert.assertTrue(CARS_AFTER_FILTRATION.isDisplayed());
                    Assert.assertEquals(carsEqualsBodyType.getText(), "Седан");
                    Assert.assertEquals(carsEqualsTransmission.getText(), "Автоматическая");
                    Assert.assertTrue(equalsCost() < costValue);

                }
                if (NEXT_ITEMS.isElementPresent()) {
                    NEXT_ITEMS.click();
                }
                else if (NO_BUTTON_FOR_NEXT_ITEMS.isElementPresent()){
                    break;
                }
                else {
                    break;
                }
            } while (CARS_AFTER_FILTRATION.isDisplayed());
            softAssert.assertAll();
        }
    }

    private int equalsCost() {
        String autoCost = CARS_GOOD_COST.getText().substring(0, CARS_GOOD_COST.getText().indexOf("$")).trim().replace(" ", "");
        return Integer.parseInt(autoCost);
    }
}
