
public class EnumsList {
    public enum TipoVista {
        AGREGAR_MASCOTA,
        AGREGAR_CLIENTE,
        AGREGAR_VETERINARIO,
        BUSCAR,
        GENERAR_TURNO,
        GENERAR_UNION_CLIENTE_MASCOTA,
    }

    public enum MascotaColor {
        BLANCO, NEGRO, MARRON, AZUL, VERDE, ROJO, AMARILLO, AZULEJO, ROSADO, VIOLETA, NARANJA, CELESTE, GRIS, ROSA,
        AMARILLADO, AZUL_CLARO, ROJO_CLARO, VERDE_CLARO, AMARILLO_CLARO, AZUL_MORADO, ROJO_MORADO, VERDE_MORADO,
        AMARILLO_MORADO
    }

    public enum Size {
        PEQUEÑO, MEDIO, GRANDE
    }

    public enum Turnos {
        DIA, TARDE, NOCHE
    }

    public enum TipoTratamiento {
        VACUNACION,
        DESPARACITACION,
        CIRUGIA,
        CONTROL_DE_PARASITOS,
        TRATAMIENTO_DENTAL,
        ATENCION_DE_EMERGENCIAS,
        ENFERMEDADES_INFECCIOSAS,
        CUIDADO_DE_HERIDAS,
        NEUTRALIZACION,
        TERAPIA_FISICA,
        EXAMENES_DE_SALUD,
        CONTROL_DEL_PESO,
        TRATAMIENTO_DE_ENFERMEDADES_CRONICAS,
        HOSPITALIZACION,
        TERAPIA_INTRAVENOSA
    }

    public enum ButtonStyle {
        RED_BUTTON, GREY_PURPLE_BUTTON, LIGHT_PURPLE_BUTTON, GREY_BUTTON
    }

}