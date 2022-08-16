package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import ru.netology.page.*;
import ru.netology.utility.CardBalanceEqual;

class MoneyTransferNegativeTest {

    public

    @BeforeEach
    void setup() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        loginPage.validLogin(authInfo).codeVerify(verificationCode);
        CardBalanceEqual.cardBalanceEqual();
    }

    @Test
    void shouldNoTransferNumberCardSame() {
        DashBoard dashBoard = new DashBoard();
        var amountTransfer = 300;
        var numberCartTo = 1;
        var cardFirstBeforeTransfer = dashBoard.getCardBalance(1);
        var cardSecondBeforeTransfer = dashBoard.getCardBalance(2);
        dashBoard.transferBetweenCards(numberCartTo)
            .transferMoney(amountTransfer, DataHelper.getCard(1).getNumber());
        var cardBalanceFirstAfterTransfer = dashBoard.getCardBalance(1);
        var cardBalanceSecondAfterTransfer = dashBoard.getCardBalance(2);

        assertEquals(cardFirstBeforeTransfer, cardBalanceFirstAfterTransfer);
        assertEquals(cardSecondBeforeTransfer, cardBalanceSecondAfterTransfer);
        assertTrue(cardBalanceSecondAfterTransfer > 0 && cardBalanceFirstAfterTransfer > 0);
    }

    @Test
    void shouldNoTransferAmountTooMuch() {
        DashBoard dashBoard = new DashBoard();
        var amountTransfer = 30000;
        var numberCartTo = 1;
        var cardFirstBeforeTransfer = dashBoard.getCardBalance(1);
        var cardSecondBeforeTransfer = dashBoard.getCardBalance(2);
        dashBoard.transferBetweenCards(numberCartTo)
            .transferMoney(amountTransfer, DataHelper.getCard(2).getNumber());
        CardTransferPage cardTransferPage = new CardTransferPage();
        cardTransferPage.errorMessage();
    }

    @Test
    void shouldInvalidNumber() {
        DashBoard dashBoard = new DashBoard();
        var amountTransfer = 300;
        var numberCartTo = 1;
        var invalidCardNunber = DataHelper.getCard(3).getNumber();
        dashBoard.transferBetweenCards(numberCartTo)
            .transferMoney(amountTransfer, invalidCardNunber);
        CardTransferPage cardTransferPage = new CardTransferPage();
        cardTransferPage.errorMessage();
    }
}
