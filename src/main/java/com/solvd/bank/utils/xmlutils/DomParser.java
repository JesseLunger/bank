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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Timestamp;
import java.util.HashMap;


public class DomParser<T> {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private Class<T> targetType;
    private StringBuilder parsedFile;

    public DomParser(Class<T> targetType) {
        this.targetType = targetType;
        this.parsedFile = new StringBuilder();
    }

    public T parse() {
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
                T returnedClass = traverseDocument(document);
                LOGGER.info(parsedFile.toString());
                return returnedClass;
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    private T traverseDocument(Document document) {
        parsedFile.append("\nroot: ").append(document.getDocumentElement().getNodeName()).append(System.lineSeparator());
        return (T) traverseNodeList(document.getDocumentElement().getChildNodes(), 1, targetType);
    }

    private Object getClassInstance(Class<?> targetType) {
        try {
            return targetType.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    private Object traverseNodeList(NodeList nodeList, int indentation, Class<?> targetType) {
        if (targetType.isPrimitive() || targetType.equals(String.class) || targetType.equals(Timestamp.class)) {
            return null;
        }
        Object classInstance = getClassInstance(targetType);
        HashMap<String, Method> methodMap = getSetters(targetType);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                printNodeInfo(node, indentation);
                Method setter = methodMap.get("set" + node.getNodeName().toLowerCase());
                try {
                    if (node.hasChildNodes()) {
                        Object childObject = traverseNodeList(node.getChildNodes(), indentation + 1, setter.getParameterTypes()[0]);
                        if (childObject != null) {
                            setter.invoke(classInstance, childObject);
                        } else if (node.getFirstChild().getNodeType() == Node.TEXT_NODE) {
                            setter.invoke(classInstance, convertToParameterType(setter.getParameterTypes()[0], node.getTextContent()));
                        }
                    }

                } catch (IllegalAccessException | InvocationTargetException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        }
        return classInstance;
    }


    private void printNodeInfo(Node node, int indentation) {
        parsedFile.append("  ".repeat(Math.max(0, indentation)));
        parsedFile.append(node.getNodeName());
        if (node.getFirstChild().getNodeType() == Node.TEXT_NODE) {
            parsedFile.append(": ").append(node.getFirstChild().getTextContent().trim());
        }
        parsedFile.append(System.lineSeparator());
    }

    private Object convertToParameterType(Class<?> paramType, String value) {
        value = value.trim();
        if (String.class.equals(paramType)) {
            return value;
        } else if (Integer.class.equals(paramType) || int.class.equals(paramType)) {
            return Integer.parseInt(value);
        } else if (Double.class.equals(paramType) || double.class.equals(paramType)) {
            return Double.parseDouble(value);
        } else if (Timestamp.class.equals(paramType)) {
            long millis = Long.parseLong(value);
            return new Timestamp(millis);
        } else {
            return null;
        }
    }

    private HashMap<String, Method> getSetters(Class<?> targetType) {
        HashMap<String, Method> methodMap = new HashMap<>();
        Method[] methods = targetType.getMethods();
        for (Method method : methods) {
            if (method.getName().startsWith("set")
                    && (method.getParameterCount() == 1)
                    && Modifier.isPublic(method.getModifiers())) {
                methodMap.put(method.getName().toLowerCase(), method);
            }
        }
        return methodMap;
    }
}
