package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Money {
    private final List<MoneyAudit> history = new ArrayList<>();

    public Money(final MoneyAudit history) {
        this.history.add(history);
    }

    public void addHistory(final MoneyAudit history){
        this.history.add(history);
    }
}
