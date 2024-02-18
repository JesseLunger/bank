package com.solvd.bank.utils.xmlutils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.lang.invoke.MethodHandles;

public class JAXBUtil<T> {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static <T> void marshall(String filePath, T targetClass) {
        try {
            File xmlFile = new File(filePath + targetClass.getClass().getSimpleName().toLowerCase() + ".json");
            JAXBContext context = JAXBContext.newInstance(targetClass.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(targetClass, xmlFile);
        } catch (JAXBException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static <T> T unmarshall(String filePath, T targetClass) {
        try {
            File xmlFile = new File(filePath + targetClass.getClass().getSimpleName().toLowerCase() + ".json");
            Class<T> targetType = (Class<T>) targetClass.getClass();
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
