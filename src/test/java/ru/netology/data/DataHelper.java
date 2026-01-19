package ru.netology.data;

import lombok.Value;

public class DataHelper {

    private DataHelper() {
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }


    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    public static int getValidTransferAmount(int currentBalance) {
        if (currentBalance <= 0) {
            return 1;
        }

        return Math.max(1, currentBalance / 10);
    }

    public static int getOverdraftAmount(int currentBalance) {
        return currentBalance + 500;
    }
}
