import Clases.Cancha;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GestionarCanchas {
    public JPanel reservaPanel;
    private JTextField idCanchaText;
    private JTextField nombreCanchaText;
    private JTextField direccionCanchaText;
    private JButton registrarActualizarButton;
    private JButton eliminarButton;
    private JTextArea textArea1;
    private JButton refrescarPáginaButton;
    private JButton salirButton;

    public GestionarCanchas() {

        registrarActualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idCancha = idCanchaText.getText();
                String nombre = nombreCanchaText.getText();
                String direccion = direccionCanchaText.getText();

                Cancha cancha = new Cancha(idCancha, nombre, direccion);
                try {
                    if (Cancha.obtenerPorId(idCancha) == null) {
                        cancha.insertar();
                    } else {
                        cancha.actualizar();
                    }
                    cargar();
                    idCanchaText.setText("");
                    nombreCanchaText.setText("");
                    direccionCanchaText.setText("");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al guardar la cancha: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idCancha = idCanchaText.getText();
                try {
                    Cancha.eliminar(idCancha);
                    cargar();
                    idCanchaText.setText("");
                    nombreCanchaText.setText("");
                    direccionCanchaText.setText("");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar la cancha: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        refrescarPáginaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargar();
            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setTitle("Menu Administrativo");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setContentPane(new Menu().menuPanel);
                frame.pack();
                frame.setSize(700, 500);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
        });
    }
    private void cargar() {
        textArea1.setText("");
        try {
            List<Cancha> canchas = Cancha.obtenerTodas();
            for (Cancha cancha : canchas) {
                textArea1.append("ID: " + cancha.getIdCancha() + ", Nombre: " + cancha.getNombre() + ", Dirección: " + cancha.getDireccion() + "\n");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar las canchas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
