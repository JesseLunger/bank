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

@XmlRootElement(name = "country")
@XmlType(propOrder = {"id", "name"})
@JsonRootName("country")
public class Country implements ExampleListener {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    public static Builder builder() {
        return new Builder(new Country());
    }

    @Override
    public void onEvent(String message) {
        LOGGER.info(this.getName() + " has received message: " + message);
    }

    public int getId() {
        return id;
    }

    @XmlElement(name = "id")
    public void setId(int id) {
        this.id = id;
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
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static class Builder {
        private Country country;

        public Builder(Country country) {
            this.country = country;
        }

        public Builder setId(int id) {
            this.country.setId(id);
            return this;
        }

        public Builder setName(String name) {
            this.country.setName(name);
            return this;
        }

        public Country build() {
            return country;
        }
    }
}
