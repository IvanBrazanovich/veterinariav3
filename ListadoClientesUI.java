
import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

public class ListadoClientesUI extends JPanel {
    private VeterinariaController controller;

    public ListadoClientesUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void setController(VeterinariaController controller) {
        this.controller = controller;
    }

    public void actualizarListadoClientes(ArrayList<Cliente> clientes) {

        removeAll();

        for (Cliente cliente : clientes) {
            JPanel clientePanel = new JPanel();
            clientePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
            clientePanel.setBackground(new Color(245, 245, 245));

            // Etiqueta con el nombre del cliente
            JLabel clienteLabel = new JLabel(cliente.getNombre());
            clientePanel.add(clienteLabel);

            // Botón de editar
            CustomizedButton deleteButton = new CustomizedButton("Eliminar", EnumsList.ButtonStyle.RED_BUTTON);
            CustomizedButton editButton = new CustomizedButton("Editar", EnumsList.ButtonStyle.LIGHT_PURPLE_BUTTON);
            CustomizedButton VerMas = new CustomizedButton("Ver Más", EnumsList.ButtonStyle.GREY_PURPLE_BUTTON);

            editButton.addActionListener(e -> controller.editarCliente(cliente));
            deleteButton.addActionListener(e -> controller.eliminarCliente(cliente.getIdCliente()));
            VerMas.addActionListener(e -> controller.verCliente(cliente));

            clientePanel.add(clienteLabel);
            clientePanel.add(deleteButton);
            clientePanel.add(editButton);
            clientePanel.add(VerMas);

            // Asegura que el panel se ajuste automáticamente
            clientePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, clientePanel.getPreferredSize().height));

            add(clientePanel);
            add(Box.createVerticalStrut(10)); // Espacio entre paneles de clientes
        }

        // Actualiza el diseño
        revalidate();
        repaint();
    }

}
