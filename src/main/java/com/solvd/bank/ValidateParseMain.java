package com.solvd.bank;

import com.solvd.bank.domain.*;
import com.solvd.bank.utils.xmlutils.DomParser;
import com.solvd.bank.utils.xmlutils.XMLValidator;

public class ValidateParseMain {
    public static void main(String[] args){

            new XMLValidator<>(Country.class).validate();
            new DomParser<>(Country.class).parse();

            new XMLValidator<>(City.class).validate();
            new DomParser<City>(City.class).parse();

            new XMLValidator<>(Location.class).validate();
            new DomParser<Location>(Location.class).parse();

            new XMLValidator<>(Associate.class).validate();
            new DomParser<Associate>(Associate.class).parse();

            new XMLValidator<>(Position.class).validate();
            new DomParser<Position>(Position.class).parse();
    }
}
