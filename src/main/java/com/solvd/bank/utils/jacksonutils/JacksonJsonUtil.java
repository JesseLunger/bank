package com.solvd.bank.utils.jacksonutils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.solvd.bank.domain.Country;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.io.FileUtils;


public class JacksonJsonUtil<T> {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    String filePath;
    T targetClass;
    ObjectMapper objectMapper = new ObjectMapper();


    public JacksonJsonUtil(T targetClass){
         this.filePath = System.getProperty("user.dir")
                + "/src/main/resources/jsonclasses/"
                + targetClass.getClass().getSimpleName()
                + ".json";
         this.targetClass = targetClass;
         this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }
    public void writeToFileAsJson(){
        try{
            String strJson = objectMapper.writeValueAsString(targetClass);
            FileUtils.write(new File(filePath), strJson);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public T readJson() {
        try {
            return (T)objectMapper.readValue(new File(filePath), targetClass.getClass());
        } catch (IOException e) {
            throw new RuntimeException("Error reading JSON from file", e);
        }
    }
}
