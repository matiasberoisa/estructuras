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
}
