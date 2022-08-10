package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Value;

import java.sql.Array;
import java.util.*;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashBoard {
    private SelenideElement title = $(byText("Личный кабинет"));
    private SelenideElement titleSecond = $(byText("Ваши карты"));
    private static ElementsCollection list = $$(".list div[data-test-id]").filter(visible);

    public DashBoard() {
        title.shouldBe(visible);
        titleSecond.shouldBe(visible);
    }

    public static String getCardId(int orderNunmber) {

        return list.get(orderNunmber - 1).attr("data-test-id");
    }

    public static int getCardBalance(int orderNunmber) {

        return extractBalance(list.get(orderNunmber - 1).text());
    }

    public static int extractBalance(String text) {
        String balansPart = text.split(":")[1];
        return Integer.parseInt(balansPart.substring(0, balansPart.indexOf("р.")).trim());
    }

    public static CardTransferPage transferBetweenCards(int numberOrderCard) {
        list.get(numberOrderCard - 1).find("button").click();
        return new CardTransferPage();
    }
}
