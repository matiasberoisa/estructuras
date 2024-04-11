/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tests.lineales;

import lineales.dinamicas.Cola;
//import lineales.estaticas.Cola;


/**
 *
 * @author Mati
 */
public class testCola {

    public static void main(String[] args) {
        // Test de cola estática
        System.out.println("test cola:");
        System.out.println("//////////////////////METODO PONER//////////////////////////////");
        Cola c1 = new Cola();
        // metodo repetitivo para poner los elementos en la cola
        for (int i = 1; i <= 11; i++) {
            if (i == 11) {
                System.out.println("se apilo el objeto 11?(TRUE en dinamica, FALSE en estatica): " + c1.poner(i) + ", arreglo: " + c1.toString());
            } else {
                System.out.println("se apilo el num " + (i) + "?: " + c1.poner(i) + ", arreglo: " + c1.toString());
            }
        }
        // en dinamica, revisa si se pone el 11, si es TRUE, saca un elem para continuar
        System.out.println("//////////////////////METODO OBTENER FRENTE//////////////////////////////");
        if (c1.obtenerFrente() == null) {
            System.out.println("si pudo poner el 11, saca un elemento para continuar");
            c1.sacar();
            System.out.println(c1.toString());
        } else {
            System.out.println("ERROR, no se puso el 11");
        }
        // probando el metodo sacar
        System.out.println("////////////////////////METODO SACAR////////////////////////////");
        if (!c1.esVacia()) {
            // verificamos primero que la cola no este vacia
            System.out.println("la cola no esta vacia");
            c1.sacar();
            System.out.println("nueva cola: " + c1.toString());
        }
        // revisa si la cola esta vacia
        System.out.println("////////////////////////METODO ESVACIA////////////////////////////");
        System.out.println("cola vacia: " + c1.esVacia());
        Cola c2 = new Cola();
        c2 = c1.clone();
        // imprime el arreglo original y la copia para probar el metodo clone
        System.out.println("///////////////////////METODO CLONE/////////////////////////////");
        System.out.println("cola original " + c1.toString());
        System.out.println("copia " + c2.toString());
        // si la cola no esta vacia, usa el metodo vaciar
        System.out.println("//////////////////////METODO VACIAR//////////////////////////////");
        if (!c1.esVacia()) {
            c1.vaciar();
            System.out.println(c1.toString() + ", cola vaciada");
        }
        System.out.println("//////////////////////FIN TEST//////////////////////////////");
    }
}
