package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CardTransferPage {

    public CardTransferPage() {
        SelenideElement title = $(byText("Пополнение карты"));
        title.shouldBe(visible);
    }

    private final SelenideElement amountField = $("[data-test-id='amount'] input");
    private final SelenideElement numberCardField = $("[data-test-id='from'] input");
    private final SelenideElement buttonTransfer = $("[data-test-id='action-transfer']");

    public void clearField() {
        amountField.sendKeys(Keys.CONTROL + "A");
        amountField.sendKeys(Keys.DELETE);
        numberCardField.sendKeys(Keys.CONTROL + "A");
        numberCardField.sendKeys(Keys.DELETE);
    }

    public void transferMoney(int amount, String number) {
        clearField();
        amountField.setValue(String.valueOf(amount));
        numberCardField.setValue(number);
        buttonTransfer.click();
    }
    public void errorMessage() {
        SelenideElement error = $(byText("Ошибка")).shouldBe(visible);
    }
}
