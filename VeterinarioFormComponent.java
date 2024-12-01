
import javax.swing.*;
import java.awt.*;

public class VeterinarioFormComponent extends JComponent {
    private JTextField txtNombre, txtApellido, txtEdad, txtDireccion, txtTelefono, txtEmail, txtDNI;
    private JComboBox<EnumsList.Turnos> comboTurno;
    private CustomizedButton btnAction;
    private VeterinariaController controller;
    private boolean isEditMode;
    private Empleado veterinario;
    private Object dialog; // Reference to the dialog

    public VeterinarioFormComponent(VeterinariaController controller, boolean isEditMode, Empleado veterinario,
            Object dialog) {
        this.controller = controller;
        this.isEditMode = isEditMode;
        this.veterinario = veterinario;
        this.dialog = dialog; // Set the dialog reference

        // Initialize components
        initializeFields();
        setLayout(new GridLayout(0, 2, 10, 10));

        // Add labels and fields
        add(new JLabel("Nombre:"));
        add(txtNombre);
        add(new JLabel("Apellido:"));
        add(txtApellido);
        add(new JLabel("Edad:"));
        add(txtEdad);
        add(new JLabel("Dirección:"));
        add(txtDireccion);
        add(new JLabel("Teléfono:"));
        add(txtTelefono);
        add(new JLabel("Email:"));
        add(txtEmail);
        add(new JLabel("DNI:"));
        add(txtDNI);

        add(new JLabel("Turno:"));
        add(comboTurno);

        // Set the button's text and action depending on the mode (add or edit)
        String buttonText = isEditMode ? "Guardar Cambios" : "Agregar Veterinario";
        btnAction = new CustomizedButton(buttonText, EnumsList.ButtonStyle.LIGHT_PURPLE_BUTTON);
        add(btnAction);

        // Button action to handle save or add operation
        btnAction.addActionListener(e -> handleButtonAction());
    }

    private void initializeFields() {
        // Create text fields
        txtNombre = new JTextField(15);
        txtApellido = new JTextField(15);
        txtEdad = new JTextField(15);
        txtDireccion = new JTextField(15);
        txtTelefono = new JTextField(15);
        txtEmail = new JTextField(15);
        txtDNI = new JTextField(15);
        comboTurno = new JComboBox<>(EnumsList.Turnos.values());

        // Set preferred size
        Dimension fieldSize = new Dimension(200, 25);
        txtNombre.setPreferredSize(fieldSize);
        txtApellido.setPreferredSize(fieldSize);
        txtEdad.setPreferredSize(fieldSize);
        txtDireccion.setPreferredSize(fieldSize);
        txtTelefono.setPreferredSize(fieldSize);
        txtEmail.setPreferredSize(fieldSize);
        txtDNI.setPreferredSize(fieldSize);
        comboTurno.setPreferredSize(fieldSize);

        if (isEditMode && veterinario != null) {
            // If in edit mode, populate the fields with existing data
            txtNombre.setText(veterinario.getNombre());
            txtApellido.setText(veterinario.getApellido());
            txtDireccion.setText(veterinario.getDireccion());
            txtTelefono.setText(veterinario.getTelefono());
            txtEmail.setText(veterinario.getEmail());
            txtDNI.setText(String.valueOf(veterinario.getDNI()));
            comboTurno.setSelectedItem(veterinario.getTipoTurno());
        }
    }

    private void handleButtonAction() {
        try {
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            int edad = Integer.parseInt(txtEdad.getText());
            String direccion = txtDireccion.getText();
            String telefono = txtTelefono.getText();
            String email = txtEmail.getText();
            int dni = Integer.parseInt(txtDNI.getText());
            EnumsList.Turnos turno = (EnumsList.Turnos) comboTurno.getSelectedItem();

            // Basic validation
            if (nombre.isEmpty() || apellido.isEmpty() || edad <= 0 || direccion.isEmpty() || telefono.isEmpty()
                    || email.isEmpty() || dni <= 0 || turno == null) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios y deben ser válidos.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (isEditMode) {
                // If in edit mode, update the existing Veterinario
                veterinario.setNombre(nombre);
                veterinario.setApellido(apellido);
                veterinario.setDireccion(direccion);
                veterinario.setTelefono(telefono);
                veterinario.setEmail(email);
                veterinario.setDNI(dni);
                veterinario.setTipoTurno(turno);

                // Update via controller
                controller.actualizarVeterinario(veterinario);
            } else {
                // If adding new Veterinario
                Empleado nuevoVeterinario = new Empleado();
                nuevoVeterinario.setNombre(nombre);
                nuevoVeterinario.setApellido(apellido);
                nuevoVeterinario.setDireccion(direccion);
                nuevoVeterinario.setTelefono(telefono);
                nuevoVeterinario.setEmail(email);
                nuevoVeterinario.setDNI(dni);
                nuevoVeterinario.setTipoTurno(turno);

                // Add via controller
                controller.agregarVeterinario(nuevoVeterinario);
            }

            // Clear the fields after submission
            clearFields();

            if (dialog instanceof JDialog) {
                // Close the dialog upon success
                ((JDialog) dialog).dispose();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Edad, DNI  deben ser números enteros válidos.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtEdad.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        txtDNI.setText("");
        comboTurno.setSelectedIndex(0);
    }

    public void setController(VeterinariaController controller) {
        this.controller = controller;
    }
}
