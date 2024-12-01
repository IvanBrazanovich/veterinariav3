
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.JOptionPane;

public class ListadoTurnos implements ListadoModelo<Turno> {
    private ArrayList<Turno> turnos = new ArrayList<>();
    private int idListadoTurnos;
    private static final String FILE_NAME = "turnos.ser";

    public ListadoTurnos() {
        load();
        idListadoTurnos = 0;
    }

    public void agregar(Turno turno) {
        turnos.add(turno);
        save();
    }

    public void eliminar(UUID id) {

        turnos.removeIf(turno -> turno.getIdTurno() == id);
    }

    public Turno getById(UUID id) {

        Turno turno = turnos.stream().filter(clienteTemp -> clienteTemp.getIdTurno().equals(id)).findFirst()
                .orElse(null);

        if (turno == null) {
            // Throw error
            JOptionPane.showMessageDialog(null, "No existe turno con id " + id, "Error",
                    JOptionPane.ERROR_MESSAGE);

            return null;
            // throw new NoSuchElementException("No existe Cliente con id " + idCliente);
        }
        return turno;

    }

    public ArrayList<Turno> get() {
        return turnos;
    }

    public void set(ArrayList<Turno> turnos) {
        this.turnos = turnos;
        save();
    }

    public int size() {
        return turnos.size();
    }

    public void save() {
        try {
            SerializationUtil.saveToFile(turnos, FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error saving turnos: " + e.getMessage());
        }
    }

    public void load() {
        try {
            turnos = SerializationUtil.loadFromFile(FILE_NAME);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading turnos: " + e.getMessage());
        }
    }
}