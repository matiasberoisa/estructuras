package clasesTPO;

import java.io.FileReader;
import java.util.HashMap;

public class test {
    public static void main(String[] args) {
        HashMap<Integer, Partido> hash = new HashMap<>();
        // FileReader lectorArchivo = new FileReader();
        Partido partido1 = new Partido("Argentina", "Chile", "grupo", "Atlanta", "benz", 2, 0);
        Partido partido2 = new Partido("USA", "COL", "grupo", "boston", "AT&T", 1, 0);
        hash.put(1, partido1);
        hash.put(2, partido2);
        System.out.println(hash);
        System.out.println(hash.get(1).toString());
        Partido part = hash.get(1);
        System.out.println(part.getCiudad());
        System.out.println(hash.containsValue(partido1));
    }
}
