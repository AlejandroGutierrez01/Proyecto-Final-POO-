import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class EliminarJugadores {
    public JPanel eliminarPanel;
    private JTextPane textPanel;
    private JTextField cedulaText;
    private JButton buscarButton;
    private JButton eliminarButton;

    public EliminarJugadores() {
        // Manejo del evento para buscar jugador
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarJugador();
            }
        });

        // Manejo del evento para eliminar jugador
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarJugador();
            }
        });
    }

    private void buscarJugador() {
        String cedula = cedulaText.getText();
        if (cedula.isEmpty()) {
            textPanel.setText("Por favor, ingresa una cédula.");
            return;
        }

        // Establece la conexión a la base de datos
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/proyecto_poo", "root", "123456")) {
            // Prepara la consulta SQL
            String sql = "SELECT * FROM Usuario WHERE cedula = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, cedula);

                // Ejecuta la consulta
                ResultSet rs = stmt.executeQuery();

                // Procesa los resultados
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");
                    String correo = rs.getString("correo");
                    String direccion = rs.getString("direccion");
                    String tipoUsuario = rs.getString("tipoUsuario");

                    String resultado = String.format("Cédula: %s\nNombre: %s %s\nCorreo: %s\nDirección: %s\nTipo Usuario: %s",
                            cedula, nombre, apellido, correo, direccion, tipoUsuario);
                    textPanel.setText(resultado);
                } else {
                    textPanel.setText("Jugador no encontrado.");
                }
            }
        } catch (SQLException ex) {
            textPanel.setText("Error al buscar jugador: " + ex.getMessage());
        }
    }


    private void eliminarJugador() {
        String cedula = cedulaText.getText();
        // Aquí se eliminará el jugador de la base de datos
        int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres eliminar el jugador con cédula " + cedula + "?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/proyecto_poo", "root", "123456");
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM Usuario WHERE cedula = ?");
                stmt.setString(1, cedula);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Jugador eliminado exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "Jugador no encontrado.");
                }
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al eliminar jugador: " + ex.getMessage());
            }
        }
    }
}
