
import javax.swing.*;
import java.awt.*;

public class ClienteFormComponent extends JComponent {
    private JTextField txtNombre, txtApellido, txtTelefono, txtCorreo, txtDireccion;
    private CustomizedButton btnAction;
    private VeterinariaController controller;
    private boolean isEditMode;
    private Cliente cliente;
    private Object dialog; // Reference to the dialog

    public ClienteFormComponent(VeterinariaController controller, boolean isEditMode, Cliente cliente, Object dialog) {
        this.controller = controller;
        this.isEditMode = isEditMode;
        this.cliente = cliente;
        this.dialog = dialog;

        // Initialize components
        initializeFields();
        setLayout(new GridLayout(0, 2, 10, 10));

        // Add labels and fields
        add(new JLabel("Nombre:"));
        add(txtNombre);
        add(new JLabel("Apellido:"));
        add(txtApellido);
        add(new JLabel("Teléfono:"));
        add(txtTelefono);
        add(new JLabel("Correo Electrónico:"));
        add(txtCorreo);
        add(new JLabel("Dirección:"));
        add(txtDireccion);

        // Set the button's text and action depending on the mode (add or edit)
        String buttonText = isEditMode ? "Guardar Cambios" : "Agregar Cliente";
        btnAction = new CustomizedButton(buttonText, EnumsList.ButtonStyle.LIGHT_PURPLE_BUTTON);
        add(btnAction);

        // Button action to handle save or add operation
        btnAction.addActionListener(e -> handleButtonAction());
    }

    private void initializeFields() {
        // Create text fields
        txtNombre = new JTextField(15);
        txtApellido = new JTextField(15);
        txtTelefono = new JTextField(15);
        txtCorreo = new JTextField(15);
        txtDireccion = new JTextField(15);

        // Set preferred size
        Dimension fieldSize = new Dimension(200, 25);
        txtNombre.setPreferredSize(fieldSize);
        txtApellido.setPreferredSize(fieldSize);
        txtTelefono.setPreferredSize(fieldSize);
        txtCorreo.setPreferredSize(fieldSize);
        txtDireccion.setPreferredSize(fieldSize);

        if (isEditMode && cliente != null) {
            // Populate the fields if in edit mode
            txtNombre.setText(cliente.getNombre());
            txtApellido.setText(cliente.getApellido());
            txtTelefono.setText(cliente.getTelefono());
            txtCorreo.setText(cliente.getEmail());
            txtDireccion.setText(cliente.getDireccion());
        }
    }

    private void handleButtonAction() {
        try {
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String telefono = txtTelefono.getText();
            String correo = txtCorreo.getText();
            String direccion = txtDireccion.getText();

            // Basic validation
            if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || correo.isEmpty()
                    || direccion.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (isEditMode) {
                // Update the existing Cliente
                cliente.setNombre(nombre);
                cliente.setApellido(apellido);
                cliente.setTelefono(telefono);
                cliente.setEmail(correo);
                cliente.setDireccion(direccion);

                // Update via controller
                controller.actualizarCliente(cliente);
            } else {
                // Add new Cliente
                Cliente nuevoCliente = new Cliente();
                nuevoCliente.setNombre(nombre);
                nuevoCliente.setApellido(apellido);
                nuevoCliente.setTelefono(telefono);
                nuevoCliente.setEmail(correo);
                nuevoCliente.setDireccion(direccion);

                // Add via controller
                controller.agregarCliente(nuevoCliente);
            }

            // Clear the fields after submission
            clearFields();

            if (dialog instanceof JDialog) {
                // Close the dialog upon success
                ((JDialog) dialog).dispose();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al procesar la información.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtDireccion.setText("");
    }

    public void setController(VeterinariaController controller) {
        this.controller = controller;
    }
}
