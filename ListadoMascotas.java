
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.UUID;

import javax.swing.JOptionPane;

// MascotaNotFoundException.java

public class ListadoMascotas implements ListadoModelo<Mascota> {
    private ArrayList<Mascota> mascotas = new ArrayList<>();
    private static final String FILE_NAME = "mascotas.ser";

    public ListadoMascotas() {
        load();
    }

    @Override
    public void agregar(Mascota mascota) {
        mascotas.add(mascota);
        save();
    }

    @Override
    public void eliminar(UUID idMascota) {
        mascotas.removeIf(mascota -> mascota.getIdMascota() == idMascota);
        save();
    }

    @Override
    public ArrayList<Mascota> get() {
        return new ArrayList<>(mascotas); // return a copy for safety
    }

    @Override
    public Mascota getById(UUID idMascota) throws NoSuchElementException {

        Mascota mascota = mascotas.stream().filter(mascotaTemp -> mascotaTemp.getIdMascota().equals(idMascota))
                .findFirst()
                .orElse(null);

        if (mascota == null) {
            // Throw error
            JOptionPane.showMessageDialog(null, "No existe mascota con id " + idMascota, "Error",
                    JOptionPane.ERROR_MESSAGE);

            return null;
            // throw new NoSuchElementException("No existe Cliente con id " + idCliente);
        }
        return mascota;

    }

    @Override

    public int size() {
        return mascotas.size();
    }

    public void save() {
        try {
            SerializationUtil.saveToFile(mascotas, FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error saving mascotas: " + e.getMessage());
        }
    }

    public void load() {
        try {
            mascotas = SerializationUtil.loadFromFile(FILE_NAME);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading mascotas: " + e.getMessage());
        }
    }

}
