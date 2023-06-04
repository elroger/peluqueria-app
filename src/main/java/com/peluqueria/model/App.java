package com.peluqueria.model;

import java.sql.*;
import java.util.Scanner;

/**
 * Esta clase representa la aplicación de una peluquería.
 */
public class App {
    private static Scanner scanner = new Scanner(System.in);
    private static Peluqueria peluqueria = new Peluqueria();
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/database";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        System.out.println("__________                                      ");
        System.out.println("\\______   \\ ____   ____   ___________   ___.__.");
        System.out.println(" |       _//  _ \\ / ___\\_/ __ \\_  __ \\ <   |  |");
        System.out.println(" |    |   (  <_> ) /_/  >  ___/|  | \\/  \\___  |");
        System.out.println(" |____|_  /\\____/\\___  / \\___  >__|     / ____|");
        System.out.println("  _________     /_____/      .___       \\/     ");
        System.out.println(" /   _____/____    ____    __| _/___________   ");
        System.out.println(" \\_____  \\\\__  \\  /    \\  / __ |\\_  __ \\__  \\  ");
        System.out.println(" /        \\/ __ \\|   |  \\/ /_/ | |  | \\/ __ \\_");
        System.out.println("/_______  (____  /___|  /\\____ | |__|  (____  /");
        System.out.println("        \\/     \\/     \\/      \\/            \\/ ");
        System.out.print("");

        // Crear la tabla "bookings"
        createBookingsTable();

        // Menú de opciones
        int option = 0;
        do {
            System.out.println("Menú:");
            System.out.println("1. Prueba de conexión con la base de datos");
            System.out.println("2. Consultar todas las citas");
            System.out.println("3. Ver nombres de clientes con cita");
            System.out.println("4. Eliminar una cita por nombre");
            System.out.println("5. Borrar todas las citas");
            System.out.println("6. Introducir datos de prueba");
            System.out.println("7. Crear una cita");
            System.out.println("8. Buscar clientes por nombre");
            System.out.println("9. Modificar fecha y hora de cita");
            System.out.println("10. Borrar cita y cliente");
            System.out.println("11. Salir");
            System.out.print("Elige qué quieres hacer: ");

            String input = scanner.nextLine();
            if (!input.matches("\\d+")) {
                System.out.println("Opción incorrecta. Intentalo de nuevo.");
                continue;
            }

            option = Integer.parseInt(input);

            switch (option) {
                case 1:
                    testDatabaseConnection();
                    break;
                case 2:
                    // Obtener todas las citas de la base de datos
                    System.out.println("Citas registradas:");
                    getAllBookings();
                    break;
                case 3:
                    // Ver nombres de clientes con cita
                    System.out.println("Nombres de clientes con cita:");
                    showBookingNames();
                    break;
                case 4:
                    // Eliminar una cita por nombre
                    System.out.print("Introduce las tres primeras letras del nombre del cliente que quieres eliminar: ");
                    String prefix = scanner.nextLine();
                    deleteBookingByPrefix(prefix);
                    break;
                case 5:
                    // Borrar todas las citas
                    deleteAllBookings();
                    break;
                case 6:
                    // Introducir datos de prueba
                    insertSampleData();
                    break;
                case 7:
                    // Crear una cita
                    createBooking();
                    break;
                case 8:
                    // Buscar clientes por nombre
                    System.out.print("Introduce las tres primeras letras del nombre del cliente que quieres buscar: ");
                    String searchPrefix = scanner.nextLine();
                    searchClientsByName(searchPrefix);
                    break;
                case 9:
                    // Modificar fecha y hora de cita
                    System.out.print("Introduce las tres primeras letras del nombre del cliente cuya cita quieres modificar: ");
                    String modifyPrefix = scanner.nextLine();
                    modifyBookingDateTime(modifyPrefix);
                    break;
                case 10:
                    // Borrar cita y cliente
                    System.out.print("Introduce las tres primeras letras del nombre del cliente cuya cita y cliente quieres borrar: ");
                    String deletePrefix = scanner.nextLine();
                    deleteBookingAndClient(deletePrefix);
                    break;
                case 11:
                    // Salir del programa
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo.");
                    break;
            }
        } while (option != 11);

        scanner.close();
    }

    /**
     * Realiza una prueba de conexión con la base de datos.
     */
    private static void testDatabaseConnection() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD)) {
            System.out.println("Conexión exitosa a la base de datos!");
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    /**
     * Crea la tabla "bookings" en la base de datos si no existe.
     */
    private static void createBookingsTable() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {

            // Verificar si la tabla ya existe
            ResultSet resultSet = connection.getMetaData().getTables(null, null, "bookings", null);
            System.out.print("Comprobando la tabla 'bookings'");
            for (int i = 0; i < 7; i++) {
                try {
                    Thread.sleep(500);
                    System.out.print(".");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println();

            if (resultSet.next()) {
                System.out.println("La tabla 'bookings' ya existe, no es necesario crearla.");
                return;
            }

            // Crear la tabla "bookings" si no existe
            String createTableSQL = "CREATE TABLE bookings ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
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

    /**
     * Inserta una nueva cita en la base de datos y en la lista de citas de la peluquería.
     *
     * @param name        nombre del cliente
     * @param email       email del cliente
     * @param phone       teléfono del cliente
     * @param date        fecha de la cita
     * @param time        hora de la cita
     * @param service     servicio solicitado
     * @param hairdresser peluquero asignado
     */
    private static void insertBooking(String name, String email, String phone, String date, String time, String service, String hairdresser) {
        Cliente cliente = new Cliente(name, email, phone);
        Cita cita = new Cita(name, email, phone, date, time, service, hairdresser);

        peluqueria.nuevoCliente(cliente);
        peluqueria.nuevaCita(cita);

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
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

    /**
     * Obtiene todas las citas de la base de datos y las muestra en la consola.
     */
    private static void getAllBookings() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM bookings")) {

            boolean hasBookings = false;
            while (resultSet.next()) {
                hasBookings = true;
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String date = resultSet.getString("date");
                String time = resultSet.getString("time");
                String service = resultSet.getString("service");
                String hairdresser = resultSet.getString("hairdresser");

                System.out.println("ID: " + id);
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

    /**
     * Elimina una cita de la base de datos y de la lista de citas de la peluquería
     * basándose en las tres primeras letras del nombre del cliente.
     *
     * @param prefix prefijo del nombre del cliente
     */
    private static void deleteBookingByPrefix(String prefix) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM bookings WHERE name LIKE ?")) {

            preparedStatement.setString(1, prefix + "%");

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.print("Seguro que quieres eliminar la cita de " + name + "? (S/N): ");
                String confirmation = scanner.nextLine();

                if (confirmation.equalsIgnoreCase("S")) {
                    try (PreparedStatement deleteStatement = connection.prepareStatement(
                            "DELETE FROM bookings WHERE id = ?")) {
                        deleteStatement.setInt(1, id);
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

    /**
     * Elimina todas las citas de la base de datos y de la lista de citas de la peluquería.
     */
    private static void deleteAllBookings() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate("DELETE FROM bookings");
            System.out.println("Todas las citas han sido eliminadas.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserta datos de prueba en la base de datos y en la lista de citas de la peluquería.
     */
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

        if (!checkIfBookingExists("Marc Márquez", "marc.marquez@yahoo.com", "656789012", "29-05-2023", "09:30", "Tinte", "Luis Alberto")) {
            insertBooking("Marc Márquez", "marc.marquez@yahoo.com", "656789012", "29-05-2023", "09:30", "Tinte", "Luis Alberto");
            newBookings++;
        } else {
            existingBookings++;
        }

        System.out.println("Datos de prueba insertados:");
        System.out.println("Citas existentes: " + existingBookings);
        System.out.println("Citas nuevas insertadas: " + newBookings);
    }

    /**
     * Crea una nueva cita a partir de los datos ingresados por el usuario y la inserta en la base de datos y en la lista de citas de la peluquería.
     */
    private static void createBooking() {
        System.out.print("Introduce el nombre del cliente: ");
        String name = scanner.nextLine();

        System.out.print("Introduce el email del cliente: ");
        String email = scanner.nextLine();

        System.out.print("Introduce el teléfono del cliente: ");
        String phone = scanner.nextLine();

        System.out.print("Introduce la fecha de la cita (dd-mm-yyyy): ");
        String date = scanner.nextLine();

        System.out.print("Introduce la hora de la cita (hh:mm): ");
        String time = scanner.nextLine();

        System.out.print("Introduce el servicio solicitado: ");
        String service = scanner.nextLine();

        System.out.print("Introduce el nombre del peluquero: ");
        String hairdresser = scanner.nextLine();

        if (checkIfBookingExists(name, email, phone, date, time, service, hairdresser)) {
            System.out.println("Ya existe una cita con esos datos.");
        } else {
            insertBooking(name, email, phone, date, time, service, hairdresser);
        }
    }

    /**
     * Muestra los nombres de todos los clientes con citas en la base de datos.
     */
    private static void showBookingNames() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
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

    /**
     * Busca clientes en la base de datos cuyos nombres coincidan con el prefijo especificado y muestra los resultados en la consola.
     *
     * @param prefix prefijo del nombre del cliente
     */
    private static void searchClientsByName(String prefix) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM bookings WHERE name LIKE ?")) {

            preparedStatement.setString(1, prefix + "%");

            ResultSet resultSet = preparedStatement.executeQuery();
            boolean hasClients = false;
            while (resultSet.next()) {
                hasClients = true;
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String date = resultSet.getString("date");
                String time = resultSet.getString("time");
                String service = resultSet.getString("service");
                String hairdresser = resultSet.getString("hairdresser");

                System.out.println("ID: " + id);
                System.out.println("Nombre: " + name);
                System.out.println("Email: " + email);
                System.out.println("Teléfono: " + phone);
                System.out.println("Fecha: " + date);
                System.out.println("Hora: " + time);
                System.out.println("Servicio: " + service);
                System.out.println("Peluquero: " + hairdresser);
                System.out.println("------------------------");
            }

            if (!hasClients) {
                System.out.println("No se encontraron clientes con ese nombre.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Modifica la fecha y hora de una cita de la base de datos y de la lista de citas de la peluquería
     * basándose en las tres primeras letras del nombre del cliente.
     *
     * @param prefix prefijo del nombre del cliente
     */
    private static void modifyBookingDateTime(String prefix) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM bookings WHERE name LIKE ?")) {

            preparedStatement.setString(1, prefix + "%");

            ResultSet resultSet = preparedStatement.executeQuery();
            boolean hasBooking = false;
            while (resultSet.next()) {
                hasBooking = true;
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String date = resultSet.getString("date");
                String time = resultSet.getString("time");

                System.out.println("Cita encontrada:");
                System.out.println("ID: " + id);
                System.out.println("Nombre: " + name);
                System.out.println("Fecha actual: " + date);
                System.out.println("Hora actual: " + time);

                System.out.print("Introduce la nueva fecha de la cita (dd-mm-yyyy): ");
                String newDate = scanner.nextLine();

                System.out.print("Introduce la nueva hora de la cita (hh:mm): ");
                String newTime = scanner.nextLine();

                try (PreparedStatement updateStatement = connection.prepareStatement(
                        "UPDATE bookings SET date = ?, time = ? WHERE id = ?")) {

                    updateStatement.setString(1, newDate);
                    updateStatement.setString(2, newTime);
                    updateStatement.setInt(3, id);

                    int rowsUpdated = updateStatement.executeUpdate();
                    if (rowsUpdated > 0) {
                        System.out.println("Cita actualizada correctamente.");
                    } else {
                        System.out.println("No se encontró ninguna cita con ese nombre.");
                    }
                }
            }

            if (!hasBooking) {
                System.out.println("No se encontró ninguna cita con ese nombre.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina una cita y el cliente asociado de la base de datos y de la lista de citas de la peluquería
     * basándose en las tres primeras letras del nombre del cliente.
     *
     * @param prefix prefijo del nombre del cliente
     */
    private static void deleteBookingAndClient(String prefix) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM bookings WHERE name LIKE ?")) {

            preparedStatement.setString(1, prefix + "%");

            ResultSet resultSet = preparedStatement.executeQuery();
            boolean hasBooking = false;
            while (resultSet.next()) {
                hasBooking = true;
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.print("Seguro que quieres eliminar la cita de " + name + " y el cliente asociado? (S/N): ");
                String confirmation = scanner.nextLine();

                if (confirmation.equalsIgnoreCase("S")) {
                    try (PreparedStatement deleteStatement = connection.prepareStatement(
                            "DELETE FROM bookings WHERE id = ?")) {

                        deleteStatement.setInt(1, id);
                        int rowsDeleted = deleteStatement.executeUpdate();
                        if (rowsDeleted > 0) {
                            System.out.println("Cita y cliente eliminados correctamente.");
                        } else {
                            System.out.println("No se encontró ninguna cita con ese nombre.");
                        }
                    }
                } else {
                    System.out.println("Eliminación cancelada.");
                }
            }

            if (!hasBooking) {
                System.out.println("No se encontró ninguna cita con ese nombre.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifica si ya existe una cita en la base de datos con los datos especificados.
     *
     * @param name        nombre del cliente
     * @param email       email del cliente
     * @param phone       teléfono del cliente
     * @param date        fecha de la cita
     * @param time        hora de la cita
     * @param service     servicio solicitado
     * @param hairdresser peluquero asignado
     * @return true si ya existe una cita con los datos especificados, false en caso contrario
     */
    private static boolean checkIfBookingExists(String name, String email, String phone, String date, String time, String service, String hairdresser) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
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
        }

        return false;
    }
}
