/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import clases.*;

/**
 *
 * @author matias.beroisa
 */
public class testLista {

    @SuppressWarnings("unused")
    public static void main(String[] args) throws CloneNotSupportedException {
        // Test de cola estática
        System.out.println("test cola:");
        System.out.println("//////////////////////METODO PONER//////////////////////////////");
        Lista l1 = new Lista();
        // metodo repetitivo para poner los elementos en la cola
        if (l1 == null) {
            System.out.println("cola nula");
        } else {
            for (int i = 1; i <= 11; i++) {
                if (i >= 11) {
                    System.out.println("se apilo el objeto 11?(TRUE en dinamica, FALSE en estatica): "
                            + l1.insertar(i, i) + ", arreglo: " + l1.toString());
                } else {
                    System.out.println(
                            "se apilo el num " + (i) + "?: " + l1.insertar(i, i) + ", arreglo: " + l1.toString());
                }
            }
        }
        // en dinamica, revisa si se pone el 11, si es TRUE, saca un elem para continuar
        System.out.println("//////////////////////METODO OBTENER FRENTE//////////////////////////////");
        if (l1.recuperar(0) != null) {
            System.out.println("frente: " + l1.recuperar(0));
        } else {
            System.out.println("ERROR, frente nulo");
        }
        // probando el metodo sacar
        System.out.println("////////////////////////METODO SACAR////////////////////////////");
        if (!l1.esVacia()) {
            // verificamos primero que la cola no este vacia
            System.out.println("la cola no esta vacia, saca un elemento del frente");
            l1.eliminar(0);
            System.out.println("nueva cola: " + l1.toString());
            System.out.println("nuevo frente: " + l1.recuperar(0));
        }
        // revisa si la cola esta vacia
        System.out.println("////////////////////////METODO ESVACIA////////////////////////////");
        System.out.println("cola vacia: " + l1.esVacia());

        // imprime el arreglo original y la copia para probar el metodo clone
        System.out.println("///////////////////////METODO CLONE/////////////////////////////");
        Lista l2 = new Lista();
        if (l1 == null) {
            System.out.println("cola l1 vacia, no se puede imprimir la copia");
        } else {
            l2 = l1.clone();
            System.out.println("cola original " + l1.toString());
            System.out.println("copia " + l2.toString());
        }
        // si la cola no esta vacia, usa el metodo vaciar
        System.out.println("//////////////////////METODO VACIAR//////////////////////////////");
        if (!l1.esVacia()) {
            l1.vaciar();
            System.out.println(l1.toString() + ", cola vaciada");
        }
        System.out.println("//////////////////////FIN TEST//////////////////////////////");
    }
}
