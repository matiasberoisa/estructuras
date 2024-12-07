/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author Mati
 */
public class NodoGen {
    private Object elem;
    private NodoGen hijoIzquierdo;
    private NodoGen hermanoDerecho;

    // constructores
    public NodoGen(Object unElem, NodoGen izq, NodoGen der) {
        elem = unElem;
        hijoIzquierdo = izq;
        hijoIzquierdo = der;
    }

    // observadores
    public Object getElem() {
        return this.elem;
    }

    public NodoGen getHijoIzquierdo() {
        return this.hijoIzquierdo;
    }

    public NodoGen getHermanoDerecho() {
        return this.hermanoDerecho;
    }

    // modificadores
    public void setElem(Object unElem) {
        elem = unElem;
    }

    public void setHijoIzquierdo(NodoGen izq) {
        hijoIzquierdo = izq;
    }

    public void setHermanoDerecho(NodoGen der) {
        hermanoDerecho = der;
    }
}
