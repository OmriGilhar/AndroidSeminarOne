package com.seminarMobile.homeworkNo1.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.seminarMobile.homeworkNo1.Adapters.CountriesAdapter;
import com.seminarMobile.homeworkNo1.Models.Country;
import com.seminarMobile.homeworkNo1.Services.CountryService;
import com.seminarMobile.homeworkNo1.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView main_TXT_title;
    private RecyclerView main_RYC_countries;
    private List<Country> countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        initViews();

    }

    private void findViews() {
        main_TXT_title = findViewById(R.id.main_TXT_title);
        main_RYC_countries = findViewById(R.id.main_RYC_countries);
    }

    private void initViews() {
        main_TXT_title.setText(R.string.countries_str);
        initCountriesRecycleView();
        requestCountries();
    }

    private void initCountriesRecycleView() {
        main_RYC_countries.setLayoutManager(
                new LinearLayoutManager(
                        this,
                        LinearLayoutManager.VERTICAL,
                        false
                )
        );
    }

    private void requestCountries() {
        String requestURL = "https://restcountries.eu/rest/v2/all";
        runRequest(requestURL);
    }

    private void fillCountriesView() {
        CountriesAdapter countryAdapter = new CountriesAdapter(this, this.countries) ;
        main_RYC_countries.setAdapter(countryAdapter);
    }

    private void runRequest(String requestURL) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                requestURL,
                null,
                response -> {
                    countries = CountryService.parseAllCountries(response);
                    fillCountriesView();
                },
                error -> Log.println(Log.ERROR, "Main:Error", "Request Error" + error.toString()));

                requestQueue.add(jsonArrayRequest);
    }

}