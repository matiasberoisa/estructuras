package tests;

import clases.dinamicas.*;

public class testHash {
    public static void main(String[] args) {
        TablaHash t1 = new TablaHash(4);
        Lista l1 = new Lista();
        System.out.println("se inserto el 1: " + t1.insertar(1));
        System.out.println("se inserto el 2: " + t1.insertar(2));
        System.out.println("se inserto el 3: " + t1.insertar(3));
        System.out.println("se inserto el 4: " + t1.insertar(4));
        System.out.println("se inserto el 5: " + t1.insertar(5));
        l1 = t1.listar();
        System.out.println(l1.toString());
        System.out.println("se elimino el 2: " + t1.eliminar(2));
        l1 = t1.listar();
        System.out.println(l1.toString());
    }

}
