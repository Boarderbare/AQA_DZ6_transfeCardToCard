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
        CardTransferPage.CardBalanceEqual();
    }

    @Test
    void shouldTransferMoneyFirstToSecondCard() {

        var amountTransfer = 300;
        var numberCartTo = 2;
        DataHelper.Card cardFirstBeforeTransfer = DataHelper.getCard(1);
        DataHelper.Card cardSecondBeforeTransfer = DataHelper.getCard(2);
        DashBoard.transferBetweenCards(numberCartTo);

        CardTransferPage.transferMoney(amountTransfer, cardFirstBeforeTransfer.getNumber());
        var cardBalanceFirstAfterTransfer = DataHelper.getCard(1).getBalabce();
        var cardBalanceSecondAfterTransfer = DataHelper.getCard(2).getBalabce();

        assertEquals(cardFirstBeforeTransfer.getBalabce()-amountTransfer,cardBalanceFirstAfterTransfer);
        assertEquals(cardSecondBeforeTransfer.getBalabce() + amountTransfer, cardBalanceSecondAfterTransfer);
        assertTrue(cardBalanceFirstAfterTransfer > 0 && cardBalanceSecondAfterTransfer > 0);
    }

    @Test
    void shouldTransferMoneySecondToFirst() {

        var amountTransfer = 1500;
        var numberCartTo = 1;

        DataHelper.Card cardFirstBeforeTransfer = DataHelper.getCard(1);
        DataHelper.Card cardSecondBeforeTransfer = DataHelper.getCard(2);
        DashBoard.transferBetweenCards(numberCartTo);

        CardTransferPage.transferMoney(amountTransfer, cardSecondBeforeTransfer.getNumber());
        var cardBalanceFirstAfterTransfer = DataHelper.getCard(1).getBalabce();
        var cardBalanceSecondAfterTransfer = DataHelper.getCard(2).getBalabce();

        assertEquals(cardFirstBeforeTransfer.getBalabce()+amountTransfer,cardBalanceFirstAfterTransfer);
        assertEquals(cardSecondBeforeTransfer.getBalabce() - amountTransfer, cardBalanceSecondAfterTransfer);
        assertTrue(cardBalanceFirstAfterTransfer > 0 && cardBalanceSecondAfterTransfer > 0);
    }
}