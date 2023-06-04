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
    <title>Peluquería New York - Modificar cita</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
    <link href="css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed sb-sidenav-toggled">
    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <h1 class="mt-4">Modificar cita</h1>
                <div class="card mb-4">
                    <div class="card-header">
                        <i class="fas fa-table me-1"></i>
                        Detalles de la cita
                    </div>
                    <div class="card-body">
                        <% 
                        // Obtener el ID de la cita seleccionada
                        String idParam = request.getParameter("id");
                        int citaId = Integer.parseInt(idParam);
                        
                        // Configuración de la conexión a la base de datos
                        String DATABASE_URL = "jdbc:mysql://localhost:3306/database";
                        String USERNAME = "root";
                        String PASSWORD = "";
                        String DRIVER = "com.mysql.cj.jdbc.Driver";
                        
                        try {
                            Class.forName(DRIVER);
                            Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                            
                            // Consulta a la base de datos para obtener los detalles de la cita seleccionada
                            String query = "SELECT * FROM bookings WHERE id = ?";
                            PreparedStatement stmt = conn.prepareStatement(query);
                            stmt.setInt(1, citaId);
                            ResultSet rs = stmt.executeQuery();
                            
                            // Comprobar si se encontró la cita
                            if (rs.next()) {
                                String nombre = rs.getString("name");
                                String email = rs.getString("email");
                                String telefono = rs.getString("phone");
                                String fecha = rs.getString("date");
                                String hora = rs.getString("time");
                                String servicio = rs.getString("service");
                                String peluquero = rs.getString("hairdresser");
                        %>
                        <form action="guardar-cambios-cita.jsp" method="post">
                            <input type="hidden" name="id" value="<%= citaId %>" />
                            <div class="form-group">
                                <label for="nombre">Nombre:</label>
                                <input type="text" id="nombre" name="nombre" class="form-control" value="<%= nombre %>" required />
                            </div>
                            <div class="form-group">
                                <label for="email">Email:</label>
                                <input type="email" id="email" name="email" class="form-control" value="<%= email %>" required />
                            </div>
                            <div class="form-group">
                                <label for="telefono">Teléfono:</label>
                                <input type="text" id="telefono" name="telefono" class="form-control" value="<%= telefono %>" required />
                            </div>
                            <div class="form-group">
                                <label for="fecha">Fecha:</label>
                                <input type="date" id="fecha" name="fecha" class="form-control" value="<%= fecha %>" required />
                            </div>
                            <div class="form-group">
                                <label for="hora">Hora:</label>
                                <input type="time" id="hora" name="hora" class="form-control" value="<%= hora %>" required />
                            </div>
                            <div class="form-group">
                                <label for="servicio">Servicio:</label>
                                <input type="text" id="servicio" name="servicio" class="form-control" value="<%= servicio %>" required />
                            </div>
                            <div class="form-group">
                                <label for="peluquero">Peluquero:</label>
                                <input type="text" id="peluquero" name="peluquero" class="form-control" value="<%= peluquero %>" required />
                            </div>
                            <br>
                            <button type="submit" class="btn btn-primary">Guardar cambios</button>
                            <a href="admin.jsp" class="btn btn-secondary">Cancelar</a>
                        </form>
                        <% 
                            } else {
                        %>
                        <div class="alert alert-danger" role="alert">
                            No se encontró la cita seleccionada.
                        </div>
                        <% 
                            }
                            rs.close();
                            stmt.close();
                            conn.close();
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
