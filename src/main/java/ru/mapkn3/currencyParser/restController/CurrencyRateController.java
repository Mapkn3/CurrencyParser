package ru.mapkn3.currencyParser.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    private final String INVALID_CURRENCY_ID_MESSAGE = "Invalid currency id";
    private final ResponseEntity invalidCurrencyIdResponseEntity = ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN).body(INVALID_CURRENCY_ID_MESSAGE);
    private final String INVALID_CURRENCY_NAME_MESSAGE = "Invalid currency name";
    private final ResponseEntity invalidCurrencyNameResponseEntity = ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN).body(INVALID_CURRENCY_NAME_MESSAGE);
    private final String INVALID_BANK_ID_MESSAGE = "Invalid bank id";
    private final ResponseEntity invalidBankIdResponseEntity = ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN).body(INVALID_BANK_ID_MESSAGE);

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
            if (bank.isParsing()) {
                for (CurrenciesEntity currency : bank.getCurrencies()) {
                    currencyRateService.parseCurrencyRateForBank(currency.getId(), bank.getId());
                }
            }
        }
    }

    @GetMapping("/theBestRates")
    public ResponseEntity<List<String>> getTheBestActualCurrencyPurchaseRates() {
        List<CurrenciesEntity> currencies = currencyService.getAllCurrencies();
        List<String> result = new ArrayList<>();
        for (CurrenciesEntity currency : currencies) {
            CurrencyRatesEntity currencyRate = currencyRateService.getTheBestActualCurrencyRateByCurrency(currency);
            result.add(currencyRate.getCurrency().getCurrency() + ": " + currencyRate.getPurchaseRate() + " | " + currencyRate.getBank().getName() + "[" + currencyRate.getBank().getUrl() + "]");
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/bank/{bankId:\\d+}")
    public ResponseEntity getCurrencyRatesForBankById(@PathVariable("bankId") int bankId) {
        BanksEntity bank = bankService.getBank(bankId);
        if (bank != null) {
            return ResponseEntity.ok(currencyRateService.getCurrencyRatesByBank(bank));
        } else {
            return invalidBankIdResponseEntity;
        }
    }

    @GetMapping("/currency/{currencyId:\\d+}")
    public ResponseEntity getCurrencyRatesForCurrencyById(@PathVariable("currencyId") int currencyId) {
        CurrenciesEntity currency = currencyService.getCurrency(currencyId);
        if (currency != null) {
            return ResponseEntity.ok(currencyRateService.getCurrencyRatesByCurrency(currency));
        } else {
            return invalidCurrencyIdResponseEntity;
        }
    }

    @GetMapping("/currency/{currencyName:\\D+}")
    public ResponseEntity getCurrencyRatesForCurrencyByName(@PathVariable("currencyName") String currencyName) {
        CurrenciesEntity currency = currencyService.getCurrencyByName(currencyName);
        if (currency != null) {
            return ResponseEntity.ok(currencyRateService.getCurrencyRatesByCurrency(currency));
        } else {
            return invalidCurrencyNameResponseEntity;
        }
    }

    @GetMapping("/{currencyId:\\d+}/{startDate:20\\d\\d-0[1-9]-0[1-9]T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-0[1-9]-0[1-9]T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-0[1-9]-[12]\\dT[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-0[1-9]-[12]\\dT2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-0[1-9]T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-0[1-9]T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-[12]\\dT[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-[12]\\dT2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-0[13-9]-30T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-0[13-9]-30T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-30T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-30T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-0[13578]-31T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-0[13578]-31T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-1[02]-31T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-1[02]-31T2[0-3]:[0-5]\\d:[0-5]\\d}/{endDate:20\\d\\d-0[1-9]-0[1-9]T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-0[1-9]-0[1-9]T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-0[1-9]-[12]\\dT[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-0[1-9]-[12]\\dT2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-0[1-9]T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-0[1-9]T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-[12]\\dT[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-[12]\\dT2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-0[13-9]-30T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-0[13-9]-30T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-30T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-30T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-0[13578]-31T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-0[13578]-31T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-1[02]-31T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-1[02]-31T2[0-3]:[0-5]\\d:[0-5]\\d}/{bankId:\\d+}")
    public ResponseEntity getSellingRatesBetweenDateForBankForCurrencyById(@PathVariable("currencyId") int currencyId,
                                                                           @PathVariable("bankId") int bankId,
                                                                           @PathVariable("startDate") String startDateString,
                                                                           @PathVariable("endDate") String endDateString) {
        CurrenciesEntity currency = currencyService.getCurrency(currencyId);
        if (currency != null) {
            return getSellingRatesBetweenDateForBankForCurrency(currency, bankId, startDateString, endDateString);
        } else {
            return invalidCurrencyIdResponseEntity;
        }
    }

    @GetMapping("/{currencyName:\\D+}/{startDate:20\\d\\d-0[1-9]-0[1-9]T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-0[1-9]-0[1-9]T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-0[1-9]-[12]\\dT[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-0[1-9]-[12]\\dT2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-0[1-9]T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-0[1-9]T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-[12]\\dT[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-[12]\\dT2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-0[13-9]-30T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-0[13-9]-30T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-30T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-30T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-0[13578]-31T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-0[13578]-31T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-1[02]-31T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-1[02]-31T2[0-3]:[0-5]\\d:[0-5]\\d}/{endDate:20\\d\\d-0[1-9]-0[1-9]T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-0[1-9]-0[1-9]T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-0[1-9]-[12]\\dT[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-0[1-9]-[12]\\dT2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-0[1-9]T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-0[1-9]T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-[12]\\dT[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-[12]\\dT2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-0[13-9]-30T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-0[13-9]-30T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-30T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-1[012]-30T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-0[13578]-31T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-0[13578]-31T2[0-3]:[0-5]\\d:[0-5]\\d|20\\d\\d-1[02]-31T[01]\\d:[0-5]\\d:[0-5]\\d|20\\d\\d-1[02]-31T2[0-3]:[0-5]\\d:[0-5]\\d}/{bankId:\\d+}")
    public ResponseEntity getSellingRatesBetweenDateForBankForCurrencyByName(@PathVariable("currencyName") String currencyName,
                                                                             @PathVariable("bankId") int bankId,
                                                                             @PathVariable("startDate") String startDateString,
                                                                             @PathVariable("endDate") String endDateString) {
        CurrenciesEntity currency = currencyService.getCurrencyByName(currencyName);
        if (currency != null) {
            return getSellingRatesBetweenDateForBankForCurrency(currency, bankId, startDateString, endDateString);
        } else {
            return invalidCurrencyNameResponseEntity;
        }
    }

    private ResponseEntity getSellingRatesBetweenDateForBankForCurrency(CurrenciesEntity currency, int bankId, String startDateString, String endDateString) {
        BanksEntity bank = bankService.getBank(bankId);
        if (bank != null) {
            Timestamp startDate = Timestamp.valueOf(startDateString.replace('T', ' '));
            Timestamp endDate = Timestamp.valueOf(endDateString.replace('T', ' '));
            if (startDate.after(endDate)) {
                Timestamp date = startDate;
                startDate = endDate;
                endDate = date;
            }
            List<CurrencyRatesEntity> currencyRates = currencyRateService.getAllCurrencyRateByCurrencyAndBankAndDateBetween(currency, bank, startDate, endDate);
            List<BigDecimal> sellingRates = new ArrayList<>();
            currencyRates.forEach(currencyRate -> sellingRates.add(currencyRate.getSellingRate()));
            return ResponseEntity.ok(sellingRates);
        } else {
            return invalidBankIdResponseEntity;
        }
    }
}
