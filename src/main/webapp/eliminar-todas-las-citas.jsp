<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>

<%
// Configuración de la conexión a la base de datos
String DATABASE_URL = "jdbc:mysql://localhost:3306/database";
String USERNAME = "root";
String PASSWORD = "";
String DRIVER = "com.mysql.cj.jdbc.Driver";

try {
    Class.forName(DRIVER);
    Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
    
    // Eliminar todas las citas de la tabla
    String deleteQuery = "DELETE FROM bookings";
    Statement stmt = conn.createStatement();
    int rowsDeleted = stmt.executeUpdate(deleteQuery);
    
    // Cerrar la conexión y redirigir a admin.jsp
    stmt.close();
    conn.close();
    response.sendRedirect("admin.jsp");
} catch (Exception e) {
    e.printStackTrace();
}
%>
