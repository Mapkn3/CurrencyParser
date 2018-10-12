package ru.mapkn3.currencyParser.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "currencies", schema = "public", catalog = "postgres")
public class CurrenciesEntity {
    private int id;
    private String currency;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "currency")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return String.format("%d -> %s", this.id, this.currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrenciesEntity that = (CurrenciesEntity) o;
        return id == that.id &&
                Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, currency);
    }
}
