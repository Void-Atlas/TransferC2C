package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPageV4 {

    private final SelenideElement amountField =
            $("[data-test-id=amount] input");
    private final SelenideElement fromField =
            $("[data-test-id=from] input");
    private final SelenideElement transferButton =
            $("[data-test-id=action-transfer]");

    public LoginPageV4() {
        amountField.shouldBe(Condition.visible);
    }

    public DashBoardPage transferMoney(int amount, String fromCardNumber) {
        amountField.setValue(String.valueOf(amount));
        fromField.setValue(fromCardNumber);
        transferButton.click();
        return new DashBoardPage();
    }
}
