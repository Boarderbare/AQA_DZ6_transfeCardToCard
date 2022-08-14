package ru.netology.data;

import lombok.Value;
import ru.netology.page.DashBoard;

import java.util.ArrayList;
import java.util.List;

public class DataHelper {
    public DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCode(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class Card {
        String number;
    }

    public static Card getCard(int orderNumber) {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card("5559 0000 0000 0001"));
        cards.add(new Card("5559 0000 0000 0002"));
        cards.add(new Card ("1234 5678 1234 5678"));
        return cards.get(orderNumber - 1);
    }
}
