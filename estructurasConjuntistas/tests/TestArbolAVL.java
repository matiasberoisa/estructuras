package tests;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import clases.dinamicas.ArbolAVL;

/**
 *
 * @author agusf
 */
public class TestArbolAVL {

    public static void main(String[] args) {
        ArbolAVL arbol = new ArbolAVL();

        /*
         * arbol.insertar(15);
         * arbol.insertar(8);
         * arbol.insertar(11);
         * arbol.insertar(19);
         * arbol.insertar(1);
         * arbol.insertar(14);
         * arbol.insertar(7);
         * arbol.insertar(200);
         * arbol.insertar(10);
         * arbol.insertar(-5);
         * arbol.insertar(0);arbol.insertar(6);
         * arbol.insertar(5);arbol.insertar(-4);
         * arbol.insertar(-1);
         */
        int elem2 = 1;
        arbol.insertar(3);
        arbol.insertar(2);
        arbol.insertar(elem2);
        arbol.insertar(4);
        arbol.insertar(5);
        arbol.insertar(6);
        arbol.insertar(7);
        arbol.insertar(8);
        arbol.insertar(9);
        arbol.insertar(10);
        arbol.insertar(11);
        arbol.insertar(12);

        System.out.println(arbol.toString());
        System.out.println(arbol.recuperar(10));
        System.out.println(arbol.recuperar(100));

        System.out.println("eliminamos el nodo 1 esperamos rta true\t\t" + arbol.eliminar(1));
        System.out.println(arbol.toString());
        System.out.println("eliminamos el nodo 11 esperamos rta true\t\t" + arbol.eliminar(11));
        System.out.println(arbol.toString());
        System.out.println("eliminamos el nodo 8 esperamos rta true\t\t" + arbol.eliminar(8));
        System.out.println(arbol.toString());
        System.out.println("eliminamos el nodo 7 esperamos rta true\t\t" + arbol.eliminar(7));
        System.out.println(arbol.toString());
        System.out.println("eliminamos el nodo 6 esperamos rta true\t\t" + arbol.eliminar(6));
        System.out.println(arbol.toString());
        System.out.println("eliminamos el nodo 5 esperamos rta true\t\t" + arbol.eliminar(5));
        System.out.println(arbol.toString());
        System.out.println("eliminamos el nodo 4 esperamos rta true\t\t" + arbol.eliminar(4));
        System.out.println(arbol.toString());
        System.out.println("eliminamos el nodo 3 esperamos rta true\t\t" + arbol.eliminar(3));
        System.out.println(arbol.toString());
        System.out.println("eliminamos el nodo 2 esperamos rta true\t\t" + arbol.eliminar(2));
        System.out.println(arbol.toString());
        System.out.println("eliminamos el nodo 10 esperamos rta true\t\t" + arbol.eliminar(10));
        System.out.println(arbol.toString());

    }
}
