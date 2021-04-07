package com.seminarMobile.homeworkNo1.Utils;

import com.seminarMobile.homeworkNo1.BusinessLogic.Country;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CountryJsonParser {
    public static List<Country> parseAllCountries(JSONArray responseJson){
        List<Country> parsedCountries = new ArrayList<>();
        try{
            for (int i=0; i< responseJson.length(); i++){
                JSONObject countryDetails = responseJson.getJSONObject(i);
                Country c = new Country();
                c.setEnglishName(countryDetails.getString("name"));
                c.setNativeName(countryDetails.getString("nativeName"));
                parsedCountries.add(c);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return parsedCountries;
    }

    public static List<String> parseBorderedCountries(JSONArray responseJson) {
        List<String> parsedBorderedCountriesNames = new ArrayList<>();
        try{
            JSONObject countryDetails = responseJson.getJSONObject(0);
            JSONArray borderedCountries = countryDetails.getJSONArray("borders");
            for (int i=0; i < borderedCountries.length(); i++){
                parsedBorderedCountriesNames.add(borderedCountries.getString(i));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return parsedBorderedCountriesNames;
    }
}
