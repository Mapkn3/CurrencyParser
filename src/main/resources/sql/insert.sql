INSERT INTO banks (name, url, parsing) VALUES ('Сбербанк', 'https://www.sberbank.ru/ru/quotes/currencies', TRUE);
INSERT INTO banks (name, url, parsing) VALUES ('Банк ВТБ', 'https://www.vtb.ru/personal/platezhi-i-perevody/obmen-valjuty/', TRUE);
INSERT INTO banks (name, url, parsing) VALUES ('Альфа-Банк', 'https://alfabank.ru/currency/', TRUE);
INSERT INTO banks (name, url, parsing) VALUES ('Московский Кредитный Банк', 'https://mkb.ru/exchange-rate', TRUE);
INSERT INTO banks (name, url, parsing) VALUES ('Банк Открытие', 'https://www.open.ru/exchange-person?from=main_menu', TRUE);
INSERT INTO banks (name, url, parsing) VALUES ('ЮниКредит Банк', 'https://www.unicreditbank.ru/ru/personal/all-currencies.html', TRUE);
INSERT INTO banks (name, url, parsing) VALUES ('Бинбанк', 'https://www.binbank.ru/private-clients/currency-exchange/profitable-rate/', TRUE);
INSERT INTO banks (name, url, parsing) VALUES ('Всероссийский Банк Развития Регионов', 'https://www.vbrr.ru/', TRUE);
INSERT INTO banks (name, url, parsing) VALUES ('Банк Санкт-Петербург', 'https://www.bspb.ru/cash-rates', TRUE);
INSERT INTO banks (name, url, parsing) VALUES ('Московский Областной Банк', 'https://mosoblbank.ru/', TRUE);
INSERT INTO banks (name, url, parsing) VALUES ('Ситибанк', 'https://www.citibank.ru/RUGCB/COA/frx/prefxratinq/flow.action?locale=ru_RU', TRUE);
--INSERT INTO banks(name, url, parsing) VALUES ('Райффайзенбанк', 'https://www.raiffeisen.ru/currency_rates/', TRUE);

--INSERT INTO banks(name, url, parsing) VALUES ('Национальный клиринговый центр', 'http://nationalclearingcentre.ru/centralRates.do', TRUE); -- нет курса dfk.n
--INSERT INTO banks(name, url, parsing) VALUES ('БМ-Банк', 'www.bm-bank.ru', TRUE); -- нет курса валют
--INSERT INTO banks(name, url, parsing) VALUES ('Росбанк', 'www.rosbank.ru', TRUE); -- нет USD
--INSERT INTO banks(name, url, parsing) VALUES ('Банк Россия', 'abr.ru', TRUE); -- нет курса валют
--INSERT INTO banks(name, url, parsing) VALUES ('Совкомбанк', 'www.sovcombank.ru', TRUE); -- нет курса валют
--INSERT INTO banks(name, url, parsing) VALUES ('Национальный Банк Траст', 'www.trust.ru', TRUE); -- нет курса валют
--INSERT INTO banks(name, url, parsing) VALUES ('Газпромбанк', 'www.gazprombank.ru', TRUE); -- нет USD, только доллары США
--INSERT INTO banks(name, url, parsing) VALUES ('Россельхозбанк', 'www.rshb.ru', TRUE); -- сложная структура курса, разный курс для каждого адреса

INSERT INTO currencies (currency) VALUES ('USD');
INSERT INTO currencies (currency) VALUES ('EUR');
INSERT INTO currencies (currency) VALUES ('GBP');
INSERT INTO currencies (currency) VALUES ('CHF');
INSERT INTO currencies (currency) VALUES ('CNY');
INSERT INTO currencies (currency) VALUES ('JPY');

INSERT INTO currencies_for_bank VALUES (1, 1);

INSERT INTO currencies_for_bank VALUES (1, 2);
INSERT INTO currencies_for_bank VALUES (2, 2);
INSERT INTO currencies_for_bank VALUES (3, 2);
INSERT INTO currencies_for_bank VALUES (4, 2);
INSERT INTO currencies_for_bank VALUES (5, 2);
INSERT INTO currencies_for_bank VALUES (6, 2);

INSERT INTO currencies_for_bank VALUES (1, 3);
INSERT INTO currencies_for_bank VALUES (2, 3);
INSERT INTO currencies_for_bank VALUES (3, 3);
INSERT INTO currencies_for_bank VALUES (4, 3);

INSERT INTO currencies_for_bank VALUES (1, 4);
INSERT INTO currencies_for_bank VALUES (2, 4);
INSERT INTO currencies_for_bank VALUES (3, 4);
INSERT INTO currencies_for_bank VALUES (4, 4);
INSERT INTO currencies_for_bank VALUES (6, 4);

INSERT INTO currencies_for_bank VALUES (1, 5);
INSERT INTO currencies_for_bank VALUES (2, 5);

INSERT INTO currencies_for_bank VALUES (1, 6);
INSERT INTO currencies_for_bank VALUES (2, 6);
INSERT INTO currencies_for_bank VALUES (3, 6);
INSERT INTO currencies_for_bank VALUES (4, 6);

INSERT INTO currencies_for_bank VALUES (1, 7);
INSERT INTO currencies_for_bank VALUES (2, 7);
INSERT INTO currencies_for_bank VALUES (3, 7);
INSERT INTO currencies_for_bank VALUES (4, 7);

INSERT INTO currencies_for_bank VALUES (1, 8);
INSERT INTO currencies_for_bank VALUES (2, 8);

INSERT INTO currencies_for_bank VALUES (1, 9);
INSERT INTO currencies_for_bank VALUES (2, 9);
INSERT INTO currencies_for_bank VALUES (3, 9);
INSERT INTO currencies_for_bank VALUES (4, 9);
INSERT INTO currencies_for_bank VALUES (6, 9);

INSERT INTO currencies_for_bank VALUES (1, 10);
INSERT INTO currencies_for_bank VALUES (2, 10);
INSERT INTO currencies_for_bank VALUES (3, 10);
INSERT INTO currencies_for_bank VALUES (4, 10);

INSERT INTO currencies_for_bank VALUES (1, 11);
INSERT INTO currencies_for_bank VALUES (2, 11);
INSERT INTO currencies_for_bank VALUES (3, 11);
INSERT INTO currencies_for_bank VALUES (4, 11);