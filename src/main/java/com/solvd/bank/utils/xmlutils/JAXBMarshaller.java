package com.solvd.bank.utils.xmlutils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.lang.invoke.MethodHandles;

public class JAXBMarshaller<T> {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final T classToConvert;
    private final File xmlFile;

    public JAXBMarshaller(T classToConvert) {
        this.classToConvert = classToConvert;
        this.xmlFile = new File(System.getProperty("user.dir")
                + "/src/main/resources/xmlclasses/"
                + classToConvert.getClass().getSimpleName()
                + ".xml");
    }

    public void marshall() {
        try {
            JAXBContext context = JAXBContext.newInstance(classToConvert.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(classToConvert, xmlFile);
        } catch (JAXBException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public T unmarshall() {
        try {
            Class<T> targetType = (Class<T>) classToConvert.getClass();
            JAXBContext context = JAXBContext.newInstance(targetType);
            Unmarshaller unmarshaller = context.createUnmarshaller();
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
