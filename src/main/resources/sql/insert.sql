INSERT INTO banks(name, url, parsing) VALUES ('Сбербанк', 'www.sberbank.ru', TRUE);
INSERT INTO banks(name, url, parsing) VALUES ('Банк ВТБ', 'www.vtb.ru', TRUE);
INSERT INTO banks(name, url, parsing) VALUES ('Газпромбанк', 'www.gazprombank.ru', TRUE); -- нет USD, только доллары США
INSERT INTO banks(name, url, parsing) VALUES ('Россельхозбанк', 'www.rshb.ru', TRUE); -- сложная структура курса, разный курс для каждого адреса
INSERT INTO banks(name, url, parsing) VALUES ('Национальный клиринговый центр', 'www.nkcbank.ru', TRUE);
INSERT INTO banks(name, url, parsing) VALUES ('Альфа-Банк', 'www.alfabank.ru', TRUE);
INSERT INTO banks(name, url, parsing) VALUES ('Московский Кредитный Банк', 'www.mkb.ru', TRUE);
INSERT INTO banks(name, url, parsing) VALUES ('Банк Открытие', 'www.openbank.ru', TRUE);
INSERT INTO banks(name, url, parsing) VALUES ('Национальный Банк Траст', 'www.trust.ru', TRUE); -- нет курса валют
INSERT INTO banks(name, url, parsing) VALUES ('ЮниКредит Банк', 'www.unicreditbank.ru', TRUE);
INSERT INTO banks(name, url, parsing) VALUES ('Райффайзенбанк', 'www.raiffeisen.ru', TRUE);
INSERT INTO banks(name, url, parsing) VALUES ('Росбанк', 'www.rosbank.ru', TRUE); -- нет USD
INSERT INTO banks(name, url, parsing) VALUES ('Банк Россия', 'abr.ru', TRUE); -- нет курса валют
INSERT INTO banks(name, url, parsing) VALUES ('Совкомбанк', 'www.sovcombank.ru', TRUE); -- нет курса валют
INSERT INTO banks(name, url, parsing) VALUES ('Бинбанк', 'www.binbank.ru', TRUE);
INSERT INTO banks(name, url, parsing) VALUES ('Всероссийский Банк Развития Регионов', 'www.vbrr.ru', TRUE);
INSERT INTO banks(name, url, parsing) VALUES ('Банк Санкт-Петербург', 'www.bspb.ru', TRUE);
INSERT INTO banks(name, url, parsing) VALUES ('БМ-Банк', 'www.bm-bank.ru', TRUE); -- нет курса валют
INSERT INTO banks(name, url, parsing) VALUES ('Московский Областной Банк', 'www.mosoblbank.ru', TRUE);
INSERT INTO banks(name, url, parsing) VALUES ('Ситибанк', 'www.citibank.ru', TRUE);

INSERT INTO currencies(currency) VALUES ('USD');
INSERT INTO currencies(currency) VALUES ('EUR');
INSERT INTO currencies(currency) VALUES ('GBR');
INSERT INTO currencies(currency) VALUES ('CHF');
INSERT INTO currencies(currency) VALUES ('CNY');
INSERT INTO currencies(currency) VALUES ('JPY');

INSERT INTO currencies_for_bank VALUES (1, 1);
INSERT INTO currencies_for_bank VALUES (2, 1);