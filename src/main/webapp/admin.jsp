<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Peluquería New York - Panel del peluquero</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
    <link href="css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    <style>
        .btn-danger {
            color: #fff;
            background-color: #dc3545;
            border-color: #dc3545;
        }
        
        .btn-danger:hover {
            color: #fff;
            background-color: #c82333;
            border-color: #bd2130;
        }
        
        .btn-primary {
            color: #fff;
            background-color: #007bff;
            border-color: #007bff;
        }
        
        .btn-primary:hover {
            color: #fff;
            background-color: #0069d9;
            border-color: #0062cc;
        }
    </style>
    <script>
        function confirmDelete(id) {
            if (confirm("¿Estás seguro de que quieres borrar esta cita?")) {
                window.location.href = "eliminar-cita.jsp?id=" + id;
            }
        }
        
        function confirmDeleteAll() {
            if (confirm("¿Estás seguro de que quieres eliminar todas las citas? Esta acción no es reversible")) {
                window.location.href = "eliminar-todas-las-citas.jsp";
            }
        }
        
        function insertTestData() {
            if (confirm("¿Estás seguro de que quieres introducir datos de prueba?")) {
                window.location.href = "datos-de-prueba.jsp";
            }
        }
        
        function downloadDatabase() {
            if (confirm("¿Estás seguro de que quieres descargar la base de datos en formato XML?")) {
                window.location.href = "descargar-base-de-datos.jsp";
            }
        }
        
        function modifyCita(id) {
            window.location.href = "modificar-cita.jsp?id=" + id;
        }
    </script>
</head>
<body class="sb-nav-fixed sb-sidenav-toggled">
    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <a href="index.jsp" style="text-decoration: none;"><h1 class="mt-4">Peluquería New York</h1></a>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item active">Panel del peluquero</li>
                </ol>
                <div class="row">
                    <div class="col-xl-3 col-md-6">
                        <div class="card bg-primary text-white mb-4">
                            <div class="card-body">Descargar base de datos</div>
                            <div class="card-footer d-flex align-items-center justify-content-between">
                                <a class="small text-white" href="javascript:downloadDatabase()">Descargar en formato XML</a>
                                <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-md-6">
                        <div class="card bg-success text-white mb-4">
                            <div class="card-body">Introducir datos de prueba</div>
                            <div class="card-footer d-flex align-items-center justify-content-between">
                                <a class="small text-white" href="javascript:insertTestData()">60 datos de prueba</a>
                                <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-md-6">
                        <div class="card bg-danger text-white mb-4">
                            <div class="card-body">BORRAR TODO</div>
                            <div class="card-footer d-flex align-items-center justify-content-between">
                                <a class="small text-white" href="javascript:confirmDeleteAll()">Eliminar todas las citas</a>
                                <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xl-6">
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-chart-area me-1"></i>
                                Estadísticas
                            </div>
                            <div class="card-body"><canvas id="myAreaChart" width="100%" height="40"></canvas></div>
                        </div>
                    </div>
                    <div class="col-xl-6">
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-chart-bar me-1"></i>
                                Gráfico de barras
                            </div>
                            <div class="card-body"><canvas id="myBarChart" width="100%" height="40"></canvas></div>
                        </div>
                    </div>
                </div>
                <div class="card mb-4">
                    <div class="card-header">
                        <i class="fas fa-table me-1"></i>
                        Lista de citas
                    </div>
                    <div class="card-body">
                        <table id="datatablesSimple" class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Nombre</th>
                                    <th>Email</th>
                                    <th>Teléfono</th>
                                    <th>Fecha</th>
                                    <th>Hora</th>
                                    <th>Servicio</th>
                                    <th>Peluquero</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% 
                                // Configuración de la conexión a la base de datos
                                String DATABASE_URL = "jdbc:mysql://localhost:3306/database";
                                String USERNAME = "root";
                                String PASSWORD = "";
                                String DRIVER = "com.mysql.cj.jdbc.Driver";
                                
                                try {
                                    Class.forName(DRIVER);
                                    Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                                    
                                    // Consulta a la base de datos
                                    String query = "SELECT * FROM bookings";
                                    Statement stmt = conn.createStatement();
                                    ResultSet rs = stmt.executeQuery(query);
                                    
                                    // Iterar sobre los resultados y mostrar en la tabla
                                    while (rs.next()) {
                                        int id = rs.getInt("id");
                                        String nombre = rs.getString("name");
                                        String email = rs.getString("email");
                                        String telefono = rs.getString("phone");
                                        String fecha = rs.getString("date");
                                        String hora = rs.getString("time");
                                        String servicio = rs.getString("service");
                                        String peluquero = rs.getString("hairdresser");
                                %>
                                <tr>
                                    <td><%= id %></td>
                                    <td><%= nombre %></td>
                                    <td><%= email %></td>
                                    <td><%= telefono %></td>
                                    <td><%= fecha %></td>
                                    <td><%= hora %></td>
                                    <td><%= servicio %></td>
                                    <td><%= peluquero %></td>
                                    <td>
                                        <a class="btn btn-danger btn-sm" href="javascript:confirmDelete(<%= id %>)">Eliminar</a>
                                        <a class="btn btn-secondary btn-sm mr-2" href="javascript:modifyCita(<%= id %>)">Modificar</a>
                                    </td>
                                </tr>
                                <% 
                                    }
                                    rs.close();
                                    stmt.close();
                                    conn.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                %>
                            </tbody>
                        </table>
                    </div>
                    
                    
                </div>
            </div>
        </main>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script src="js/scripts.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
    <script src="assets/demo/chart-area-demo.js"></script>
    <script src="assets/demo/chart-bar-demo.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
    <script src="js/datatables-simple-demo.js"></script>
</body>
</html>
