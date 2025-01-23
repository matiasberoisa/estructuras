package clasesTPO;

public class Equipo {
    private String nombrePais;
    private String tecnico;
    private String grupoInicial;

    public Equipo(String pa, String tec, String gru) {
        nombrePais = pa;
        tecnico = tec;
        grupoInicial = gru;
    }

    public String getPais() {
        return this.nombrePais;
    }

    public String getTecnico() {
        return this.tecnico;
    }

    public String getGrupo() {
        return this.grupoInicial;
    }
}
