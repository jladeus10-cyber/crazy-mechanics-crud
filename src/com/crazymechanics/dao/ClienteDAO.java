package com.crazymechanics.dao;

import com.crazymechanics.conexion.ConexionBD;
import com.crazymechanics.modelo.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteDAO {

    // 1. REGISTRAR (Create)
    public boolean registrarCliente(Cliente c) {
        String sql = "INSERT INTO clientes (nombre, apellido, telefono, correo) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellido());
            ps.setString(3, c.getTelefono());
            ps.setString(4, c.getCorreo());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar: " + e.getMessage());
            return false;
        }
    }

    // 2. CONSULTAR / LISTAR (Read)
    public List<Cliente> listarClientes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Connection conn = ConexionBD.obtenerConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setTelefono(rs.getString("telefono"));
                c.setCorreo(rs.getString("correo"));
                lista.add(c);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar: " + e.getMessage());
        }
        return lista;
    }

    // 3. ACTUALIZAR (Update)
    public boolean actualizarCliente(Cliente c) {
        String sql = "UPDATE clientes SET nombre = ?, apellido = ?, telefono = ? WHERE correo = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellido());
            ps.setString(3, c.getTelefono());
            ps.setString(4, c.getCorreo()); // El correo actúa como identificador único
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar: " + e.getMessage());
            return false;
        }
    }

    // 4. ELIMINAR (Delete)
    public boolean eliminarCliente(String correo) {
        String sql = "DELETE FROM clientes WHERE correo = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, correo);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar: " + e.getMessage());
            return false;
        }
    }
}