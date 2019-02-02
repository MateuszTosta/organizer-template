package com.sda.files;

public class ConfigDTO{
    private String userName;
    private String localization;
    private Double caloriesLimit;

    public ConfigDTO(String userName, String localization, Double caloriesLimit){
        this.userName=userName;
        this.localization=localization;
        this.caloriesLimit=caloriesLimit;
    }

    public String getUserName(){
        return userName;
    }

    public String getLocalization(){
        return localization;
    }

    public Double getCaloriesLimit(){
        return caloriesLimit;
    }
}