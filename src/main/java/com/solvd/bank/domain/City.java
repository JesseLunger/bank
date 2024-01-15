package com.solvd.bank.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.solvd.bank.utils.patternsutil.ExampleListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.lang.invoke.MethodHandles;

@JsonRootName("city")
@XmlRootElement(name = "city")
@XmlType(propOrder = {"id", "country", "name"})
public class City  implements ExampleListener {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void onEvent(String message) {
        LOGGER.info(this.getName() + " has received message: " + message);
    }

    @JsonProperty("id")
    private int id;

    @JsonProperty("country")
    private Country country;

    @JsonProperty("name")
    private String name;

    public int getId() {
        return id;
    }

    @XmlElement(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    @XmlElement(name = "country")
    public void setCountry(Country country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", country=" + country +
                ", name='" + name + '\'' +
                '}';
    }
}
