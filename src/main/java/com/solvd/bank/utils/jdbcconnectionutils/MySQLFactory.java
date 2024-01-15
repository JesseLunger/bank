package com.solvd.bank.utils.jdbcconnectionutils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.lang.invoke.MethodHandles;
import java.sql.Connection;

public class MySQLFactory {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final SqlSessionFactory sqlSessionFactory;

    private static String useCase;

    static {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            LOGGER.error("Error reading mybatis-config.xml", e);
            throw new ExceptionInInitializerError("Unable to initialize MyBatis SQLSessionFactory");
        }
    }

    private MySQLFactory(String useCase) {

    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public static Connection getConnection() throws InterruptedException {
        return ConnectionPool.getConnection();
    }
}
