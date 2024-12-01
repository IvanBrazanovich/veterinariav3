
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;

import org.w3c.dom.events.MouseEvent;
import java.awt.event.MouseAdapter;

public class VerClienteModal extends JDialog {
    private VeterinariaController controller;
    private Cliente cliente;

    public VerClienteModal(VeterinariaController controller, Cliente cliente) {
        super((JFrame) null, "Detalles del Cliente", true); // true para que sea modal
        this.controller = controller;
        this.cliente = cliente;

        System.out.println(cliente.getIdCliente());
        // Configuración del layout
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245)); // Fondo suave para el modal

        // Crear panel para los detalles del cliente
        JPanel panelDetalles = new JPanel();
        panelDetalles.setLayout(new GridLayout(0, 2, 10, 10)); // Grid flexible (0 filas, 2 columnas)
        panelDetalles.setBackground(Color.WHITE);
        panelDetalles.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Relleno para separar del borde

        // Personalizar los detalles del cliente
        panelDetalles.add(createLabel("ID Cliente:"));
        JLabel idClienteLabel = createLabel(String.valueOf(cliente.getIdCliente()));
        idClienteLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // Copiar el texto del ID Turno al portapapeles
                StringSelection stringSelection = new StringSelection(idClienteLabel.getText());
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
                JOptionPane.showMessageDialog(null, "ID Cliente copiado al portapapeles");
            }
        });
        panelDetalles.add(idClienteLabel);
        panelDetalles.add(createLabel("Nombre:"));
        panelDetalles.add(createValueLabel(cliente.getNombre()));

        panelDetalles.add(createLabel("Apellido:"));
        panelDetalles.add(createValueLabel(cliente.getApellido()));

        panelDetalles.add(createLabel("Teléfono:"));
        panelDetalles.add(createValueLabel(cliente.getTelefono()));

        panelDetalles.add(createLabel("Correo Electrónico:"));
        panelDetalles.add(createValueLabel(cliente.getEmail()));

        panelDetalles.add(createLabel("Dirección:"));
        panelDetalles.add(createValueLabel(cliente.getDireccion()));

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

    // Método auxiliar para crear las etiquetas de valor (más grandes y resaltadas)
    private JLabel createValueLabel(String value) {
        JLabel label = new JLabel(value);
        label.setFont(new Font("Arial", Font.BOLD, 14)); // Negrita para resaltar los valores
        label.setForeground(new Color(0, 0, 0)); // Negro para resaltar los valores
        return label;
    }

}
