
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.w3c.dom.events.MouseEvent;
import java.awt.event.MouseAdapter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

public class VerTurnoModal extends JDialog {
    private Turno turno;
    private VeterinariaController controller;

    public VerTurnoModal(VeterinariaController controller, Turno turno) {
        super((JFrame) null, "Detalles del Turno", true);
        this.turno = turno;
        this.controller = controller;
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));

        System.out.println(turno.getIdTurno());
        // Panel de detalles del turno
        JPanel panelDetalles = new JPanel();
        panelDetalles.setLayout(new GridLayout(6, 2, 10, 10));
        panelDetalles.setBackground(Color.WHITE);
        panelDetalles.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Agregar detalles del turno
        panelDetalles.add(createLabel("ID Turno:"));
        JLabel idTurnoLabel = createLabel(String.valueOf(turno.getIdTurno()));
        idTurnoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // Copiar el texto del ID Turno al portapapeles
                StringSelection stringSelection = new StringSelection(idTurnoLabel.getText());
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
                JOptionPane.showMessageDialog(null, "ID Turno copiado al portapapeles");
            }
        });
        panelDetalles.add(idTurnoLabel);
        panelDetalles.add(createLabel("ID Trabajador:"));
        panelDetalles.add(createLabel(String.valueOf(turno.getTrabajadorFk())));

        panelDetalles.add(createLabel("ID Mascota:"));
        panelDetalles.add(createLabel(String.valueOf(turno.getMascotasFk())));

        panelDetalles.add(createLabel("Fecha:"));
        panelDetalles.add(createLabel(turno.getFecha().toString()));

        panelDetalles.add(createLabel("Observación:"));
        panelDetalles.add(createLabel(turno.getObservacion()));

        // Panel para listar tratamientos
        JPanel panelTratamientos = new JPanel(new BorderLayout());
        JLabel labelTratamientos = new JLabel("Tratamientos:");
        labelTratamientos.setFont(new Font("Arial", Font.BOLD, 14));
        panelTratamientos.add(labelTratamientos, BorderLayout.NORTH);

        JPanel listaTratamientos = new JPanel(new GridLayout(0, 1));
        listaTratamientos.setBackground(Color.WHITE);

        Tratamiento tratamiento = turno.getTratamientos();

        listaTratamientos.add(createLabel("- " + tratamiento.getDescripcion()));

        panelTratamientos.add(listaTratamientos, BorderLayout.CENTER);
        panelDetalles.add(panelTratamientos);

        add(panelDetalles, BorderLayout.CENTER);

        // Botón para cerrar el modal
        CustomizedButton btnCerrar = new CustomizedButton("Cerrar", EnumsList.ButtonStyle.RED_BUTTON);
        btnCerrar.addActionListener(e -> setVisible(false));
        add(btnCerrar, BorderLayout.SOUTH);

        // Configuración del modal
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    // Método auxiliar para crear etiquetas con estilo
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setForeground(new Color(60, 60, 60));
        return label;
    }

}
