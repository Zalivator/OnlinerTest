package onliner;

import framework.BaseTest;
import io.qameta.allure.Description;
import onliner.pageObject.AutoBaraholkaPage;
import onliner.pageObject.MainPage;
import org.testng.annotations.Test;

public class FiltrationAuto extends BaseTest {

    @Description("Фильтрация авто на странице 'Автобарахолка' по стоимости, типу кузова и типу коробки передач")
    @Test
    public void autobaraholkaTest() {
        MainPage mainPage = new MainPage();
        mainPage.isPageOpened();
        mainPage.navigateHeaderMenuAB();

        AutoBaraholkaPage autoBaraholkaPage = new AutoBaraholkaPage();
        autoBaraholkaPage.isPageOpened();
        autoBaraholkaPage.choiceCurrency();
        autoBaraholkaPage.entryCarPrice();
        autoBaraholkaPage.choiceOfBodyType();
        autoBaraholkaPage.choiceGearbox();
        autoBaraholkaPage.isAutoVisible();
    }
    /*
    1. Перейти на вкладку Автобарахолка
    2. Фильтровать авто по цене до 100000$
    3. Фильтровать авто по типу кузова: седан
    4. Фильтровать авто по коробке передач: автоматическая
    5. Проверить работу фильтров
     */
}
