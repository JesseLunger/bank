package com.solvd.bank.utils.patternsutil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

public class CountryView {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public void printCountryDetails(String countryName, int countryId) {
        LOGGER.info("CountryName: " + countryName + ", ID: " + countryId);
    }
}
