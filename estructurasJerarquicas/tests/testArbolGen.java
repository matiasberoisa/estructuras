package tests;

import clases.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author matias.beroisa
 */
public class testArbolGen {

    public static void main(String[] args) throws CloneNotSupportedException {
        ArbolGen a1 = new ArbolGen();
        @SuppressWarnings("unused")
        ArbolGen a2 = new ArbolGen();
        a1.insertar(20, 1);
        a1.insertar(13, 20);
        a1.insertar(54, 20);
        a1.insertar(15, 13);
        a1.insertar(12, 13);
        a1.insertar(11, 54);
        a1.insertar(27, 54);
        a1.insertar(4, 54);
        a1.insertar(100, 54);
        a1.insertar(17, 27);
        System.out.println("original:");
        System.out.println(a1.toString());
        System.out.println(a1.gradoMaximo());

    }
}
