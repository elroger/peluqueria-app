package com.peluqueria.model;

import java.sql.*;
import java.util.Scanner;


public class AppSQLite {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Crear la tabla "bookings"
        createBookingsTable();

        // Menú de opciones
        int option = 0;
        do {
            System.out.println("Menú:");
            System.out.println("1. Consultar todas las citas");
            System.out.println("2. Ver nombres de clientes con cita");
            System.out.println("3. Eliminar una cita por nombre");
            System.out.println("4. Borrar todas las citas");
            System.out.println("5. Introducir datos de prueba");
            System.out.println("6. Crear una cita");
            System.out.println("7. Buscar clientes por nombre");
            System.out.println("8. Salir");
            System.out.print("Ingresa una opción: ");

            String input = scanner.nextLine();
            if (!input.matches("\\d+")) {
                System.out.println("Opción incorrecta. Intenta de nuevo.");
                continue;
            }

            option = Integer.parseInt(input);

            switch (option) {
                case 1:
                    // Obtener todas las citas de la tabla
                    System.out.println("Citas registradas:");
                    getAllBookings();
                    break;
                case 2:
                    // Ver nombres de clientes con cita
                    System.out.println("Nombres de clientes con cita:");
                    showBookingNames();
                    break;
                case 3:
                    // Eliminar una cita por nombre
                    System.out.print("Ingresa las tres primeras letras del nombre del cliente que quieres eliminar: ");
                    String prefix = scanner.nextLine();
                    deleteBookingByPrefix(prefix);
                    break;
                case 4:
                    // Borrar todas las citas
                    deleteAllBookings();
                    break;
                case 5:
                    // Introducir datos de prueba
                    insertSampleData();
                    break;
                case 6:
                    // Crear una cita
                    createBooking();
                    break;
                case 7:
                    // Buscar clientes por nombre
                    System.out.print("Ingresa las tres primeras letras del nombre del cliente que quieres buscar: ");
                    String searchPrefix = scanner.nextLine();
                    searchClientsByName(searchPrefix);
                    break;
                case 8:
                    // Salir del programa
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo.");
                    break;
            }
        } while (option != 8);

        scanner.close();
    }

    private static void createBookingsTable() {
        String databasePath = "jdbc:sqlite:database.db";
        try (Connection connection = DriverManager.getConnection(databasePath);
             Statement statement = connection.createStatement()) {

            String createTableSQL = "CREATE TABLE IF NOT EXISTS bookings ("
                    + "name TEXT, "
                    + "email TEXT, "
                    + "phone TEXT, "
                    + "date TEXT, "
                    + "time TEXT, "
                    + "service TEXT, "
                    + "hairdresser TEXT"
                    + ")";

            statement.executeUpdate(createTableSQL);
            System.out.println("Tabla 'bookings' creada correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertBooking(String name, String email, String phone, String date, String time, String service, String hairdresser) {
        String databasePath = "jdbc:sqlite:database.db";
        try (Connection connection = DriverManager.getConnection(databasePath);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO bookings (name, email, phone, date, time, service, hairdresser) VALUES (?, ?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, date);
            preparedStatement.setString(5, time);
            preparedStatement.setString(6, service);
            preparedStatement.setString(7, hairdresser);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Cita insertada correctamente.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getAllBookings() {
        String databasePath = "jdbc:sqlite:database.db";
        try (Connection connection = DriverManager.getConnection(databasePath);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM bookings")) {

            boolean hasBookings = false;
            while (resultSet.next()) {
                hasBookings = true;
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String date = resultSet.getString("date");
                String time = resultSet.getString("time");
                String service = resultSet.getString("service");
                String hairdresser = resultSet.getString("hairdresser");

                System.out.println("Nombre: " + name);
                System.out.println("Email: " + email);
                System.out.println("Teléfono: " + phone);
                System.out.println("Fecha: " + date);
                System.out.println("Hora: " + time);
                System.out.println("Servicio: " + service);
                System.out.println("Peluquero: " + hairdresser);
                System.out.println("------------------------");
            }

            if (!hasBookings) {
                System.out.println("No hay citas que mostrar.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteBookingByPrefix(String prefix) {
        String databasePath = "jdbc:sqlite:database.db";
        try (Connection connection = DriverManager.getConnection(databasePath);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM bookings WHERE name LIKE ?")) {

            preparedStatement.setString(1, prefix + "%");

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                System.out.print("Seguro que quieres eliminar la cita de " + name + "? (S/N): ");
                String confirmation = scanner.nextLine();

                if (confirmation.equalsIgnoreCase("S")) {
                    try (PreparedStatement deleteStatement = connection.prepareStatement(
                            "DELETE FROM bookings WHERE name LIKE ?")) {
                        deleteStatement.setString(1, prefix + "%");
                        int rowsDeleted = deleteStatement.executeUpdate();
                        if (rowsDeleted > 0) {
                            System.out.println("Cita eliminada correctamente.");
                        } else {
                            System.out.println("No se encontró ninguna cita con ese nombre.");
                        }
                    }
                } else {
                    System.out.println("Eliminación cancelada.");
                }
            } else {
                System.out.println("No se encontró ninguna cita con ese nombre.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteAllBookings() {
        String databasePath = "jdbc:sqlite:database.db";
        try (Connection connection = DriverManager.getConnection(databasePath);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate("DELETE FROM bookings");
            System.out.println("Todas las citas han sido eliminadas.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertSampleData() {
        int existingBookings = 0;
        int newBookings = 0;

        if (!checkIfBookingExists("Rafa Nadal", "rafa.nadal@gmail.com", "612345678", "25-05-2023", "10:00", "Corte de pelo", "Luis Alberto")) {
            insertBooking("Rafa Nadal", "rafa.nadal@gmail.com", "612345678", "25-05-2023", "10:00", "Corte de pelo", "Luis Alberto");
            newBookings++;
        } else {
            existingBookings++;
        }

        if (!checkIfBookingExists("Sergio Ramos", "sergio.ramos@yahoo.com", "645678901", "26-05-2023", "14:30", "Peinado", "Juan Carlos")) {
            insertBooking("Sergio Ramos", "sergio.ramos@yahoo.com", "645678901", "26-05-2023", "14:30", "Peinado", "Juan Carlos");
            newBookings++;
        } else {
            existingBookings++;
        }

        if (!checkIfBookingExists("Pau Gasol", "pau_gasol@hotmail.com", "678901234", "27-05-2023", "11:15", "Afeitado", "Ramón Antonio")) {
            insertBooking("Pau Gasol", "pau_gasol@hotmail.com", "678901234", "27-05-2023", "11:15", "Afeitado", "Ramón Antonio");
            newBookings++;
        } else {
            existingBookings++;
        }

        if (!checkIfBookingExists("Fernando Alonso", "fer_alonso@gmail.com", "623456789", "28-05-2023", "16:00", "Tratamiento capilar", "Antonio José")) {
            insertBooking("Fernando Alonso", "fer_alonso@gmail.com", "623456789", "28-05-2023", "16:00", "Tratamiento capilar", "Antonio José");
            newBookings++;
        } else {
            existingBookings++;
        }

        if (!checkIfBookingExists("Marc Márquez", "marc.marquez@yahoo.com", "654789012", "29-05-2023", "09:30", "Corte de barba", "Pedro Luis")) {
            insertBooking("Marc Márquez", "marc.marquez@yahoo.com", "654789012", "29-05-2023", "09:30", "Corte de barba", "Pedro Luis");
            newBookings++;
        } else {
            existingBookings++;
        }

        if (!checkIfBookingExists("Miguel Indurain", "miguel.indurain@hotmail.com", "687901234", "30-05-2023", "13:45", "Tinte de cabello", "Javier Ramírez")) {
            insertBooking("Miguel Indurain", "miguel.indurain@hotmail.com", "687901234", "30-05-2023", "13:45", "Tinte de cabello", "Javier Ramírez");
            newBookings++;
        } else {
            existingBookings++;
        }

        if (newBookings == 6) {
            System.out.println("Se han insertado las 6 citas de prueba correctamente.");
        } else if (newBookings > 0) {
            System.out.println("Se han introducido " + newBookings + " cita(s), porque ya existían " + existingBookings + " con los mismos datos.");
        } else {
            System.out.println("No se ha introducido ninguna cita de prueba, porque ya existían 6 con los mismos datos.");
        }
    }

    private static boolean checkIfBookingExists(String name, String email, String phone, String date, String time, String service, String hairdresser) {
        String databasePath = "jdbc:sqlite:database.db";
        try (Connection connection = DriverManager.getConnection(databasePath);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM bookings WHERE name = ? AND email = ? AND phone = ? AND date = ? AND time = ? AND service = ? AND hairdresser = ?")) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, date);
            preparedStatement.setString(5, time);
            preparedStatement.setString(6, service);
            preparedStatement.setString(7, hairdresser);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void showBookingNames() {
        String databasePath = "jdbc:sqlite:database.db";
        try (Connection connection = DriverManager.getConnection(databasePath);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT name FROM bookings")) {

            boolean hasBookings = false;
            while (resultSet.next()) {
                hasBookings = true;
                String name = resultSet.getString("name");
                System.out.println(name);
            }

            if (!hasBookings) {
                System.out.println("No hay citas que mostrar.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createBooking() {
        System.out.println("Creando una nueva cita:");

        System.out.print("Nombre del cliente: ");
        String name = scanner.nextLine();

        System.out.print("Email del cliente: ");
        String email = scanner.nextLine();
        if (!isValidEmail(email)) {
            System.out.println("Formato de email incorrecto. La cita no se ha creado.");
            return;
        }

        System.out.print("Teléfono del cliente: ");
        String phone = scanner.nextLine();
        if (!isValidPhoneNumber(phone)) {
            System.out.println("Formato de teléfono incorrecto. La cita no se ha creado.");
            return;
        }

        System.out.print("Fecha de la cita (DD-MM-AAAA): ");
        String date = scanner.nextLine();
        if (!isValidDate(date)) {
            System.out.println("Formato de fecha incorrecto. La cita no se ha creado.");
            return;
        }

        System.out.print("Hora de la cita (HH:MM): ");
        String time = scanner.nextLine();
        if (!isValidTime(time)) {
            System.out.println("Formato de hora incorrecto. La cita no se ha creado.");
            return;
        }

        System.out.println("Servicios disponibles:");
        System.out.println("1. Corte de pelo");
        System.out.println("2. Peinado");
        System.out.println("3. Afeitado");
        System.out.println("4. Tratamiento capilar");
        System.out.println("5. Corte de barba");
        System.out.println("6. Tinte de cabello");
        System.out.print("¿Qué servicio quieres? Elige el número de servicio: ");
        String serviceOption = scanner.nextLine();
        String service = getServiceByOption(serviceOption);
        if (service == null) {
            System.out.println("Opción de servicio inválida. La cita no se ha creado.");
            return;
        }

        System.out.println("Peluqueros disponibles:");
        System.out.println("1. Luis Alberto");
        System.out.println("2. Juan Carlos");
        System.out.println("3. Ramón Antonio");
        System.out.print("¿Qué peluquero quieres? Elige el número de peluquero: ");
        String hairdresserOption = scanner.nextLine();
        String hairdresser = getHairdresserByOption(hairdresserOption);
        if (hairdresser == null) {
            System.out.println("Opción de peluquero inválida. La cita no se ha creado.");
            return;
        }

        insertBooking(name, email, phone, date, time, service, hairdresser);
        System.out.println("Cita creada correctamente:");
        System.out.println("Nombre: " + name);
        System.out.println("Email: " + email);
        System.out.println("Teléfono: " + phone);
        System.out.println("Fecha: " + date);
        System.out.println("Hora: " + time);
        System.out.println("Servicio: " + service);
        System.out.println("Peluquero: " + hairdresser);
    }

    private static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "^[0-9]{9}$";
        return phoneNumber.matches(phoneRegex);
    }

    private static boolean isValidDate(String date) {
        String dateRegex = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(\\d{4})$";
        return date.matches(dateRegex);
    }

    private static boolean isValidTime(String time) {
        String timeRegex = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";
        return time.matches(timeRegex);
    }

    private static String getServiceByOption(String option) {
        switch (option) {
            case "1":
                return "Corte de pelo";
            case "2":
                return "Peinado";
            case "3":
                return "Afeitado";
            case "4":
                return "Tratamiento capilar";
            case "5":
                return "Corte de barba";
            case "6":
                return "Tinte de cabello";
            default:
                return null;
        }
    }

    private static String getHairdresserByOption(String option) {
        switch (option) {
            case "1":
                return "Luis Alberto";
            case "2":
                return "Juan Carlos";
            case "3":
                return "Ramón Antonio";
            default:
                return null;
        }
    }

    private static void searchClientsByName(String prefix) {
        String databasePath = "jdbc:sqlite:database.db";
        try (Connection connection = DriverManager.getConnection(databasePath);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT name FROM bookings WHERE name LIKE ?")) {

            preparedStatement.setString(1, prefix + "%");

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                System.out.print("El cliente que buscas es " + name + "? (S/N): ");
                String confirmation = scanner.nextLine();

                if (confirmation.equalsIgnoreCase("S")) {
                    handleBookingOptions(name);
                } else {
                    System.out.println("Búsqueda cancelada.");
                }
            } else {
                System.out.println("No se encontró ningún cliente con ese nombre.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void handleBookingOptions(String name) {
        System.out.println("Opciones disponibles:");
        System.out.println("1. Consultar cita");
        System.out.println("2. Modificar fecha");
        System.out.println("3. Modificar hora");
        System.out.println("4. Borrar cita");
        System.out.println("5. Borrar cliente");
        System.out.print("Elige una opción: ");
        String option = scanner.nextLine();

        switch (option) {
            case "1":
                // Consultar cita
                getBookingByClientName(name);
                break;
            case "2":
                // Modificar fecha
                System.out.print("Ingresa la nueva fecha de la cita (DD-MM-AAAA): ");
                String newDate = scanner.nextLine();
                updateBookingDate(name, newDate);
                break;
            case "3":
                // Modificar hora
                System.out.print("Ingresa la nueva hora de la cita (HH:MM): ");
                String newTime = scanner.nextLine();
                updateBookingTime(name, newTime);
                break;
            case "4":
                // Borrar cita
                deleteBookingByClientName(name);
                break;
            case "5":
                // Borrar cliente
                deleteClient(name);
                break;
            default:
                System.out.println("Opción inválida.");
                break;
        }
    }

    private static void getBookingByClientName(String name) {
        String databasePath = "jdbc:sqlite:database.db";
        try (Connection connection = DriverManager.getConnection(databasePath);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM bookings WHERE name = ?")) {

            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String date = resultSet.getString("date");
                String time = resultSet.getString("time");
                String service = resultSet.getString("service");
                String hairdresser = resultSet.getString("hairdresser");

                System.out.println("Cita encontrada:");
                System.out.println("Nombre: " + name);
                System.out.println("Email: " + email);
                System.out.println("Teléfono: " + phone);
                System.out.println("Fecha: " + date);
                System.out.println("Hora: " + time);
                System.out.println("Servicio: " + service);
                System.out.println("Peluquero: " + hairdresser);
            } else {
                System.out.println("No se encontró ninguna cita para el cliente " + name + ".");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateBookingDate(String name, String newDate) {
        String databasePath = "jdbc:sqlite:database.db";
        try (Connection connection = DriverManager.getConnection(databasePath);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE bookings SET date = ? WHERE name = ?")) {

            preparedStatement.setString(1, newDate);
            preparedStatement.setString(2, name);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Fecha de la cita modificada correctamente.");
            } else {
                System.out.println("No se encontró ninguna cita para el cliente " + name + ".");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateBookingTime(String name, String newTime) {
        String databasePath = "jdbc:sqlite:database.db";
        try (Connection connection = DriverManager.getConnection(databasePath);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE bookings SET time = ? WHERE name = ?")) {

            preparedStatement.setString(1, newTime);
            preparedStatement.setString(2, name);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Hora de la cita modificada correctamente.");
            } else {
                System.out.println("No se encontró ninguna cita para el cliente " + name + ".");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteBookingByClientName(String name) {
        String databasePath = "jdbc:sqlite:database.db";
        try (Connection connection = DriverManager.getConnection(databasePath);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM bookings WHERE name = ?")) {

            preparedStatement.setString(1, name);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Cita borrada correctamente.");
            } else {
                System.out.println("No se encontró ninguna cita para el cliente " + name + ".");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteClient(String name) {
        String databasePath = "jdbc:sqlite:database.db";
        try (Connection connection = DriverManager.getConnection(databasePath);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM bookings WHERE name = ?")) {

            preparedStatement.setString(1, name);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Cliente y cita(s) borrados correctamente.");
            } else {
                System.out.println("No se encontró ningún cliente con ese nombre.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
