<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%
// Obtén el ID de la cita a eliminar desde la URL
int id = Integer.parseInt(request.getParameter("id"));

// Configuración de la conexión a la base de datos
String DATABASE_URL = "jdbc:mysql://localhost:3306/database";
String USERNAME = "root";
String PASSWORD = "";
String DRIVER = "com.mysql.cj.jdbc.Driver";

try {
    Class.forName(DRIVER);
    Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
    
    // Eliminar la cita de la base de datos
    String query = "DELETE FROM bookings WHERE id = ?";
    PreparedStatement pstmt = conn.prepareStatement(query);
    pstmt.setInt(1, id);
    pstmt.executeUpdate();
    
    pstmt.close();
    conn.close();
    
    // Redireccionar de vuelta a la página principal después de eliminar la cita
    response.sendRedirect("admin.jsp");
} catch (Exception e) {
    e.printStackTrace();
}
%>
