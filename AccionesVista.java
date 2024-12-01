
import javax.swing.*;
import java.awt.*;

public class AccionesVista extends JPanel {

    private VeterinariaController controller;

    public AccionesVista() {
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(new Color(245, 245, 245));

        // Configura botones de acción
        addButton("Mascotas", EnumsList.TipoVista.AGREGAR_MASCOTA);
        addButton("Clientes", EnumsList.TipoVista.AGREGAR_CLIENTE);
        addButton("Veterinarios", EnumsList.TipoVista.AGREGAR_VETERINARIO);
        addButton("Buscar", EnumsList.TipoVista.BUSCAR);
        addButton("Turnos", EnumsList.TipoVista.GENERAR_TURNO);
        addButton("Clientes-Mascotas", EnumsList.TipoVista.GENERAR_UNION_CLIENTE_MASCOTA);
    }

    private void addButton(String text, EnumsList.TipoVista vista) {
        CustomizedButton button = new CustomizedButton(text, EnumsList.ButtonStyle.GREY_BUTTON);
        button.setFocusPainted(false);
        button.addActionListener(e -> controller.cambiarVista(vista));
        add(Box.createVerticalStrut(15)); // Espacio entre botones
        add(button);
    }

    public void setController(VeterinariaController controller) {
        this.controller = controller;
    }
}
