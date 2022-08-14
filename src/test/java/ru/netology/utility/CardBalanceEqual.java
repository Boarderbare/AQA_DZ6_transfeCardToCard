package ru.netology.utility;

import lombok.experimental.UtilityClass;
import ru.netology.data.DataHelper;
import ru.netology.page.CardTransferPage;
import ru.netology.page.DashBoard;

@UtilityClass
public class CardBalanceEqual {

    public void cardBalanceEqual() {
        DashBoard dashBoard = new DashBoard();

        var cardFirstBalance = dashBoard.getCardBalance(1);
        var cardSecondBalance = dashBoard.getCardBalance(2);
        var cardFirstNumber = DataHelper.getCard(1).getNumber();
        var cardSecondNumber = DataHelper.getCard(2).getNumber();

        if (cardFirstBalance > 10_000) {
            var amountTransfer = cardFirstBalance - 10_000;
            dashBoard.transferBetweenCards(2)
                .transferMoney(amountTransfer, cardFirstNumber);
        }
        if (cardSecondBalance > 10_000) {
            var amountTransfer = cardSecondBalance - 10_000;
            dashBoard.transferBetweenCards(1)
                .transferMoney(amountTransfer, cardSecondNumber);
        }
    }
}
