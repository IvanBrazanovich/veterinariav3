
// LISTADO DE COLORES

import java.io.Serializable;

import java.util.UUID;

public class Mascota implements Serializable {
    private UUID idMascota;
    private String nombre;
    private int edad;
    private UUID ownerFK;
    private EnumsList.MascotaColor color;
    private float peso;
    private EnumsList.Size size;

    public float getPeso() {
        return peso;
    }

    public EnumsList.Size getSize() {
        return size;
    }

    public void setSize(EnumsList.Size size) {
        this.size = size;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public void setColor(String color) {
        this.color = EnumsList.MascotaColor.valueOf(color);
    }

    public EnumsList.MascotaColor getColor() {
        return color;
    }

    public void setColor(EnumsList.MascotaColor color) {
        this.color = color;
    }

    public UUID getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(UUID idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public UUID getOwnerFk() {
        return ownerFK;
    }

    public void setOwnerFk(UUID personaId) {
        this.ownerFK = personaId;
    }

    public Mascota() {
        this.idMascota = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "Mascota{" +
                "idMascota=" + idMascota +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", ownerFK=" + ownerFK +
                ", color=" + color +
                ", peso=" + peso +
                ", size=" + size +
                '}';
    }

}