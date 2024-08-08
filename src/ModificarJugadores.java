import Clases.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificarJugadores {
    public JPanel modifiJugPanel;
    private JTextField cedulaText;
    private JTextField nuevaCedulaText;
    private JTextField nuevoNombreText;
    private JTextField nuevoApellidoText;
    private JTextField nuevoCorreoText;
    private JTextField nuevaDireccionText;
    private JButton modificarButton;
    private JButton buscarButton;

    public ModificarJugadores() {
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarJugador();
            }
        });

        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarJugador();
            }
        });
    }

    private void buscarJugador() {
        String cedula = cedulaText.getText();
        Usuarios usuario = Usuarios.obtenerPorCedula(cedula); // Método para obtener el usuario de la base de datos

        if (usuario != null) {
            nuevaCedulaText.setText(usuario.getCedula());
            nuevoNombreText.setText(usuario.getNombre());
            nuevoApellidoText.setText(usuario.getApellido());
            nuevoCorreoText.setText(usuario.getCorreo());
            nuevaDireccionText.setText(usuario.getDireccion());
        } else {
            JOptionPane.showMessageDialog(null,"Jugador no encontrado.");
        }
    }

    private void modificarJugador() {
        String cedula = cedulaText.getText();
        String nuevaCedula = nuevaCedulaText.getText();
        String nuevoNombre = nuevoNombreText.getText();
        String nuevoApellido = nuevoApellidoText.getText();
        String nuevoCorreo = nuevoCorreoText.getText();
        String nuevaDireccion = nuevaDireccionText.getText();

        Usuarios usuario = Usuarios.obtenerPorCedula(cedula);

        if (usuario != null) {
            usuario.setCedula(nuevaCedula);
            usuario.setNombre(nuevoNombre);
            usuario.setApellido(nuevoApellido);
            usuario.setCorreo(nuevoCorreo);
            usuario.setDireccion(nuevaDireccion);
            usuario.actualizar(); // Método para actualizar el usuario en la base de datos
            JOptionPane.showMessageDialog(null,"Jugador modificado exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null,"Jugador no encontrado.");
        }
    }
}
