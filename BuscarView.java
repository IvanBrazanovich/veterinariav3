
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class BuscarView extends JPanel {
    private VeterinariaController controller;
    private Object resultado;

    public BuscarView() {
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Left Panel (Buttons)
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        leftPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(leftPanel, BorderLayout.WEST);
        leftPanel.setLayout(new GridLayout(0, 1, 10, 10));
        leftPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, leftPanel.getPreferredSize().height));
        add(rightPanel, BorderLayout.CENTER);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        // Buttons for Left Panel
        CustomizedButton buscarVeterinarioButton = new CustomizedButton("Buscar Veterinario",
                EnumsList.ButtonStyle.LIGHT_PURPLE_BUTTON);
        CustomizedButton buscarMascotaButton = new CustomizedButton("Buscar Mascota",
                EnumsList.ButtonStyle.LIGHT_PURPLE_BUTTON);
        CustomizedButton buscarClienteButton = new CustomizedButton("Buscar Cliente",
                EnumsList.ButtonStyle.LIGHT_PURPLE_BUTTON);
        CustomizedButton buscarTurnoButton = new CustomizedButton("Buscar Turno",
                EnumsList.ButtonStyle.LIGHT_PURPLE_BUTTON);

        leftPanel.add(buscarVeterinarioButton);
        leftPanel.add(buscarMascotaButton);
        leftPanel.add(buscarClienteButton);
        leftPanel.add(buscarTurnoButton);

        // Right Panel (Search Results)
        rightPanel.add(new JLabel("Buscar por ID:"));
        JScrollPane resultadoScroll = new JScrollPane();
        JPanel resultadoPanel = new JPanel();
        resultadoPanel.setLayout(new BoxLayout(resultadoPanel, BoxLayout.Y_AXIS));
        rightPanel.add(resultadoScroll);
        resultadoScroll.setViewportView(resultadoPanel);

        // Actions for the Buttons
        buscarVeterinarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFormularioBusqueda("Veterinario", resultadoPanel);
            }
        });

        buscarMascotaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFormularioBusqueda("Mascota", resultadoPanel);
            }
        });

        buscarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFormularioBusqueda("Cliente", resultadoPanel);
            }
        });

        buscarTurnoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFormularioBusqueda("Turno", resultadoPanel);
            }
        });

        // Initial Revalidate
        revalidate();
        repaint();
    }

    private void mostrarFormularioBusqueda(String tipoBusqueda, JPanel resultadoPanel) {
        // Clear previous components
        resultadoPanel.removeAll();

        // Create the new search form
        JPanel buscarPanel = new JPanel();
        buscarPanel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Buscar " + tipoBusqueda + " por ID");
        JTextField inputID = new JTextField(15);
        CustomizedButton buscarButton = new CustomizedButton("Buscar", EnumsList.ButtonStyle.LIGHT_PURPLE_BUTTON);

        buscarPanel.add(label);
        buscarPanel.add(inputID);
        buscarPanel.add(buscarButton);

        // Add the search form to the result panel
        resultadoPanel.add(buscarPanel);

        // Result label to display the search result
        JLabel resultadoLabel = new JLabel();
        resultadoPanel.add(resultadoLabel);

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if the input is UUID
                try {
                    UUID.fromString(inputID.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "El ID ingresado no es válido", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Call the controller's search method based on the search type
                switch (tipoBusqueda) {
                    case "Veterinario":
                        resultado = controller.buscarVeterinarioPorID(UUID.fromString(inputID.getText()));
                        break;
                    case "Mascota":
                        resultado = controller.buscarMascotaPorID(UUID.fromString(inputID.getText()));
                        break;
                    case "Cliente":
                        resultado = controller.buscarClientePorID(UUID.fromString(inputID.getText()));
                        break;
                    case "Turno":
                        resultado = controller.buscarTurnoPorID(UUID.fromString(inputID.getText()));
                        break;
                }

                // Display the result in the label
                if (resultado != null) {

                    // utilizar switch
                    switch (tipoBusqueda) {
                        case "Veterinario":
                            controller.verVeterinario((Empleado) resultado);
                            break;
                        case "Mascota":
                            controller.verMascota((Mascota) resultado);
                            break;
                        case "Cliente":
                            controller.verCliente((Cliente) resultado);
                            break;
                        case "Turno":
                            controller.verTurno((Turno) resultado);
                            break;
                        default:

                            break;
                    }
                } else {
                    resultadoLabel.setText("Resultado: No se encontro ningun objeto con ese ID");
                }

                // Refresh the panel to show the updated result
                resultadoPanel.revalidate();
                resultadoPanel.repaint();
            }
        });

        // Revalidate and repaint to show the new components
        resultadoPanel.revalidate();
        resultadoPanel.repaint();
    }

    public void setController(VeterinariaController controller) {
        this.controller = controller;
    }
}
