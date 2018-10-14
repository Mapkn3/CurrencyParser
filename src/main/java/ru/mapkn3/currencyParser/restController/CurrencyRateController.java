package ru.mapkn3.currencyParser.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.mapkn3.currencyParser.model.BanksEntity;
import ru.mapkn3.currencyParser.model.CurrenciesEntity;
import ru.mapkn3.currencyParser.model.CurrencyRatesEntity;
import ru.mapkn3.currencyParser.service.BankService;
import ru.mapkn3.currencyParser.service.CurrencyRateService;
import ru.mapkn3.currencyParser.service.CurrencyService;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/currencyRate", produces = MediaType.APPLICATION_JSON_VALUE)
public class CurrencyRateController {
    @Autowired
    CurrencyRateService currencyRateService;
    @Autowired
    BankService bankService;
    @Autowired
    CurrencyService currencyService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void parseAllNow() {
        for (BanksEntity bank : bankService.getAllBanks()) {
            for (CurrenciesEntity currency : bank.getCurrencies()) {
                currencyRateService.parseCurrencyRateForBank(currency.getId(), bank.getId());
            }
        }
    }

    @GetMapping("/theBestRates")
    public List<String> getTheBestActualCurrencyPurchaseRates() {
        List<CurrenciesEntity> currencies = currencyService.getAllCurrencies();
        List<String> result = new ArrayList<>();
        for (CurrenciesEntity currency : currencies) {
            CurrencyRatesEntity currencyRate = currencyRateService.getTheBestActualCurrencyRateByCurrency(currency);
            result.add(currencyRate.getCurrency().getCurrency() + ": " + currencyRate.getPurchaseRate() + " | " + currencyRate.getBank().getName() + "[" + currencyRate.getBank().getUrl() + "]");
        }
        return result;
    }

    @GetMapping("/bank/{bankId}")
    public List<CurrencyRatesEntity> getCurrencyRatesForBankById(@PathVariable("bankId") int bankId) {
        BanksEntity bank = bankService.getBank(bankId);
        return currencyRateService.getCurrencyRatesByBank(bank);
    }

    @GetMapping("/currency/{currencyId:\\d+}")
    public List<CurrencyRatesEntity> getCurrencyRatesForCurrencyById(@PathVariable("currencyId") int currencyId) {
        CurrenciesEntity currency = currencyService.getCurrency(currencyId);
        return currencyRateService.getCurrencyRatesByCurrency(currency);
    }

    @GetMapping("/currency/{currencyName:\\D+}")
    public List<CurrencyRatesEntity> getCurrencyRatesForCurrencyByName(@PathVariable("currencyName") String currencyName) {
        CurrenciesEntity currency = currencyService.getCurrencyByName(currencyName);
        return currencyRateService.getCurrencyRatesByCurrency(currency);
    }

    @GetMapping("/{currencyId:\\d+}/{startDate:20\\d\\d-0[1-9]-0[1-9]T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-0[1-9]-0[1-9]T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-0[1-9]-[12]\\dT[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-0[1-9]-[12]\\dT2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-0[1-9]T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-0[1-9]T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-[12]\\dT[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-[12]\\dT2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-0[13-9]-30T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-0[13-9]-30T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-30T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-30T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-0[13578]-31T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-0[13578]-31T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-1[02]-31T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-1[02]-31T2[0-3]:[0-5]\\d:[0-5]\\d}/{endDate:20\\d\\d-0[1-9]-0[1-9]T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-0[1-9]-0[1-9]T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-0[1-9]-[12]\\dT[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-0[1-9]-[12]\\dT2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-0[1-9]T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-0[1-9]T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-[12]\\dT[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-[12]\\dT2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-0[13-9]-30T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-0[13-9]-30T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-30T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-30T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-0[13578]-31T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-0[13578]-31T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-1[02]-31T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-1[02]-31T2[0-3]:[0-5]\\d:[0-5]\\d}/{bankId}")
    public List<BigDecimal> getSellingRatesBetweenDateForBankForCurrentById(@PathVariable("currencyId") int currencyId,
                                                                            @PathVariable("bankId") int bankId,
                                                                            @PathVariable("startDate") String startDate,
                                                                            @PathVariable("endDate") String endDate) {
        Timestamp start = Timestamp.valueOf(startDate.replace('T', ' '));
        Timestamp end = Timestamp.valueOf(endDate.replace('T', ' '));
        if (start.after(end)) {
            Timestamp date = start;
            start = end;
            end = date;
        }
        CurrenciesEntity currency = currencyService.getCurrency(currencyId);
        BanksEntity bank = bankService.getBank(bankId);
        List<CurrencyRatesEntity> currencyRates = currencyRateService.getAllCurrencyRateByCurrencyAndBankAndDateBetween(currency, bank, start, end);
        List<BigDecimal> sellingRates = new ArrayList<>();
        currencyRates.forEach(currencyRate -> sellingRates.add(currencyRate.getSellingRate()));
        return sellingRates;
    }

    @GetMapping("/{currencyName:\\D+}/{startDate:20\\d\\d-0[1-9]-0[1-9]T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-0[1-9]-0[1-9]T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-0[1-9]-[12]\\dT[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-0[1-9]-[12]\\dT2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-0[1-9]T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-0[1-9]T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-[12]\\dT[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-[12]\\dT2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-0[13-9]-30T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-0[13-9]-30T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-30T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-30T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-0[13578]-31T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-0[13578]-31T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-1[02]-31T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-1[02]-31T2[0-3]:[0-5]\\d:[0-5]\\d}/{endDate:20\\d\\d-0[1-9]-0[1-9]T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-0[1-9]-0[1-9]T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-0[1-9]-[12]\\dT[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-0[1-9]-[12]\\dT2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-0[1-9]T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-0[1-9]T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-[12]\\dT[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-[12]\\dT2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-0[13-9]-30T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-0[13-9]-30T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-30T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-30T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-0[13578]-31T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-0[13578]-31T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-1[02]-31T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-1[02]-31T2[0-3]:[0-5]\\d:[0-5]\\d}/{bankId}")
    public List<BigDecimal> getSellingRatesBetweenDateForBankForCurrentByName(@PathVariable("currencyName") String currencyName,
                                                                            @PathVariable("bankId") int bankId,
                                                                            @PathVariable("startDate") String startDate,
                                                                            @PathVariable("endDate") String endDate) {
        Timestamp start = Timestamp.valueOf(startDate.replace('T', ' '));
        Timestamp end = Timestamp.valueOf(endDate.replace('T', ' '));
        if (start.after(end)) {
            Timestamp date = start;
            start = end;
            end = date;
        }
        CurrenciesEntity currency = currencyService.getCurrencyByName(currencyName);
        BanksEntity bank = bankService.getBank(bankId);
        List<CurrencyRatesEntity> currencyRates = currencyRateService.getAllCurrencyRateByCurrencyAndBankAndDateBetween(currency, bank, start, end);
        List<BigDecimal> sellingRates = new ArrayList<>();
        currencyRates.forEach(currencyRate -> sellingRates.add(currencyRate.getSellingRate()));
        return sellingRates;
    }
}
