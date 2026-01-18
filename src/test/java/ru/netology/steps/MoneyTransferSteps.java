package ru.netology.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import ru.netology.data.DataHelper;
import ru.netology.page.DashBoardPage;
import ru.netology.page.LoginPageV3;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferSteps {

    private DashBoardPage dashboardPage;

    // Первая карта
    private final String firstCardTestId =
            "92df3f1c-a033-48e6-8390-206f6b1f56c0";

    @Пусть("пользователь залогинен с именем {string} и паролем {string}")
    public void userIsLoggedIn(String login, String password) {
        var authInfo = new DataHelper.AuthInfo(login, password);
        var verificationCode =
                DataHelper.getVerificationCodeFor(authInfo);

        var loginPage =
                Selenide.open("http://localhost:9999", LoginPageV3.class);
        var verificationPage = loginPage.validLogin(authInfo);
        dashboardPage = verificationPage.valiVerify(verificationCode);
    }

    @Когда("пользователь переводит {int} рублей с карты с номером {string} на свою 1 карту с главной страницы")
    public void shouldTransferMoney(int amount, String fromCardNumber) {
        var transferPage =
                dashboardPage.selectCardToTransfer(firstCardTestId);
        dashboardPage =
                transferPage.transferMoney(amount, fromCardNumber);
    }

    @Тогда("баланс его 1 карты из списка на главной странице должен стать {int} рублей")
    public void balanceShouldBe(int expectedBalance) {
        var actualBalance =
                dashboardPage.getCardBalance(firstCardTestId);
        assertEquals(expectedBalance, actualBalance);
    }
}
