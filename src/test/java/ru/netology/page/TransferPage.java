package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private static final SelenideElement amountInput      = $("[data-test-id=amount] input.input__control");
    private static final SelenideElement fromCardInput    = $("[data-test-id=from] input.input__control");
    private static final SelenideElement transferButton   = $("[data-test-id=action-transfer]");
    private static final SelenideElement errorNotification = $("[data-test-id=error-notification] .notification__content");

    public DashBoardPage makeTransfer(String amount, String fromCardNumber) {
        amountInput.setValue(amount);
        fromCardInput.setValue(fromCardNumber);
        transferButton.click();

        return new DashBoardPage();
    }

    public TransferPage makeTransferExpectingError(String amount, String fromCardNumber) {
        amountInput.setValue(amount);
        fromCardInput.setValue(fromCardNumber);
        transferButton.click();
        return this;  // остаёмся на странице
    }

    public String getErrorMessage() {
        return errorNotification.getText();
    }
}