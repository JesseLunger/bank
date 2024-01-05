package com.solvd.bank.utils.xmlutils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.lang.invoke.MethodHandles;

public class JAXBMarshaller<T> {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private T classToConvert;

    public JAXBMarshaller(T classToConvert) {
        this.classToConvert = classToConvert;
    }

    public void marshall() {
        try {
            JAXBContext context = JAXBContext.newInstance(classToConvert.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(classToConvert,
                    new File(System.getProperty("user.dir")
                            + "/src/main/resources/xmlclasses/"
                            + classToConvert.getClass().getSimpleName()
                            + ".xml"));
        } catch (JAXBException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
