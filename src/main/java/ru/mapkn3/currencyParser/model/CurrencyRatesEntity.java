package ru.mapkn3.currencyParser.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "currency_rates", schema = "public", catalog = "postgres")
public class CurrencyRatesEntity {
    private int id;
    private Timestamp parseTime;
    private Timestamp currencyUpdateTime;
    private BigDecimal sellingRate;
    private BigDecimal purchaseRate;
    private CurrenciesEntity currency;
    private BanksEntity bank;

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
}
