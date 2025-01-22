/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases.dinamicas;

/**
 *
 * @author Mati
 */
public class TablaHash {

    private int tamaño;
    private int cantidad = 0;
    private Nodo[] hash;

    public TablaHash(int t) {
        tamaño = t;
        cantidad = 0;
        hash = new Nodo[tamaño];
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
        int pos = elem.hashCode() % this.tamaño;
        Nodo aux = this.hash[pos];
        Nodo anterior = null;
        boolean encontrado = false;
        while (!encontrado && aux != null) {
            encontrado = aux.getElem().equals(elem);
            if (encontrado) {
                cantidad--;
                if (anterior != null) {
                    anterior.setEnlace(aux.getEnlace());
                } else {
                    hash[pos] = aux.getEnlace();
                }
            } else {
                anterior = aux;
                aux = aux.getEnlace();
            }

        }
        return encontrado;
    }

    public boolean esVacio() {
        return cantidad == 0;
    }

    public Lista listar() {
        Lista lis = new Lista();
        int pos = 0, contadorPos = 1;
        Nodo aux = this.hash[pos];
        if (!esVacio()) {
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
