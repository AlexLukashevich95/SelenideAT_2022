package pages.selenide;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.switchTo;

public class YandexMainPage extends BasePage{

    @Step("Поереходим по имени ссылки {linkName}")
    public <T extends BasePage> T goLinkByName(String linkName, Class<T> typeNextPage){
        ElementsCollection elem = $$x("//li[contains(@class,'services-new__list-item')]/a");
        elem.find(text(linkName)).click();
        switchTo().window(1);
        return typeNextPage.cast(page(typeNextPage));
    }
}
