
import java.util.UUID;

public class Empleado extends Persona {

    private UUID idEmpleado;
    private int sueldo;
    private int horasExtras;
    private EnumsList.Turnos tipoTurno;

    public void setIdEmpleado(UUID idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public EnumsList.Turnos getTipoTurno() {
        return tipoTurno;
    }

    public void setTipoTurno(EnumsList.Turnos tipoTurno) {
        this.tipoTurno = tipoTurno;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    public int getHorasExtras() {
        return horasExtras;
    }

    public void setHorasExtras(int horasExtras) {
        this.horasExtras = horasExtras;
    }

    public UUID getIdEmpleado() {
        return idEmpleado;
    }

    public Empleado() {
        super();
        this.idEmpleado = UUID.randomUUID();
    }

    public Empleado(String nombre, String apellido, int edad, String direccion, String telefono, String email, int DNI,
            String cargo) {
        super(nombre, apellido, edad, direccion, telefono, email, DNI);
    }

    @Override
    public String toString() {
        return "Empleado{" +
                ", idEmpleado=" + idEmpleado +
                "} " + super.toString();
    }

}
