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

    public void setNombre(String nn) {
        nombre = nn;
    }

    public void setAlojamiento(boolean al) {
        alojamiento = al;
    }

    public void setSede(boolean se) {
        sede = se;
    }

    public String toString() {
        return nombre + ";" + alojamiento + ";" + sede;
    }
}
