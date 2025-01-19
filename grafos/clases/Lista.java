/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

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

    @SuppressWarnings("null")
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
        Object elemento = null;
        Nodo aux = this.cabecera;
        int contador = 0;
        while (this.cabecera != null && contador != pos) {
            if (contador == pos) {
                elemento = this.cabecera.getElem();
            } else {
                aux = aux.getEnlace();
                contador++;
            }
        }
        return elemento;
    }

    public int localizar(Object busca) {
        int pos = -1;
        boolean encontrado = false;
        int aux = 1;
        Nodo nodoAux = cabecera;
        while ((!encontrado) && (aux <= this.longitud())) {

            if (nodoAux.getElem() == busca) {
                pos = aux;
                encontrado = true;
            }
            nodoAux = nodoAux.getEnlace();
            aux++;
        }
        return pos;
    }

    public int longitud() {
        Nodo aux = this.cabecera;
        int contador = 0;
        while (aux != null) {
            contador++;
            aux = aux.getEnlace();
        }
        return contador;
    }

    public boolean esVacia() {
        return (this.cabecera == null);
    }

    public void vaciar() {
        if (!esVacia()) {
            this.cabecera = null;
        }
    }

    @Override
    public Lista clone() throws CloneNotSupportedException {
        Lista aux = new Lista();
        Nodo actual = this.cabecera;
        int pos = 0;
        while (actual != null) {
            aux.insertar(actual.getElem(), pos);
            actual = actual.getEnlace();
            pos++;
        }
        return aux;
    }

    public String toString() {
        String s = "";
        if (this.cabecera == null) {
            s = "Lista vacia";
        } else {
            // se ubica para recorrer la cola
            Nodo aux = this.cabecera;
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
