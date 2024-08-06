import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GestionJugadores {
    public JPanel gestionPanel;
    public JPanel mostrarForms;
    private JRadioButton agregarButton;
    private JRadioButton modifiButton;
    private JRadioButton eliminarButton;

    public GestionJugadores() {

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(agregarButton);
        grupo.add(modifiButton);
        grupo.add(eliminarButton);
        CardLayout cl = new CardLayout();
        mostrarForms.setLayout(cl);
        mostrarForms.add(new ModificarJugadores().modifiJugPanel, "Modificar");
        mostrarForms.add(new EliminarJugadores().eliminarPanel, "Eliminar");
        mostrarForms.add(new Registro().registroPanel,"Registrar");


        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(mostrarForms, "Registrar");
            }
        });
        modifiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(mostrarForms, "Modificar");
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(mostrarForms,"Eliminar");
            }
        });
    }
}