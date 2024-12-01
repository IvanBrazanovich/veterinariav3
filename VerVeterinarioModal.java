
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;

import java.awt.event.MouseAdapter;

public class VerVeterinarioModal extends JDialog {
    private VeterinariaController controller;
    private Empleado empleado;

    public VerVeterinarioModal(VeterinariaController controller, Empleado empleado) {
        super((JFrame) null, "Detalles del Veterinario", true); // true para que sea modal
        this.controller = controller;
        this.empleado = empleado;
        System.out.println(empleado.getIdEmpleado());
        // Configuración del layout
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245)); // Fondo suave para el modal

        // Crear panel para los detalles del veterinario
        JPanel panelDetalles = new JPanel();
        panelDetalles.setLayout(new GridLayout(0, 2, 10, 10)); // Grid flexible (0 filas, 2 columnas)
        panelDetalles.setBackground(Color.WHITE);
        panelDetalles.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Relleno para separar del borde

        // Personalizar los detalles del veterinario
        panelDetalles.add(createLabel("ID Veterinario:"));
        JLabel idVeterinarioLabel = createLabel(String.valueOf(empleado.getIdEmpleado()));
        idVeterinarioLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // Copiar el texto del ID Turno al portapapeles
                StringSelection stringSelection = new StringSelection(idVeterinarioLabel.getText());
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
                JOptionPane.showMessageDialog(null, "ID Veterinario copiado al portapapeles");
            }
        });
        panelDetalles.add(idVeterinarioLabel);
        panelDetalles.add(createLabel("Nombre:"));
        panelDetalles.add(createValueLabel(empleado.getNombre()));

        panelDetalles.add(createLabel("Apellido:"));
        panelDetalles.add(createValueLabel(empleado.getApellido()));

        panelDetalles.add(createLabel("Teléfono:"));
        panelDetalles.add(createValueLabel(empleado.getTelefono()));

        panelDetalles.add(createLabel("Correo Electrónico:"));
        panelDetalles.add(createValueLabel(empleado.getEmail()));

        panelDetalles.add(createLabel("Dirección:"));
        panelDetalles.add(createValueLabel(empleado.getDireccion()));

        panelDetalles.add(createLabel("Sueldo:"));
        panelDetalles.add(createValueLabel(String.valueOf(empleado.getSueldo())));

        panelDetalles.add(createLabel("Horas Extras:"));
        panelDetalles.add(createValueLabel(String.valueOf(empleado.getHorasExtras())));

        panelDetalles.add(createLabel("Tipo de Turno:"));

        EnumsList.Turnos tipoTurno = empleado.getTipoTurno();
        String turnoString = tipoTurno != null ? tipoTurno.toString() : "No asignado";

        panelDetalles.add(createValueLabel(turnoString)); // Asegúrate de que el tipoTurno esté
                                                          // en formato String

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
