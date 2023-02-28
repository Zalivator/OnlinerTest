package onliner;

import framework.BaseTest;
import io.qameta.allure.Description;
import onliner.pageObject.CatalogPage;
import onliner.pageObject.MainPage;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {

    @Description("Переход на страницу 'Игровые ноутбуки'. Ожидаемый результат: страница 'Игровые ноутбуки' загружена.")
    @Test
    public void onlinerTest() {
        MainPage mainPage = new MainPage();
        mainPage.isPageOpened();
        mainPage.navigateHeaderMenu();

        CatalogPage catalogPage = new CatalogPage();
        catalogPage.isPageOpened();
        catalogPage.navigateToComputers();
        catalogPage.navigateToSubMenuItems();
        catalogPage.navigateToSubMenuCategory();
    }
}
