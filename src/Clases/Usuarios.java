package Clases;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Usuarios {
    String cedula;
    String nombre;
    String apellido;
    String correo;
    String direccion;
    String contrasenia;
    String tipoUsuario;


    public Usuarios(String cedula, String nombre, String apellido, String correo, String direccion, String contrasenia, String tipoUsuario) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.direccion = direccion;
        this.contrasenia = contrasenia;
        this.tipoUsuario = tipoUsuario;
    }

    public Usuarios() {

    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    String url = "jdbc:mysql://localhost:3307/proyecto_poo";
    String user = "root";
    String password = "123456";

    public String login(String cedula, String contrasenia) {
        String sql = "SELECT contrasenia, tipoUsuario FROM Usuarios WHERE cedula = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, cedula);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String storedPasswordHash = resultSet.getString("contrasenia");
                String tipoUsuario = resultSet.getString("tipo_usuario");
                return tipoUsuario;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public boolean registrarUsuario(Usuarios usuario) {
        String sql = "INSERT INTO Usuario (cedula, nombre, apellido, correo, direccion, contrasenia, tipoUsuario) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, usuario.getCedula());
            preparedStatement.setString(2, usuario.getNombre());
            preparedStatement.setString(3, usuario.getApellido());
            preparedStatement.setString(4, usuario.getCorreo());
            preparedStatement.setString(5, usuario.getDireccion());
            preparedStatement.setString(6, (usuario.getContrasenia())); // Usa el hash
            preparedStatement.setString(7, usuario.getTipoUsuario());

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Usuarios obtenerPorCedula(String cedula) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuarios usuario = null;

        try {
            String url = "jdbc:mysql://localhost:3307/proyecto_poo";
            String user = "root";
            String password = "123456";
            conn = DriverManager.getConnection(url, user, password);

            // Consulta SQL
            String query = "SELECT * FROM Usuario WHERE cedula = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, cedula);
            rs = stmt.executeQuery();

            // Procesar resultados
            if (rs.next()) {
                usuario = new Usuarios();
                usuario.setCedula(rs.getString("cedula"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setContrasenia(rs.getString("contrasenia"));
                usuario.setTipoUsuario(rs.getString("tipoUsuario"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de errores
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return usuario;
    }
    public void actualizar() {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            String url = "jdbc:mysql://localhost:3307/proyecto_poo";
            String user = "root";
            String password = "123456";
            conn = DriverManager.getConnection(url, user, password);
            String query = "UPDATE Usuario SET nombre = ?, apellido = ?, correo = ?, direccion = ?, cedula = ? WHERE cedula = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, correo);
            stmt.setString(4, direccion);
            stmt.setString(5, cedula);
            stmt.setString(6, cedula);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de errores
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}



