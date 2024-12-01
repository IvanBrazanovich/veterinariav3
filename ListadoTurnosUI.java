
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

//Listado de turnos UI
public class ListadoTurnosUI extends JPanel {

    private VeterinariaController controller;

    public ListadoTurnosUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
    // Generar UI

    public void setController(VeterinariaController controller) {
        this.controller = controller;
    }

    public void actualizarListadoTurnos(ArrayList<Turno> turnos) {

        removeAll();

        for (Turno turno : turnos) {
            JPanel turnoPanel = new JPanel();
            turnoPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
            turnoPanel.setBackground(new Color(245, 245, 245));

            // Etiqueta con el nombre del cliente
            JLabel clienteLabel = new JLabel(turno.getObservacion());
            turnoPanel.add(clienteLabel);

            // Botón de editar
            CustomizedButton deleteButton = new CustomizedButton("Eliminar", EnumsList.ButtonStyle.RED_BUTTON);
            CustomizedButton editButton = new CustomizedButton("Editar", EnumsList.ButtonStyle.LIGHT_PURPLE_BUTTON);
            CustomizedButton verMas = new CustomizedButton("Ver Más", EnumsList.ButtonStyle.GREY_PURPLE_BUTTON);

            editButton.addActionListener(e -> controller.editarTurno(turno));
            deleteButton.addActionListener(e -> controller.eliminarTurno(turno.getIdTurno()));
            verMas.addActionListener(e -> controller.verTurno(turno));

            turnoPanel.add(clienteLabel);
            turnoPanel.add(deleteButton);
            turnoPanel.add(editButton);
            turnoPanel.add(verMas);

            // Asegura que el panel se ajuste automáticamente
            turnoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, turnoPanel.getPreferredSize().height));

            add(turnoPanel);
            add(Box.createVerticalStrut(10)); // Espacio entre paneles de clientes
        }

        revalidate();
        repaint();
    }

}
