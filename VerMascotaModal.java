
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

import org.w3c.dom.events.MouseEvent;
import java.awt.event.MouseAdapter;

public class VerMascotaModal extends JDialog {
    private VeterinariaController controller;
    private Mascota mascota;

    public VerMascotaModal(VeterinariaController controller, Mascota mascota) {
        super((JFrame) null, "Detalles de la Mascota", true); // true para que sea modal
        this.controller = controller;
        this.mascota = mascota;
        System.out.println(mascota.getIdMascota());
        // Configuración del layout
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245)); // Fondo suave para el modal

        // Crear panel para los detalles de la mascota
        JPanel panelDetalles = new JPanel();
        panelDetalles.setLayout(new GridLayout(7, 2, 10, 10)); // Espaciado entre los componentes
        panelDetalles.setBackground(Color.WHITE);
        panelDetalles.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Relleno para separar del borde

        // Personalizar los detalles de la mascota
        panelDetalles.add(createLabel("ID Mascota:"));
        JLabel idMascotaLabel = createLabel(String.valueOf(mascota.getIdMascota()));
        idMascotaLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // Copiar el texto del ID Turno al portapapeles
                StringSelection stringSelection = new StringSelection(idMascotaLabel.getText());
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
                JOptionPane.showMessageDialog(null, "ID Mascota copiado al portapapeles");
            }
        });
        panelDetalles.add(idMascotaLabel);
        panelDetalles.add(createLabel("Nombre:"));
        panelDetalles.add(createLabel(mascota.getNombre()));

        panelDetalles.add(createLabel("Edad:"));
        panelDetalles.add(createLabel(String.valueOf(mascota.getEdad())));

        panelDetalles.add(createLabel("Color:"));
        panelDetalles.add(createLabel(mascota.getColor().toString()));

        panelDetalles.add(createLabel("Peso:"));
        panelDetalles.add(createLabel(String.valueOf(mascota.getPeso())));

        panelDetalles.add(createLabel("Tamaño:"));
        panelDetalles.add(createLabel(mascota.getSize().toString()));

        panelDetalles.add(createLabel("ID Propietario:"));
        panelDetalles.add(createLabel(String.valueOf(mascota.getOwnerFk())));

        // Agregar el panel al dialog
        add(panelDetalles, BorderLayout.CENTER);

        // Botón para cerrar el modal
        CustomizedButton btnCerrar = new CustomizedButton("Cerrar", EnumsList.ButtonStyle.RED_BUTTON);
        btnCerrar.addActionListener(e -> setVisible(false));
        add(btnCerrar, BorderLayout.SOUTH);

        // Configuración del modal
        pack();
        setLocationRelativeTo(null); // Centrado en la ventana principal
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    // Método auxiliar para crear etiquetas con estilo
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setForeground(new Color(60, 60, 60)); // Texto oscuro para mejor contraste
        return label;
    }

}
