/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author Mati
 */
public class NodoArbol {
    private Object elem;
    private NodoArbol izquierdo;
    private NodoArbol derecho;

    // constructores
    public NodoArbol(Object unElem, NodoArbol izq, NodoArbol der) {
        elem = unElem;
        izquierdo = izq;
        derecho = der;
    }

    // observadores
    public Object getElem() {
        return this.elem;
    }

    public NodoArbol getIzquierdo() {
        return this.izquierdo;
    }

    public NodoArbol getDerecho() {
        return this.derecho;
    }

    // modificadores
    public void setElem(Object unElem) {
        elem = unElem;
    }

    public void setIzquierdo(NodoArbol izq) {
        izquierdo = izq;
    }

    public void setDerecho(NodoArbol der) {
        derecho = der;
    }
}
