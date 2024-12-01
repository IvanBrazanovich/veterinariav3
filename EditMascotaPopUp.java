
import javax.swing.*;
import java.awt.*;

public class EditMascotaPopUp extends JDialog {
    public EditMascotaPopUp(VeterinariaController controller, Mascota mascota) {
        super((JFrame) null, "Editar Mascota", true);
        setLayout(new BorderLayout());
        MascotaFormComponent form = new MascotaFormComponent(controller, true, mascota, this);
        add(form, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null); // Center the dialog on the screen
        setVisible(true);
    }
}
