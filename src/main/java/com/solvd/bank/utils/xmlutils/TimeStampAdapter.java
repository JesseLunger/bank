package com.solvd.bank.utils.xmlutils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.sql.Timestamp;

public class TimeStampAdapter extends XmlAdapter<Long, Timestamp> {

    @Override
    public Timestamp unmarshal(Long value) {
        return new Timestamp(value);
    }

    @Override
    public Long marshal(Timestamp value) {
        return value.getTime();
    }
}
