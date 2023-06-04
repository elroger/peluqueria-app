package com.peluqueria.servlets;

import com.peluqueria.model.Cita;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet encargado de procesar el formulario de reserva de citas.
 */
public class BookingServlet extends HttpServlet {
    private static final Object lock = new Object();

    /**
     * Método que maneja la solicitud POST del formulario de reserva de citas.
     * 
     * @param request  Objeto HttpServletRequest que contiene la información de la solicitud HTTP.
     * @param response Objeto HttpServletResponse que se utilizará para enviar la respuesta HTTP.
     * @throws ServletException Si ocurre un error durante la ejecución del servlet.
     * @throws IOException      Si ocurre un error de entrada/salida durante la ejecución del servlet.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Configuración de la conexión a la base de datos
        String DATABASE_URL = "jdbc:mysql://localhost:3306/database"; // URL de la base de datos
        String USERNAME = "root"; // Nombre de usuario de la base de datos
        String PASSWORD = ""; // Contraseña de la base de datos

        // Obtener los datos del formulario
        String name = request.getParameter("app_name"); // Nombre del cliente
        String email = request.getParameter("app_email"); // Email del cliente
        String phone = request.getParameter("app_phone"); // Teléfono del cliente
        String date = request.getParameter("app_date"); // Fecha de la cita
        String time = request.getParameter("app_time"); // Hora de la cita
        String service = request.getParameter("app_service"); // Servicio seleccionado
        String hairdresser = request.getParameter("app_barber"); // Peluquero seleccionado

        // Crear objeto Cita con los datos
        Cita cita = new Cita(name, email, phone, date, time, service, hairdresser);

        /* La sincronización con el objeto "lock" garantiza que solo un hilo a la vez pueda acceder 
        a la sección crítica del código que maneja la conexión y la consulta a la base de datos,
        y de esta manera las operaciones de inserción de citas se realizarán sin problemas de concurrencia. */
        synchronized (lock) {
            Connection conn = null;
            PreparedStatement stmt = null;

            try {
                // Establecer la conexión con la base de datos MySQL
                Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar el controlador JDBC
                conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD); // Establecer la conexión
                System.out.println("Se ha establecido conexión con la base de datos!");

                // Preparar la sentencia SQL para insertar los datos de la cita
                String sql = "INSERT INTO bookings (name, email, phone, date, time, service, hairdresser) VALUES (?, ?, ?, ?, ?, ?, ?)";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, cita.getName()); 
                stmt.setString(2, cita.getEmail()); 
                stmt.setString(3, cita.getPhone()); 
                stmt.setString(4, cita.getDate()); 
                stmt.setString(5, cita.getTime()); 
                stmt.setString(6, cita.getService()); 
                stmt.setString(7, cita.getHairdresser());
                stmt.executeUpdate(); // Ejecutar la sentencia SQL

                System.out.println("Cita reservada correctamente <3");

                // Redirigir al usuario a la página de éxito (success.jsp)
                response.sendRedirect("success.jsp");
            } catch (ClassNotFoundException | SQLException e) {
                // En caso de error, mostrar un mensaje de error en la consola
                e.printStackTrace();
                System.out.println("Error al procesar la solicitud: " + e.getMessage());
                response.getWriter().println("Error al procesar la solicitud: " + e.getMessage());
            } finally {
                // Cerrar la conexión y liberar los recursos
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
