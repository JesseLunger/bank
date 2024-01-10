package com.solvd.bank.utils.xmlutils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;

public class XMLValidator<T> {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final Class<T> targetType;

    public XMLValidator(Class<T> targetType) {
        this.targetType = targetType;
    }

    public boolean validate() {
        File xsdFile = new File(System.getProperty("user.dir")
                + "/src/main/resources/xsdschemas/"
                + targetType.getSimpleName()
                + ".xsd"
        );
        File xmlFile = new File(System.getProperty("user.dir")
                + "/src/main/resources/xmlclasses/"
                + targetType.getSimpleName()
                + ".xml");
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlFile));
            return true;
        } catch (SAXException | IOException e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }
}
