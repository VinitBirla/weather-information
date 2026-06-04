package com.practice.weatherinfo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "weather")
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // JSON key is "date" — one of exactly 7 fields the test verifies with hasSize(7)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    @Column(name = "date_recorded")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    // double (not float) — test: jsonPath("$.lat").value(weather.getLat())
    // JsonPath parses numbers as Double; getLat() must return double to match
    @Column(name = "lat")
    private double lat;

    @Column(name = "lon")
    private double lon;

    @Column(name = "temperature")
    private double temperature;

    // Required by JPA
    public Weather() {}

    // Constructor used in ALL 4 tests:
    // new Weather(new Date(), "Nashville", "Tennessee", 36.1189f, -86.6892f, 37.3)
    // float lat/lon are widened to double automatically
    public Weather(Date date, String city, String state, float lat, float lon, double temperature) {
        this.date = date;
        this.city = city;
        this.state = state;
        this.lat = lat;
        this.lon = lon;
        this.temperature = temperature;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    // "date" serializes correctly as JSON key
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    // ALL 4 tests call weather.setDateRecorded(Date) — maps to same field
    // @JsonIgnore prevents "dateRecorded" appearing in JSON (keeps exactly 7 fields)
    @JsonIgnore
    public Date getDateRecorded() { return date; }
    public void setDateRecorded(Date date) { this.date = date; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public double getLat() { return lat; }
    public void setLat(double lat) { this.lat = lat; }

    public double getLon() { return lon; }
    public void setLon(double lon) { this.lon = lon; }

    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }
}
