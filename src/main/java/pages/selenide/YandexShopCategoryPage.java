package pages.selenide;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class YandexShopCategoryPage extends BasePage{

    List<SelenideElement> products = new ArrayList<>();


    @Step("Фильтруем товары по производителю {nameBrand}")
    public YandexShopCategoryPage chooseBrand(String nameBrand){
        ElementsCollection brands = $$x("//fieldset[contains(legend,'Производитель')]//li/div");
        brands.find(text(nameBrand)).click();
        $x("//div[@class='_2Lvbi _1oZmP']").shouldNotBe(visible);
        return this;
    }

    @Step("Показать {maxItems} продуктов на странице")
    public YandexShopCategoryPage chooseItemsPerPage(String maxItems){
        $x("//button[contains(.,'Показывать по') and @aria-expanded]").click();
        $x("//button[contains(.,'Показывать по "+maxItems+"') and @data-tid]").click();
        $$x("//div[contains(@data-zone-name,'snippetList')]/article").shouldBe(CollectionCondition.size(Integer.parseInt(maxItems)));
        return this;
    }

    @Step("Проверка всех продуктов {nameBrand} в результате поиска")
    public YandexShopCategoryPage checkProductsBrand(String nameBrand){
        boolean check = true;
        products = $$x("//div[contains(@data-zone-name,'snippetList')]/article//h3");
        do{
            if(!products.stream().allMatch(x -> x.getText().contains(nameBrand))){
                check=false;
                break;
            }
            $x("//a[@aria-label='Следующая страница']").click();
            $x("//div[@class='_2Lvbi _1oZmP']").shouldNotBe(visible);
        }while ($x("//a[@aria-label='Следующая страница']").isDisplayed());
        Assertions.assertTrue(check);
        return this;
    }
}
