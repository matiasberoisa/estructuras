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
}
