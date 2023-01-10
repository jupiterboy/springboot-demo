package com.jupiterboy.springboot.influxdb;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;

import java.time.Instant;

@Measurement(name = "temperature")
public class Temperature {

    @Column(tag = true)
    public String location;

    @Column
    public Double value;

    @Column(timestamp = true)
    public Instant time;
}