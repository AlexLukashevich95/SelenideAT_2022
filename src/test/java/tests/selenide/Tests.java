package tests.selenide;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.selenide.YandexMainPage;
import pages.selenide.YandexShopPage;

import static com.codeborne.selenide.Selenide.open;

public class Tests extends BaseTests{

    @Feature("Проверка результатов поиска")
    @DisplayName("Проверка результатов поиска ")
    @ParameterizedTest(name = "{displayName} {arguments}")
    @CsvSource({"Apple"})
    public void yandexSelenideCheckBrands(String brand) {
        open("https://www.yandex.ru/", YandexMainPage.class)
                .goLinkByName("Маркет", YandexShopPage.class)
                .goToCategory("Электроника","Смартфоны")
                .chooseBrand(brand)
                .chooseItemsPerPage("12")
                .checkProductsBrand(brand);
    }
}
