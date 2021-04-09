package com.seminarMobile.homeworkNo1.Adapters;

import com.seminarMobile.homeworkNo1.Models.Country;
import com.seminarMobile.homeworkNo1.Services.CountryService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CountriesAdapterTest {
    @Test
    public void parseAllCountries() throws JSONException {
        List<Country> countriesClassMock;

        JSONObject countryNo1 = new JSONObject();
        countryNo1.put("name", "Israel");
        countryNo1.put("nativeName", "ישראל");

        JSONObject countryNo2 = new JSONObject();
        countryNo2.put("name", "Spain");
        countryNo2.put("nativeName", "España");

        JSONObject countryNo3 = new JSONObject();
        countryNo3.put("name", "Japan");
        countryNo3.put("nativeName", "日本");

        JSONArray countriesMock = new JSONArray();
        countriesMock.put(countryNo1);
        countriesMock.put(countryNo2);
        countriesMock.put(countryNo3);

        countriesClassMock = CountryService.parseAllCountries(countriesMock);
        Assert.assertEquals(countriesClassMock.get(0).getEnglishName(), "Israel");
        Assert.assertEquals(countriesClassMock.get(0).getNativeName(), "ישראל");

        Assert.assertEquals(countriesClassMock.get(1).getEnglishName(), "Spain");
        Assert.assertEquals(countriesClassMock.get(1).getNativeName(), "España");

        Assert.assertEquals(countriesClassMock.get(2).getEnglishName(), "Japan");
        Assert.assertEquals(countriesClassMock.get(2).getNativeName(), "日本");

    }

    @Test
    public void parseBorderedCountries() throws JSONException {
        List<String> countriesBorderedMock;

        JSONArray borderedCountries = new JSONArray();
        borderedCountries.put("ISR");
        borderedCountries.put("JPN");

        JSONObject countryDetails = new JSONObject();
        countryDetails.put("borders", borderedCountries);

        JSONArray testInput = new JSONArray();
        testInput.put(countryDetails);

        countriesBorderedMock = CountryService.parseBorderedCountries(testInput);

        Assert.assertEquals("ISR", countriesBorderedMock.get(0));
        Assert.assertEquals("JPN", countriesBorderedMock.get(1));
    }
}