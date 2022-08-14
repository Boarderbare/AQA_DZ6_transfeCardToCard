package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashBoard {
    private final ElementsCollection list = $$(".list div[data-test-id]").filter(visible);

    public DashBoard() {
        SelenideElement title = $(byText("Личный кабинет"));
        title.shouldBe(visible);
        SelenideElement titleSecond = $(byText("Ваши карты"));
        titleSecond.shouldBe(visible);
    }

    public int getCardBalance(int orderNunmber) {
        return extractBalance(list.get(orderNunmber - 1).text());
    }

    public int extractBalance(String text) {
        String balansPart = text.split(":")[1];
        return Integer.parseInt(balansPart.substring(0, balansPart.indexOf("р.")).trim());
    }

    public CardTransferPage transferBetweenCards(int numberOrderCard) {
        list.get(numberOrderCard - 1).find("button").click();
        return new CardTransferPage();
    }
}
