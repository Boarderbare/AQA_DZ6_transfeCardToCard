package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public static void CardBalanceEqual(){
        var cardFirstBalance = DataHelper.getCard(1).getBalabce();
        var cardSecondBalance = DataHelper.getCard(2).getBalabce();
        var cardFirstNumber =DataHelper.getCard(1).getNumber();
        var cardSecondNumber =DataHelper.getCard(2).getNumber();

        if (cardFirstBalance>10_000){
            var amountTransfer=cardFirstBalance-10_000;
            DashBoard.transferBetweenCards(2);
            transferMoney(amountTransfer,cardFirstNumber );
        }
        if (cardSecondBalance>10_000) {
            var amountTransfer = cardSecondBalance - 10_000;
            DashBoard.transferBetweenCards(1);
            transferMoney(amountTransfer, cardSecondNumber);
        }
    }
}
