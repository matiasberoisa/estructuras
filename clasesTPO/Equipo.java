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

    public void setPais(String nn) {
        nombrePais = nn;
    }

    public void setAlojamiento(String te) {
        tecnico = te;
    }

    public void setGrupo(String gr) {
        grupoInicial = gr;
    }

    public String toString() {
        return nombrePais + ";" + tecnico + ";" + grupoInicial;
    }
}
