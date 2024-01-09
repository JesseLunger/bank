package com.solvd.bank.utils.jacksonutils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;

public class JacksonUtil<T> {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> String writeJson(String filePath, T targetClass) {
        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            File file = new File(filePath + targetClass.getClass().getSimpleName().toLowerCase() + ".json");
            String json = objectMapper.writeValueAsString(targetClass);
            FileUtils.write(file, json);
            return json;
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
            return null;
        }
    }

    public static <T> T readJson(String filePath, T targetClass) {
        try {
            File file = new File(filePath + targetClass.getClass().getSimpleName().toLowerCase() + ".json");
            return (T) objectMapper.readValue(file, targetClass.getClass());
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
            return null;
        }
    }
}
