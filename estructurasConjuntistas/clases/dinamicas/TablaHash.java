/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases.dinamicas;

import clases.*;

/**
 *
 * @author Mati
 */
public class TablaHash {

    private final int tamaño = 4;
    private final Nodo[] tabla;
    @SuppressWarnings("unused")
    private int cantidad;
    private Nodo[] hash;

    public TablaHash() {
        this.tabla = new Nodo[tamaño];
        this.cantidad = 0;
    }

    public boolean pertenece(Object elem) {
        int pos = elem.hashCode() % this.tamaño;
        Nodo aux = this.hash[pos];
        boolean encontrado = false;
        while (!encontrado && aux != null) {
            encontrado = aux.getElem().equals(elem);
            aux = aux.getEnlace();
        }
        return encontrado;
    }

    public boolean insertar(Object elem) {
        // primero verifica si el elemento ya esta cargado
        // si no lo encuentra, lo pone adelante del resto
        int pos = elem.hashCode() % this.tamaño;
        Nodo aux = this.hash[pos];
        boolean encontrado = false;

        while (!encontrado && aux != null) {
            encontrado = aux.getElem().equals(elem);
            aux = aux.getEnlace();
        }

        if (!encontrado) {
            this.hash[pos] = new Nodo(elem, this.hash[pos]);
            this.cantidad++;
        }

        return !encontrado;
    }

    public boolean eliminar(Object elem) {

        return true;
    }

    @SuppressWarnings("null")
    public Lista listar() {
        Lista lis = null;
        int pos = 0, contadorPos = 1;
        Nodo aux = null;
        if (this.tabla != null) {
            while (pos < this.tamaño) {
                aux = this.hash[pos];
                while (aux != null) {
                    Object elem = aux.getElem();
                    lis.insertar(elem, contadorPos);
                    contadorPos++;
                    aux = aux.getEnlace();
                }
                pos++;
            }
        }
        return lis;
    }
}
