package com.solvd.bank.utils.tests;

import com.solvd.bank.domain.City;
import com.solvd.bank.domain.Country;
import com.solvd.bank.service.CountriesService;
import com.solvd.bank.utils.patternsutil.CountryController;
import com.solvd.bank.utils.patternsutil.CountryView;
import com.solvd.bank.utils.patternsutil.ExampleListeners;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

public class PatternImpl {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {

        //Example of Builder implementation
        Country country = Country.builder()
                .setId(1)
                .setName("exampleCountry")
                .build();

        LOGGER.info(country);

        //Example of Listener Implementation
        City city = new City();
        city.setCountry(country);
        city.setName("exampleCity");
        city.setId(1);

        ExampleListeners exampleListeners = new ExampleListeners();
        exampleListeners.subscribe(country);
        exampleListeners.subscribe(city);
        exampleListeners.notifyListeners("notifying all listeners!");

        //Example of Controller Implementation
        Country model = new CountriesService().getCountryById(1);
        CountryView countryView = new CountryView();
        CountryController countryController = new CountryController(model, countryView);
        countryController.updateCountryView();
        countryController.setCountryId(4);
        countryController.setCountryName("changedName");
        countryController.updateCountryView();
        LOGGER.info(country);
    }
}
