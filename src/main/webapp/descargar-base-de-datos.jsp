<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Peluquería New York - Descargar base de datos</title>
</head>
<body>
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
            
            // Crear un objeto StringBuilder para almacenar el contenido XML
            StringBuilder xmlContent = new StringBuilder();
            xmlContent.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            xmlContent.append("<bookings>\n");
            
            // Iterar sobre los resultados y agregarlos al contenido XML
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("name");
                String email = rs.getString("email");
                String telefono = rs.getString("phone");
                String fecha = rs.getString("date");
                String hora = rs.getString("time");
                String servicio = rs.getString("service");
                String peluquero = rs.getString("hairdresser");
                
                xmlContent.append("  <booking>\n");
                xmlContent.append("    <id>").append(id).append("</id>\n");
                xmlContent.append("    <nombre>").append(nombre).append("</nombre>\n");
                xmlContent.append("    <email>").append(email).append("</email>\n");
                xmlContent.append("    <telefono>").append(telefono).append("</telefono>\n");
                xmlContent.append("    <fecha>").append(fecha).append("</fecha>\n");
                xmlContent.append("    <hora>").append(hora).append("</hora>\n");
                xmlContent.append("    <servicio>").append(servicio).append("</servicio>\n");
                xmlContent.append("    <peluquero>").append(peluquero).append("</peluquero>\n");
                xmlContent.append("  </booking>\n");
            }
            
            xmlContent.append("</bookings>");
            
            rs.close();
            stmt.close();
            conn.close();
            
            // Crear el archivo XML y escribir el contenido
            String filePath = "database.xml";
            File file = new File(filePath);
            FileWriter writer = new FileWriter(file);
            writer.write(xmlContent.toString());
            writer.close();
            
            // Descargar el archivo XML
            response.setContentType("application/xml");
            response.setContentLength((int) file.length());
            response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
            
            FileInputStream fileInputStream = new FileInputStream(file);
            ServletOutputStream servletOutputStream = response.getOutputStream();
            
            byte[] outputByte = new byte[4096];
            while (fileInputStream.read(outputByte, 0, 4096) != -1) {
                servletOutputStream.write(outputByte, 0, 4096);
            }
            
            fileInputStream.close();
            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    %>
</body>
</html>
