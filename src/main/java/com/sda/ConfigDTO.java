package com.sda;




public class ConfigDTO {

    private String userName;
    private String localization;
    private String caloriesLimit;

    public ConfigDTO(String userName, String localization, String caloriesLimit) {
        this.userName = userName;
        this.localization = localization;
        this.caloriesLimit = caloriesLimit;
    }

    public String getUserName() {
        return userName;
    }

    public String getLocalization() {
        return localization;
    }

    public String getCaloriesLimit() {
        return caloriesLimit;
    }
}



