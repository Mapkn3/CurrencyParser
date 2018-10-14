CREATE TABLE banks (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    url VARCHAR(100) NOT NULL,
    parsing BOOLEAN NOT NULL
);

CREATE TABLE currencies (
    id SERIAL PRIMARY KEY,
    currency VARCHAR(3) NOT NULL
);
CREATE TABLE currencies_for_bank (
    currency_id INTEGER REFERENCES currencies (id) ON UPDATE CASCADE ON DELETE CASCADE NOT NULL,
    bank_id INTEGER REFERENCES banks (id) ON UPDATE CASCADE ON DELETE CASCADE NOT NULL,
    PRIMARY KEY (currency_id, bank_id)
);

CREATE TABLE currency_rates (
    id SERIAL PRIMARY KEY,
    parse_time TIMESTAMP NOT NULL,
    currency_update_time TIMESTAMP NOT NULL,
    currency_id INTEGER REFERENCES currencies (id) ON UPDATE CASCADE ON DELETE CASCADE NOT NULL,
    selling_rate NUMERIC(8, 4) NOT NULL,
    purchase_rate NUMERIC(8, 4) NOT NULL,
    bank_id INTEGER REFERENCES banks (id) ON UPDATE CASCADE ON DELETE CASCADE NOT NULL
);