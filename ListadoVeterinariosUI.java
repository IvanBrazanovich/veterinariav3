
import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

public class ListadoVeterinariosUI extends JPanel {
    private VeterinariaController controller;

    public ListadoVeterinariosUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void setController(VeterinariaController controller) {
        this.controller = controller;
    }

    public void actualizarListadoVeterinarios(ArrayList<Empleado> veterinarios) {

        removeAll();

        for (Empleado veterinario : veterinarios) {
            JPanel empleadoPanel = new JPanel();
            empleadoPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
            empleadoPanel.setBackground(new Color(245, 245, 245)); // Mismo color de fondo

            // Etiqueta con el nombre del veterinario
            JLabel empleadoLabel = new JLabel(veterinario.getNombre());
            empleadoPanel.add(empleadoLabel);

            CustomizedButton deleteButton = new CustomizedButton("Eliminar", EnumsList.ButtonStyle.RED_BUTTON);
            CustomizedButton editButton = new CustomizedButton("Editar", EnumsList.ButtonStyle.LIGHT_PURPLE_BUTTON);
            CustomizedButton VerMas = new CustomizedButton("Ver Más", EnumsList.ButtonStyle.GREY_PURPLE_BUTTON);

            editButton.addActionListener(e -> controller.editarVeterinario(veterinario));
            deleteButton.addActionListener(e -> controller.eliminarVeterinario(veterinario.getIdEmpleado()));
            VerMas.addActionListener(e -> controller.verVeterinario(veterinario));

            empleadoPanel.add(empleadoLabel);
            empleadoPanel.add(deleteButton);
            empleadoPanel.add(editButton);
            empleadoPanel.add(VerMas);

            // Ajustar el tamaño máximo del panel
            empleadoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, empleadoPanel.getPreferredSize().height));

            add(empleadoPanel);
            add(Box.createVerticalStrut(10)); // Espacio entre paneles de veterinarios
        }

        // Asegura que el componente se actualice de inmediato
        revalidate();
        repaint();
    }

}
