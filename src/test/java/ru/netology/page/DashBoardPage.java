package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DashBoardPage {

    private final SelenideElement header = $("[data-test-id=dashboard]");

    public DashBoardPage() {
        header.shouldBe(Condition.visible);
    }

    public int getCardBalance(String cardTestId) {
        var text = $("[data-test-id='" + cardTestId + "']")
                .getText();
        return Integer.parseInt(
                text.replaceAll("[^0-9]", "")
        );
    }

    public LoginPageV4 selectCardToTransfer(String cardTestId) {
        $("[data-test-id='" + cardTestId + "'] [data-test-id=action-deposit]")
                .click();
        return new LoginPageV4();
    }
}
