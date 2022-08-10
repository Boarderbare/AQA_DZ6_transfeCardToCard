package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import ru.netology.page.*;

class MoneyTransferNegativeTest {

    @BeforeEach
    void setup() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        loginPage.validLogin(authInfo).codeVerify(verificationCode);
        CardTransferPage.CardBalanceEqual();
    }

    @Test
    void shouldNoTransferNumberCardSame() {

        var amountTransfer = 300;
        var numberCartTo = 1;
        var cardFirstBeforeTransfer = DataHelper.getCard(1);
        var cardSecondBeforeTransfer = DataHelper.getCard(2);
        DashBoard.transferBetweenCards(numberCartTo);

        CardTransferPage.transferMoney(amountTransfer, cardFirstBeforeTransfer.getNumber());
        var cardBalanceFirstAfterTransfer = DataHelper.getCard(1).getBalabce();
        var cardBalanceSecondAfterTransfer = DataHelper.getCard(2).getBalabce();

        assertEquals(cardFirstBeforeTransfer.getBalabce(), cardBalanceFirstAfterTransfer);
        assertEquals(cardSecondBeforeTransfer.getBalabce(), cardBalanceSecondAfterTransfer);
        assertTrue(cardBalanceSecondAfterTransfer > 0 && cardBalanceFirstAfterTransfer > 0);
    }

    @Test
    void shouldNoTransferAmountTooMuch() {

        var amountTransfer = 30000;
        var numberCartTo = 1;
        var cardFirstBeforeTransfer = DataHelper.getCard(1);
        var cardSecondBeforeTransfer = DataHelper.getCard(2);
        DashBoard.transferBetweenCards(numberCartTo);
        CardTransferPage.transferMoney(amountTransfer, cardSecondBeforeTransfer.getNumber());
        var actual = ErrorPage.errorOnPage();
        assertEquals("Ошибка", actual);
    }

    @Test
    void shouldInvalidNumber() {
        var amountTransfer = 300;
        var numberCartTo = 1;
        String cardNunber = "1234 5678 1234 5678";
        DashBoard.transferBetweenCards(numberCartTo);
        CardTransferPage.transferMoney(amountTransfer, cardNunber);
        var actual = ErrorPage.errorOnPage();
        assertEquals("Ошибка", actual);
    }
}
