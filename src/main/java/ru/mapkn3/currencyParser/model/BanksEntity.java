package ru.mapkn3.currencyParser.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "banks", schema = "public", catalog = "postgres")
public class BanksEntity {
    private int id;
    private String name;
    private String url;
    private boolean parsing;

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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "parsing")
    public boolean isParsing() {
        return parsing;
    }

    public void setParsing(boolean isParse) {
        this.parsing = isParse;
    }

    @Override
    public String toString() {
        return String.format("%d -> %s: %s [%b]", this.id, this.name, this.url, this.parsing);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BanksEntity that = (BanksEntity) o;
        return id == that.id &&
                parsing == that.parsing &&
                Objects.equals(name, that.name) &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, url, parsing);
    }
}
