
import java.awt.BorderLayout;

import javax.swing.JComponent;

public class AddVeterinarioComponente extends JComponent {
    private VeterinariaController controller;

    public AddVeterinarioComponente(VeterinariaController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());
        VeterinarioFormComponent form = new VeterinarioFormComponent(controller, false, null, this);

        add(form, BorderLayout.CENTER);
    }

    public void setController(VeterinariaController controller) {
        this.controller = controller;
    }
}
