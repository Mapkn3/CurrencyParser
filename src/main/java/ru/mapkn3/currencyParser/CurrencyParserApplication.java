package ru.mapkn3.currencyParser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.mapkn3.currencyParser.task.UpdateCurrencyRatesTask;

@SpringBootApplication
public class CurrencyParserApplication {

    public static void main(String[] args) {
        SpringApplication.run(new Class[] {CurrencyParserApplication.class, UpdateCurrencyRatesTask.class}, args);
    }
}
