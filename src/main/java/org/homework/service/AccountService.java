package org.homework.service;

import org.homework.annotation.Service;
import org.homework.entity.Account;
import org.homework.repository.AccountSerializer;

@Service
public class AccountService {
    private AccountSerializer accountSerializer;
    private Account[] accounts;

    private CurrencyService currencyServ;

    public AccountService() {
        accountSerializer = new AccountSerializer();
        accounts = accountSerializer.read();
        currencyServ = new CurrencyService();
    }

    private int getAccountNumber(String accName) {
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].accountName.equals(accName)) {
                return i;
            }
        }
        return -1;
    }

    public void cashTranslation(String donor, String acceptor, double sum) {
        int donorNumber = getAccountNumber(donor);
        if (donorNumber == -1) {
            System.out.println("Аккаунт отправителя не существует");
            return;
        }
        int acceptorNumber = getAccountNumber(acceptor);
        if (donorNumber == -1 || acceptorNumber == -1) {
            System.out.println("Аккаунт получателя не существует");
            return;
        }

        if (sum <= 0) {
            System.out.println("Некорректная сумма перевода");
            return;
        }

        if (accounts[donorNumber].balance < sum) {
            System.out.println("Недостаточно средств на счету отправителя");
            return;
        }

        double sumDonor = currencyServ.convertFromUSD(currencyServ.convertToUSD(sum, "RUB"),
                accounts[donorNumber].currencyAbbrev);
        double sumAcceptor = currencyServ.convertFromUSD(currencyServ.convertToUSD(sum, "RUB"),
                accounts[acceptorNumber].currencyAbbrev);

        accounts[acceptorNumber].balance = accounts[acceptorNumber].balance + sumAcceptor;
        accounts[donorNumber].balance = accounts[donorNumber].balance - sumDonor;
        accountSerializer.write(accounts);
        System.out.println("Перевод осуществлен успешно. Счета обновлены");
    }

}
