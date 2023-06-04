package com.peluqueria.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase que representa una peluquería.
 */
public class Peluqueria {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/database";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private List<Cliente> listaClientes;
    private List<Cita> listaCitas;
    private Map<String, Integer> mapaServicios;

    public Peluqueria() {
        listaClientes = new ArrayList<>();
        listaCitas = new ArrayList<>();
        mapaServicios = new HashMap<>();
    }

    /**
     * Busca un cliente en la base de datos.
     *
     * @param cliente El cliente a buscar.
     * @return true si el cliente se encuentra en la base de datos, false en caso contrario.
     */
    public boolean buscaCliente(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean encontrado = false;

        try {
            conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            String sql = "SELECT * FROM clientes WHERE name = ? AND email = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getName());
            stmt.setString(2, cliente.getEmail());
            rs = stmt.executeQuery();

            encontrado = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }

        return encontrado;
    }

    /**
     * Agrega un nuevo cliente a la base de datos y a la lista de clientes.
     *
     * @param cliente El cliente a agregar.
     */
    public void nuevoCliente(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            String sql = "INSERT INTO clientes (name, email) VALUES (?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getName());
            stmt.setString(2, cliente.getEmail());
            stmt.executeUpdate();

            listaClientes.add(cliente);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt);
        }
    }

    /**
     * Elimina un cliente de la base de datos y de la lista de clientes.
     *
     * @param cliente El cliente a eliminar.
     */
    public void eliminaCliente(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            String sql = "DELETE FROM clientes WHERE name = ? AND email = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getName());
            stmt.setString(2, cliente.getEmail());
            stmt.executeUpdate();

            listaClientes.remove(cliente);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt);
        }
    }

    /**
     * Busca una cita en la base de datos.
     *
     * @param cliente El cliente asociado a la cita a buscar.
     * @return true si la cita se encuentra en la base de datos, false en caso contrario.
     */
    public boolean buscaCita(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean encontrado = false;

        try {
            conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            String sql = "SELECT * FROM citas WHERE name = ? AND email = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getName());
            stmt.setString(2, cliente.getEmail());
            rs = stmt.executeQuery();

            encontrado = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }

        return encontrado;
    }

    /**
     * Elimina una cita de la base de datos.
     *
     * @param cliente El cliente asociado a la cita a eliminar.
     */
    public void eliminaCita(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            String sql = "DELETE FROM citas WHERE name = ? AND email = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getName());
            stmt.setString(2, cliente.getEmail());
            stmt.executeUpdate();

            Cita cita = buscarCita(cliente.getName(), cliente.getEmail());
            if (cita != null) {
                listaCitas.remove(cita);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt);
        }
    }

    /**
     * Modifica una cita en la base de datos.
     *
     * @param cliente El cliente asociado a la cita a modificar.
     * @param cita    La nueva información de la cita.
     */
    public void modificaCita(Cliente cliente, Cita cita) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            String sql = "UPDATE citas SET name = ?, email = ?, phone = ?, date = ?, time = ?, service = ?, hairdresser = ? WHERE name = ? AND email = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cita.getName());
            stmt.setString(2, cita.getEmail());
            stmt.setString(3, cita.getPhone());
            stmt.setString(4, cita.getDate());
            stmt.setString(5, cita.getTime());
            stmt.setString(6, cita.getService());
            stmt.setString(7, cita.getHairdresser());
            stmt.setString(8, cliente.getName());
            stmt.setString(9, cliente.getEmail());
            stmt.executeUpdate();

            Cita citaAnterior = buscarCita(cliente.getName(), cliente.getEmail());
            if (citaAnterior != null) {
                listaCitas.remove(citaAnterior);
                listaCitas.add(cita);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt);
        }
    }

    /**
     * Modifica un cliente en la base de datos.
     *
     * @param cliente    El cliente a modificar.
     * @param nuevoDatos La nueva información del cliente.
     */
    public void modificaCliente(Cliente cliente, Cliente nuevoDatos) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            String sql = "UPDATE clientes SET name = ?, email = ? WHERE name = ? AND email = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nuevoDatos.getName());
            stmt.setString(2, nuevoDatos.getEmail());
            stmt.setString(3, cliente.getName());
            stmt.setString(4, cliente.getEmail());
            stmt.executeUpdate();

            Cliente clienteModificado = buscarCliente(cliente.getName(), cliente.getEmail());
            if (clienteModificado != null) {
                listaClientes.remove(cliente);
                listaClientes.add(nuevoDatos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt);
        }
    }

    /**
     * Agrega una nueva cita a la base de datos y a la lista de citas.
     *
     * @param cita La cita a agregar.
     */
    public void nuevaCita(Cita cita) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            String sql = "INSERT INTO citas (name, email, phone, date, time, service, hairdresser) VALUES (?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cita.getName());
            stmt.setString(2, cita.getEmail());
            stmt.setString(3, cita.getPhone());
            stmt.setString(4, cita.getDate());
            stmt.setString(5, cita.getTime());
            stmt.setString(6, cita.getService());
            stmt.setString(7, cita.getHairdresser());
            stmt.executeUpdate();

            listaCitas.add(cita);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt);
        }
    }

    /**
     * Busca un cliente en la lista de clientes.
     *
     * @param name  El nombre del cliente a buscar.
     * @param email El email del cliente a buscar.
     * @return El objeto Cliente si se encuentra en la lista, o null si no se encuentra.
     */
    public Cliente buscarCliente(String name, String email) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getName().equals(name) && cliente.getEmail().equals(email)) {
                return cliente;
            }
        }
        return null;
    }

    /**
     * Busca una cita en la lista de citas.
     *
     * @param name  El nombre del cliente asociado a la cita a buscar.
     * @param email El email del cliente asociado a la cita a buscar.
     * @return El objeto Cita si se encuentra en la lista, o null si no se encuentra.
     */
    public Cita buscarCita(String name, String email) {
        for (Cita cita : listaCitas) {
            if (cita.getName().equals(name) && cita.getEmail().equals(email)) {
                return cita;
            }
        }
        return null;
    }

    /**
     * Cierra la conexión y libera los recursos utilizados por la base de datos.
     *
     * @param conn La conexión a cerrar.
     * @param stmt El objeto PreparedStatement a cerrar.
     */
    private void closeResources(Connection conn, PreparedStatement stmt) {
        closeResources(conn, stmt, null);
    }

    /**
     * Cierra la conexión y libera los recursos utilizados por la base de datos.
     *
     * @param conn La conexión a cerrar.
     * @param stmt El objeto PreparedStatement a cerrar.
     * @param rs   El objeto ResultSet a cerrar.
     */
    private void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
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
