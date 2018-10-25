package ru.mapkn3.currencyParser.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "currency_rates", schema = "public", catalog = "postgres")
public class CurrencyRatesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "parse_time")
    private Timestamp parseTime;

    @Basic
    @Column(name = "currency_update_time")
    private Timestamp currencyUpdateTime;

    @Basic
    @Column(name = "selling_rate")
    private BigDecimal sellingRate;

    @Basic
    @Column(name = "purchase_rate")
    private BigDecimal purchaseRate;

    @ManyToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "id", nullable = false)
    private CurrenciesEntity currency;

    @ManyToOne
    @JoinColumn(name = "bank_id", referencedColumnName = "id", nullable = false)
    private BanksEntity bank;

    @Override
    public String toString() {
        return String.format("%d -> %s (%tc) %s: %.2f|%.2f - %tc",
                this.id, this.bank.getName(), this.parseTime, this.currency.getCurrency(), this.sellingRate, this.purchaseRate, this.currencyUpdateTime);
    }

    public CurrencyRatesEntity(Timestamp parseTime, Timestamp currencyUpdateTime, BigDecimal sellingRate, BigDecimal purchaseRate, CurrenciesEntity currency, BanksEntity bank) {
        this.parseTime = parseTime;
        this.currencyUpdateTime = currencyUpdateTime;
        this.sellingRate = sellingRate;
        this.purchaseRate = purchaseRate;
        this.currency = currency;
        this.bank = bank;
    }
/*
    public CurrencyRatesEntity() {
    }

    public CurrencyRatesEntity(Timestamp parseTime, Timestamp currencyUpdateTime, BigDecimal sellingRate, BigDecimal purchaseRate, CurrenciesEntity currency, BanksEntity bank) {
        this.parseTime = parseTime;
        this.currencyUpdateTime = currencyUpdateTime;
        this.sellingRate = sellingRate;
        this.purchaseRate = purchaseRate;
        this.currency = currency;
        this.bank = bank;
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
    @Column(name = "parse_time")
    public Timestamp getParseTime() {
        return parseTime;
    }

    public void setParseTime(Timestamp parseTime) {
        this.parseTime = parseTime;
    }

    @Basic
    @Column(name = "currency_update_time")
    public Timestamp getCurrencyUpdateTime() {
        return currencyUpdateTime;
    }

    public void setCurrencyUpdateTime(Timestamp currencyUpdateTime) {
        this.currencyUpdateTime = currencyUpdateTime;
    }

    @Basic
    @Column(name = "selling_rate")
    public BigDecimal getSellingRate() {
        return sellingRate;
    }

    public void setSellingRate(BigDecimal sellingRate) {
        this.sellingRate = sellingRate;
    }

    @Basic
    @Column(name = "purchase_rate")
    public BigDecimal getPurchaseRate() {
        return purchaseRate;
    }

    public void setPurchaseRate(BigDecimal purchaseRate) {
        this.purchaseRate = purchaseRate;
    }

    @Override
    public String toString() {
        return String.format("%d -> %s (%tc) %s: %.2f|%.2f - %tc",
                this.id, this.bank.getName(), this.parseTime, this.currency.getCurrency(), this.sellingRate, this.purchaseRate, this.currencyUpdateTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyRatesEntity that = (CurrencyRatesEntity) o;
        return id == that.id &&
                Objects.equals(parseTime, that.parseTime) &&
                Objects.equals(currencyUpdateTime, that.currencyUpdateTime) &&
                Objects.equals(sellingRate, that.sellingRate) &&
                Objects.equals(purchaseRate, that.purchaseRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parseTime, currencyUpdateTime, sellingRate, purchaseRate);
    }

    @ManyToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "id", nullable = false)
    public CurrenciesEntity getCurrency() {
        return currency;
    }

    public void setCurrency(CurrenciesEntity currency) {
        this.currency = currency;
    }

    @ManyToOne
    @JoinColumn(name = "bank_id", referencedColumnName = "id", nullable = false)
    public BanksEntity getBank() {
        return bank;
    }

    public void setBank(BanksEntity bank) {
        this.bank = bank;
    }
*/
}
