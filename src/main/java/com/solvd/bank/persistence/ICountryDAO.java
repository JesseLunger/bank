package com.solvd.bank.persistence;

import com.solvd.bank.domain.Country;
import com.solvd.bank.domain.Location;

import java.util.ArrayList;

public interface ICountryDAO extends IBaseDAO<Country> {

    ArrayList<Location> getAllLocationsByCountry(Country country);

}
