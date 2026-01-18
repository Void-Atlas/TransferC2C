package ru.netology.data;

import lombok.Value;

public class CardInfo {

    private CardInfo() {}

    public static Card getFirstCard() {
        return new Card(
                "92df3f1c-a033-48e6-8390-206f6b1f56c0",
                "5559 0000 0000 0001"
        );
    }

    public static Card getSecondCard() {
        return new Card(
                "0f3f5c2a-249e-4c3d-8287-09f7a039391d",
                "5559 0000 0000 0002"
        );
    }

    @Value
    public static class Card {
        String testId;
        String number;
    }
}
