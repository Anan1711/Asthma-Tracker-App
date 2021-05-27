package com.example.asthmatracker.Model;

public class healthinfo{
    private String heartRate, spO2, temperature, ECG, humidity, airQuality, roomTemp, bloodpressure;

    public healthinfo(){

    }
    public healthinfo(String heartRate, String spO2, String temperature, String ECG, String humidity, String airQuality, String roomTemp, String bloodpressure) {
        this.heartRate = heartRate;
        this.spO2 = spO2;
        this.temperature = temperature;
        this.ECG = ECG;
        this.humidity = humidity;
        this.airQuality = airQuality;
        this.roomTemp = roomTemp;
        this.bloodpressure = bloodpressure;
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

    public String getECG() {
        return ECG;
    }

    public void setECG(String ECG) {
        this.ECG = ECG;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getAirQuality() {
        return airQuality;
    }

    public void setAirQuality(String airQuality) {
        this.airQuality = airQuality;
    }

    public String getRoomTemp() {
        return roomTemp;
    }

    public void setRoomTemp(String roomTemp) {
        this.roomTemp = roomTemp;
    }

    public String getBloodpressure() {
        return bloodpressure;
    }

    public void setBloodpressure(String bloodpressure) {
        this.bloodpressure = bloodpressure;
    }
}
