
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class TurnoFormComponent extends JComponent {
    private JTextField txtObservaciones;
    private JComboBox<String> dayComboBox, monthComboBox, yearComboBox, tratamientosComboBox;
    private JComboBox<Empleado> veterinariosComboBox;
    private JComboBox<Mascota> mascotasComboBox;
    private CustomizedButton btnAction;
    private VeterinariaController controller;
    private boolean isEditMode;
    private Turno turno;
    private Object parentDialog; // Optional JDialog reference

    public TurnoFormComponent(VeterinariaController controller, boolean isEditMode, Turno turno, Object parentDialog) {
        this.controller = controller;
        this.isEditMode = isEditMode;
        this.turno = turno;
        this.parentDialog = parentDialog;
        // Initialize components
        initializeFields();

        // Set layout
        setLayout(new GridLayout(0, 2, 10, 10));

        // Add labels and fields
        add(new JLabel("Fecha del Turno - Día:"));
        add(dayComboBox);
        add(new JLabel("Mes:"));
        add(monthComboBox);
        add(new JLabel("Año:"));
        add(yearComboBox);
        add(new JLabel("Observaciones:"));
        add(txtObservaciones);
        add(new JLabel("Tratamiento:"));
        add(tratamientosComboBox);
        add(new JLabel("Veterinario:"));
        add(veterinariosComboBox);
        add(new JLabel("Mascota:"));
        add(mascotasComboBox);

        // Set the button's text and action depending on the mode (add or edit)
        String buttonText = isEditMode ? "Guardar Cambios" : "Agregar Turno";
        btnAction = new CustomizedButton(buttonText, EnumsList.ButtonStyle.LIGHT_PURPLE_BUTTON);
        add(btnAction);

        // Action for the button
        btnAction.addActionListener(e -> handleButtonAction());
    }

    private void initializeFields() {
        // Create text fields and combo boxes
        txtObservaciones = new JTextField(15);

        // Initialize date combo boxes
        String[] days = new String[31];
        for (int i = 0; i < 31; i++) {
            days[i] = String.format("%02d", i + 1);
        }
        dayComboBox = new JComboBox<>(days);

        String[] months = {
                "01 - Enero", "02 - Febrero", "03 - Marzo", "04 - Abril", "05 - Mayo", "06 - Junio",
                "07 - Julio", "08 - Agosto", "09 - Septiembre", "10 - Octubre", "11 - Noviembre", "12 - Diciembre"
        };
        monthComboBox = new JComboBox<>(months);

        String[] years = new String[11];
        int currentYear = LocalDate.now().getYear();
        for (int i = 0; i < 11; i++) {
            years[i] = String.valueOf(currentYear + i);
        }
        yearComboBox = new JComboBox<>(years);

        // Populate treatments combo box
        ArrayList<Tratamiento> tratamientosList = controller.getTratamientosList();
        String[] tratamientosStrings = tratamientosList.stream().map(Tratamiento::getNombre).toArray(String[]::new);
        tratamientosComboBox = new JComboBox<>(tratamientosStrings);

        // Populate veterinarians combo box
        ArrayList<Empleado> veterinariosList = controller.getVeterinariosList();
        veterinariosComboBox = new JComboBox<>(veterinariosList.toArray(new Empleado[0]));

        veterinariosComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Empleado) {
                    Empleado empleado = (Empleado) value;
                    setText(empleado.getNombre()); // Assuming getNombre() returns the name of the employee
                }
                return this;
            }
        });
        // Populate pets combo box
        ArrayList<Mascota> mascotasList = controller.getMascotasList();
        mascotasComboBox = new JComboBox<>(mascotasList.toArray(new Mascota[0]));
        mascotasComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Mascota) {
                    Mascota mascota = (Mascota) value;
                    setText(mascota.getNombre()); // Assuming getNombre() returns the name of the employee
                }
                return this;
            }
        });
        // If editing, populate the fields with current turno data
        if (isEditMode && turno != null) {
            LocalDate fecha = turno.getFecha();
            dayComboBox.setSelectedItem(String.format("%02d", fecha.getDayOfMonth()));
            monthComboBox.setSelectedIndex(fecha.getMonthValue() - 1);
            yearComboBox.setSelectedItem(String.valueOf(fecha.getYear()));

            txtObservaciones.setText(turno.getObservacion());

            // Set selected treatment
            ArrayList<Tratamiento> tratamientosList2 = controller.getTratamientosList();
            for (int i = 0; i < tratamientosList2.size(); i++) {
                if (tratamientosList2.get(i).equals(turno.getTratamientos())) {
                    tratamientosComboBox.setSelectedIndex(i);
                    break;
                }
            }

            // Set selected veterinarian and pet
            for (int i = 0; i < veterinariosList.size(); i++) {
                if (veterinariosList.get(i).getIdEmpleado().equals(turno.getTrabajadorFk())) {
                    veterinariosComboBox.setSelectedIndex(i);
                    break;
                }
            }

            for (int i = 0; i < mascotasList.size(); i++) {
                if (mascotasList.get(i).getIdMascota().equals(turno.getMascotasFk())) {
                    mascotasComboBox.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    private void handleButtonAction() {
        try {
            // Validate required fields
            if (txtObservaciones.getText().trim().isEmpty() ||
                    dayComboBox.getSelectedItem() == null || monthComboBox.getSelectedItem() == null ||
                    yearComboBox.getSelectedItem() == null || tratamientosComboBox.getSelectedItem() == null ||
                    veterinariosComboBox.getSelectedItem() == null || mascotasComboBox.getSelectedItem() == null) {

                JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Parse date values
            int day = Integer.parseInt((String) dayComboBox.getSelectedItem());
            int month = Integer.parseInt(((String) monthComboBox.getSelectedItem()).split(" - ")[0]);
            int year = Integer.parseInt((String) yearComboBox.getSelectedItem());

            LocalDate fecha = LocalDate.of(year, month, day);
            String observacionesText = txtObservaciones.getText();

            // Get selected treatment, veterinarian, and pet
            Tratamiento tratamientoSeleccionado = controller.getTratamientosList()
                    .get(tratamientosComboBox.getSelectedIndex());
            Empleado veterinarioSeleccionado = (Empleado) veterinariosComboBox.getSelectedItem();
            Mascota mascotaSeleccionada = (Mascota) mascotasComboBox.getSelectedItem();

            if (isEditMode) {
                // Update existing turno
                turno.setFecha(fecha);
                turno.setObservacion(observacionesText);
                turno.setTratamientos(tratamientoSeleccionado);
                turno.setTrabajadorFk(veterinarioSeleccionado.getIdEmpleado());
                turno.setMascotasFk(mascotaSeleccionada.getIdMascota());

                controller.actualizarTurno(turno);
                JOptionPane.showMessageDialog(this, "Turno actualizado exitosamente", "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Add new turno
                Turno newTurno = new Turno();
                newTurno.setFecha(fecha);
                newTurno.setObservacion(observacionesText);
                newTurno.setTratamientos(tratamientoSeleccionado);
                newTurno.setTrabajadorFk(veterinarioSeleccionado.getIdEmpleado());
                newTurno.setMascotasFk(mascotaSeleccionada.getIdMascota());

                controller.agregarTurno(newTurno);
                JOptionPane.showMessageDialog(this, "Turno agregado exitosamente", "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            // Clear fields after submission
            clearFields();

            if (parentDialog instanceof JDialog) {
                // Close the dialog upon success
                ((JDialog) parentDialog).dispose();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al procesar la información.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        txtObservaciones.setText("");
        dayComboBox.setSelectedIndex(0);
        monthComboBox.setSelectedIndex(0);
        yearComboBox.setSelectedIndex(0);
        tratamientosComboBox.setSelectedIndex(0);
        veterinariosComboBox.setSelectedIndex(0);
        mascotasComboBox.setSelectedIndex(0);
    }

    public void setController(VeterinariaController controller) {
        this.controller = controller;
    }
}
