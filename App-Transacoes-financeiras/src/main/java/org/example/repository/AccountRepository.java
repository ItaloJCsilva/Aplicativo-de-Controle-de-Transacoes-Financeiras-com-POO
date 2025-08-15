package org.example.repository;

import org.example.exceptions.AccountNotFoundException;
import org.example.models.AccountWallet;

import java.util.List;

import static org.example.repository.CommonsRepository.checkFoundForTransaction;

public class AccountRepository {

    private List<AccountWallet> accounts;


    public List<AccountWallet> list(){
        return this.accounts;
    }

    public AccountWallet findByPix( final String pix ){
        return accounts.stream()
                .filter((AccountWallet a) -> a.getPix().contains(pix)).
                findFirst().
                orElseThrow(()-> new AccountNotFoundException("A conta com a chave pix" + pix + "não existe ou foi encerrada"));

    }

    public AccountWallet create(final List<String> pix, final long initialFounds){
        var newAccounnt = new AccountWallet(initialFounds,pix);
        accounts.add(newAccounnt);
        return newAccounnt;
    }
    public void deposit(final String pix,final long foundsAmaount){
        var target = findByPix(pix);
        target.addMoney(foundsAmaount,"deposito");

    }
    public long withdrawn (final String pix,final long amount){
        var source = findByPix(pix);
        checkFoundForTransaction(source,amount);
        source.reduceMoney(amount);
        return  amount;

    }

    public void transferMoney(final String sourcePix, final String targetpix, final long amount){
        var source = findByPix(sourcePix);
        checkFoundForTransaction(source,amount);
        var target = findByPix(targetpix);
        var message ="pix enviado de " + sourcePix + " para " + targetpix + "" ;
        target.addMoney(source.reduceMoney(amount),source.getService(),message);
    }




}
