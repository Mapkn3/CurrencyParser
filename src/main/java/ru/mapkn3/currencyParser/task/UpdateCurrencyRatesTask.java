package ru.mapkn3.currencyParser.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.mapkn3.currencyParser.model.BanksEntity;
import ru.mapkn3.currencyParser.model.CurrenciesEntity;
import ru.mapkn3.currencyParser.service.BankService;
import ru.mapkn3.currencyParser.service.CurrencyRateService;

@EnableScheduling
public class UpdateCurrencyRatesTask {
    @Autowired
    CurrencyRateService currencyRateService;
    @Autowired
    BankService bankService;

    @Scheduled(fixedRate = 5 * 60 * 1000)
    public void updateCurrencyRates() {
        for (BanksEntity bank : bankService.getAllBanks()) {
            for (CurrenciesEntity currency : bank.getCurrencies()) {
                currencyRateService.parseCurrencyRateForBank(currency.getId(), bank.getId());
            }
        }
    }
}
