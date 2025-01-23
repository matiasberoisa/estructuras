package tests;

import clases.dinamicas.*;
import clasesTPO.Partido;

public class testHash {
    public static void main(String[] args) {
        TablaHash t1 = new TablaHash(4);
        Lista l1 = new Lista();
        Partido partido1 = new Partido("Argentina", "Chile", "grupo", "Atlanta", "benz", 2, 0);
        Partido partido2 = new Partido("USA", "COL", "grupo", "boston", "AT&T", 1, 0);
        System.out.println("se inserto el 1: " + t1.insertar(partido1.toString()));
        System.out.println("se inserto el 2: " + t1.insertar(partido2.toString()));
        System.out.println(t1.toString());
        l1 = t1.listar();
        System.out.println(l1.toString());
        System.out.println("se elimino el 2: " + t1.eliminar(2));
        l1 = t1.listar();
        System.out.println(l1.toString());
    }

}
