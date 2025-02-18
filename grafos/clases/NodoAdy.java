/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Mati
 */
class NodoAdy {
    private NodoVert vertice;
    private NodoAdy sigAdyacente;
    private Object etiqueta;

    public NodoAdy(NodoVert vert, NodoAdy sig, Object et) {
        this.vertice = vert;
        this.sigAdyacente = sig;
        this.etiqueta = et;
    }

    public NodoAdy(NodoVert vert, NodoAdy sig) {
        this.vertice = vert;
        this.sigAdyacente = sig;
    }

    public NodoVert getVertice() {
        return this.vertice;
    }

    public NodoAdy getSigAdyacente() {
        return this.sigAdyacente;
    }

    public Object getEtiqueta() {
        return this.etiqueta;
    }

    public void setVertice(NodoVert vert) {
        this.vertice = vert;
    }

    public void setSigAdyacente(NodoAdy sig) {
        this.sigAdyacente = sig;
    }

    public void setEtiqueta(Object et) {
        this.etiqueta = et;
    }
}
