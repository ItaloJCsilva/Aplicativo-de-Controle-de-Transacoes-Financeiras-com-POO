package org.example.models;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.example.models.BankService.INVESTMENT;

public class InvestmentWallet extends Wallet{
    private final Investment investment;
    private final AccountWallet account;

    public InvestmentWallet(final Investment investment, final AccountWallet account, final long amount) {
        super(INVESTMENT);
        this.investment = investment;
        this.account = account;
        addMoney(account.reduceMoney(amount), getService(), "Investimento");
    }

    public void updateAmount(final long percent){
        long amount = getFunds() * percent / 100;
        MoneyAudit history = new MoneyAudit(UUID.randomUUID(), getService(), "Rendimentos", OffsetDateTime.now());
        List<Money> money = Stream.generate(() -> new Money(history)).limit(amount).toList();
        this.money.addAll(money);
    }
}
