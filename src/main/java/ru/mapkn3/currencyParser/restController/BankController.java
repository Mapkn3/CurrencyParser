package ru.mapkn3.currencyParser.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.mapkn3.currencyParser.model.BanksEntity;
import ru.mapkn3.currencyParser.model.CurrenciesEntity;
import ru.mapkn3.currencyParser.service.BankService;
import ru.mapkn3.currencyParser.service.CurrencyService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/bank", produces = MediaType.APPLICATION_JSON_VALUE)
public class BankController {
    @Autowired
    private BankService bankService;
    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/all")
    public List<BanksEntity> getAllBanks() {
        return bankService.getAllBanks();
    }

    @GetMapping("/parsing")
    public List<BanksEntity> getAllParsingBanks() {
        return bankService.getParsingBanks();
    }

    @GetMapping("/{id}")
    public BanksEntity getBank(@PathVariable("id") int id) {
        return bankService.getBank(id);
    }

    @PutMapping("/{id}")
    public BanksEntity changeParsing(@PathVariable("id") int id) {
        BanksEntity bank = bankService.getBank(id);
        bank.setParsing(!bank.isParsing());
        bankService.updateBank(bank);
        return bank;
    }

    @GetMapping("/{id}/currencies")
    public Set<CurrenciesEntity> getCurrenciesForBank(@PathVariable("id") int id) {
        return bankService.getBank(id).getCurrencies();
    }

    @PostMapping("/{id}/currencies/{currencyId:\\d+}")
    public Set<CurrenciesEntity> addCurrencyForBankById(@PathVariable("id") int bankId, @PathVariable("currencyId") int currencyId) {
        return addCurrencyForBank(bankId, currencyService.getCurrency(currencyId));
    }

    @DeleteMapping("/{id}/currencies/{currencyId:\\d+}")
    public Set<CurrenciesEntity> deleteCurrencyForBankById(@PathVariable("id") int bankId, @PathVariable("currencyId") int currencyId) {
        return deleteCurrencyForBank(bankId, currencyService.getCurrency(currencyId));
    }

    @PostMapping("/{id}/currencies/{currency:\\D+}")
    public Set<CurrenciesEntity> addCurrencyForBankByName(@PathVariable("id") int bankId, @PathVariable("currency") String currencyName) {
        return addCurrencyForBank(bankId, currencyService.getCurrencyByName(currencyName));
    }

    @DeleteMapping("/{id}/currencies/{currency:\\D+}")
    public Set<CurrenciesEntity> deleteCurrencyForBankByName(@PathVariable("id") int bankId, @PathVariable("currency") String currencyName) {
        return deleteCurrencyForBank(bankId, currencyService.getCurrencyByName(currencyName));
    }

    private Set<CurrenciesEntity> addCurrencyForBank(int bankId, CurrenciesEntity currency) {
        BanksEntity bank = bankService.getBank(bankId);
        Set<CurrenciesEntity> currencies = bank.getCurrencies();
        if (currency != null) {
            currencies.add(currency);
            bankService.updateBank(bank);
        }
        return currencies;
    }

    private Set<CurrenciesEntity> deleteCurrencyForBank(int bankId, CurrenciesEntity currency) {
        BanksEntity bank = bankService.getBank(bankId);
        Set<CurrenciesEntity> currencies = bank.getCurrencies();
        if (currency != null) {
            currencies.remove(currency);
            bankService.updateBank(bank);
        }
        return currencies;
    }
}
