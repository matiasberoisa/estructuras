/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tests;

import clases.*;

/**
 *
 * @author Mati
 */
public class jerarquicos {

    public static void main(String[] args) throws CloneNotSupportedException {
        ArbolBin a1 = new ArbolBin();
        ArbolBin a2 = new ArbolBin();
        @SuppressWarnings("unused")
        Lista l1 = new Lista();
        a1.insertar(1, 0, 'I');
        a1.insertar(2, 1, 'I');
        a1.insertar(3, 1, 'D');
        a1.insertar(4, 2, 'I');
        a1.insertar(5, 2, 'D');
        a1.insertar(9, 5, 'D');
        a1.insertar(34, 3, 'D');

        a2.insertar(1, 0, 'I');
        a2.insertar(2, 1, 'I');
        a2.insertar(3, 1, 'D');
        a2.insertar(4, 2, 'I');
        a2.insertar(5, 2, 'D');
        a2.insertar(50, 5, 'D');
        a2.insertar(47, 3, 'D');
        System.out.println(a1.toString());
        System.out.println(a1.ancestros(2));
    }
}
