/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lineales.dinamicas;

/**
 *
 * @author Mati
 */
public class Lista {

    private Nodo cabecera;

    public Lista() {
        this.cabecera = null;
    }

    // metodos
    public boolean insertar(Object unElem, int pos) {
        // inserta el elemento nuevo en la posicion pos
        // detecta y reporta error posicion invalida
        boolean exito = true;
        if (pos < 1 || pos > this.longitud() + 1) {
            exito = false;
        } else {
            if (pos == 1) { // crea un nuevo nodo y se enlaza en la cabecera
                this.cabecera = new Nodo(unElem, this.cabecera);
            } else { // avanza hasat el elemento en la posicion pos - 1
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                // crea el nodo y lo enlaza
                Nodo nuevo = new Nodo(unElem, aux.getEnlace());
                aux.setEnlace(nuevo);
            }
        }
        // nunca hay error de lista llena, entonces devuelve true
        return exito;
    }

    public boolean eliminar(int pos) {
        Nodo aux = null;
        boolean exito = true;
        if (pos > 1) {
            if (pos == 1) {
                if (aux.getEnlace() == null) {
                    aux = this.cabecera.getEnlace();
                } else {
                    cabecera.setEnlace(aux);
                    cabecera = aux;
                }
            } else {
                aux.setEnlace(aux.getEnlace().getEnlace());
            }
        } else {
            exito = false;
        }
        return exito;
    }

    public Object recuperar(int pos) {
        return null;
    }
    
    public int localizar(Object elElem) {
        return 0;
    }

    public int longitud() {
        return 0;
    }

    public boolean esVacia() {
        return (this.cabecera == null);
    }

    public void vaciar() {
        if (!esVacia()) {
            this.cabecera = null;
        }
    }

    public Lista clone() {
        return null;
    }

    public String toString() {
        return "";
    }
}
