/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tests;

import clases.dinamicas.*;

/**
 *
 * @author Mati
 */
public class testABB {
    public static void main(String[] args) {
        ArbolBB a1 = new ArbolBB();
        a1.insertar(56);
        a1.insertar(13);
        a1.insertar(78);
        a1.insertar(7);
        a1.insertar(24);
        a1.insertar(15);
        a1.insertar(100);

        System.out.println(a1.toString());
        System.out.println(a1.clonarParteInvertida(13));
    }
}
