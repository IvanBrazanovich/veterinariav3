
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.UUID;

import javax.swing.JOptionPane;

public class ListadoClientes implements ListadoModelo<Cliente> {
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private static final String FILE_NAME = "clientes.ser";

    public ListadoClientes() {
        load();
    }

    @Override
    public void agregar(Cliente cliente) {
        clientes.add(cliente);
        save();
    }

    @Override
    public void eliminar(UUID idCliente) {
        clientes.removeIf(cliente -> cliente.getIdCliente().equals(idCliente));
        save();

    }

    @Override
    public ArrayList<Cliente> get() {
        return new ArrayList<>(clientes);
    }

    @Override
    public Cliente getById(UUID idCliente) throws NoSuchElementException {

        Cliente cliente = clientes.stream().filter(clienteTemp -> clienteTemp.getIdCliente().equals(idCliente))
                .findFirst()
                .orElse(null);

        if (cliente == null) {
            // Throw error
            JOptionPane.showMessageDialog(null, "No existe cliente con id " + idCliente, "Error",
                    JOptionPane.ERROR_MESSAGE);

            return null;
            // throw new NoSuchElementException("No existe Cliente con id " + idCliente);
        }
        return cliente;

    }

    public void save() {
        try {
            SerializationUtil.saveToFile(clientes, FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error saving clientes: " + e.getMessage());
        }
    }

    public void load() {
        try {
            clientes = SerializationUtil.loadFromFile(FILE_NAME);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading clientes: " + e.getMessage());
        }
    }

    @Override
    public int size() {
        return clientes.size();
    }
}
