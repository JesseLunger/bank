package com.solvd.bank.utils.xmlutils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;

public class DomParser<T> {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private Class<T> targetType;

    private StringBuilder parsedFile;

    public DomParser(Class<T> targetType) {
        this.targetType = targetType;
        this.parsedFile = new StringBuilder();
    }

    public void parse() {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        File xmlFile = new File(System.getProperty("user.dir")
                + "/src/main/resources/xmlclasses/"
                + targetType.getSimpleName()
                + ".xml"
        );
        try {
            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            if (document != null) {
                traverseDocument(document);
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.info(parsedFile.toString());
    }

    private void traverseDocument(Document document) {
        parsedFile.append("\nroot: ").append(document.getDocumentElement().getNodeName()).append(System.lineSeparator());
        traverseNodeList(document.getDocumentElement().getChildNodes(), 1);
    }

    private void traverseNodeList(NodeList nodeList, int indentation) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                printNodeInfo(node, indentation);
                if (node.hasChildNodes()) {
                    traverseNodeList(node.getChildNodes(), indentation + 1);
                }
            }
        }
    }

    private void printNodeInfo(Node node, int indentation) {
        for (int i = 0; i < indentation; i++) {
            parsedFile.append("  ");
        }
        parsedFile.append(node.getNodeName());
        if (node.hasChildNodes() && node.getFirstChild().getNodeType() == Node.TEXT_NODE) {
            parsedFile.append(": ").append(node.getFirstChild().getTextContent().trim());
        }

        parsedFile.append(System.lineSeparator());
    }
}
