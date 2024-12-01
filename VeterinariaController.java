
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.UUID;

import javax.swing.JOptionPane;

public class VeterinariaController {
    // UI
    private VeterinariaUI veterinariaUI;

    // Solamente puedo tener un listado por vista y debe ser único

    // MODELOS
    private ListadoMascotas listadoMascotas;
    private ListadoVeterinarios listadoVeterinarios;
    private ListadoClientes listadoClientes;
    private ListadoTurnos listadoTurnos;
    private ArrayList<Tratamiento> tratamientos;

    public VeterinariaController() {

        this.listadoClientes = new ListadoClientes();
        this.listadoMascotas = new ListadoMascotas();
        this.listadoVeterinarios = new ListadoVeterinarios();
        this.listadoTurnos = new ListadoTurnos();

        this.tratamientos = TratamientoDataGenerator.generateTratamientos();

        // UI
        this.veterinariaUI = new VeterinariaUI();
        veterinariaUI.setController(this);
    }

    // MASCOTAS MÉTODOS
    // TODO LO QUE TENGA QUE VER CON AGREGAR SER DEBE PASAR EL ELEMENTO YA HECHO
    // MASCOTA()

    public void agregarMascota(Mascota mascota) {

        listadoMascotas.agregar(mascota);
        veterinariaUI.actualizarListadoMascotas(listadoMascotas.get());
    }

    public void eliminarMascota(UUID id) {
        listadoMascotas.eliminar(id);
        veterinariaUI.actualizarListadoMascotas(listadoMascotas.get());
    }

    public void editarMascota(Mascota mascota) {
        EditMascotaPopUp editMascotaPopUp = new EditMascotaPopUp(this, mascota);

    }

    public void verMascota(Mascota mascota) {
        VerMascotaModal verMascotaModal = new VerMascotaModal(this, mascota);
    }

    public ArrayList<Mascota> getListadoMascotasWithOwner() {
        ArrayList<Mascota> mascotasWithOwner = new ArrayList<>();
        for (Mascota mascota : listadoMascotas.get()) {
            if (mascota.getOwnerFk() != null) {
                mascotasWithOwner.add(mascota);
            }
        }
        return mascotasWithOwner;
    }

    public void actualizarMascota(Mascota mascotaActualizada) {
        // Buscar la mascota existente por ID
        Mascota mascotaExistente = listadoMascotas.getById(mascotaActualizada.getIdMascota());

        if (mascotaExistente != null) {
            // Verificar si el nombre ha cambiado
            if (!mascotaExistente.getNombre().equals(mascotaActualizada.getNombre())) {
                mascotaExistente.setNombre(mascotaActualizada.getNombre());
            }

            // Verificar si la edad ha cambiado
            if (mascotaExistente.getEdad() != mascotaActualizada.getEdad()) {
                mascotaExistente.setEdad(mascotaActualizada.getEdad());
            }

            // Verificar si el peso ha cambiado
            if (mascotaExistente.getPeso() != mascotaActualizada.getPeso()) {
                mascotaExistente.setPeso(mascotaActualizada.getPeso());
            }

            // Check owner change
            if (mascotaExistente.getOwnerFk().equals(mascotaActualizada.getOwnerFk())
                    && mascotaActualizada.getOwnerFk() != null) {
                mascotaExistente.setOwnerFk(mascotaActualizada.getOwnerFk());
            }

            if (mascotaExistente.getColor() != mascotaActualizada.getColor()) {
                mascotaExistente.setColor(mascotaActualizada.getColor());
            }

            if (mascotaExistente.getSize() != mascotaActualizada.getSize()) {
                mascotaExistente.setSize(mascotaActualizada.getSize());
            }
            // Actualizar la lista de mascotas
            veterinariaUI.actualizarListadoMascotas(listadoMascotas.get());
        } else {
            JOptionPane.showMessageDialog(null, "Mascota no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // VETERINARIO

    public void agregarVeterinario(Empleado veterinario) {
        listadoVeterinarios.agregar(veterinario);

        veterinariaUI.actualizarListadoVeterinarios(listadoVeterinarios.get());
    }

    public void eliminarVeterinario(UUID id) {
        listadoVeterinarios.eliminar(id);
        veterinariaUI.actualizarListadoVeterinarios(listadoVeterinarios.get());
    }

    public void editarVeterinario(Empleado veterinario) {
        EditVeterinarioPopUp editVeterinarioPopUp = new EditVeterinarioPopUp(this, veterinario);
    }

    public void actualizarVeterinario(Empleado veterinarioActualizado) {
        // Buscar el veterinario existente por ID
        Empleado veterinarioExistente = listadoVeterinarios.getById(veterinarioActualizado.getIdEmpleado());

        if (veterinarioExistente != null) {
            // Verificar si el nombre ha cambiado
            if (!veterinarioExistente.getNombre().equals(veterinarioActualizado.getNombre())) {
                veterinarioExistente.setNombre(veterinarioActualizado.getNombre());
            }

            // Verificar si el teléfono ha cambiado
            if (!veterinarioExistente.getTelefono().equals(veterinarioActualizado.getTelefono())) {
                veterinarioExistente.setTelefono(veterinarioActualizado.getTelefono());
            }

            // Verificar si el sueldo ha cambiado
            if (veterinarioExistente.getSueldo() != veterinarioActualizado.getSueldo()) {
                veterinarioExistente.setSueldo(veterinarioActualizado.getSueldo());
            }

            // Verificar si las horas extras han cambiado
            if (veterinarioExistente.getHorasExtras() != veterinarioActualizado.getHorasExtras()) {
                veterinarioExistente.setHorasExtras(veterinarioActualizado.getHorasExtras());
            }

            // Verificar si el tipo de turno ha cambiado
            if (veterinarioExistente.getTipoTurno() != veterinarioActualizado.getTipoTurno()) {
                veterinarioExistente.setTipoTurno(veterinarioActualizado.getTipoTurno());
            }

            // Email
            if (!veterinarioExistente.getEmail().equals(veterinarioActualizado.getEmail())) {
                veterinarioExistente.setEmail(veterinarioActualizado.getEmail());
            }

            // Actualizar la lista de veterinarios en la UI
            veterinariaUI.actualizarListadoVeterinarios(listadoVeterinarios.get());

        } else {
            JOptionPane.showMessageDialog(null, "Veterinario no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void verVeterinario(Empleado veterinario) {
        VerVeterinarioModal verVeterinarioModal = new VerVeterinarioModal(this, veterinario);
    }

    // CLIENTES

    public void eliminarCliente(UUID id) {
        listadoClientes.eliminar(id);

        veterinariaUI.actualizarLIstadoClientes(listadoClientes.get());
    }

    public void editarCliente(Cliente cliente) {
        EditClientePopUp editClientePopUp = new EditClientePopUp(this, cliente);
    }

    public void agregarCliente(Cliente cliente) {
        listadoClientes.agregar(cliente);
        veterinariaUI.actualizarLIstadoClientes(listadoClientes.get());
    }

    public void actualizarCliente(Cliente clienteActualizado) {
        Cliente clienteExistente = listadoClientes.getById(clienteActualizado.getIdCliente());

        if (clienteExistente != null) {
            // Verificar si el nombre ha cambiado
            if (!clienteExistente.getNombre().equals(clienteActualizado.getNombre())) {
                clienteExistente.setNombre(clienteActualizado.getNombre());
            }

            // Verificar si el apellido ha cambiado
            if (!clienteExistente.getApellido().equals(clienteActualizado.getApellido())) {
                clienteExistente.setApellido(clienteActualizado.getApellido());
            }

            // Verificar si la dirección ha cambiado
            if (!clienteExistente.getDireccion().equals(clienteActualizado.getDireccion())) {
                clienteExistente.setDireccion(clienteActualizado.getDireccion());
            }

            // Verificar si el teléfono ha cambiado
            if (!clienteExistente.getTelefono().equals(clienteActualizado.getTelefono())) {
                clienteExistente.setTelefono(clienteActualizado.getTelefono());
            }

            // Verificar si el email ha cambiado
            if (!clienteExistente.getEmail().equals(clienteActualizado.getEmail())) {
                clienteExistente.setEmail(clienteActualizado.getEmail());
            }

            // Verificar si el DNI ha cambiado
            if (clienteExistente.getDNI() != clienteActualizado.getDNI()) {
                clienteExistente.setDNI(clienteActualizado.getDNI());
            }

            // Actualizar la lista de clientes en la UI
            veterinariaUI.actualizarLIstadoClientes(listadoClientes.get());

        }
    }

    public void verCliente(Cliente cliente) {
        VerClienteModal verClienteModal = new VerClienteModal(this, cliente);
    }

    // TURNOS

    public void eliminarTurno(UUID id) {
        listadoTurnos.eliminar(id);
        veterinariaUI.actualizarListadoTurnos(listadoTurnos.get());
    }

    public void editarTurno(Turno turno) {
        EditTurnoPopUp editTurnoPopUp = new EditTurnoPopUp(this, turno);
    }

    public void agregarTurno(Turno turno) {
        listadoTurnos.agregar(turno);

        veterinariaUI.actualizarListadoTurnos(listadoTurnos.get());
    }

    public void verTurno(Turno turno) {
        VerTurnoModal verTurnoModal = new VerTurnoModal(this, turno);
    }

    public void actualizarTurno(Turno turnoActualizada) {
        // Buscar la mascota existente por ID
        Turno turnoExistente = listadoTurnos.getById(turnoActualizada.getIdTurno());

        if (turnoExistente != null) {
            // Verificar si el nombre ha cambiado
            if (!turnoExistente.getObservacion().equals(turnoActualizada.getObservacion())) {
                turnoExistente.setObservacion(null);
            }

            // Verificar si la edad ha cambiado
            if (turnoExistente.getFecha() != turnoActualizada.getFecha()) {
                turnoExistente.setFecha(turnoActualizada.getFecha());
            }

            // Verificar si el peso ha cambiado
            if (turnoExistente.getObservacion() != turnoActualizada.getObservacion()) {
                turnoExistente.setObservacion(turnoActualizada.getObservacion());
            }

            // Check owner change
            if (turnoExistente.getTratamientos() != turnoActualizada.getTratamientos()) {
                turnoExistente.setTratamientos(turnoActualizada.getTratamientos());
            }

            if (turnoExistente.getTrabajadorFk() != turnoActualizada.getTrabajadorFk()) {
                turnoExistente.setTrabajadorFk(turnoActualizada.getTrabajadorFk());
            }

            if (turnoExistente.getMascotasFk() != turnoActualizada.getMascotasFk()) {
                turnoExistente.setMascotasFk(turnoActualizada.getMascotasFk());
            }

            // Actualizar la lista de mascotas
            veterinariaUI.actualizarListadoTurnos(listadoTurnos.get());
        } else {
            JOptionPane.showMessageDialog(null, "Turno no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Cambiar vistas
    public void cambiarVista(EnumsList.TipoVista tipoVista) {
        veterinariaUI.cleanContenedorVistas();

        switch (tipoVista) {
            case AGREGAR_MASCOTA:
                veterinariaUI.vistaAgregarMascota();
                veterinariaUI.actualizarListadoMascotas(listadoMascotas.get());
                break;
            case AGREGAR_CLIENTE:
                veterinariaUI.vistaAgregarCliente();
                veterinariaUI.actualizarLIstadoClientes(listadoClientes.get());
                break;
            case AGREGAR_VETERINARIO:
                veterinariaUI.vistaAgregarVeterinario();
                veterinariaUI.actualizarListadoVeterinarios(listadoVeterinarios.get());
                break;
            case BUSCAR:
                veterinariaUI.vistaBuscar();
                break;
            case GENERAR_TURNO:
                veterinariaUI.vistaGenerarTurno();
                veterinariaUI.actualizarListadoTurnos(listadoTurnos.get());
                break;
            case GENERAR_UNION_CLIENTE_MASCOTA:
                veterinariaUI.vistaGenerarUnionClienteMascota();

                veterinariaUI.actualizarListadoMascotas(getListadoMascotasWithOwner());
                break;
            default:
                veterinariaUI.vistaAgregarMascota();
                break;

        }

        veterinariaUI.actualizarTodasVistas();
    }

    public void unirClienteMascota(UUID idCliente, UUID idMascota) {
        // Si el cliente o la mascota están vacíos o no seleccionados
        if (idCliente == null || idMascota == null) {
            JOptionPane.showMessageDialog(null, "El ID del cliente y la mascota son obligatorios", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cliente cliente = listadoClientes.getById(idCliente);
        Mascota mascota = listadoMascotas.getById(idMascota);

        // Si alguno no existe
        if (cliente == null || mascota == null) {
            JOptionPane.showMessageDialog(null, "El cliente o la mascota no existen", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Unir cliente y mascota
        mascota.setOwnerFk(cliente.getIdCliente());
        listadoMascotas.eliminar(mascota.getIdMascota());
        listadoMascotas.agregar(mascota);

        veterinariaUI.actualizarListadoMascotas(listadoMascotas.get());
        veterinariaUI.actualizarListadoMascotas(getListadoMascotasWithOwner());
    }

    // Get data from listados
    public ArrayList<Cliente> getClientesList() {

        return listadoClientes.get();
    }

    public ArrayList<Mascota> getMascotasList() {
        return listadoMascotas.get();
    }

    public ArrayList<Turno> getTurnosList() {
        return listadoTurnos.get();
    }

    public ArrayList<Empleado> getVeterinariosList() {
        return listadoVeterinarios.get();
    }

    public ArrayList<Tratamiento> getTratamientosList() {
        return tratamientos;
    }

    // Search methods
    public Mascota buscarMascotaPorID(UUID id) {
        try {
            return listadoMascotas.getById(id);
        } catch (NoSuchElementException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public Cliente buscarClientePorID(UUID id) {
        try {
            System.out.println(id);
            System.out.println(listadoClientes.getById(id));
            System.out.println(listadoClientes);

            System.out.println("luego");
            return listadoClientes.getById(id);
        } catch (NoSuchElementException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public Turno buscarTurnoPorID(UUID id) {
        try {
            return listadoTurnos.getById(id);
        } catch (NoSuchElementException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public Empleado buscarVeterinarioPorID(UUID id) {
        try {
            return listadoVeterinarios.getById(id);
        } catch (NoSuchElementException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

}
