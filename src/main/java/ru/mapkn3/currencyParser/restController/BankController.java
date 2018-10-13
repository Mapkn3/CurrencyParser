package ru.mapkn3.currencyParser.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mapkn3.currencyParser.model.BanksEntity;
import ru.mapkn3.currencyParser.service.BankService;

import java.util.List;

@RestController
@RequestMapping(value = "/bank", produces = MediaType.APPLICATION_JSON_VALUE)
public class BankController {
    @Autowired
    private BankService bankService;

    @GetMapping("/all")
    public List<BanksEntity> getAllBanks() {
        return bankService.getAllBanks();
    }
}
