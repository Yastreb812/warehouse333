package ru.yastrebov.warehouse333.warehouse.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "locations")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class  Location {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ratemin")
    private Integer ratemin;

    @Column(name = "ratemax")
    private Integer ratemax;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRatemin() {
        return ratemin;
    }

    public void setRatemin(Integer ratemin) {
        this.ratemin = ratemin;
    }

    public Integer getRatemax() {
        return ratemax;
    }

    public void setRatemax(Integer ratemax) {
        this.ratemax = ratemax;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", ratemin=" + ratemin +
                ", ratemax=" + ratemax +
                '}';
    }
}
