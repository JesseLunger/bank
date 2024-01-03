package com.solvd.bank;

import com.solvd.bank.domain.*;
import com.solvd.bank.service.mybatis.CountriesService;
import com.solvd.bank.utils.xmlutils.DomParser;
import com.solvd.bank.utils.xmlutils.JAXBMarshaller;
import com.solvd.bank.utils.xmlutils.XMLValidator;
import org.w3c.dom.Document;

import java.sql.Timestamp;

public class JAXBMain {
    public static void main(String[] args){

            Country country = new Country();
            country.setId(1);
            country.setName("ExampleCountry");
            new JAXBMarshaller<>(country).marshall();
            new XMLValidator<>(Country.class).validate();
            new DomParser<>(Country.class).parse();

            City city = new City();
            city.setId(1);
            city.setName("ExampleCity");
            city.setCountry(country);
            new JAXBMarshaller<>(city).marshall();
            new XMLValidator<>(City.class).validate();
            new DomParser<City>(City.class).parse();

            Location location = new Location();
            location.setId(1);
            location.setCity(city);
            location.setZipCode("1234");
            location.setAddress("ExampleAddress");
            new JAXBMarshaller<>(location).marshall();
            new XMLValidator<>(Location.class).validate();
            new DomParser<Location>(Location.class).parse();

            Associate associate = new Associate();
            associate.setId(1);
            associate.setLocation(location);
            associate.setPrimaryName("Example");
            associate.setSecondaryName("Associate");
            associate.setEmail("example@examplemail.com");
            associate.setDateJoined(new Timestamp(System.currentTimeMillis()));
            associate.setPhoneNumber("543-342-2342");
            new JAXBMarshaller<>(associate).marshall();
            new XMLValidator<>(Associate.class).validate();
            new DomParser<Associate>(Associate.class).parse();

            Position position = new Position();
            position.setId(1);
            position.setSalary(70000);
            position.setPosition("ExamplePosition");
            new JAXBMarshaller<>(position).marshall();
            new XMLValidator<>(Position.class).validate();
            new DomParser<Position>(Position.class).parse();
    }
}
