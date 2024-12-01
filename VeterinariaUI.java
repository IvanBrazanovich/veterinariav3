
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

public class VeterinariaUI extends JFrame {
    private VeterinariaController controller;
    // Listados
    private ListadoMascotasUI listadoMascotasView;
    private ListadoVeterinariosUI listadoVeterinariosView;
    private ListadoClientesUI listadoClientesView;
    private ListadoTurnosUI listadoTurnosView;

    // Add components
    private AddMascotaComponent addMascotaView;
    private AddVeterinarioComponente addVeterinarioView;
    private AddClienteComponente addClienteView;
    private AddTurnoComponente addTurnoView;

    private BuscarView buscarView;

    private JScrollPane contenedorVistasScroll;
    private JPanel contenedorVistas;
    private AccionesVista accionesVista;
    // UI components
    private JPanel addComponents;
    private JPanel listados;

    private JPanel contenedorUnion;

    public VeterinariaUI() {
        setTitle("Veterinaria CRUD");
        // set full size
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));// Crear un layout con margenes
        contenedorVistasScroll = createContenedorVistaScroll();

        // Generar vista principal
        accionesVista = createAccionesView();

        add(accionesVista, BorderLayout.WEST);

        setVisible(true);
        pack(); // Asegúrate de ajustar la ventana a los componentes
    }

    public void setController(VeterinariaController controller) {
        this.controller = controller;
        accionesVista.setController(controller);

    }

    public AddMascotaComponent createAddMascotaView() {
        addMascotaView = new AddMascotaComponent(controller);

        return addMascotaView;
    }

    public AddClienteComponente createAddClienteView() {
        addClienteView = new AddClienteComponente(controller);

        return addClienteView;
    }

    public AddVeterinarioComponente createAddVeterinarioView() {
        addVeterinarioView = new AddVeterinarioComponente(controller);

        return addVeterinarioView;
    }

    public ListadoClientesUI createListadoClientesView() {
        listadoClientesView = new ListadoClientesUI();
        listadoClientesView.setController(controller);

        return listadoClientesView;
    }

    public ListadoVeterinariosUI createListadoVeterinariosView() {
        listadoVeterinariosView = new ListadoVeterinariosUI();
        listadoVeterinariosView.setController(controller);

        return listadoVeterinariosView;
    }

    public ListadoMascotasUI createListadoMascotasView() {
        listadoMascotasView = new ListadoMascotasUI();
        listadoMascotasView.setController(controller);

        return listadoMascotasView;
    }

    public ListadoTurnosUI createListadoTurnosView() {
        listadoTurnosView = new ListadoTurnosUI();
        listadoTurnosView.setController(controller);

        return listadoTurnosView;
    }

    public AddTurnoComponente createAddTurnoView() {
        addTurnoView = new AddTurnoComponente(controller);
        addTurnoView.setController(controller);

        return addTurnoView;
    }

    public BuscarView createBuscarView() {
        buscarView = new BuscarView();
        buscarView.setController(controller);
        return buscarView;
    }

    // SIDEVIEWS

    public AccionesVista createAccionesView() {
        accionesVista = new AccionesVista();
        accionesVista.setBackground(new Color(220, 220, 220)); // Fondo gris claro
        accionesVista.setBorder(new EmptyBorder(10, 10, 10, 10)); // Espaciado interior
        return accionesVista;
    }

    public JScrollPane createContenedorVistaScroll() {
        contenedorVistasScroll = new JScrollPane();
        contenedorVistas = new JPanel();
        contenedorVistas.setLayout(new BoxLayout(contenedorVistas, BoxLayout.Y_AXIS)); // Set layout on the internal
                                                                                       // panel
        contenedorVistas.setBackground(Color.WHITE); // Fondo blanco para contraste
        contenedorVistas.setBorder(new TitledBorder("Página principal")); //

        contenedorVistasScroll.setViewportView(contenedorVistas); // Add panel to JScrollPane

        return contenedorVistasScroll;
    }

    // Actualizar vistas

    public void actualizarListadoMascotas(ArrayList<Mascota> mascotas) {
        listadoMascotasView.actualizarListadoMascotas(mascotas);
        actualizarTodasVistas(); // Ensures the view fully refreshes
    }

    public void actualizarListadoVeterinarios(ArrayList<Empleado> veterinarios) {
        listadoVeterinariosView.actualizarListadoVeterinarios(veterinarios);
        actualizarTodasVistas(); // Ensures thcontenedorVistasScrolle view fully refreshes
    }

    public void actualizarLIstadoClientes(ArrayList<Cliente> clientes) {
        listadoClientesView.actualizarListadoClientes(clientes);
        actualizarTodasVistas(); // Ensures the view fully refreshes
    }

    public void actualizarListadoTurnos(ArrayList<Turno> turnos) {
        listadoTurnosView.actualizarListadoTurnos(turnos);
        actualizarTodasVistas(); // Ensures the view fully refreshes
    }

    // Tipos de vistas

    public void vistaAgregarMascota() {

        listadoMascotasView = createListadoMascotasView();
        listadoMascotasView.setController(controller);
        listadoMascotasView.setBorder(new TitledBorder("Listado de Mascotas")); // Borde con título

        addMascotaView = createAddMascotaView();
        addMascotaView.setController(controller);
        addMascotaView.setBorder(new EmptyBorder(5, 5, 5, 5)); // Espaciado para separar el formulario

        contenedorVistas.add(addMascotaView);
        contenedorVistas.add(listadoMascotasView);

        contenedorVistasScroll.setViewportView(contenedorVistas);

        add(contenedorVistasScroll, BorderLayout.CENTER);

    }

    public void vistaAgregarCliente() {

        listadoClientesView = createListadoClientesView();
        listadoClientesView.setController(controller);
        listadoClientesView.setBorder(new TitledBorder("Listado de Clientes"));
        contenedorVistas.add(listadoClientesView);

        addClienteView = createAddClienteView();
        addClienteView.setController(controller);
        addClienteView.setBorder(new EmptyBorder(5, 5, 5, 5));
        contenedorVistas.add(addClienteView);

        contenedorVistas.add(addClienteView);
        contenedorVistas.add(listadoClientesView);

        contenedorVistasScroll.setViewportView(contenedorVistas);

        add(contenedorVistasScroll, BorderLayout.CENTER);

    }

    public void vistaAgregarVeterinario() {

        listadoVeterinariosView = createListadoVeterinariosView();
        listadoVeterinariosView.setController(controller);
        listadoVeterinariosView.setBorder(new TitledBorder("Listado de Veterinarios"));
        contenedorVistas.add(listadoVeterinariosView);

        addVeterinarioView = createAddVeterinarioView();
        addVeterinarioView.setController(controller);
        addVeterinarioView.setBorder(new EmptyBorder(5, 5, 5, 5));
        contenedorVistas.add(addVeterinarioView);

        contenedorVistas.add(addVeterinarioView);
        contenedorVistas.add(listadoVeterinariosView);

        contenedorVistasScroll.setViewportView(contenedorVistas);

        add(contenedorVistasScroll, BorderLayout.CENTER);

    }

    public void vistaBuscar() {

        // Añadir buscar
        buscarView = createBuscarView();

        contenedorVistas.add(buscarView);

        contenedorVistasScroll.setViewportView(contenedorVistas);

        add(contenedorVistasScroll, BorderLayout.CENTER);

    }

    public void vistaGenerarTurno() {

        // Añadir add turno
        addTurnoView = createAddTurnoView();
        contenedorVistas.add(addTurnoView);

        // Añadir listado turnos
        listadoTurnosView = createListadoTurnosView();
        contenedorVistas.add(listadoTurnosView);

        contenedorVistasScroll.setViewportView(contenedorVistas);
        add(contenedorVistasScroll, BorderLayout.CENTER);
    }

    public void vistaGenerarUnionClienteMascota() {
        contenedorUnion = new JPanel();
        contenedorUnion.setLayout(new GridLayout(0, 2, 10, 10));

        // Crear la etiqueta para el cliente
        JLabel idClienteLabel = new JLabel("Seleccionar cliente:");

        // Crear el JComboBox para los clientes
        JComboBox<Cliente> comboCliente = new JComboBox<>(controller.getClientesList().toArray(new Cliente[0]));

        comboCliente.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Cliente) {
                    Cliente cliente = (Cliente) value;
                    setText(cliente.getNombre()); // Assuming getNombre() returns the name of the employee
                }
                return this;
            }
        });
        // Crear la etiqueta para la mascota
        JLabel idMascotaLabel = new JLabel("Seleccionar mascota:");

        // Crear el JComboBox para las mascotas
        JComboBox<Mascota> comboMascota = new JComboBox<>(controller.getMascotasList().toArray(new Mascota[0]));

        comboMascota.setRenderer(new DefaultListCellRenderer() {
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

        // Crear el botón de unión
        CustomizedButton btnUnion = new CustomizedButton("Unir Cliente con Mascota",
                EnumsList.ButtonStyle.LIGHT_PURPLE_BUTTON);
        btnUnion.addActionListener(e -> {
            Cliente clienteSeleccionado = (Cliente) comboCliente.getSelectedItem();
            Mascota mascotaSeleccionada = (Mascota) comboMascota.getSelectedItem();

            if (clienteSeleccionado != null && mascotaSeleccionada != null) {
                controller.unirClienteMascota(clienteSeleccionado.getIdCliente(),
                        mascotaSeleccionada.getIdMascota());
            } else {
                JOptionPane.showMessageDialog(null, "Debes seleccionar un cliente y una mascota.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Agregar los componentes al contenedor
        contenedorUnion.add(idClienteLabel);
        contenedorUnion.add(comboCliente);
        contenedorUnion.add(idMascotaLabel);
        contenedorUnion.add(comboMascota);
        contenedorUnion.add(btnUnion);
        contenedorUnion.setMaximumSize(new Dimension(Integer.MAX_VALUE, contenedorUnion.getPreferredSize().height));

        contenedorVistas.add(contenedorUnion);

        // Listar mascotas con owners
        listadoMascotasView = createListadoMascotasView();
        contenedorVistas.add(listadoMascotasView);
        listadoMascotasView.setBorder(new TitledBorder("Listado de Mascotas")); // Borde con título

        contenedorVistasScroll.setViewportView(contenedorVistas);

        add(contenedorVistasScroll, BorderLayout.CENTER);
    }

    public void cleanContenedorVistas() {
        contenedorVistas.removeAll();
    }

    // Actualizar todas las vistas

    public void actualizarTodasVistas() {
        // Verifica y actualiza la vista de listado de mascotas
        if (listadoMascotasView != null) {
            listadoMascotasView.revalidate();
            listadoMascotasView.repaint();
        }

        // Verifica y actualiza la vista para agregar mascota
        if (addMascotaView != null) {
            addMascotaView.revalidate();
            addMascotaView.repaint();
        }

        // Verifica y actualiza la vista de listado de veterinarios
        if (listadoVeterinariosView != null) {
            listadoVeterinariosView.revalidate();
            listadoVeterinariosView.repaint();
        }

        // Verifica y actualiza la sección de listados
        if (listados != null) {
            listados.revalidate();
            listados.repaint();
        }

        // Verifica y actualiza la sección de agregar componentes
        if (addComponents != null) {
            addComponents.revalidate();
            addComponents.repaint();
        }

        // Verifica y actualiza nuevamente la vista para agregar mascota
        if (addMascotaView != null) {
            addMascotaView.revalidate();
            addMascotaView.repaint();
        }

        // Verifica y actualiza nuevamente la vista para agregar cliente
        if (addClienteView != null) {
            addClienteView.revalidate();
            addClienteView.repaint();
        }

        // Verifica y actualiza nuevamente la vista para agregar cliente
        if (listadoClientesView != null) {
            listadoClientesView.revalidate();
            listadoClientesView.repaint();
        }

        if (contenedorVistas != null) {
            contenedorVistas.revalidate();
            contenedorVistas.repaint();
        }

        if (contenedorVistasScroll != null) {
            contenedorVistasScroll.revalidate();
            contenedorVistasScroll.repaint();
        }

        if (contenedorUnion != null) {
            contenedorUnion.revalidate();
            contenedorUnion.repaint();
        }

        if (listadoTurnosView != null) {
            listadoTurnosView.revalidate();
            listadoTurnosView.repaint();
        }

        if (addTurnoView != null) {
            addTurnoView.revalidate();
            addTurnoView.repaint();
        }

        revalidate();
        repaint();

    }

}
