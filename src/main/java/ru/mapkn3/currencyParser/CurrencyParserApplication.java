package ru.mapkn3.currencyParser;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

@SpringBootApplication
public class CurrencyParserApplication {

    public static void main(String[] args) {
        //SpringApplication.run(CurrencyParserApplication.class, args);
        String urlVtb = "https://www.vtb.ru/personal/platezhi-i-perevody/obmen-valjuty/";
        String urlSber = "https://www.sberbank.ru/ru/quotes/currencies";
        String urlNkc = "http://nationalclearingcentre.ru/centralRates.do";
        String urlAlfa = "https://alfabank.ru/currency/";
        String urlMkb = "https://mkb.ru/exchange-rate";
        String urlOpen = "https://www.open.ru/exchange-person?from=main_menu";
        String urlUni = "https://www.unicreditbank.ru/ru/personal/all-currencies.html";
        //String urlRai = "https://www.raiffeisen.ru/currency_rates/"; //слишком хорошо
        String urlBin = "https://www.binbank.ru/private-clients/currency-exchange/profitable-rate/";
        String urlVbrr = "https://www.vbrr.ru/";
        String urlBspb = "https://www.bspb.ru/cash-rates";
        String urlMosobl = "https://mosoblbank.ru/";
        String urlCiti = "https://www.citibank.ru/RUGCB/COA/frx/prefxratinq/flow.action?locale=ru_RU";

        open(urlCiti);
        System.out.println($(By.xpath("//td[text() = 'USD' or contains(text(), 'USD') or text() = 'USD/RUB' or ./*[text() = 'USD'] or ./*/*[text() = 'USD']]/.. | //div[contains(text(), 'USD')]/../.. | //div/p[text() = 'USD']/../../.. | //div/label[contains(text(), 'USD')]/../../../..")));
    }
}
