/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TDAs;

/**
 *
 * @author Mati
 */
class NodoVert {

    private Object elem;
    private NodoVert sigVertice;
    private NodoAdy primerAdy;

    public NodoVert(Object el, NodoVert sig, NodoAdy ady) {
        elem = el;
        sigVertice = sig;
        primerAdy = ady;
    }

    public Object getElem() {
        return this.elem;
    }

    public void setElem(Object el) {
        this.elem = el;
    }

    public NodoVert getSigVertice() {
        return this.sigVertice;
    }

    public void setSigVertice(NodoVert sig) {
        this.sigVertice = sig;
    }

    public NodoAdy getPrimerAdy() {
        return this.primerAdy;
    }

    public void setPrimerAdy(NodoAdy ady) {
        this.primerAdy = ady;
    }
}
