
import javax.swing.*;

import java.awt.*;

public class AddMascotaComponent extends JComponent implements AddComponenteInterfaz<VeterinariaController> {

    private MascotaFormComponent mascotaForm;
    private VeterinariaController controller;

    public AddMascotaComponent(VeterinariaController controller) {
        this.controller = controller;

        // Crear ClienteFormComponent en modo "agregar" (no se pasa un cliente
        // existente)
        mascotaForm = new MascotaFormComponent(controller, false, null, this);

        // Configuración del layout
        setLayout(new BorderLayout());

        add(mascotaForm, BorderLayout.CENTER); // Agrega el formulario de cliente al panel principal
    }

    @Override
    public void setController(VeterinariaController controller) {
        this.controller = controller;
    }
}
