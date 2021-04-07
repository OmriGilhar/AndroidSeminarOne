package com.seminarMobile.homeworkNo1.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.seminarMobile.homeworkNo1.Activities.CountryActivity;
import com.seminarMobile.homeworkNo1.BusinessLogic.Country;
import com.seminarMobile.homeworkNo1.R;

import java.util.List;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.ViewHolder> {

    private List<Country> countries;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public CountriesAdapter(Context context, List<Country> countries) {
        this.mInflater = LayoutInflater.from(context);
        this.countries = countries;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.country_list, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Country country = countries.get(position);
        holder.countryEnglish.setText(country.getEnglishName());
        holder.countryNative.setText(country.getNativeName());
        holder.countryList_LYO_main.setOnClickListener(v -> {
            Intent countryActivity = new Intent(mInflater.getContext(), CountryActivity.class);
            countryActivity.putExtra("countryName", country.getEnglishName());

            ((Activity)mInflater.getContext()).startActivityForResult(countryActivity, 1);
        });
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return countries.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout countryList_LYO_main;
        TextView countryEnglish;
        TextView countryNative;

        ViewHolder(View itemView) {
            super(itemView);
            countryList_LYO_main = itemView.findViewById(R.id.countryList_LYO_main);
            countryEnglish = itemView.findViewById(R.id.countryList_TXT_c1);
            countryNative = itemView.findViewById(R.id.countryList_TXT_c2);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Country getItem(int id) {
        return countries.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}