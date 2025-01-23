package clasesTPO;

public class Ciudad {
    private String nombre;
    private boolean alojamiento;
    private boolean sede;

    public Ciudad(String nn, boolean al, boolean se) {
        nombre = nn;
        alojamiento = al;
        sede = se;
    }

    public String getNombre() {
        return this.nombre;
    }

    public boolean tieneAlojamiento() {
        return this.alojamiento;
    }

    public boolean sedeCopa() {
        return this.sede;
    }
}
