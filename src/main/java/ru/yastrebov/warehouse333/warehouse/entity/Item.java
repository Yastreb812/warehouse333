package ru.yastrebov.warehouse333.warehouse.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "items")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Item {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "volume")
    private Integer volume;

    @Column(name = "value")
    private Integer value;

    @Column(name = "art")
    private Boolean art;

    public Item() {

    }

    public Item(Integer id, Integer volume, Integer value, Boolean art) {
        this.id = id;
        this.volume = volume;
        this.value = value;
        this.art = art;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Boolean getArt() {
        return art;
    }

    public void setArt(Boolean art) {
        this.art = art;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", volume=" + volume +
                ", value=" + value +
                ", art=" + art +
                '}';
    }
}