
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
        Nodo aux = this.cabecera;
        boolean exito = true;
        int contadorPos = 1;
        if (pos >= 1 && pos <= longitud()) {
            if (pos == 1) {
                if (this.cabecera.getEnlace() == null) {
                    this.cabecera = null;
                    aux = null;
                } else {
                    this.cabecera = this.cabecera.getEnlace();
                }
            } else {
                while (contadorPos != (pos - 1)) {
                    aux = aux.getEnlace();
                    contadorPos++;
                }
                aux.setEnlace(aux.getEnlace().getEnlace());
            }
        } else {
            exito = false;
        }
        return exito;
    }

    public Object recuperar(int pos) {
        Nodo aux = this.cabecera;
        int contador = 1;
        while (aux != null && contador != pos) {
            aux = aux.getEnlace();
            contador++;
        }
        return aux.getElem();
    }

    public int localizar(Object elElem) {
        Nodo pos = this.cabecera;
        int posicion = -1, aux = 1;
        while (pos != null && posicion == -1) {
            if (pos.getElem() == elElem) {
                posicion = aux;
            }
            pos = pos.getEnlace();
            aux++;
        }
        return posicion;
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
        Nodo aux;
        Lista copia = new Lista();
        // Nodo actual = this.cabecera;
        // int pos = 1;
        // while (actual != null) {
        aux = recorrerRecursivo(this.cabecera);
        copia.cabecera = aux;
        // aux.insertar(actual.getElem(), pos);
        // actual = actual.getEnlace();
        // pos++;
        // }
        return copia;
    }

    private Nodo recorrerRecursivo(Nodo actual) {
        Nodo nuevo = null;
        if (actual != null) {
            nuevo = new Nodo(actual.getElem(), null);
            nuevo.setEnlace(recorrerRecursivo(actual.getEnlace()));
        }
        return nuevo;
    }

    @Override
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

    public Lista obtenerMultiplos(int num) {
        Lista nuevaLista = new Lista();
        if (this.cabecera != null) {
            obtenerMultiplosAux(nuevaLista, num);
        }
        return nuevaLista;
    }

    private void obtenerMultiplosAux(Lista nuevaLis, int num) {
        Nodo actual = this.cabecera, aux = null;
        int i;
        for (i = 1; i <= this.longitud(); i++) {
            if (i % num == 0) {
                if (aux == null) {
                    nuevaLis.cabecera = new Nodo(actual.getElem(), null);
                    aux = nuevaLis.cabecera;
                } else {
                    aux.setEnlace(new Nodo(actual.getElem(), null));
                    aux = aux.getEnlace();
                }
            }
            actual = actual.getEnlace();
        }
    }

    public void eliminarAparaciones(Object x) {
        Nodo aux = this.cabecera;
        if (this.cabecera != null) {
            if (this.cabecera.getElem().equals(x)) {
                if (this.cabecera.getEnlace() != null) {
                    this.cabecera = this.cabecera.getEnlace();
                }
            }
            while (aux.getEnlace() != null) {
                Nodo nuevo = null;
                if (aux.getEnlace().getElem().equals(x)) {
                    if (aux.getEnlace().getEnlace() != null) {
                        nuevo = aux.getEnlace().getEnlace();
                    }
                    aux.setEnlace(nuevo);
                } else {
                    aux = aux.getEnlace();
                }
            }
        }
    }

    public boolean moverAAnteultimaPosicion(int pos) {
        Nodo aux = this.cabecera;
        int contador = 0;
        boolean exito = false;
        if (longitud() > 0 && pos > 0 && pos <= this.longitud() && pos != longitud() - 1) {
            while (contador < this.longitud() - 1 && !exito) {
                if (contador != pos - 2 && contador != longitud() - 2) {
                    aux = aux.getEnlace();
                    contador++;
                } else {
                    exito = true;
                    if (pos == this.longitud()) {
                        Object elemento = aux.getElem();
                        aux.setElem(aux.getEnlace().getElem());
                        aux = aux.getEnlace();
                        aux.setElem(elemento);
                    } else {
                        Nodo ultPos = null;
                        Nodo elem = null;
                        if (pos == 1) {
                            elem = this.cabecera;
                            // caso 1: primera posicion
                            while (contador < this.longitud() - 2) {
                                aux = aux.getEnlace();
                                contador++;
                            }
                            this.cabecera = this.cabecera.getEnlace();
                        } else {
                            // caso 2: cualquier posicion menos la anteultima
                            elem = aux.getEnlace();
                            aux.setEnlace(aux.getEnlace().getEnlace());
                            while (contador < this.longitud() - 2) {
                                aux = aux.getEnlace();
                                contador++;
                            }
                        }
                        ultPos = aux.getEnlace();
                        aux.setEnlace(elem);
                        elem.setEnlace(ultPos);
                    }
                }
            }
        }
        return exito;
    }

    public void agregarElem(Object nuevo, int pos) {
        Nodo nuevoElem;
        Nodo aux = this.cabecera;
        Nodo sigNodo;
        int contador = 0;
        for (int i = 1; i < this.longitud() + 1; i++) {
            if (i == 1) {
                nuevoElem = new Nodo(nuevo, null);
                this.cabecera = nuevoElem;
                nuevoElem.setEnlace(aux);
            } else {
                contador++;
                if (contador == pos) {
                    nuevoElem = new Nodo(nuevo, null);
                    sigNodo = aux.getEnlace();
                    aux.setEnlace(nuevoElem);
                    nuevoElem.setEnlace(sigNodo);
                    contador = -1;
                }
                aux = aux.getEnlace();
            }
        }
    }

    public void agregarElemRec(Object nuevo, int pos) {
        if (this.cabecera != null && pos < this.longitud() + 1 && pos > 0) {
            agregarAux(this.cabecera, nuevo, pos, 1, false);
        }
    }

    private void agregarAux(Nodo aux, Object nuevo, int pos, int contador, boolean insertado) {
        if (aux != null) {
            Nodo nuevoElem;
            if (contador == 1 && !insertado) {
                nuevoElem = new Nodo(nuevo, null);
                this.cabecera = nuevoElem;
                nuevoElem.setEnlace(aux);
                agregarAux(aux.getEnlace(), nuevo, pos, contador + 1, true);
            } else {
                if (contador == pos) {
                    nuevoElem = new Nodo(nuevo, null);
                    Nodo sigNodo = aux.getEnlace();
                    aux.setEnlace(nuevoElem);
                    nuevoElem.setEnlace(sigNodo);
                    agregarAux(aux.getEnlace(), nuevo, pos, 0, insertado);
                } else {
                    agregarAux(aux.getEnlace(), nuevo, pos, contador + 1, insertado);
                }
            }
        }
    }

    public void invertir() {
        Nodo aux = this.cabecera;
        Nodo nuevo = null;
        Nodo sig = aux.getEnlace();
        Nodo ant = null;
        while (aux != null) {
            nuevo = aux;
            nuevo.setEnlace(ant);
            ant = nuevo;
            aux = sig;
            if (sig != null) {
                sig = sig.getEnlace();
            }
        }
        this.cabecera = nuevo;
    }

    public void invertir2() {
        if (this.cabecera != null) {
            invertirAux(this.cabecera, this.cabecera.getEnlace(), null);
        }
    }

    private void invertirAux(Nodo aux, Nodo sig, Nodo ant) {
        if (aux != null) {
            aux.setEnlace(ant);
            ant = aux;
            aux = sig;
            if (sig != null) {
                sig = sig.getEnlace();
                invertirAux(aux, sig, ant);
            } else {
                this.cabecera = ant;
            }
        }
    }

    public Lista intercalar(Lista l2) {
        Nodo original = new Nodo(this.cabecera.getElem(), null);
        Lista lis = new Lista();
        lis.cabecera = original;
        Nodo rec = this.cabecera.getEnlace();
        Nodo rec2 = l2.cabecera;
        if (this.cabecera != null && rec2 != null) {
            while (rec != null && rec2 != null) {
                if (rec2 != null) {
                    original.setEnlace(rec2);
                    original = original.getEnlace();
                    rec2 = rec2.getEnlace();
                    if (rec != null) {
                        original.setEnlace(rec);
                        original = original.getEnlace();
                        rec = rec.getEnlace();
                    }
                }
            }
            if (rec2 != null) {
                original.setEnlace(rec2);
            }
        } else if (this.cabecera != null) {
            lis.cabecera = l2.cabecera;
        } else {
            lis.cabecera = this.cabecera;
        }
        return lis;
    }

    public int contar(Object elem) {
        int[] contador = new int[1];
        contador[0] = 0;
        if (!esVacia()) {
            contarAux(this.cabecera, elem, contador);
        }
        return contador[0];
    }

    private void contarAux(Nodo actual, Object elem, int[] contador) {
        if (actual != null) {
            Object elemento = actual.getElem();
            if (elemento.equals(elem)) {
                contador[0]++;
            }
            contarAux(actual.getEnlace(), elem, contador);
        }
    }

    public boolean esCapicua() {
        boolean capicua = true;
        if (!esVacia()) {
            while (this.longitud() > 1 && capicua) {
                Object primerElem = recuperar(1);
                Object ultElem = recuperar(this.longitud());
                if (primerElem.equals(ultElem)) {
                    eliminar(1);
                    eliminar(this.longitud());
                } else {
                    capicua = false;
                }
            }
        }

        return capicua;
    }
}
