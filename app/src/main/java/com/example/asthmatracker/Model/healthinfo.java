package com.example.asthmatracker.Model;

public class healthinfo{
    private String heartRate, spO2, temperature, BP, FVC, PEF, FEV1;

    public healthinfo(){

    }
    public healthinfo(String heartRate, String spO2, String temperature, String BP, String FVC, String PEF, String FEV1) {
        this.heartRate = heartRate;
        this.spO2 = spO2;
        this.temperature = temperature;
        this.BP = BP;
        this.FVC = FVC;
        this.PEF = PEF;
        this.FEV1 = FEV1;
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

    public String getBP() {
        return BP;
    }

    public void setBP(String BP) {
        this.BP = BP;
    }

    public String getFVC() {
        return FVC;
    }

    public void setFVC(String FVC) {
        this.FVC = FVC;
    }

    public String getPEF() {
        return PEF;
    }

    public void setPEF(String PEF) {
        this.PEF = PEF;
    }

    public String getFEV1() {
        return FEV1;
    }

    public void setFEV1(String FEV1) {
        this.FEV1 = FEV1;
    }
}
