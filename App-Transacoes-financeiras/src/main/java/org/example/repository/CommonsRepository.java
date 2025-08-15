package org.example.repository;

import lombok.NoArgsConstructor;
import org.example.exceptions.NoFundsEnoughtException;
import org.example.models.AccountWallet;
import org.example.models.Money;
import org.example.models.MoneyAudit;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static lombok.AccessLevel.PRIVATE;
import static org.example.models.BankService.ACCOUNT;

@NoArgsConstructor(access = PRIVATE)
public final class CommonsRepository {

    public static  void checkFoundForTransaction(final AccountWallet source, final long amount ){
        if (source.getFunds() < amount){
            throw new NoFundsEnoughtException("Sua conta não tem dinheiro o suficiente para reali" +
                    "zar essa transação");
        }

    }
    public static List<Money> generateMoney(final UUID transacionalId, final long founds, final String description){
        var history = new MoneyAudit(transacionalId,ACCOUNT,description, OffsetDateTime.now());
        return Stream.generate(()-> new Money(history)).limit(founds).toList();

    }
}
