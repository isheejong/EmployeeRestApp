/* Copyright © 2016 Oracle and/or its affiliates. All rights reserved. */

package com.developer.db;

//import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.util.Optional;

import com.mysql.jdbc.Connection;

public class DBConnectionMysql {

    private static final String URL = "jdbc:mysql://";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    //Environment Variable Cloud
    //public static final Optional<String> DBAAS_USERNAME = Optional.ofNullable(System.getenv("MYSQLCS_USER_NAME"));
    //public static final Optional<String> DBAAS_PASSWORD = Optional.ofNullable(System.getenv("MYSQLCS_USER_PASSWORD"));
    //public static final Optional<String> DBAAS_DEFAULT_CONNECT_DESCRIPTOR = Optional.ofNullable(System.getenv("MYSQLCS_CONNECT_STRING"));
    
    //public static final String DBAAS_USERNAME = System.getenv("MYSQL_ENV_MYSQL_USER");
    public static final String DBAAS_USERNAME = "root";
    //public static final String DBAAS_PASSWORD = System.getenv("MYSQL_ENV_MYSQL_ROOT_PASSWORD");
    public static final String DBAAS_PASSWORD = "Ora_cle123";
    //public static final String OCCS_DEFAULT_CONNECT_DESCRIPTOR = System.getenv("MYSQL_PORT_3306_TCP_ADDR") + ":3306/EMPLOYEES";
    public static final String OCCS_DEFAULT_CONNECT_DESCRIPTOR = "129.144.12.159" + ":3306/mydatabase";
    
    //Local settings        
    //public static final String LOCAL_USERNAME = "root";
    //public static final String LOCAL_PASSWORD = "oracle";
    //public static final String LOCAL_DEFAULT_CONNECT_DESCRIPTOR = "localhost:3306/employees";

    private static Connection connection = null;
    private static DBConnectionMysql instance = null;

    private DBConnectionMysql() {
        try {
            Class.forName(DRIVER).newInstance();
        } catch (Exception sql) {
            System.out.println(sql.getStackTrace());
        }
    }

    public static DBConnectionMysql getInstance() {
        if (connection == null) {
            instance = new DBConnectionMysql();
        }
        return instance;
    }

    public Connection getConnection() {
        if (connection == null) {
            try {     
            	//System.out.println("#### OCCS_DEFAULT_CONNECT_DESCRIPTOR = " +OCCS_DEFAULT_CONNECT_DESCRIPTOR);
        		//connection = (Connection)DriverManager.getConnection(URL + DBAAS_DEFAULT_CONNECT_DESCRIPTOR.orElse(LOCAL_DEFAULT_CONNECT_DESCRIPTOR),
        		//		DBAAS_USERNAME.orElse(LOCAL_USERNAME), DBAAS_PASSWORD.orElse(LOCAL_PASSWORD));
        		connection = (Connection)DriverManager.getConnection(URL + OCCS_DEFAULT_CONNECT_DESCRIPTOR, DBAAS_USERNAME, DBAAS_PASSWORD);
            } catch (SQLException e) {
                e.getMessage();
            }
        }
        return connection;
    }
}
