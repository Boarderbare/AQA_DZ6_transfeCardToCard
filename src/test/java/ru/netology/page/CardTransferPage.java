package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CardTransferPage {

    public CardTransferPage() {
        SelenideElement title = $(byText("Пополнение карты"));
        title.shouldBe(visible);
    }

    private static final SelenideElement amountField = $("[data-test-id='amount'] input");
    private static final SelenideElement numberCardField = $("[data-test-id='from'] input");
    private static final SelenideElement buttonTransfer = $("[data-test-id='action-transfer']");

    public static void transferMoney(int amount, String number) {

        amountField.setValue(String.valueOf(amount));
        numberCardField.setValue(number);
        buttonTransfer.click();
    }
}
