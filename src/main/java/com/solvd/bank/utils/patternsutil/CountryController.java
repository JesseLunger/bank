package com.solvd.bank.utils.patternsutil;

import com.solvd.bank.domain.Country;

public class CountryController {

    private Country country;
    private CountryView countryView;

    public CountryController(Country country, CountryView countryView) {
        this.country = country;
        this.countryView = countryView;
    }

    public int getCountryId() {
        return country.getId();
    }

    public void setCountryId(int id) {
        country.setId(id);
    }

    public String getCountryName() {
        return country.getName();
    }

    public void setCountryName(String name) {
        country.setName(name);
    }

    public void updateCountryView() {
        countryView.printCountryDetails(country.getName(), country.getId());
    }
}

