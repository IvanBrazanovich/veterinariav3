
import javax.swing.*;

import java.awt.*;

public class AddTurnoComponente extends JComponent implements AddComponenteInterfaz<VeterinariaController> {

    private TurnoFormComponent turnoForm;
    private VeterinariaController controller;

    public AddTurnoComponente(VeterinariaController controller) {
        this.controller = controller;

        // Crear ClienteFormComponent en modo "agregar" (no se pasa un cliente
        // existente)
        turnoForm = new TurnoFormComponent(controller, false, null, this);

        // Configuración del layout
        setLayout(new BorderLayout());

        add(turnoForm, BorderLayout.CENTER); // Agrega el formulario de cliente al panel principal
    }

    @Override
    public void setController(VeterinariaController controller) {
        this.controller = controller;
    }
}
