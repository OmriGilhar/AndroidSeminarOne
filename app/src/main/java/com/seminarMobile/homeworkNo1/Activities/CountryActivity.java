package com.seminarMobile.homeworkNo1.Activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.seminarMobile.homeworkNo1.Models.Country;
import com.seminarMobile.homeworkNo1.R;
import com.seminarMobile.homeworkNo1.Adapters.CountriesAdapter;
import com.seminarMobile.homeworkNo1.Services.CountryService;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class CountryActivity extends AppCompatActivity {

    private String englishCountryName;
    private TextView country_TXT_title;
    private RecyclerView country_RYC_countries;
    private final List<Country> borderedCountries = new ArrayList<>();
    private List<String> borderedCountriesNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        findViews();
        initViews();

    }

    private void findViews() {
        country_TXT_title = findViewById(R.id.country_TXT_title);
        country_RYC_countries = findViewById(R.id.country_RYC_countries);
    }

    private void initViews() {
        country_TXT_title.setText(R.string.borderedCountries);
        getCountryName();
        initCountriesRecycleView();
        requestBorderedCountries();

    }

    private void requestBorderedCountries() {
        String requestURL = "https://restcountries.eu/rest/v2/name/" + englishCountryName.toLowerCase();
        runRequest(requestURL);
    }

    private void runRequest(String requestURL) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                requestURL,
                null,
                response -> {
                    borderedCountriesNames = CountryService.parseBorderedCountries(response);
                    for(String border: borderedCountriesNames){
                        String requestBorderURL = "https://restcountries.eu/rest/v2/alpha/" + border.toLowerCase();
                        requestCountry(requestBorderURL, requestQueue);
                    }
                },
                error -> Log.println(Log.ERROR, "Main:Error", "Request Error" + error.toString()));

        requestQueue.add(jsonArrayRequest);
    }


    public void requestCountry(String requestURL, RequestQueue requestQueue) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                requestURL,
                null,
                response -> {
                    try {
                        Country c = new Country();
                        c.setEnglishName(response.getString("name"));
                        c.setNativeName(response.getString("nativeName"));
                        borderedCountries.add(c);
                        validateContinue();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Log.println(Log.ERROR, "Main:Error", "Request Error" + error.toString()));

        requestQueue.add(jsonObjectRequest);
    }

    private void validateContinue() {
        if(borderedCountries.size() == borderedCountriesNames.size()){
            fillCountriesView();
        }
    }


    private void fillCountriesView() {
        CountriesAdapter countryAdapter = new CountriesAdapter(this, this.borderedCountries) ;
        country_RYC_countries.setAdapter(countryAdapter);
    }

    private void initCountriesRecycleView() {
        country_RYC_countries.setLayoutManager(
                new LinearLayoutManager(
                        this,
                        LinearLayoutManager.VERTICAL,
                        false
                )
        );
    }


    private void getCountryName() {
        this.englishCountryName = getIntent().getStringExtra("countryName");
    }

}