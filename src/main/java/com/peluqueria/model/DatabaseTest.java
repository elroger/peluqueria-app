package com.peluqueria.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Esta clase se utiliza para realizar pruebas de conexión y consultas a la base de datos.
 */
public class DatabaseTest {

    public static void main(String[] args) {
        String databasePath = "jdbc:mysql://localhost:3306/database";
        String username = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(databasePath, username, password)) {
            System.out.println("Conexión exitosa a la base de datos!");

            // Realiza una consulta
            String query = "SELECT * FROM bookings";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                // Itera sobre los resultados de la consulta
                while (resultSet.next()) {
                    // Obtén los valores de cada columna
                    int id = resultSet.getInt("id");
                    String nombre = resultSet.getString("nombre");
                    int edad = resultSet.getInt("edad");

                    // Haz algo con los valores obtenidos
                    System.out.println("ID: " + id);
                    System.out.println("Nombre: " + nombre);
                    System.out.println("Edad: " + edad);
                    System.out.println("-----------------------");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
