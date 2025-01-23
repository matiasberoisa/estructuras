package clasesTPO;

public class Ruta {
    private String origen;
    private String destino;
    private int tiempoEstimado;

    public Ruta(String o, String d, int ti) {
        origen = o;
        destino = d;
        tiempoEstimado = ti;
    }

    public String getOrigen() {
        return this.origen;
    }

    public String getDestino() {
        return this.destino;
    }

    public int getTiempo() {
        return this.tiempoEstimado;
    }

    public void setOrigen(String nn) {
        origen = nn;
    }

    public void setDestino(String des) {
        destino = des;
    }

    public void setTiempo(int ti) {
        tiempoEstimado = ti;
    }

    public String toString() {
        return origen + ";" + destino + ";" + tiempoEstimado;
    }
}
