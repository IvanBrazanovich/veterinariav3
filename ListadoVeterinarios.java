
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.UUID;

public class ListadoVeterinarios implements ListadoModelo<Empleado> {
    private ArrayList<Empleado> veterinarios = new ArrayList<>();
    private static final String FILE_NAME = "veterinarios.ser";

    public ListadoVeterinarios() {
        load();
    }

    @Override
    public void agregar(Empleado veterinario) {
        veterinarios.add(veterinario);
        save();
    }

    @Override
    public void eliminar(UUID idEmpleado) {
        veterinarios.removeIf(empleado -> empleado.getIdEmpleado() == idEmpleado);

        save();
    }

    @Override
    public ArrayList<Empleado> get() {
        return new ArrayList<>(veterinarios);
    }

    @Override
    public Empleado getById(UUID idEmpleado) throws NoSuchElementException {

        Empleado veterinario = veterinarios.stream()
                .filter(veterinarioTemp -> veterinarioTemp.getIdEmpleado().equals(idEmpleado)).findFirst()
                .orElse(null);

        if (veterinario == null) {
            throw new NoSuchElementException("No existe empleado con id " + idEmpleado);
        }
        return veterinario;

    }

    @Override
    public int size() {
        return veterinarios.size();
    }

    public void save() {
        try {
            SerializationUtil.saveToFile(veterinarios, FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error saving veterinarios: " + e.getMessage());
        }
    }

    public void load() {
        try {
            veterinarios = SerializationUtil.loadFromFile(FILE_NAME);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading veterinarios: " + e.getMessage());
        }
    }

}
