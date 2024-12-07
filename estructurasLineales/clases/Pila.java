/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author matias.beroisa
 */
public class Pila {

    private Nodo tope;

    // constructores
    public Pila() {
        this.tope = null;
    }

    public boolean apilar(Object unElemento) {
        // crea un nuevo nodo delante de la antigua cabecera
        Nodo nuevo = new Nodo(unElemento, this.tope);
        // actualiza el tope para que apunte a nodo nuevo
        this.tope = nuevo;
        // nunca hay error de pila llena, entonces devuelve true
        return true;
    }

    public boolean desapilar() {
        boolean exito = false;
        if (!esVacia()) {
            this.tope = tope.getEnlace();
            exito = true;
        }
        return exito;
    }

    public Object obtenerTope() {
        Object topeRetorno = null;
        if (this.tope != null) {
            topeRetorno = this.tope.getElem();
        }
        return topeRetorno;
    }

    public Boolean esVacia() {
        return this.tope == null;
    }

    public void vaciar() {
        if (!esVacia()) {
            this.tope = null;
        }
    }

    public Pila clone() {
        Nodo original = this.tope, clonada;
        Pila aux = new Pila();
        if (!esVacia()) {
            aux.tope = new Nodo(original.getElem(), null);
            clonada = aux.tope;
            original = original.getEnlace();
            while (original != null) {
                Nodo clon = new Nodo(original.getElem(), null);
                clonada.setEnlace(clon);
                clonada = clonada.getEnlace();
                original = original.getEnlace();
            }
        }
        return aux;
    }

    public String toString() {
        String s = "";

        if (this.tope == null) {
            s = "Pila vacia";
        } else {
            // se ubica para recorrer la pila
            Nodo aux = this.tope;
            s = "[";

            while (aux != null) {
                // agrega texto del elem y avanza
                s += aux.getElem().toString();
                aux = aux.getEnlace();
                if (aux != null) {
                    s += ",";
                }

            }
            s += "]";
        }

        return s;
    }
}
