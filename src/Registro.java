import Clases.Usuarios;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Registro {
    public JPanel registroPanel;
    private JTextField cedulaText;
    private JTextField nombreText;
    private JTextField verificarText;
    private JTextField contraseniaText;
    private JTextField apellidoText;
    private JTextField correoText;
    private JTextField direccionText;
    private JButton enviarButton;
    private JButton salirButton;

    public Registro() {
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = cedulaText.getText();
                String nombre = nombreText.getText();
                String apellido = apellidoText.getText();
                String correo = correoText.getText();
                String direccion = direccionText.getText();
                String contrasenia = contraseniaText.getText();
                String verificarContra = verificarText.getText();
                String tipoUsuario = "administrador";
                if(Objects.equals(contrasenia, verificarContra)){
                    Usuarios usuario = new Usuarios(cedula, nombre, apellido, correo, direccion, contrasenia, tipoUsuario);
                    boolean registrado = usuario.registrarUsuario(usuario);
                    if (registrado) {
                        JOptionPane.showMessageDialog(null, "Registro exitoso.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al registrar usuario.");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,"Las contraseñas no coinciden");
                }
            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setTitle("Iniciar Sesión");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setContentPane(new Login().loginPane);
                frame.pack();
                frame.setSize(800, 600);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);

            }
        });
    }
}
