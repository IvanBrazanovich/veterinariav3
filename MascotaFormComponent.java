
import javax.swing.*;
import java.awt.*;

public class MascotaFormComponent extends JComponent {

    private JTextField txtNombre, txtPeso, txtEdad;
    private JComboBox<EnumsList.MascotaColor> cbColor;
    private JComboBox<EnumsList.Size> cbSize;
    private CustomizedButton btnAction;
    private VeterinariaController controller;
    private Mascota mascota; // Mascota reference, null in add mode
    private boolean isEditMode;
    private Object parentDialog; // Optional JDialog reference

    public MascotaFormComponent(VeterinariaController controller, boolean isEditMode, Mascota mascota,
            Object parentDialog) {
        this.controller = controller;
        this.isEditMode = isEditMode;
        this.mascota = mascota;
        this.parentDialog = parentDialog;

        // Initialize fields and layout
        initializeFields();
        setLayout(new GridLayout(0, 2, 10, 10));
        addComponentsToPanel();

        // Configure button
        String buttonText = isEditMode ? "Guardar Cambios" : "Agregar Mascota";
        btnAction = new CustomizedButton(buttonText, EnumsList.ButtonStyle.LIGHT_PURPLE_BUTTON);
        add(new JLabel()); // Empty label for alignment
        add(btnAction);

        // Button action to handle save or add operation
        btnAction.addActionListener(e -> handleButtonAction());
    }

    private void initializeFields() {
        // Initialize text fields
        txtNombre = new JTextField(15);
        txtPeso = new JTextField(15);
        txtEdad = new JTextField(15);
        cbColor = new JComboBox<>(EnumsList.MascotaColor.values());
        cbSize = new JComboBox<>(EnumsList.Size.values());

        // Set default data if in edit mode
        if (isEditMode && mascota != null) {
            txtNombre.setText(mascota.getNombre());
            txtPeso.setText(String.valueOf(mascota.getPeso()));
            txtEdad.setText(String.valueOf(mascota.getEdad()));
            cbColor.setSelectedItem(mascota.getColor());
            cbSize.setSelectedItem(mascota.getSize());
        }
    }

    private void addComponentsToPanel() {
        // Add components to panel with labels
        add(new JLabel("Nombre:"));
        add(txtNombre);
        add(new JLabel("Peso:"));
        add(txtPeso);
        add(new JLabel("Edad:"));
        add(txtEdad);
        add(new JLabel("Color:"));
        add(cbColor);
        add(new JLabel("Tamaño:"));
        add(cbSize);
    }

    private void handleButtonAction() {
        try {
            // Retrieve field values
            String nombre = txtNombre.getText();
            float peso = Float.parseFloat(txtPeso.getText());
            int edad = Integer.parseInt(txtEdad.getText());
            EnumsList.MascotaColor color = (EnumsList.MascotaColor) cbColor.getSelectedItem();
            EnumsList.Size size = (EnumsList.Size) cbSize.getSelectedItem();

            // Basic validation
            if (nombre.isEmpty() || peso <= 0 || edad <= 0) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios y deben ser válidos", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (isEditMode) {
                // Update existing Mascota
                mascota.setNombre(nombre);
                mascota.setPeso(peso);
                mascota.setEdad(edad);
                mascota.setColor(color);
                mascota.setSize(size);

                // Update via controller
                controller.actualizarMascota(mascota);
            } else {
                // Add new Mascota
                Mascota nuevaMascota = new Mascota();
                nuevaMascota.setNombre(nombre);
                nuevaMascota.setPeso(peso);
                nuevaMascota.setEdad(edad);
                nuevaMascota.setColor(color);
                nuevaMascota.setSize(size);

                // Add via controller
                controller.agregarMascota(nuevaMascota);
            }

            JOptionPane.showMessageDialog(this,
                    isEditMode ? "Mascota actualizada correctamente" : "Mascota agregada correctamente", "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

            // Clear fields after submission
            clearFields();

            if (parentDialog instanceof JDialog) {
                // Close the dialog upon success
                ((JDialog) parentDialog).dispose();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Peso y Edad deben ser valores numéricos válidos", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        txtNombre.setText("");
        txtPeso.setText("");
        txtEdad.setText("");
        cbColor.setSelectedIndex(0);
        cbSize.setSelectedIndex(0);
    }

    public void setController(VeterinariaController controller) {
        this.controller = controller;
    }
}
