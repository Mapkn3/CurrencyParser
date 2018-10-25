package ru.mapkn3.currencyParser.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    private final String INVALID_BANK_ID_MESSAGE = "Invalid bank id";
    private final ResponseEntity invalidBankIdResponseEntity = ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN).body(INVALID_BANK_ID_MESSAGE);

    @Autowired
    private BankService bankService;
    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/all")
    public ResponseEntity<List<BanksEntity>> getAllBanks() {
        return ResponseEntity.ok(bankService.getAllBanks());
    }

    @GetMapping("/parsing")
    public ResponseEntity<List<BanksEntity>> getAllParsingBanks() {
        return ResponseEntity.ok(bankService.getParsingBanks());
    }

    @GetMapping("/{id:\\d+}")
    public ResponseEntity getBank(@PathVariable("id") int id) {
        BanksEntity bank = bankService.getBank(id);
        if (bank != null) {
            return ResponseEntity.ok(bankService.getBank(id));
        } else {
            return invalidBankIdResponseEntity;
        }
    }

    @PutMapping("/{id:\\d+}")
    public ResponseEntity changeParsing(@PathVariable("id") int id) {
        BanksEntity bank = bankService.getBank(id);
        if (bank != null) {
            bank.setParsing(!bank.isParsing());
            bankService.updateBank(bank);
            return ResponseEntity.ok(bank);
        } else {
            return invalidBankIdResponseEntity;
        }
    }

    @GetMapping("/{id:\\d+}/currencies")
    public ResponseEntity getCurrenciesForBank(@PathVariable("id") int id) {
        BanksEntity bank = bankService.getBank(id);
        if (bank != null) {
            return ResponseEntity.ok(bank.getCurrencies());
        } else {
            return invalidBankIdResponseEntity;
        }
    }

    @PostMapping("/{id:\\d+}/currencies/{currencyId:\\d+}")
    public ResponseEntity addCurrencyForBankById(@PathVariable("id") int bankId, @PathVariable("currencyId") int currencyId) {
        return addCurrencyForBank(bankId, currencyService.getCurrency(currencyId));
    }

    @DeleteMapping("/{id:\\d+}/currencies/{currencyId:\\d+}")
    public ResponseEntity deleteCurrencyForBankById(@PathVariable("id") int bankId, @PathVariable("currencyId") int currencyId) {
        return deleteCurrencyForBank(bankId, currencyService.getCurrency(currencyId));
    }

    @PostMapping("/{id:\\d+}/currencies/{currency:\\D+}")
    public ResponseEntity addCurrencyForBankByName(@PathVariable("id") int bankId, @PathVariable("currency") String currencyName) {
        return addCurrencyForBank(bankId, currencyService.getCurrencyByName(currencyName));
    }

    @DeleteMapping("/{id:\\d+}/currencies/{currency:\\D+}")
    public ResponseEntity deleteCurrencyForBankByName(@PathVariable("id") int bankId, @PathVariable("currency") String currencyName) {
        return deleteCurrencyForBank(bankId, currencyService.getCurrencyByName(currencyName));
    }

    private ResponseEntity addCurrencyForBank(int bankId, CurrenciesEntity currency) {
        BanksEntity bank = bankService.getBank(bankId);
        if (bank != null) {
            if (currency != null) {
                Set<CurrenciesEntity> currencies = bank.getCurrencies();
                currencies.add(currency);
                bankService.updateBank(bank);
                return ResponseEntity.ok(currencies);
            } else {
                return ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN).body("Invalid currency");
            }
        } else {
            return invalidBankIdResponseEntity;
        }

    }

    private ResponseEntity deleteCurrencyForBank(int bankId, CurrenciesEntity currency) {
        BanksEntity bank = bankService.getBank(bankId);
        if (bank != null) {
            if (currency != null) {
                Set<CurrenciesEntity> currencies = bank.getCurrencies();
                currencies.remove(currency);
                bankService.updateBank(bank);
                return ResponseEntity.ok(currencies);
            } else {
                return ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN).body("Invalid currency");
            }
        } else {
            return invalidBankIdResponseEntity;
        }
    }
}
