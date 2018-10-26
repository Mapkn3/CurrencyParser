package ru.mapkn3.currencyParser.service;

import com.codeborne.selenide.ex.ElementNotFound;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mapkn3.currencyParser.model.BanksEntity;
import ru.mapkn3.currencyParser.model.CurrenciesEntity;
import ru.mapkn3.currencyParser.model.CurrencyRatesEntity;
import ru.mapkn3.currencyParser.repository.CurrencyRatesRepository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Slf4j
@Service
@Transactional
public class CurrencyRateServiceImpl implements CurrencyRateService {
    @Autowired
    CurrencyRatesRepository repository;
    @Autowired
    CurrencyService currencyService;
    @Autowired
    BankService bankService;

    @Override
    @Transactional(readOnly = true)
    public CurrencyRatesEntity getCurrencyRate(int id) {
        log.debug("Getting currency rate with id=" + id);
        CurrencyRatesEntity currencyRatesEntity = repository.findById(id).orElse(null);
        if (currencyRatesEntity == null) {
            log.debug("Currency rate with id=" + id + " not found");
        }
        return currencyRatesEntity;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CurrencyRatesEntity> getCurrencyRatesByBank(BanksEntity bank) {
        log.debug("Getting currency rates for bank: " + bank);
        return repository.findAllByBank(bank);
    }

    @Override
    public List<CurrencyRatesEntity> getCurrencyRatesByCurrency(CurrenciesEntity currency) {
        log.debug("Getting currency rates for currency: " + currency);
        return repository.findAllByCurrency(currency);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CurrencyRatesEntity> getAllCurrencyRates() {
        List<CurrencyRatesEntity> currencyRates = (List<CurrencyRatesEntity>) repository.findAll();
        log.debug("Get " + currencyRates.size() + "currency rates:");
        currencyRates.forEach(currencyRate -> log.debug(currencyRate.toString()));
        return currencyRates;
    }

    @Override
    public void deleteCurrencyRate(CurrencyRatesEntity currencyRate) {
        repository.delete(currencyRate);
        log.debug("Delete currency rates: " + currencyRate);
    }

    @Override
    public List<CurrencyRatesEntity> getAllCurrencyRateByCurrencyAndBankAndDateBetween(CurrenciesEntity currenciesEntity, BanksEntity banksEntity, Timestamp startTime, Timestamp endTime) {
        List<CurrencyRatesEntity> currencyRates = repository.findAllByCurrencyAndBankAndCurrencyUpdateTimeBetweenOrderByCurrencyUpdateTimeDesc(currenciesEntity, banksEntity, startTime, endTime);
        log.debug("Getting currency rates for " + currenciesEntity.getCurrency() + " in " + banksEntity.getName() + " for date between " + startTime + " - " + endTime);
        return currencyRates;
    }

    @Override
    public CurrencyRatesEntity getTheBestActualCurrencyRateByCurrency(CurrenciesEntity currency) {
        CurrencyRatesEntity currencyRate = repository.findDistinctFirstByCurrencyOrderByPurchaseRateAscCurrencyUpdateTimeDesc(currency);
        log.debug("Get the best actual currency rate for " + currency);
        return currencyRate;
    }

    @Override
    public CurrencyRatesEntity parseCurrencyRateForBank(int currencyId, int bankId) {
        CurrenciesEntity currency = currencyService.getCurrency(currencyId);
        BanksEntity bank = bankService.getBank(bankId);
        if (currency != null && bank != null) {
            if (bank.getCurrencies().contains(currency)) {
                try {
                    String m = currency.getCurrency();
                    String pattern = "//td[(text() = '" + m + "' or contains(text(), '" + m + "') or text() = '" + m + "/RUB' or ./*[text() = '" + m + "'] or ./*/*[text() = '" + m + "']) and not(contains(text(), 'EUR/USD'))]/.." +
                            "| //div[contains(text(), '" + m + "')]/../.." +
                            "| //div/p[text() = '" + m + "']/../../.." +
                            "| //div/label[contains(text(), '" + m + "')]/../../../..";
                    String regexp = "\\d{2}[,\\.]\\d{2,4}";

                    open(bank.getUrl());
                    String currencyRateHtml = $(By.xpath(pattern)).innerHtml();
                    Date date = new Date();

                    Pattern r = Pattern.compile(regexp);
                    Matcher matcher = r.matcher(currencyRateHtml);
                    matcher.find();
                    String buyString = matcher.group();
                    double buy = Double.parseDouble(buyString.replace(',', '.'));
                    matcher.find();
                    String sellString = matcher.group();
                    double sell = Double.parseDouble(sellString.replace(',', '.'));
                    if (buy > sell) {
                        double value = buy;
                        buy = sell;
                        sell = value;
                    }
                    CurrencyRatesEntity currencyRate = new CurrencyRatesEntity(
                            new Timestamp(date.getTime()),
                            new Timestamp(date.getTime()),
                            new BigDecimal(buy),
                            new BigDecimal(sell),
                            currency,
                            bank);
                    repository.save(currencyRate);
                    log.debug("Parse currency rate for " + currency.getCurrency() + " from " + bank.getName() + "(" + bank.getUrl() + "):\n" + currencyRate);
                    return currencyRate;
                } catch (ElementNotFound e) {
                    log.debug("Not found currency rate for " + currency.getCurrency() + " in " + bank.getName() + "(" + bank.getUrl() + ")");
                    return null;
                } catch (IllegalStateException e) {
                    log.debug("Not found correct value for " + currency.getCurrency() + " in " + bank.getName() + "(" + bank.getUrl() + ")");
                    return null;
                }
            }
            log.debug("Bank not contain this currency");
            return null;
        }
        log.debug("Currency or bank not found by id");
        return null;
    }
}
