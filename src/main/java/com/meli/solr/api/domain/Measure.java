package com.meli.solr.api.domain;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.meli.solr.api.domain.enumeration.WeatherType;

/**
 * A Measure.
 */
@Entity
@Table(name = "measure")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Measure extends AbstractAuditingEntity implements  Serializable {

    private static final long serialVersionUID = 1L;

    @Id 
    @Column(name = "day")
    private Integer day;

    @Enumerated(EnumType.STRING)
    @Column(name = "weather")
    private WeatherType weather;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Integer getDay() {
        return day;
    }

    public Measure day(Integer day) {
        this.day = day;
        return this;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public WeatherType getWeather() {
        return weather;
    }

    public Measure weather(WeatherType weather) {
        this.weather = weather;
        return this;
    }

    public void setWeather(WeatherType weather) {
        this.weather = weather;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Measure)) {
            return false;
        }
        return day != null && day.equals(((Measure) o).day);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Measure{" +
            ", day=" + getDay() +
            ", weather='" + getWeather() + "'" +
            "}";
    }
}
