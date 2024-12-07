/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TDAs;

/**
 *
 * @author Mati
 */
public class Grafo {

    private NodoVert inicio;

    public Grafo() {
        this.inicio = null;
    }

    public boolean insertarVertice(Object elem) {
        boolean exito = false;
        NodoVert aux = this.ubicarVertice(elem);
        if (aux == null) {
            this.inicio = new NodoVert(elem, this.inicio, null);
            exito = true;
        }
        return exito;
    }

    private NodoVert ubicarVertice(Object elemBuscado) {
        NodoVert aux = this.inicio;
        while (aux != null && !aux.getElem().equals(elemBuscado)) {
            aux = aux.getSigVertice();
        }

        return aux;
    }

    public boolean eliminarVertice(Object elem) {
        return true;
    }

    public boolean existeVertice(Object elem) {
        return true;
    }

    public boolean insertarArco() {
        return true;
    }

    public boolean eliminarArco() {
        return true;
    }

    public boolean existeArco() {
        return true;
    }

    public boolean vacio() {
        return this.inicio == null;
    }
}
