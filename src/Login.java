import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Login {
    public JPanel loginPane;
    private JTextField userText;
    private JPasswordField contraseniaText;
    private JComboBox<String> comboBox1;
    private JButton enviarButton;
    private JButton registrateButton;

    public Login() {
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String item = (String) comboBox1.getSelectedItem();
                String user = userText.getText();
                String password = Arrays.toString(contraseniaText.getPassword());

                if ( !user.isEmpty() && !password.isEmpty() && !"Seleccione una opción".equals(item) ){
                    if("Administrador".equals(item)){
                        JFrame frame = new JFrame();
                        frame.setTitle("Gestión de usuarios");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setContentPane(new Gestion().gestionPanel);
                        frame.pack();
                        frame.setSize(800, 600);
                        frame.setVisible(true);
                        frame.setLocationRelativeTo(null);
                        ((JFrame) SwingUtilities.getWindowAncestor(enviarButton)).dispose();
                    }
                    else if("Encargado/Jugador".equals(item)){
                        JFrame frame = new JFrame();
                        frame.setTitle("Gestión de usuarios");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setContentPane(new Reserva().reservaPanel);
                        frame.pack();
                        frame.setSize(800, 600);
                        frame.setVisible(true);
                        frame.setLocationRelativeTo(null);
                        ((JFrame) SwingUtilities.getWindowAncestor(enviarButton)).dispose();
                    }
                }
                else {
                    JOptionPane.showInputDialog(null,"sexo");
                }
            }
        });
        registrateButton.addActionListener(new ActionListener() {
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
                ((JFrame) SwingUtilities.getWindowAncestor(enviarButton)).dispose();
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }
}
