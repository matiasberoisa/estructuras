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
        a1.insertar("A", "A");
        a1.insertar("B", "A");
        a1.insertar("C", "A");
        a1.insertar("D", "A");
        a1.insertar("E", "B");
        a1.insertar("F", "B");
        a1.insertar("G", "B");
        a1.insertar("H", "D");
        System.out.println("original:");
        System.out.println(a1.toString());
        System.out.println("camino hoja mas cercana:");
        System.out.println(a1.caminoAHojaMasCercana());
    }
}
