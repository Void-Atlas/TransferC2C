package ru.netology.test;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import ru.netology.data.CardInfo;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPageV3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.getAuthInfo;

public class MoneyTransferTest {

    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        var info = getAuthInfo();
        var verificationCode = DataHelper.getVerificationCodeFor(info);

        var loginPage = Selenide.open("http://localhost:9999", LoginPageV3.class);
        var verificationPage = loginPage.validLogin(info);
        var dashBoardPage = verificationPage.valiVerify(verificationCode);

    }

    @Test
    void shouldTransferMoneyFromFirstCardToSecond() {
        var authInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);

        var firstCard = CardInfo.getFirstCard();
        var secondCard = CardInfo.getSecondCard();

        var loginPage = Selenide.open("http://localhost:9999", LoginPageV3.class);
        var verificationPage = loginPage.validLogin(authInfo);
        var dashboardPage = verificationPage.valiVerify(verificationCode);

        var balanceFirstBefore = dashboardPage.getCardBalance(firstCard.getTestId());
        var balanceSecondBefore = dashboardPage.getCardBalance(secondCard.getTestId());

        int transferAmount = 666;

        var transferPage = dashboardPage.selectCardToTransfer(secondCard.getTestId());
        dashboardPage = transferPage.transferMoney(transferAmount, firstCard.getNumber());

        var balanceFirstAfter = dashboardPage.getCardBalance(firstCard.getTestId());
        var balanceSecondAfter = dashboardPage.getCardBalance(secondCard.getTestId());

        assertEquals(balanceFirstBefore - transferAmount, balanceFirstAfter);
        assertEquals(balanceSecondBefore + transferAmount, balanceSecondAfter);
    }
}