import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registro {
    public JPanel registroPanel;
    private JTextField cedulaText;
    private JTextField nombreText;
    private JTextField verificarText;
    private JTextField contraseniaText;
    private JTextField apellidoText;
    private JTextField telefonoText;
    private JTextField direccionText;
    private JCheckBox aceptoLosTerminosYCheckBox;
    private JButton enviarButton;
    private JButton salirButton;

    public Registro() {
        enviarButton.addActionListener(new ActionListener() {
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
