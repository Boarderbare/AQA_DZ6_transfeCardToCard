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
    }

    @Test
    void shouldNoTransferNumberCardSame() {

        var amountTransfer = 300;
        var numberCartTo = 1;

        DataHelper.Card cardFirstBeforeTransfer = DataHelper.getCard(1);
        DataHelper.Card cardSecondBeforeTransfer = DataHelper.getCard(2);
        DashBoard.transferBetweenCards(numberCartTo);

        CardTransferPage.transferMoney(amountTransfer, cardFirstBeforeTransfer.getNumber());
        DataHelper.Card cardFirstAfterTransfer = DataHelper.getCard(1);
        DataHelper.Card cardSecondAfterTransfer = DataHelper.getCard(2);

        assertEquals(cardFirstBeforeTransfer.getBalabce(), cardFirstAfterTransfer.getBalabce());
        assertEquals(cardSecondBeforeTransfer.getBalabce(), cardSecondAfterTransfer.getBalabce());
        assertTrue(cardSecondAfterTransfer.getBalabce() > 0 && cardFirstAfterTransfer.getBalabce() > 0);
    }

    @Test
    void shouldNoTransferAmountTooMuch() {

        var amountTransfer = 30000;
        var numberCartTo = 1;

        DataHelper.Card cardFirstBeforeTransfer = DataHelper.getCard(1);
        DataHelper.Card cardSecondBeforeTransfer = DataHelper.getCard(2);
        DashBoard.transferBetweenCards(numberCartTo);

        CardTransferPage.transferMoney(amountTransfer, cardSecondBeforeTransfer.getNumber());
        DataHelper.Card cardFirstAfterTransfer = DataHelper.getCard(1);
        DataHelper.Card cardSecondAfterTransfer = DataHelper.getCard(2);

        assertFalse(cardSecondAfterTransfer.getBalabce() > 0 && cardFirstAfterTransfer.getBalabce() > 0);
    }

    @Test
    void shouldInvalidNumber() {
        var amountTransfer = 300;
        var numberCartTo = 1;
        String cardNunber = "1234 5678 1234 5678";
        DashBoard.transferBetweenCards(numberCartTo);
        CardTransferPage.transferMoney(amountTransfer, cardNunber);
        var actual = DashBoard.ErrorOnPage();
        assertEquals("Ошибка", actual);
    }
}
