import Clases.Reserva;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ReservarJugador {
    public JPanel jugadorPanel;
    private JTextField cedulaText;
    private JTextField idCanchaText;
    private JTextField fechaText;
    private JTextField horaIniText;
    private JTextField horaFinText;
    private JTextArea mostrarArea;
    private JButton reservarButton;
    private JButton mostrarReservasButton;
    private JButton salirButton;

    public ReservarJugador() {
        reservarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reservarCancha();
            }
        });
        mostrarReservasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarReservas();
            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setTitle("Login");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setContentPane(new Login().loginPane);
                frame.pack();
                frame.setSize(700, 500);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
        });
    }

    private void reservarCancha() {
        Reserva reserva = new Reserva();
        reserva.setCedulaFK(cedulaText.getText());
        reserva.setIdCanchaFK(Integer.parseInt(idCanchaText.getText()));
        reserva.setFecha(fechaText.getText());
        reserva.setHoraInicio(horaIniText.getText());
        reserva.setHoraFin(horaFinText.getText());
        reserva.insertar();
        mostrarReservas();
    }

    private void mostrarReservas() {
        List<Reserva> reservas = Reserva.obtenerTodas(); // Cambiar esto para filtrar por el jugador actual

        // Construir el texto para el JTextArea
        StringBuilder sb = new StringBuilder();
        sb.append("Reservas:\n\n");

        for (Reserva reserva : reservas) {
            sb.append("ID Reserva: ").append(reserva.getIdReserva()).append("\n");
            sb.append("Cédula Usuario: ").append(reserva.getCedulaFK()).append("\n");
            sb.append("ID Cancha: ").append(reserva.getIdCanchaFK()).append("\n");
            sb.append("Fecha: ").append(reserva.getFecha()).append("\n");
            sb.append("Hora Inicio: ").append(reserva.getHoraInicio()).append("\n");
            sb.append("Hora Fin: ").append(reserva.getHoraFin()).append("\n");
            sb.append("----------------------------\n");
        }

        mostrarArea.setText(sb.toString());
    }
}
