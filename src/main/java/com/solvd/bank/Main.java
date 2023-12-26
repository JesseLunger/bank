package com.solvd.bank;

import com.solvd.bank.domain.*;
import com.solvd.bank.persistence.impl.*;
import com.solvd.bank.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {
        ConnectionPool.initializePool();
        CountryDAO countriesJdbc = new CountryDAO();
        Country testCountry = new Country();
        testCountry.setName("testCountry");
        countriesJdbc.saveEntity(testCountry);
        testCountry.setName("nameChange");
        countriesJdbc.updateEntity(testCountry);
        countriesJdbc.removeEntityByID(testCountry.getId());
        ArrayList<Country> countries = new ArrayList<>(countriesJdbc.getAll());
        Country nextKey = countriesJdbc.getEntityById(countries.get(0).getId());

//        CitiesJdbcImpl citiesJdbc = new CitiesJdbcImpl();
//        Cities testCity = new Cities();
//        testCity.setCountry(nextKey);
//        testCity.setName("testCity");
//        citiesJdbc.saveEntity(testCity);
//        testCity.setName("nameChange");
//        citiesJdbc.updateEntity(testCity);
//        citiesJdbc.removeEntityByID(testCity.getId());
//        ArrayList<Cities> cities = new ArrayList<>(citiesJdbc.getAll());
//        Cities getCity = citiesJdbc.getEntityById(cities.get(0).getId());
//
//        LocationsJdbcImpl locationsJdbc = new LocationsJdbcImpl();
//        Locations testLocation = new Locations();
//        testLocation.setCity(getCity);
//        testLocation.setZipCode("12345");
//        testLocation.setAddress("Test Address");
//        locationsJdbc.saveEntity(testLocation);
//        testLocation.setZipCode("54321");
//        locationsJdbc.updateEntity(testLocation);
//        locationsJdbc.removeEntityByID(testLocation.getId());
//        ArrayList<Locations> locations = new ArrayList<>(locationsJdbc.getAll());
//        Locations getLocation = locationsJdbc.getEntityById(locations.get(0).getId());
//
//        BranchesJdbcImpl branchesJdbc = new BranchesJdbcImpl();
//        Branches testBranch = new Branches();
//        testBranch.setLocation(getLocation);
//        testBranch.setBranchName("Test Branch");
//        branchesJdbc.saveEntity(testBranch);
//        testBranch.setBranchName("Updated Branch");
//        branchesJdbc.updateEntity(testBranch);
//        locationsJdbc.removeEntityByID(testLocation.getId());
//        ArrayList<Branches> allBranches = new ArrayList<>(branchesJdbc.getAll());
//        Branches getBranch = branchesJdbc.getEntityById(allBranches.get(0).getId());
//
//        AssociatesJdbcImpl associatesJdbc = new AssociatesJdbcImpl();
//        Associates testAssociate = new Associates();
//        testAssociate.setLocation(getLocation);
//        testAssociate.setPrimaryName("John Doe");
//        testAssociate.setSecondaryName("Doe");
//        testAssociate.setDateJoined(new Timestamp(System.currentTimeMillis()));
//        testAssociate.setEmail("john.doe@example.com");
//        testAssociate.setPhoneNumber("123-456-7890");
//        associatesJdbc.saveEntity(testAssociate);
//        testAssociate.setPrimaryName("Updated Name");
//        associatesJdbc.updateEntity(testAssociate);
//        ArrayList<Associates> associatesList = new ArrayList<>(associatesJdbc.getAll());
//        associatesJdbc.removeEntityByID(testAssociate.getId());
//        Associates getAssociate = associatesJdbc.getEntityById(associatesList.get(0).getId());
//
//        PositionsJdbcImpl positionsJdbc = new PositionsJdbcImpl();
//        Positions testPosition = new Positions();
//        testPosition.setPosition("Test Position");
//        positionsJdbc.saveEntity(testPosition);
//        testPosition.setPosition("Updated Position");
//        positionsJdbc.updateEntity(testPosition);
//        positionsJdbc.removeEntityByID(testPosition.getId());
//        ArrayList<Positions> positionsList = new ArrayList<>(positionsJdbc.getAll());
//        Positions getPosition = positionsJdbc.getEntityById(positionsList.get(0).getId());
//
//        StaffJdbcImpl staffJdbc = new StaffJdbcImpl();
//        Staff testStaff = new Staff();
//        testStaff.setAssociate(associatesList.get(6));
//        testStaff.setPosition(getPosition);
//        testStaff.setDateHired(new Timestamp(System.currentTimeMillis()));
//        staffJdbc.saveEntity(testStaff);
//        testStaff.setDateHired(new Timestamp(System.currentTimeMillis() - 1000000));
//        staffJdbc.updateEntity(testStaff);
//        staffJdbc.removeEntityByID(testStaff.getAssociate().getId());
//        ArrayList<Staff> staffList = new ArrayList<>(staffJdbc.getAll());
//        Staff getStaff = staffJdbc.getEntityById(staffList.get(0).getAssociate().getId());
//











//
//
//        Countries countries = countriesJdbc.getEntityById(1);
//        countries.
//        countriesJdbc.saveEntity(countries1);

        //        countries.setName("new foundLan");
//        countriesJdbc.updateEntity(countries);
//
//        System.out.println(countries.getName());

//        countriesJdbc.removeEntityByID(1);
    }

}

