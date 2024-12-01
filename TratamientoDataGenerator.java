import java.util.ArrayList;

public class TratamientoDataGenerator {

    public static ArrayList<Tratamiento> generateTratamientos() {
        ArrayList<Tratamiento> tratamientos = new ArrayList<>();

        System.out.println(EnumsList.TipoTratamiento.VACUNACION);
        tratamientos.add(new Tratamiento("Vacunación anual", 1, 1.0f, 50.0, EnumsList.TipoTratamiento.VACUNACION,
                "Protección anual contra enfermedades comunes en mascotas."));

        tratamientos
                .add(new Tratamiento("Desparasitación canina", 1, 0.5f, 25.0, EnumsList.TipoTratamiento.DESPARACITACION,
                        "Eliminación de parásitos internos y externos en perros."));

        tratamientos.add(new Tratamiento("Cirugía menor", 3, 2.0f, 150.0, EnumsList.TipoTratamiento.CIRUGIA,
                "Procedimiento quirúrgico de baja complejidad para pequeños problemas de salud."));

        tratamientos.add(
                new Tratamiento("Control de parásitos", 1, 0.25f, 20.0, EnumsList.TipoTratamiento.CONTROL_DE_PARASITOS,
                        "Revisión y tratamiento contra infestaciones parasitarias."));

        tratamientos
                .add(new Tratamiento("Limpieza dental", 1, 0.1f, 100.0, EnumsList.TipoTratamiento.TRATAMIENTO_DENTAL,
                        "Limpieza profunda de los dientes para evitar infecciones."));

        tratamientos
                .add(new Tratamiento("Emergencia veterinaria", 1, 0.0f, 200.0,
                        EnumsList.TipoTratamiento.ATENCION_DE_EMERGENCIAS,
                        "Atención inmediata en casos críticos de salud."));

        tratamientos.add(new Tratamiento("Tratamiento de enfermedades infecciosas", 7, 2.0f, 300.0,
                EnumsList.TipoTratamiento.ENFERMEDADES_INFECCIOSAS,
                "Tratamiento especializado para infecciones graves en mascotas."));

        tratamientos
                .add(new Tratamiento("Cuidado de heridas", 5, 1.5f, 75.0, EnumsList.TipoTratamiento.CUIDADO_DE_HERIDAS,
                        "Cuidado y desinfección de heridas para una recuperación rápida."));

        tratamientos.add(new Tratamiento("Neutralización", 1, 1.0f, 120.0, EnumsList.TipoTratamiento.NEUTRALIZACION,
                "Procedimiento para evitar la reproducción en mascotas."));

        tratamientos.add(new Tratamiento("Terapia física", 10, 1.0f, 400.0, EnumsList.TipoTratamiento.TERAPIA_FISICA,
                "Sesiones de rehabilitación física para mejorar la movilidad."));

        tratamientos.add(
                new Tratamiento("Examen general de salud", 1, 0.0f, 80.0, EnumsList.TipoTratamiento.EXAMENES_DE_SALUD,
                        "Revisión completa de salud para detectar problemas tempranos."));

        tratamientos.add(new Tratamiento("Control de peso", 3, 0.0f, 60.0, EnumsList.TipoTratamiento.CONTROL_DEL_PESO,
                "Seguimiento y guía para mantener un peso saludable en la mascota."));

        tratamientos.add(new Tratamiento("Tratamiento de enfermedades crónicas", 30, 1.0f, 500.0,
                EnumsList.TipoTratamiento.TRATAMIENTO_DE_ENFERMEDADES_CRONICAS,
                "Tratamiento a largo plazo para enfermedades crónicas."));

        tratamientos
                .add(new Tratamiento("Hospitalización veterinaria", 2, 0.0f, 1000.0,
                        EnumsList.TipoTratamiento.HOSPITALIZACION,
                        "Cuidado médico completo para mascotas hospitalizadas."));

        tratamientos.add(
                new Tratamiento("Terapia intravenosa", 1, 0.5f, 200.0, EnumsList.TipoTratamiento.TERAPIA_INTRAVENOSA,
                        "Administración de medicamentos o líquidos vía intravenosa para recuperación rápida."));

        return tratamientos;
    }

}
