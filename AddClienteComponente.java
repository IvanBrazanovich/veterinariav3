
import javax.swing.*;

import java.awt.*;

public class AddClienteComponente extends JComponent implements AddComponenteInterfaz<VeterinariaController> {

    private ClienteFormComponent clienteForm;
    private VeterinariaController controller;

    public AddClienteComponente(VeterinariaController controller) {
        this.controller = controller;

        // Crear ClienteFormComponent en modo "agregar" (no se pasa un cliente
        // existente)
        clienteForm = new ClienteFormComponent(controller, false, null, this);

        // Configuración del layout
        setLayout(new BorderLayout());

        add(clienteForm, BorderLayout.CENTER); // Agrega el formulario de cliente al panel principal
    }

    @Override
    public void setController(VeterinariaController controller) {
        this.controller = controller;
    }
}
