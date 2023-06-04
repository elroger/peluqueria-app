package com.peluqueria.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Esta clase se utiliza para establecer la conexión con la base de datos.
 */
public class DatabaseConnector {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/database";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    /**
     * Obtiene una conexión a la base de datos.
     *
     * @return la conexión establecida
     * @throws SQLException si ocurre un error al establecer la conexión
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
    }
}
