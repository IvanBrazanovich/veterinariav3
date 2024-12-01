
import javax.swing.*;
import java.awt.*;

public class EditVeterinarioPopUp extends JDialog {
    public EditVeterinarioPopUp(VeterinariaController controller, Empleado veterinario) {
        super((JFrame) null, "Editar Veterinario", true);
        setLayout(new BorderLayout());
        VeterinarioFormComponent form = new VeterinarioFormComponent(controller, true, veterinario, this);
        add(form, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null); // Center the dialog on the screen
        setVisible(true);
    }
}
