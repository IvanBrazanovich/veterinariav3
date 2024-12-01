
import java.io.Serializable;
import java.util.UUID;

public class Tratamiento implements Serializable {
    private UUID idTratamiento;
    private String nombre;
    private int duracion;
    private float dosis;
    private double precio;
    private EnumsList.TipoTratamiento tipoTratamiento;
    private String descripcion;

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public float getDosis() {
        return dosis;
    }

    public void setDosis(float dosis) {
        this.dosis = dosis;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public EnumsList.TipoTratamiento getTipoTratamiento() {
        return tipoTratamiento;
    }

    public void setTipoTratamiento(EnumsList.TipoTratamiento tipoTratamiento) {
        this.tipoTratamiento = tipoTratamiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Tratamiento() {
        this.idTratamiento = UUID.randomUUID();

    }

    public Tratamiento(String nombre, int duracion, float dosis, double precio,
            EnumsList.TipoTratamiento tipoTratamiento,
            String descripcion) {
        this.idTratamiento = UUID.randomUUID();
        this.nombre = nombre;
        this.duracion = duracion;
        this.dosis = dosis;
        this.precio = precio;
        this.tipoTratamiento = tipoTratamiento;
        this.descripcion = descripcion;
    }

    public Tratamiento(String nombre) {
        this.nombre = nombre;
    }

    public UUID getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(UUID idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    @Override
    public String toString() {
        // RERTURN ALL
        return '\n' + nombre + '\n' + duracion + '\n' + dosis + '\n' + precio + '\n' + tipoTratamiento + '\n'
                + descripcion;
    }

}
