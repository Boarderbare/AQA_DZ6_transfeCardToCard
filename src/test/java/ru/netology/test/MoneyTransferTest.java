package ru.netology.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.*;
import ru.netology.utility.CardBalanceEqual;

import static com.codeborne.selenide.Selenide.*;

class MoneyTransferTest {

    @BeforeEach
    void setup() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        loginPage.validLogin(authInfo).codeVerify(verificationCode);
        CardBalanceEqual.cardBalanceEqual();
    }

    @Test
    void shouldTransferMoneyFirstToSecondCard() {
        DashBoard dashBoard = new DashBoard();
        var amountTransfer = 300;
        var numberCartTo = 2;
        var cardFirstBeforeTransfer = dashBoard.getCardBalance(1);
        var cardSecondBeforeTransfer = dashBoard.getCardBalance(2);
        dashBoard.transferBetweenCards(numberCartTo)
            .transferMoney(amountTransfer, DataHelper.getCard(1).getNumber());
        var cardBalanceFirstAfterTransfer = dashBoard.getCardBalance(1);
        var cardBalanceSecondAfterTransfer = dashBoard.getCardBalance(2);

        assertEquals(cardFirstBeforeTransfer - amountTransfer, cardBalanceFirstAfterTransfer);
        assertEquals(cardSecondBeforeTransfer + amountTransfer, cardBalanceSecondAfterTransfer);
        assertTrue(cardBalanceFirstAfterTransfer > 0 && cardBalanceSecondAfterTransfer > 0);
    }

    @Test
    void shouldTransferMoneySecondToFirst() {
        DashBoard dashBoard = new DashBoard();
        var amountTransfer = 1500;
        var numberCartTo = 1;
        var cardFirstBeforeTransfer = dashBoard.getCardBalance(1);
        var cardSecondBeforeTransfer = dashBoard.getCardBalance(2);
        dashBoard.transferBetweenCards(numberCartTo)
                .transferMoney(amountTransfer, DataHelper.getCard(2).getNumber());
        var cardBalanceFirstAfterTransfer = dashBoard.getCardBalance(1);
        var cardBalanceSecondAfterTransfer = dashBoard.getCardBalance(2);

        assertEquals(cardFirstBeforeTransfer + amountTransfer, cardBalanceFirstAfterTransfer);
        assertEquals(cardSecondBeforeTransfer - amountTransfer, cardBalanceSecondAfterTransfer);
        assertTrue(cardBalanceFirstAfterTransfer > 0 && cardBalanceSecondAfterTransfer > 0);
    }
}