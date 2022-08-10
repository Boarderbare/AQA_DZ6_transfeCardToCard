package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ErrorPage {
    private static SelenideElement error = $(byText("Ошибка")).shouldBe(visible);

    public ErrorPage() {
    }

    public static String errorOnPage() {
        return error.text();
    }
}
