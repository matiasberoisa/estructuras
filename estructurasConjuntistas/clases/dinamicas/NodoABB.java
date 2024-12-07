/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases.dinamicas;

/**
 *
 * @author Mati
 */
public class NodoABB {

    @SuppressWarnings("rawtypes")
    private Comparable elem;
    private NodoABB izquierdo;
    private NodoABB derecho;

    @SuppressWarnings("rawtypes")
    public NodoABB(Comparable el, NodoABB izq, NodoABB der) {
        this.elem = el;
        this.izquierdo = izq;
        this.derecho = der;
    }

    @SuppressWarnings("rawtypes")
    public NodoABB(Comparable el) {
        this.elem = el;
    }

    @SuppressWarnings("rawtypes")
    public Comparable getElem() {
        return this.elem;
    }

    @SuppressWarnings("rawtypes")
    public void setElem(Comparable el) {
        elem = el;
    }

    public NodoABB getIzquierdo() {
        return this.izquierdo;
    }

    public void setIzquierdo(NodoABB izq) {
        izquierdo = izq;
    }

    public NodoABB getDerecho() {
        return this.derecho;
    }

    public void setDerecho(NodoABB der) {
        derecho = der;
    }
}
