package ru.netology.test;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashBoardPage;
import ru.netology.page.LoginPageV3;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoneyTransferTest {

    private DashBoardPage dashboard;

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
        var loginPage = Selenide.page(LoginPageV3.class);

        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);

        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        dashboard = verificationPage.valiVerify(verificationCode);
    }

    @Test
    void shouldTransferMoneyFromFirstToSecondCard() {
        int balanceBefore = dashboard.getFirstCardBalance();

        int amount = balanceBefore / 10;

        dashboard.selectCardToTransfer("0f3f5c2a-249e-4c3d-8287-09f7a039391d")
                .makeTransfer(String.valueOf(amount), "5559 0000 0000 0001");

        int balanceAfter = dashboard.getFirstCardBalance();

        assertTrue(balanceAfter < balanceBefore,
                "Баланс карты отправителя должен уменьшиться после перевода");
    }
}