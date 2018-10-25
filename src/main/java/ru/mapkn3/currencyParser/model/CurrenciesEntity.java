package ru.mapkn3.currencyParser.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "currencies", schema = "public", catalog = "postgres")
public class CurrenciesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "currency")
    private String currency;

    @Override
    public String toString() {
        return String.format("%d -> %s", this.id, this.currency);
    }
/*

    public CurrenciesEntity() {
    }

    public CurrenciesEntity(String currency) {
        this.currency = currency;
    }

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
*/
}
