package com.company.model;

import com.company.exceptions.InvalidDirectionException;

public enum Direction {
    BUY("buy"),
    SELL("sell");

    String title;

    Direction(String title) {
        this.title = title;
    }

    public static Direction getDirectionByTitle(String title) throws InvalidDirectionException {
        switch (title) {
            case "buy": return BUY;
            case "sell": return SELL;
        }

        throw new InvalidDirectionException("It is impossible to find Direction by title: " + title);
    }

    public String getTitle() {
        return title;
    }
}
