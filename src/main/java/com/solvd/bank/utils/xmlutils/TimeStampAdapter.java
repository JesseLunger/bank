package com.solvd.bank.utils.xmlutils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.lang.invoke.MethodHandles;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimeStampAdapter extends XmlAdapter<String, Timestamp> {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final String FORMAT_STRING = "MM-dd-yyyy HH:mm a z";

    @Override
    public Timestamp unmarshal(String value) {
        try {
            return new Timestamp(new SimpleDateFormat(FORMAT_STRING).parse(value).getTime());
        } catch (ParseException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public String marshal(Timestamp value) {
        return new SimpleDateFormat(FORMAT_STRING).format(value);
    }
}
