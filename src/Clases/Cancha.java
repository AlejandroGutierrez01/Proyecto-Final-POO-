package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Cancha {
    String idCancha;
    String nombre;
    String direccion;

    // Constructor
    public Cancha() {

    }

    public Cancha(String idCancha, String nombre, String direccion) {
        this.idCancha = idCancha;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public String getIdCancha() {
        return idCancha;
    }

    public void setIdCancha(String idCancha) {
        this.idCancha = idCancha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    private static Connection connect() {
        Connection connection = null;
        try {
            String url = "jdbc:mysql://localhost:3307/proyecto_poo";
            String user = "root";
            String password = "123456";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
        return connection;
    }

    public void insertar() {
        String sql = "INSERT INTO Cancha (idCancha, nombre, ubicacion) VALUES (?, ?, ?)";
        try (Connection connection = connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idCancha);
            stmt.setString(2, nombre);
            stmt.setString(3, direccion);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar la cancha: " + e.getMessage());
        }
    }

    public static void eliminar(String idCancha) {
        String sql = "DELETE FROM Cancha WHERE idCancha = ?";
        try (Connection connection = connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idCancha);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar la cancha: " + e.getMessage());
        }
    }

    public void actualizar() {
        String sql = "UPDATE Cancha SET nombre = ?, ubicacion = ? WHERE idCancha = ?";
        try (Connection connection = connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, direccion);
            stmt.setString(3, idCancha);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar la cancha: " + e.getMessage());
        }
    }

    public static Cancha obtenerPorId(String idCancha) {
        Cancha cancha = null;
        String sql = "SELECT * FROM Cancha WHERE idCancha = ?";
        String url = "jdbc:mysql://localhost:3307/proyecto_poo";
        String user = "root";
        String password = "123456";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idCancha);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cancha = new Cancha();
                    cancha.setIdCancha(rs.getString("idCancha"));
                    cancha.setNombre(rs.getString("nombre"));
                    cancha.setDireccion(rs.getString("ubicacion"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la cancha: " + e.getMessage());
        }
        return cancha;
    }

    public static List<Cancha> obtenerTodas() {
        List<Cancha> canchas = new ArrayList<>();
        String sql = "SELECT * FROM Cancha";
        String url = "jdbc:mysql://localhost:3307/proyecto_poo";
        String user = "root";
        String password = "123456";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Cancha cancha = new Cancha();
                cancha.setIdCancha(rs.getString("idCancha"));
                cancha.setNombre(rs.getString("nombre"));
                cancha.setDireccion(rs.getString("ubicacion"));
                canchas.add(cancha);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener las canchas: " + e.getMessage());
        }
        return canchas;
    }
}
