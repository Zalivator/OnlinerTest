package onliner.tests;

import framework.BaseTest;
import io.qameta.allure.Description;
import onliner.pageObject.pages.AutoBaraholkaPage;
import onliner.pageObject.pages.MainPage;
import org.testng.annotations.Test;

public class FiltrationAuto extends BaseTest {

    @Description("Фильтрация авто на странице 'Автобарахолка' по стоимости, типу кузова и типу коробки передач")
    @Test
    public void autobaraholkaTest() {
        MainPage mainPage = new MainPage();
        mainPage.navigateHeaderMenu("Автобарахолка");

        AutoBaraholkaPage autoBaraholkaPage = new AutoBaraholkaPage();
        autoBaraholkaPage.choiceCurrency("USD");
        autoBaraholkaPage.entryCarPrice("до");
        autoBaraholkaPage.choiceOfBodyType("Седан");
        autoBaraholkaPage.choiceGearbox("Автоматическая");
        autoBaraholkaPage.viewFiltrationAuto();
    }
    /*
    1. Перейти на вкладку Автобарахолка
    2. Фильтровать авто по цене до 100000$
    3. Фильтровать авто по типу кузова: седан
    4. Фильтровать авто по коробке передач: автоматическая
    5. Проверить работу фильтров
     */
}
