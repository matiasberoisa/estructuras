package clasesTPO;

public class Partido {
    private String eq1;
    private String eq2;
    private String instancia;
    private String ciudad;
    private String estadio;
    private int golesE1;
    private int golesE2;

    public Partido(String e1, String e2, String ins, String ci, String es, int gol1, int gol2) {
        eq1 = e1;
        eq2 = e2;
        instancia = ins;
        ciudad = ci;
        estadio = es;
        golesE1 = gol1;
        golesE2 = gol2;
    }

    public String getEquipo1() {
        return this.eq1;
    }

    public String getEquipo2() {
        return this.eq2;
    }

    public String getInstancia() {
        return this.instancia;
    }

    public String getCiudad() {
        return this.ciudad;
    }

    public String getEstadio() {
        return this.estadio;
    }

    public int golesEquipo1() {
        return this.golesE1;
    }

    public int golesEquipo2() {
        return this.golesE2;
    }

    public String toString() {
        return eq1 + ";" + eq2 + ";" + instancia + ";" + ciudad + ";" + estadio + ";" + golesE1 + ";" + golesE2;
    }
}
