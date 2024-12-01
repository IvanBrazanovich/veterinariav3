
import java.util.ArrayList;
import java.util.UUID;

public class Cliente extends Persona {

    private ArrayList<Mascota> mascotas = new ArrayList<>();
    private UUID idCliente;

    public Cliente() {
        super();
        this.idCliente = UUID.randomUUID();
    }

    public UUID getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(UUID idCliente) {
        this.idCliente = idCliente;
    }

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

}
