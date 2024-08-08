package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private int idReserva;
    private String cedulaFK;
    private int idCanchaFK;
    private String fecha;
    private String horaInicio;
    private String horaFin;

    // Constructor
    public Reserva() {}

    public Reserva(int idReserva, String cedulaFK, int idCanchaFK, String fecha, String horaInicio, String horaFin) {
        this.idReserva = idReserva;
        this.cedulaFK = cedulaFK;
        this.idCanchaFK = idCanchaFK;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public String getCedulaFK() {
        return cedulaFK;
    }

    public void setCedulaFK(String cedulaFK) {
        this.cedulaFK = cedulaFK;
    }

    public int getIdCanchaFK() {
        return idCanchaFK;
    }

    public void setIdCanchaFK(int idCanchaFK) {
        this.idCanchaFK = idCanchaFK;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
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
        String sql = "INSERT INTO Reserva (cedulaFK, idCanchaFK, fecha, horaInicio, horaFin) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cedulaFK);
            stmt.setInt(2, idCanchaFK);
            stmt.setString(3, fecha);
            stmt.setString(4, horaInicio);
            stmt.setString(5, horaFin);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar la reserva: " + e.getMessage());
        }
    }

    public static void eliminar(int idReserva) {
        String sql = "DELETE FROM Reserva WHERE idReserva = ?";
        try (Connection connection = connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idReserva);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar la reserva: " + e.getMessage());
        }
    }

    public void actualizar() {
        String sql = "UPDATE Reserva SET cedulaFK = ?, idCanchaFK = ?, fecha = ?, horaInicio = ?, horaFin = ? WHERE idReserva = ?";
        try (Connection connection = connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cedulaFK);
            stmt.setInt(2, idCanchaFK);
            stmt.setString(3, fecha);
            stmt.setString(4, horaInicio);
            stmt.setString(5, horaFin);
            stmt.setInt(6, idReserva);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar la reserva: " + e.getMessage());
        }
    }

    public static Reserva obtenerPorId(int idReserva) {
        Reserva reserva = null;
        String sql = "SELECT * FROM Reserva WHERE idReserva = ?";
        try (Connection connection = connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idReserva);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    reserva = new Reserva();
                    reserva.setIdReserva(rs.getInt("idReserva"));
                    reserva.setCedulaFK(rs.getString("cedulaFK"));
                    reserva.setIdCanchaFK(rs.getInt("idCanchaFK"));
                    reserva.setFecha(rs.getString("fecha"));
                    reserva.setHoraInicio(rs.getString("horaInicio"));
                    reserva.setHoraFin(rs.getString("horaFin"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la reserva: " + e.getMessage());
        }
        return reserva;
    }

    // Get all reservations
    public static List<Reserva> obtenerTodas() {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM Reserva";
        try (Connection connection = connect();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setIdReserva(rs.getInt("idReserva"));
                reserva.setCedulaFK(rs.getString("cedulaFK"));
                reserva.setIdCanchaFK(rs.getInt("idCanchaFK"));
                reserva.setFecha(rs.getString("fecha"));
                reserva.setHoraInicio(rs.getString("horaInicio"));
                reserva.setHoraFin(rs.getString("horaFin"));
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener las reservas: " + e.getMessage());
        }
        return reservas;
    }
    public static List<Reserva> obtenerPorCedula(String cedula) {
        List<Reserva> reservas = new ArrayList<>();
        String query = "SELECT * FROM Reserva WHERE cedulaFK = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/proyecto_poo", "root", "123456");
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, cedula);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Reserva reserva = new Reserva();
                    reserva.setIdReserva(rs.getInt("idReserva"));
                    reserva.setCedulaFK(rs.getString("cedulaFK"));
                    reserva.setIdCanchaFK(rs.getInt("idCanchaFK"));
                    reserva.setFecha(rs.getString("fecha"));
                    reserva.setHoraInicio(rs.getString("horaInicio"));
                    reserva.setHoraFin(rs.getString("horaFin"));
                    reservas.add(reserva);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservas;
    }
}
