package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$$;

public class DashBoardPage {

    private final ElementsCollection cards = $$(".list__item div");

    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    private final String firstCardTestId = "92df3f1c-a033-48e6-8390-206f6b1f56c0";
    private final String secondCardTestId = "0f3f5c2a-249e-4c3d-8287-09f7a039391d";

    public DashBoardPage() {

    }

    public int getFirstCardBalance() {
        return extractBalanceFromCardByTestId(firstCardTestId);
    }

    public int getSecondCardBalance() {
        return extractBalanceFromCardByTestId(secondCardTestId);
    }

    private int extractBalanceFromCardByTestId(String cardTestId) {
        SelenideElement card = cards.findBy(attribute("data-test-id", cardTestId));
        String text = card.getText();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        int start = text.indexOf(balanceStart);
        int finish = text.indexOf(balanceFinish, start);

        if (start == -1 || finish == -1) {
            throw new IllegalStateException("Не удалось найти баланс в тексте карточки:\n" + text);
        }

        String valueStr = text.substring(start + balanceStart.length(), finish).trim();
        return Integer.parseInt(valueStr);
    }

    public TransferPage selectCardToTransfer(String cardTestId) {
        cards.findBy(attribute("data-test-id", cardTestId))
                .$("[data-test-id=action-deposit]")
                .click();
        return new TransferPage();
    }

    public TransferPage depositToFirstCard() {
        return selectCardToTransfer(firstCardTestId);
    }

    public TransferPage depositToSecondCard() {
        return selectCardToTransfer(secondCardTestId);
    }
}