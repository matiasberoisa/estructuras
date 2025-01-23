package clasesTPO;

import java.io.IOException;

public class testCarga {
    public static void main(String[] args) throws IOException {
        Carga carga = new Carga();
        Ruta[] ciudades;
        ciudades = carga.cargaRutas(44);
        for (int i = 0; i < ciudades.length; i++) {
            System.out.println(ciudades[i].getTiempo());
        }
    }
}
