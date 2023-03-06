package onliner.pageObject.pages;

import framework.BasePage;
import framework.elements.Label;
import framework.elements.TextBox;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CatalogPage extends BasePage {
    private static final By PAGE_LOCATOR = By.xpath("//div[@class='catalog-navigation__title' and text()='Каталог']");
    private static final String NAVIGATE_MENU = "//span[@class='catalog-navigation-classifier__item-title-wrapper' and contains(text(), '%s')]";
    private static final String NAV_SUBMENU_CATEGORY = "//div[@class='catalog-navigation-list__category']//span[@class='catalog-navigation-list__dropdown-title' and contains(text(), '%s')]";
    private static final String NAV_SUBMENU_ITEM = "//div[@class='catalog-navigation-list__aside-title' and contains(text(), '%s')]";

    public CatalogPage() {
        super(PAGE_LOCATOR, "'Catalog' page");
    }

    @Step("Выбор заголовка 'Компьютеры и сети' в меню навигации.")
    public void navigateToMainMenuItem(String navMenuItem) {
        TextBox navMenuOnCatalogPage = new TextBox(By.xpath(String.format(NAVIGATE_MENU, navMenuItem)));
        navMenuOnCatalogPage.click();
    }

    @Step("Наведение на подменю 'Ноутбуки, компьютеры, мониторы'.")
    public void navigateToSubMenuItems(String subMenuItem) {
        Label navSubMenuItem = new Label(By.xpath(String.format(NAV_SUBMENU_ITEM, subMenuItem)));
        navSubMenuItem.moveToElement();
    }

    @Step("Переход на страницу 'Игровые ноутбуки'.")
    public void navigateToSubMenuCategory(String subMenuCategory) {
        Label navSubMenuCategory = new Label(By.xpath(String.format(NAV_SUBMENU_CATEGORY, subMenuCategory)));
        navSubMenuCategory.moveAndClickByAction();
    }
}
