package com.example.asthmatracker.Model;

public class healthinfo{
    private String heartRate, spO2, temperature;

    public healthinfo(){

    }
    public healthinfo(String heartRate, String spO2, String temperature) {
        this.heartRate = heartRate;
        this.spO2 = spO2;
        this.temperature = temperature;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public String getSpO2() {
        return spO2;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public void setSpO2(String spO2) {
        this.spO2 = spO2;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
