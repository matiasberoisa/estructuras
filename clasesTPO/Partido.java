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

    public void setEquipo1(String e1) {
        eq1 = e1;
    }

    public void setEquipo2(String e2) {
        eq2 = e2;
    }

    public void setInstancia(String ins) {
        instancia = ins;
    }

    public void setCiudad(String ci) {
        ciudad = ci;
    }

    public void setEstadio(String es) {
        estadio = es;
    }

    public void setGolesEquipo1(int gol) {
        golesE1 = gol;
    }

    public void setGolesEquipo2(int gol) {
        golesE2 = gol;
    }

    public String toString() {
        return eq1 + ";" + eq2 + ";" + instancia + ";" + ciudad + ";" + estadio + ";" + golesE1 + ";" + golesE2;
    }
}
