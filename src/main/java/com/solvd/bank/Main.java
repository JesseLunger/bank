package com.solvd.bank;

import com.solvd.bank.domain.City;
import com.solvd.bank.domain.Country;
import com.solvd.bank.persistence.mybatisImpl.MBCityDAO;
import com.solvd.bank.persistence.mybatisImpl.MBCountryDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {

        MBCountryDAO countriesJdbc = new MBCountryDAO();
        Country testCountry = new Country();
        testCountry.setName("testCountry");
        countriesJdbc.saveEntity(testCountry);
        System.out.println(testCountry);
        testCountry.setName("nameChange");
        countriesJdbc.updateEntity(testCountry);
        countriesJdbc.removeEntityById(testCountry.getId());
        ArrayList<Country> countries = new ArrayList<>(countriesJdbc.getAll());
        Country getCountry = countriesJdbc.getEntityById(countries.get(0).getId());

        MBCityDAO citiesJdbc = new MBCityDAO();
        City testCity = new City();
        testCity.setCountry(getCountry);
        testCity.setName("testCity");
        citiesJdbc.saveEntity(testCity);
        System.out.println(testCity);

//        testCity.setName("nameChange");
//        citiesJdbc.updateEntity(testCity);
//        citiesJdbc.removeEntityById(testCity.getId());
//        ArrayList<City> cities = new ArrayList<>(citiesJdbc.getAll());
//        City getCity = citiesJdbc.getEntityById(cities.get(0).getId());
//
//        LocationDAO locationsJdbc = new LocationDAO();
//        Location testLocation = new Location();
//        testLocation.setCity(getCity);
//        testLocation.setZipCode("12345");
//        testLocation.setAddress("Test Address");
//        locationsJdbc.saveEntity(testLocation);
//        testLocation.setZipCode("54321");
//        locationsJdbc.updateEntity(testLocation);
//        locationsJdbc.removeEntityByID(testLocation.getId());
//        ArrayList<Location> locations = new ArrayList<>(locationsJdbc.getAll());
//        Location getLocation = locationsJdbc.getEntityById(locations.get(0).getId());
//
//        BranchDAO branchesJdbc = new BranchDAO();
//        Branch testBranch = new Branch();
//        testBranch.setLocation(getLocation);
//        testBranch.setBranchName("Test Branch");
//        branchesJdbc.saveEntity(testBranch);
//        testBranch.setBranchName("Updated Branch");
//        branchesJdbc.updateEntity(testBranch);
//        locationsJdbc.removeEntityByID(testLocation.getId());
//        ArrayList<Branch> allBranches = new ArrayList<>(branchesJdbc.getAll());
//        Branch getBranch = branchesJdbc.getEntityById(allBranches.get(0).getId());
//
//        AssociateDAO associatesJdbc = new AssociateDAO();
//        Associate testAssociate = new Associate();
//        testAssociate.setLocation(getLocation);
//        testAssociate.setPrimaryName("John Doe");
//        testAssociate.setSecondaryName("Doe");
//        testAssociate.setDateJoined(new Timestamp(System.currentTimeMillis()));
//        testAssociate.setEmail("john.doe@example.com");
//        testAssociate.setPhoneNumber("123-456-7890");
//        associatesJdbc.saveEntity(testAssociate);
//        testAssociate.setPrimaryName("Updated Name");
//        associatesJdbc.updateEntity(testAssociate);
//        ArrayList<Associate> associatesList = new ArrayList<>(associatesJdbc.getAll());
//        associatesJdbc.removeEntityByID(testAssociate.getId());
//        Associate getAssociate = associatesJdbc.getEntityById(associatesList.get(0).getId());
//
//        PositionDAO positionsJdbc = new PositionDAO();
//        Position testPosition = new Position();
//        testPosition.setPosition("Test Position");
//        positionsJdbc.saveEntity(testPosition);
//        testPosition.setPosition("Updated Position");
//        positionsJdbc.updateEntity(testPosition);
//        positionsJdbc.removeEntityByID(testPosition.getId());
//        ArrayList<Position> positionsList = new ArrayList<>(positionsJdbc.getAll());
//        Position getPosition = positionsJdbc.getEntityById(positionsList.get(0).getId());
//
//        StaffDAO staffJdbc = new StaffDAO();
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


    }

}

