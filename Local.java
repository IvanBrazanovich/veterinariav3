
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class Local implements Serializable {
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private ArrayList<Empleado> empleados = new ArrayList<>();
    private ArrayList<Turno> consultas = new ArrayList<>();
    private ArrayList<Tratamiento> tratamientos = new ArrayList<>();
    private ArrayList<Mascota> mascotas = new ArrayList<>();

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    public void setMascotas(Mascota mascota) {
        this.mascotas.add(mascota);
    }

    public void removeMascotas(UUID IdMascota) {
        this.mascotas.removeIf(mascota -> mascota.getIdMascota() == IdMascota);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(ArrayList<Empleado> empleados) {
        this.empleados = empleados;
    }

    public void setEmpleados(Empleado empleado) {
        this.empleados.add(empleado);
    }

    public void setConsultas(Turno consulta) {
        this.consultas.add(consulta);
    }

    public void setTratamientos(Tratamiento tratamiento) {
        this.tratamientos.add(tratamiento);
    }

    public void removeEmpleados(UUID IdEmpleado) {
        this.empleados.removeIf(empleado -> empleado.getIdEmpleado() == IdEmpleado);
    }

    public ArrayList<Turno> getConsultas() {
        return consultas;
    }

    public void setConsultas(ArrayList<Turno> consultas) {
        this.consultas = consultas;
    }

    public ArrayList<Tratamiento> getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(ArrayList<Tratamiento> tratamientos) {
        this.tratamientos = tratamientos;
    }

    public Local() {

    }

    public Local(String nombre, String direccion, String telefono, String email) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

}
