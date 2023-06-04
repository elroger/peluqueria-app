<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Peluquería New York - Guardar cambios</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
    <link href="css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed sb-sidenav-toggled">
    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <h1 class="mt-4">Guardar cambios</h1>
                <div class="card mb-4">
                    <div class="card-header">
                        <i class="fas fa-table me-1"></i>
                        Resultado
                    </div>
                    <div class="card-body">
                        <% 
                        // Obtener los datos enviados desde el formulario
                        String idParam = request.getParameter("id");
                        int citaId = Integer.parseInt(idParam);
                        String nombre = request.getParameter("nombre");
                        String email = request.getParameter("email");
                        String telefono = request.getParameter("telefono");
                        String fecha = request.getParameter("fecha");
                        String hora = request.getParameter("hora");
                        String servicio = request.getParameter("servicio");
                        String peluquero = request.getParameter("peluquero");
                        
                        // Configuración de la conexión a la base de datos
                        String DATABASE_URL = "jdbc:mysql://localhost:3306/database";
                        String USERNAME = "root";
                        String PASSWORD = "";
                        String DRIVER = "com.mysql.cj.jdbc.Driver";
                        
                        try {
                            Class.forName(DRIVER);
                            Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                            
                            // Actualizar la cita en la base de datos
                            String query = "UPDATE bookings SET name = ?, email = ?, phone = ?, date = ?, time = ?, service = ?, hairdresser = ? WHERE id = ?";
                            PreparedStatement stmt = conn.prepareStatement(query);
                            stmt.setString(1, nombre);
                            stmt.setString(2, email);
                            stmt.setString(3, telefono);
                            stmt.setString(4, fecha);
                            stmt.setString(5, hora);
                            stmt.setString(6, servicio);
                            stmt.setString(7, peluquero);
                            stmt.setInt(8, citaId);
                            
                            int rowsAffected = stmt.executeUpdate();
                            
                            stmt.close();
                            conn.close();
                            
                            if (rowsAffected > 0) {
                        %>
                        <div class="alert alert-success" role="alert">
                            Los cambios se han guardado correctamente.
                        </div>
                        <button onclick="window.location.href='admin.jsp'">Volver al panel de administración</button>
                        <% 
                            } else {
                        %>
                        <div class="alert alert-danger" role="alert">
                            Ha ocurrido un error al guardar los cambios.
                        </div>
                        <button onclick="window.location.href='admin.jsp'">Volver al panel de administración</button>
                        <% 
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        %>
                    </div>
                </div>
            </div>
        </main>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script src="js/scripts.js"></script>
</body>
</html>
