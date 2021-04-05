package com.example.myapplication.BusinessLogic;

public class Country {
    private String englishName;
    private String nativeName;

    public void Country(){}

    public void Country(String englishName, String nativeName){
        this.setEnglishName(englishName);
        this.setNativeName(nativeName);
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }
}
