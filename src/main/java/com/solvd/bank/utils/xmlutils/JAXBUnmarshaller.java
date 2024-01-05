package com.solvd.bank.utils.xmlutils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.lang.invoke.MethodHandles;


public class JAXBUnmarshaller<T> {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private Class<T> targetType;

    public JAXBUnmarshaller(Class<T> targetType) {
        this.targetType = targetType;
    }

    public T unmarshall() {
        try {
            JAXBContext context = JAXBContext.newInstance(targetType);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            File xmlFile = new File(System.getProperty("user.dir")
                    + "/src/main/resources/xmlclasses/"
                    + targetType.getSimpleName()
                    + ".xml"
            );

            Object unmarshalledObject = unmarshaller.unmarshal(xmlFile);

            if (targetType.isInstance(unmarshalledObject)) {
                return targetType.cast(unmarshalledObject);
            }
        } catch (JAXBException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }
}

