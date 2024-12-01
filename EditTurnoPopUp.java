
import javax.swing.*;
import java.awt.*;

public class EditTurnoPopUp extends JDialog {
    public EditTurnoPopUp(VeterinariaController controller, Turno turno) {
        super((JFrame) null, "Editar Turno", true);
        setLayout(new BorderLayout());
        TurnoFormComponent form = new TurnoFormComponent(controller, true, turno, this);
        add(form, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null); // Center the dialog on the screen
        setVisible(true);
    }
}
