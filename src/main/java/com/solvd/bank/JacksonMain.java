package com.solvd.bank;

import com.solvd.bank.domain.Associate;
import com.solvd.bank.domain.City;
import com.solvd.bank.domain.Country;
import com.solvd.bank.domain.Location;
import com.solvd.bank.utils.jacksonutils.JacksonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.Timestamp;

public class JacksonMain {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {

        String filePath = System.getProperty("user.dir") + "/src/main/resources/jsonclasses/";

        Country country = new Country();
        country.setId(1);
        country.setName("ExampleCountry");
        String countryJson = JacksonUtil.writeJson(filePath, country);
        Country newCountry = JacksonUtil.readJson(filePath, country);
        assert (country.getName().equals(newCountry.getName()));

        City city = new City();
        city.setId(1);
        city.setName("ExampleCity");
        city.setCountry(country);
        String cityJson = JacksonUtil.writeJson(filePath, city);
        City newCity = JacksonUtil.readJson(filePath, city);
        assert (city.getName().equals(newCity.getName()));

        Location location = new Location();
        location.setCity(city);
        location.setAddress("123 example st");
        location.setZipCode("11111");
        location.setId(1);

        Associate associate = new Associate();
        associate.setPhoneNumber("123-456-7890");
        associate.setLocation(location);
        associate.setId(1);
        associate.setPrimaryName("example");
        associate.setSecondaryName("person");
        associate.setEmail("exampleMail.com");
        associate.setDateJoined(new Timestamp(System.currentTimeMillis()));
        JacksonUtil.writeJson(filePath, associate);
        Associate newAssociate = JacksonUtil.readJson(filePath, associate);
        assert (associate.getDateJoined().equals(newAssociate.getDateJoined()));
    }
}
