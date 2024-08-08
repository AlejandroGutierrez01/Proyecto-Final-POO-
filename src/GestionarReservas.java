import Clases.Reserva;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GestionarReservas {
    public JPanel reservasPanel;
    private JTextField cedulaRsvText;
    private JTextField idCanchaRsvText;
    private JTextField fechaText;
    private JTextField horaIniText;
    private JTextField horaFinText;
    private JTable reservasTable;
    private JButton agregarButton;
    private JButton actualizarButton;
    private JButton eliminarButton;
    private JButton mostrarButton;
    private ModeloTabla modeloTabla;

    public GestionarReservas() {
        modeloTabla = new ModeloTabla();
        reservasTable.setModel(modeloTabla);

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Reserva reserva = new Reserva();
                reserva.setCedulaFK(cedulaRsvText.getText());
                reserva.setIdCanchaFK(Integer.parseInt(idCanchaRsvText.getText()));
                reserva.setFecha(fechaText.getText());
                reserva.setHoraInicio(horaIniText.getText());
                reserva.setHoraFin(horaFinText.getText());
                reserva.insertar();
                mostrarReservas();
            }
        });

        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = reservasTable.getSelectedRow();
                if (row >= 0) {
                    int idReserva = (int) reservasTable.getValueAt(row, 0);
                    Reserva reserva = new Reserva();
                    reserva.setIdReserva(idReserva);
                    reserva.setCedulaFK(cedulaRsvText.getText());
                    reserva.setIdCanchaFK(Integer.parseInt(idCanchaRsvText.getText()));
                    reserva.setFecha(fechaText.getText());
                    reserva.setHoraInicio(horaIniText.getText());
                    reserva.setHoraFin(horaFinText.getText());
                    reserva.actualizar();
                    mostrarReservas();
                }
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = reservasTable.getSelectedRow();
                if (row >= 0) {
                    int idReserva = (int) reservasTable.getValueAt(row, 0);
                    Reserva.eliminar(idReserva);
                    mostrarReservas();
                }
            }
        });

        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarReservas();
            }
        });
    }

    private void mostrarReservas() {
        List<Reserva> reservas = Reserva.obtenerTodas();
        modeloTabla.setReservas(reservas);
    }

    static class ModeloTabla extends AbstractTableModel {
        private List<Reserva> reservas;
        private final String[] columnNames = {"ID Reserva", "Cédula Usuario", "ID Cancha", "Fecha", "Hora Inicio", "Hora Fin"};

        public ModeloTabla() {
            reservas = new ArrayList<>();
        }

        public void setReservas(List<Reserva> reservas) {
            this.reservas = reservas;
            fireTableDataChanged();
        }

        @Override
        public int getRowCount() {
            return reservas.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Reserva reserva = reservas.get(rowIndex);
            switch (columnIndex) {
                case 0: return reserva.getIdReserva();
                case 1: return reserva.getCedulaFK();
                case 2: return reserva.getIdCanchaFK();
                case 3: return reserva.getFecha();
                case 4: return reserva.getHoraInicio();
                case 5: return reserva.getHoraFin();
                default: return null;
            }
        }

        @Override
        public String getColumnName(int columnIndex) {
            return columnNames[columnIndex];
        }
    }
}
