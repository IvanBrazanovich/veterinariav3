
import javax.swing.*;

import java.awt.*;

import java.util.ArrayList;

public class ListadoMascotasUI extends JPanel {
    private VeterinariaController controller;

    public ListadoMascotasUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    }

    public void setController(VeterinariaController controller) {
        this.controller = controller;
    }

    public void actualizarListadoMascotas(ArrayList<Mascota> mascotas) {

        removeAll();

        for (Mascota mascota : mascotas) {
            JPanel petPanel = new JPanel();
            petPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
            petPanel.setBackground(new Color(245, 245, 245));

            // Etiqueta con el nombre de la mascota
            JLabel mascotaLabel = new JLabel(mascota.getNombre());
            petPanel.add(mascotaLabel);

            // Si tiene owner
            if (mascota.getOwnerFk() != null) {
                JLabel ownerLabel = new JLabel("Owner: " + mascota.getOwnerFk());
                petPanel.add(ownerLabel);
            }

            // Botón de editar
            CustomizedButton deleteButton = new CustomizedButton("Eliminar", EnumsList.ButtonStyle.RED_BUTTON);
            CustomizedButton editButton = new CustomizedButton("Editar", EnumsList.ButtonStyle.LIGHT_PURPLE_BUTTON);
            CustomizedButton VerMas = new CustomizedButton("Ver Más", EnumsList.ButtonStyle.GREY_PURPLE_BUTTON);

            editButton.addActionListener(e -> controller.editarMascota(mascota));
            deleteButton.addActionListener(e -> controller.eliminarMascota(mascota.getIdMascota()));
            VerMas.addActionListener(e -> controller.verMascota(mascota));

            petPanel.add(mascotaLabel);
            petPanel.add(deleteButton);
            petPanel.add(editButton);
            petPanel.add(VerMas);

            // Asegura que el panel se ajuste automáticamente
            petPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, petPanel.getPreferredSize().height));

            add(petPanel);
            add(Box.createVerticalStrut(10)); // Espacio entre paneles de mascotas
        }

        // Ensure the component layout updates immediately
        revalidate();
        repaint();
    }

}
