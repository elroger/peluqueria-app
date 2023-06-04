<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>

<%
    // Configuración de la conexión a la base de datos
    String DATABASE_URL = "jdbc:mysql://localhost:3306/database";
    String USERNAME = "root";
    String PASSWORD = "";

    // Obtener los datos del formulario
    String name = request.getParameter("app_name");
    String email = request.getParameter("app_email");
    String phone = request.getParameter("app_phone");
    String date = request.getParameter("app_date");
    String time = request.getParameter("app_time");
    String service = request.getParameter("app_service");
    String hairdresser = request.getParameter("app_barber");

    Connection conn = null;
    PreparedStatement stmt = null;

    try {
        // Establecer la conexión con la base de datos MySQL
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

        // Preparar la sentencia SQL para insertar los datos de la cita
        String sql = "INSERT INTO bookings (name, email, phone, date, time, service, hairdresser) VALUES (?, ?, ?, ?, ?, ?, ?)";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setString(2, email);
        stmt.setString(3, phone);
        stmt.setString(4, date);
        stmt.setString(5, time);
        stmt.setString(6, service);
        stmt.setString(7, hairdresser);
        stmt.executeUpdate();

        // Redirigir al usuario a la página de éxito (success.jsp)
        response.sendRedirect("success.jsp");
    } catch (Exception e) {
        // En caso de error, mostrar un mensaje de error
        out.println("Error al procesar la solicitud: " + e.getMessage());
    } finally {
        // Cerrar la conexión y liberar los recursos
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
%>
