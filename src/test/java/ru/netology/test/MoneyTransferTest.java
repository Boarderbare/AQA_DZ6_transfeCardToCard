package ru.netology.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.*;

import static com.codeborne.selenide.Selenide.*;

class MoneyTransferTest {


    @BeforeEach
    void setup() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        loginPage.validLogin(authInfo).codeVerify(verificationCode);
    }

    @Test
    void shouldTransferMoneyFirstToSecondCard() {

        var amountTransfer = 300;
        var numberCartTo = 2;
        DataHelper.Card cardFirstBeforeTransfer = DataHelper.getCard(1);
        DataHelper.Card cardSecondBeforeTransfer = DataHelper.getCard(2);
        DashBoard.transferBetweenCards(numberCartTo);

        CardTransferPage.transferMoney(amountTransfer, cardFirstBeforeTransfer.getNumber());
        DataHelper.Card cardFirstAfterTransfer = DataHelper.getCard(1);
        DataHelper.Card cardSecondAfterTransfer = DataHelper.getCard(2);

        assertEquals(cardFirstBeforeTransfer.getBalabce() - amountTransfer, cardFirstAfterTransfer.getBalabce());
        assertEquals(cardSecondBeforeTransfer.getBalabce() + amountTransfer, cardSecondAfterTransfer.getBalabce());
        assertTrue(cardSecondAfterTransfer.getBalabce() > 0 && cardFirstAfterTransfer.getBalabce() > 0);
    }

    @Test
    void shouldTransferMoneySecondToFirst() {

        var amountTransfer = 1500;
        var numberCartTo = 1;

        DataHelper.Card cardFirstBeforeTransfer = DataHelper.getCard(1);
        DataHelper.Card cardSecondBeforeTransfer = DataHelper.getCard(2);
        DashBoard.transferBetweenCards(numberCartTo);

        CardTransferPage.transferMoney(amountTransfer, cardSecondBeforeTransfer.getNumber());
        DataHelper.Card cardFirstAfterTransfer = DataHelper.getCard(1);
        DataHelper.Card cardSecondAfterTransfer = DataHelper.getCard(2);

        assertEquals(cardFirstBeforeTransfer.getBalabce() + amountTransfer, cardFirstAfterTransfer.getBalabce());
        assertEquals(cardSecondBeforeTransfer.getBalabce() - amountTransfer, cardSecondAfterTransfer.getBalabce());
        assertTrue(cardSecondAfterTransfer.getBalabce() > 0 && cardFirstAfterTransfer.getBalabce() > 0);
    }
}