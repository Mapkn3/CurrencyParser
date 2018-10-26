package ru.mapkn3.currencyParser.restController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

@Api(tags = "Bank", description = "Bank API")
@RestController
@RequestMapping(value = "/bank", produces = MediaType.APPLICATION_JSON_VALUE)
public class BankController {
    private final String INVALID_BANK_ID_MESSAGE = "Invalid bank id";
    private final ResponseEntity invalidBankIdResponseEntity = ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN).body(INVALID_BANK_ID_MESSAGE);

    @Autowired
    private BankService bankService;
    @Autowired
    private CurrencyService currencyService;

    @ApiOperation(value = "Get information about all banks", notes = "Get all banks")
    @ApiResponse(code = 200, message = "List all banks")
    @GetMapping("/all")
    public ResponseEntity<List<BanksEntity>> getAllBanks() {
        return ResponseEntity.ok(bankService.getAllBanks());
    }

    @ApiOperation(value = "Get information about all banks ready for parsing", notes = "Get all parsing banks")
    @ApiResponse(code = 200, message = "List all parsing banks")
    @GetMapping("/parsing")
    public ResponseEntity<List<BanksEntity>> getAllParsingBanks() {
        return ResponseEntity.ok(bankService.getParsingBanks());
    }

    @ApiOperation(value = "Get bank by bank id", notes = "Get bank")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Show information about bank"),
            @ApiResponse(code = 400, message = "Bad request: invalid bank id")
    })
    @GetMapping("/{id:\\d+}")
    public ResponseEntity getBank(@PathVariable("id") int id) {
        BanksEntity bank = bankService.getBank(id);
        if (bank != null) {
            return ResponseEntity.ok(bankService.getBank(id));
        } else {
            return invalidBankIdResponseEntity;
        }
    }

    @ApiOperation(value = "Change ready for parsing state for bank by bank id", notes = "Change ready for parsing state for bank")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Change ready for parsing state for bank completed."),
            @ApiResponse(code = 400, message = "Bad request: invalid bank id")
    })
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

    @ApiOperation(value = "Get available currencies for bank by bank id", notes = "Get available currencies for bank")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List available currencies for bank"),
            @ApiResponse(code = 400, message = "Bad request: invalid bank id")
    })
    @GetMapping("/{id:\\d+}/currencies")
    public ResponseEntity getCurrenciesForBank(@PathVariable("id") int id) {
        BanksEntity bank = bankService.getBank(id);
        if (bank != null) {
            return ResponseEntity.ok(bank.getCurrencies());
        } else {
            return invalidBankIdResponseEntity;
        }
    }

    @ApiOperation(value = "Add currency by id to available currencies for bank by bank id", notes = "Add currency to available currencies for bank")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Add currency to available currencies for bank completed"),
            @ApiResponse(code = 400, message = "Bad request: invalid bank id"),
            @ApiResponse(code = 400, message = "Bad request: invalid currency id")
    })
    @PostMapping("/{id:\\d+}/currencies/{currencyId:\\d+}")
    public ResponseEntity addCurrencyForBankById(@PathVariable("id") int bankId, @PathVariable("currencyId") int currencyId) {
        return addCurrencyForBank(bankId, currencyService.getCurrency(currencyId));
    }

    @ApiOperation(value = "Delete currency by id from available currencies for bank by bank id", notes = "Delete currency from available currencies for bank")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Delete currency from available currencies for bank completed"),
            @ApiResponse(code = 400, message = "Bad request: invalid bank id"),
            @ApiResponse(code = 400, message = "Bad request: invalid currency id")
    })
    @DeleteMapping("/{id:\\d+}/currencies/{currencyId:\\d+}")
    public ResponseEntity deleteCurrencyForBankById(@PathVariable("id") int bankId, @PathVariable("currencyId") int currencyId) {
        return deleteCurrencyForBank(bankId, currencyService.getCurrency(currencyId));
    }

    @ApiOperation(value = "Add currency by name to available currencies for bank by bank id", notes = "Add currency to available currencies for bank")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Add currency to available currencies for bank completed"),
            @ApiResponse(code = 400, message = "Bad request: invalid bank id"),
            @ApiResponse(code = 400, message = "Bad request: invalid currency name")
    })
    @PostMapping("/{id:\\d+}/currencies/{currency:\\D+}")
    public ResponseEntity addCurrencyForBankByName(@PathVariable("id") int bankId, @PathVariable("currency") String currencyName) {
        return addCurrencyForBank(bankId, currencyService.getCurrencyByName(currencyName));
    }


    @ApiOperation(value = "Delete currency by name from available currencies for bank by bank id", notes = "Delete currency from available currencies for bank")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Delete currency from available currencies for bank completed"),
            @ApiResponse(code = 400, message = "Bad request: invalid bank id"),
            @ApiResponse(code = 400, message = "Bad request: invalid currency id")
    })
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
