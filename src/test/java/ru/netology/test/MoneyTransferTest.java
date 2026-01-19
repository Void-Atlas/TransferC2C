package ru.netology.test;

import com.codeborne.selenide.Selenide;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashBoardPage;
import ru.netology.page.LoginPageV3;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.getFirstCard;
import static ru.netology.data.DataHelper.getSecondCard;
import static ru.netology.page.DashBoardPage.pushFirstCardButton;
import static ru.netology.page.DashBoardPage.pushSecondCardButton;

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
    public void shouldTransferFrom1To2() {
        int amount = 3500;
        val dashBoardPage = new DashBoardPage();
        val firstCardBalanceStart = dashBoardPage.getFirstCardBalance();
        val secondCardBalanceStart = dashBoardPage.getSecondCardBalance();
        val transactionPage = pushSecondCardButton();
        transactionPage.makeTransfer(String.valueOf(amount), getFirstCard().getLastFourDigits()); // Исправлено
        val firstCardBalanceFinish = firstCardBalanceStart - amount;
        val secondCardBalanceFinish = secondCardBalanceStart + amount;
        assertEquals(firstCardBalanceFinish, dashBoardPage.getFirstCardBalance());
        assertEquals(secondCardBalanceFinish, dashBoardPage.getSecondCardBalance());
    }

    @Test
    public void shouldTransferFrom2To1() {
        int amount = 5000;
        val dashBoardPage = new DashBoardPage();
        val firstCardBalanceStart = dashBoardPage.getFirstCardBalance();
        val secondCardBalanceStart = dashBoardPage.getSecondCardBalance();
        val transactionPage = pushFirstCardButton();
        transactionPage.makeTransfer(String.valueOf(amount), getSecondCard().getLastFourDigits()); // Исправлено
        val firstCardBalanceFinish = firstCardBalanceStart + amount;
        val secondCardBalanceFinish = secondCardBalanceStart - amount;
        assertEquals(firstCardBalanceFinish, dashBoardPage.getFirstCardBalance());
        assertEquals(secondCardBalanceFinish, dashBoardPage.getSecondCardBalance());
    }
}