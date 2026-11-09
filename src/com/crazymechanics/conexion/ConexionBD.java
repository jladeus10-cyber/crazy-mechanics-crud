package com.crazymechanics.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/crazy_mechanics";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "Jorge2020";

    public static Connection obtenerConexion() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            // System.out.println("✅ Conexión exitosa a MySQL");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "❌ Error de conexion: " + e.getMessage());
        }
        return conexion;
    }
}