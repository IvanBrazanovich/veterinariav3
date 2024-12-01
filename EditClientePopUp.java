
import javax.swing.*;
import java.awt.*;

public class EditClientePopUp extends JDialog {
    public EditClientePopUp(VeterinariaController controller, Cliente cliente) {
        super((JFrame) null, "Editar Mascota", true);
        setLayout(new BorderLayout());
        ClienteFormComponent form = new ClienteFormComponent(controller, true, cliente, this);
        add(form, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null); // Center the dialog on the screen
        setVisible(true);
    }
}
