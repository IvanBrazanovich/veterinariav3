
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Turno implements Serializable {

    private UUID idTurno;
    private UUID trabajadorFk;
    private UUID mascotasFk;
    private LocalDate fecha;
    private String observacion;
    private Tratamiento tratamientos;

    public Tratamiento getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(Tratamiento tratamientos) {
        this.tratamientos = tratamientos;
    }

    public void setMascotasFk(UUID mascotasFk) {
        this.mascotasFk = mascotasFk;
    }

    public UUID getMascotasFk() {
        return mascotasFk;
    }

    public void setTrabajadorFk(UUID trabajadorFk) {
        this.trabajadorFk = trabajadorFk;
    }

    public UUID getTrabajadorFk() {
        return trabajadorFk;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public UUID getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(UUID idTurno) {
        this.idTurno = idTurno;
    }

    public Turno() {
        this.idTurno = UUID.randomUUID();
    }

    public Turno(LocalDate fecha, String observacion) {
        this.fecha = fecha;
        this.observacion = observacion;
        this.idTurno = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "Turno [idTurno=" + idTurno + ", trabajadorFk=" + trabajadorFk + ", mascotasFk=" + mascotasFk
                + ", fecha=" + fecha + ", observacion=" + observacion + ", tratamientos=" + tratamientos + "]";
    }

}
