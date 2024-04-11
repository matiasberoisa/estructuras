/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.dinamicas;

import java.util.Objects;

/**
 *
 * @author matias.beroisa
 */
public class Cola {

    private Nodo frente;
    private Nodo fin;

    // constructor
    public Cola() {
        this.frente = null;
        this.fin = null;
    }

    // metodos
    public boolean poner(Object unelem) {
        // crea un nuevo nodo delante de la antigua cabecera
        Nodo nuevo = new Nodo(unelem, this.fin);
        if (this.frente != null) {
            this.frente = this.fin = nuevo;
        } else {
            this.fin = nuevo;
        }
        // nunca hay error de cola llena, entonces devuelve true
        return true;
    }

    public boolean sacar() {
        boolean exito = true;
        if (this.frente == null) {
            // la cola esta vacia, reporta error
            exito = false;
        } else {
            // hay al menos un elemento
            // quita el primer elemento y actualiza el frente(y fin queda vacia)
            this.frente = this.frente.getEnlace();
            if (this.frente == null) {
                this.fin = null;
            }
        }
        return exito;
    }

    public Object obtenerFrente() {
        Object frenteRetorno = null;
        if (this.frente != this.fin) {
            frenteRetorno = this.frente;
        }
        return frenteRetorno;
    }

    public Boolean esVacia() {
        return Objects.equals(this.frente, this.fin);
    }

    public void vaciar() {
        if (!esVacia()) {
            this.frente = null;
            this.fin = null;
        }
    }

    public Cola clone() {
        Cola aux = new Cola();
        Nodo actual = this.frente;
        if (this.frente != null) {
            while (actual != null) {
                aux.poner(frente);
                actual = this.frente.getEnlace();
            }
        }
        return aux;
    }

    @Override
    public String toString() {
        String s = "";
        if (this.frente == this.fin) {
            s = "Pila vacia";
        } else {
            // se ubica para recorrer la cola
            Nodo aux = this.fin;
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
        return s; // return the string here
    }
}
