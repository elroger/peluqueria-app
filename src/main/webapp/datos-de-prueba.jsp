<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*, java.util.Random, java.text.SimpleDateFormat, java.util.Calendar" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Datos de Prueba</title>
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
        
        // Generar datos de prueba
        Random random = new Random();
        String[] nombres = {
            "Lionel Messi", "Cristiano Ronaldo", "Neymar Jr.", "Luis Suarez", "Zlatan Ibrahimovic",
            "Sergio Ramos", "Andres Iniesta", "Gareth Bale", "Manuel Neuer", "Robert Lewandowski",
            "Thierry Henry", "Ronaldinho", "David Beckham", "Diego Maradona", "Pele",
            "Zinedine Zidane", "Ronaldo Nazario", "Michel Platini", "Johan Cruyff", "Alfredo Di Stefano"
        };
        String[] servicios = {
            "Corte de pelo", "Afeitado", "Arreglo de barba", "Coloracion de cabello"
        };
        String[] peluqueros = {
            "Luis Alberto", "Juan Carlos", "Ramon Antonio"
        };
        String[] dominiosEmail = {
            "gmail.com", "hotmail.com", "yahoo.com", "outlook.com", "aol.com"
        };
        String[] telefonos = {
            "612345678", "654321987", "687654321", "635241987", "689754321"
        };
        
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO bookings (name, email, phone, date, time, service, hairdresser) VALUES (?, ?, ?, ?, ?, ?, ?)");
        
        Calendar calendar = Calendar.getInstance();
        
        for (int i = 0; i < 60; i++) {
            int randomIndex = random.nextInt(nombres.length);
            String nombre = nombres[randomIndex];
            String email = nombre.toLowerCase().replace(" ", "") + "@" + dominiosEmail[random.nextInt(dominiosEmail.length)];
            String telefono = telefonos[random.nextInt(telefonos.length)];
            
            // Generar fecha aleatoria entre 2020 y 2023 (excluyendo los domingos)
            calendar.set(Calendar.YEAR, random.nextInt(4) + 2020);
            calendar.set(Calendar.MONTH, random.nextInt(12));
            int maxDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            int dayOfMonth = random.nextInt(maxDayOfMonth) + 1;
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            
            // Verificar si el día es domingo
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek == Calendar.SUNDAY) {
                // Si es domingo, se mueve al siguiente día
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
            
            // Formatear la fecha en el formato dd/MM/yyyy
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String fecha = dateFormat.format(calendar.getTime());
            
            // Generar hora aleatoria entre 9:00 y 20:00
            int hour = random.nextInt(12) + 9;
            int minute = random.nextInt(60);
            String hora = String.format("%02d:%02d", hour, minute);
            
            String servicio = servicios[random.nextInt(servicios.length)];
            String peluquero = peluqueros[random.nextInt(peluqueros.length)];
            
            pstmt.setString(1, nombre);
            pstmt.setString(2, email);
            pstmt.setString(3, telefono);
            pstmt.setString(4, fecha);
            pstmt.setString(5, hora);
            pstmt.setString(6, servicio);
            pstmt.setString(7, peluquero);
            
            pstmt.executeUpdate();
        }
        
        pstmt.close();
        conn.close();
        
        response.sendRedirect("admin.jsp");
    } catch (Exception e) {
        e.printStackTrace();
    }
    %>
</body>
</html>
