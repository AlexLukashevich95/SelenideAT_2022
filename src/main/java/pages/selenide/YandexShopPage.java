package pages.selenide;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class YandexShopPage extends BasePage{

    @Step("Переходим по категории {nameCategory} и подкатегории {nameSubcategory}")
    public YandexShopCategoryPage goToCategory(String nameCategory,String nameSubcategory){
        $x("//button[@id='catalogPopupButton']").click();
        ElementsCollection categories = $$x("//li[@data-zone-name='category-link']");
        categories.find(text(nameCategory)).hover();
        ElementsCollection subcategories = $$x("//ul[@data-autotest-id='subItems']/li");
        subcategories.find(text(nameSubcategory)).click();
        return page(YandexShopCategoryPage.class);
    }
}
