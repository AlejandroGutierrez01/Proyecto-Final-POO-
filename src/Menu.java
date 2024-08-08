import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    public JPanel menuPanel;
    private JButton gestionarCanchasButton;
    private JButton gestionarJugadoresUsuariosButton;
    private JButton gestionarHorariosButton;
    private JButton cerrarSesionButton;
    private JLabel imgLabel;

    public Menu() {
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Images/icon.png"));
        Image image = imageIcon.getImage();
        Image tamanio = image.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        ImageIcon imageIcon1 = new ImageIcon(tamanio);
        imgLabel.setIcon(imageIcon1);

        gestionarCanchasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setTitle("Gestion de Canchas");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setContentPane(new GestionarCanchas().reservaPanel);
                frame.pack();
                frame.setSize(700, 500);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
                ((JFrame) SwingUtilities.getWindowAncestor(gestionarCanchasButton)).dispose();
            }
        });
        gestionarJugadoresUsuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setTitle("Gestion de usuarios");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setContentPane(new GestionJugadores().gestionPanel);
                frame.pack();
                frame.setSize(800, 600);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
                ((JFrame) SwingUtilities.getWindowAncestor(gestionarJugadoresUsuariosButton)).dispose();
            }
        });
        gestionarHorariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setTitle("Gestion de Reservas");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setContentPane(new GestionarReservas().reservasPanel);
                frame.pack();
                frame.setSize(700, 500);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
                ((JFrame) SwingUtilities.getWindowAncestor(gestionarHorariosButton)).dispose();
            }
        });
        cerrarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setTitle("Iniciar Sesion");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setContentPane(new Login().loginPane);
                frame.pack();
                frame.setSize(700, 500);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
                ((JFrame) SwingUtilities.getWindowAncestor(cerrarSesionButton)).dispose();
            }
        });
    }
}
