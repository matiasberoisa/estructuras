/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases.dinamicas;

/**
 *
 * @author Mati
 */
public class NodoAVL {

    @SuppressWarnings("rawtypes")
    private Comparable elem;
    private int altura;
    private NodoAVL izquierdo;
    private NodoAVL derecho;

    @SuppressWarnings("rawtypes")
    public NodoAVL(Comparable el, NodoAVL izq, NodoAVL der) {
        this.elem = el;
        this.izquierdo = izq;
        this.derecho = der;
    }

    @SuppressWarnings("rawtypes")
    public NodoAVL(Comparable el) {
        this.elem = el;
    }

    public int getAltura() {
        return altura;
    }

    public void recalcularAltura() {
        int alturaDerecho = -1, alturaIzquierdo = -1;
        if (derecho != null) {
            alturaDerecho = derecho.getAltura();
        }
        if (izquierdo != null) {
            alturaIzquierdo = izquierdo.getAltura();
        }
        altura = Math.max(alturaIzquierdo, alturaDerecho) + 1;
    }

    @SuppressWarnings("rawtypes")
    public Comparable getElem() {
        return this.elem;
    }

    @SuppressWarnings("rawtypes")
    public void setElem(Comparable el) {
        elem = el;
    }

    public NodoAVL getIzquierdo() {
        return this.izquierdo;
    }

    public void setIzquierdo(NodoAVL izq) {
        izquierdo = izq;
    }

    public NodoAVL getDerecho() {
        return this.derecho;
    }

    public void setDerecho(NodoAVL der) {
        derecho = der;
    }
}
