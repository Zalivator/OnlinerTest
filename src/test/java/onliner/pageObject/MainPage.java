package onliner.pageObject;

import framework.elements.Label;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

public class MainPage {
    private static final Label PAGE_LOCATOR = new Label(By.xpath("//img[@class='onliner_logo']"));
    private static final String NAV_MENU_ITEM = "//span[@class='b-main-navigation__text' and text()='%s']";

    @Step("Проверка загрузившейся страницы.")
    public void isPageOpened() {
        Assert.assertTrue(PAGE_LOCATOR.isDisplayed(), "ERROR: Главная страница не была загружена!");
    }

    @Step("Переход на страницу 'Каталог'.")
    public void navigateHeaderMenu() {
        Label mainMenuItem = new Label(By.xpath(String.format(NAV_MENU_ITEM, "Каталог")));
        mainMenuItem.clickAndWait();
    }

    @Step("Переход на страницу 'Автобарахолка'.")
    public void navigateHeaderMenuAB() {
        Label mainMenuItem = new Label(By.xpath(String.format(NAV_MENU_ITEM, "Автобарахолка")));
        mainMenuItem.clickAndWait();
    }
}
