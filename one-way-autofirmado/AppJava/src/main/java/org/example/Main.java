package org.example;

import java.sql.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        Properties connConfig = new Properties();
        connConfig.setProperty("user", "root");
        connConfig.setProperty("password", "12345Abcde");
        connConfig.setProperty("sslMode", "verify-full");
        connConfig.setProperty("serverSslCert", "/certs/server-cert.pem");

        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://mariadb/test", connConfig)) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();

            System.out.println();
            System.out.println("--- Datos de conexión ------------------------------------------");
            System.out.printf("Base de datos: %s%n", databaseMetaData.getDatabaseProductName());
            System.out.printf("  Versión: %s%n", databaseMetaData.getDatabaseProductVersion());
            System.out.printf("Driver: %s%n", databaseMetaData.getDriverName());
            System.out.printf("  Versión: %s%n", databaseMetaData.getDriverVersion());
            System.out.println("----------------------------------------------------------------");
            System.out.println();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
