import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    public JPanel loginPane;
    private JTextField userText;
    private JPasswordField contraseniaText;
    private JComboBox<String> comboBox1;
    private JButton loginBtn;
    private JButton registrateBtn;

    public Login() {
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipoUsuario = (String) comboBox1.getSelectedItem();
                String usuario = userText.getText();
                String contrasenia = new String(contraseniaText.getPassword());

                if (!usuario.isEmpty() && !contrasenia.isEmpty() && !"Seleccione una opción".equals(tipoUsuario)) {
                    if (validarLogin(usuario, contrasenia, tipoUsuario)) {
                        if ("Administrador".equals(tipoUsuario)) {
                            JFrame frame = new JFrame();
                            frame.setTitle("Gestión de usuarios");
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frame.setContentPane(new GestionJugadores().gestionPanel);
                            frame.pack();
                            frame.setSize(800, 600);
                            frame.setVisible(true);
                            frame.setLocationRelativeTo(null);
                        } else if ("Jugador".equals(tipoUsuario)) {
                            JFrame frame = new JFrame();
                            frame.setTitle("Reserva");
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frame.setContentPane(new ReservarJugador().jugadorPanel);
                            frame.pack();
                            frame.setSize(800, 600);
                            frame.setVisible(true);
                            frame.setLocationRelativeTo(null);
                        }
                        // Cierra el panel de login después del login exitoso
                        ((JFrame) SwingUtilities.getWindowAncestor(loginBtn)).dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Credenciales inválidas.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos y seleccione un tipo de usuario.");
                }
            }
        });

        registrateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setTitle("Registro de usuarios");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setContentPane(new Registro().registroPanel);
                frame.pack();
                frame.setSize(800, 600);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
                ((JFrame) SwingUtilities.getWindowAncestor(registrateBtn)).dispose();
            }
        });
    }

    private boolean validarLogin(String usuario, String contrasenia, String tipoUsuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean valido = false;

        try {
            // Conexión a la base de datos
            String url = "jdbc:mysql://localhost:3307/proyecto_poo";
            String user = "root";
            String password = "123456";
            conn = DriverManager.getConnection(url, user, password);

            // Consulta SQL para validar el login
            String query = "SELECT * FROM Usuario WHERE cedula = ? AND contrasenia = ? AND tipoUsuario = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, usuario);
            stmt.setString(2, contrasenia);
            stmt.setString(3, tipoUsuario);
            rs = stmt.executeQuery();

            // Verificar si existe un usuario que coincida con las credenciales
            if (rs.next()) {
                valido = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return valido;
    }
}
