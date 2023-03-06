package onliner.pageObject.pages;

import framework.BasePage;
import framework.elements.Label;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class MainPage extends BasePage {
    private static final By PAGE_LOCATOR = By.xpath("//img[@class='onliner_logo']");
    private static final String NAV_MENU_ITEM = "//span[@class='b-main-navigation__text' and text()='%s']";

    public MainPage() {
        super(PAGE_LOCATOR, "'Main' page");
    }

    @Step("Переход на страницу из верхнего навигационного меню.")
    public void navigateHeaderMenu(String headerMenuItem) {
        Label mainMenuItem = new Label(By.xpath(String.format(NAV_MENU_ITEM, headerMenuItem)));
        mainMenuItem.clickAndWait();
    }
}
