/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tests;

import clases.estaticas.*;

/**
 *
 * @author Mati
 */
public class testHeap {

    public static void main(String[] args) {
        HeapMin heap = new HeapMin();
        heap.insertar(30);
        heap.insertar(123);
        heap.insertar(23);
        heap.insertar(3);
        heap.insertar(1);
        heap.insertar(12);
        heap.insertar(33);
        heap.insertar(91);
        heap.insertar(24);
        heap.insertar(13);
        heap.insertar(10);
        heap.insertar(29);
        heap.insertar(100);
        heap.insertar(200);
        heap.insertar(139);

        System.out.println(heap.toString());

    }
}
