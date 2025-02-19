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
public class ArbolGen {

    private NodoGen raiz;

    public ArbolGen() {
        this.raiz = null;
    }

    public boolean insertar(Object elemNuevo, Object elemPadre) {
        boolean exito = true;
        if (raiz == null) {
            this.raiz = new NodoGen(elemNuevo, null, null);
        } else {
            NodoGen nPadre = obtenerNodo(this.raiz, elemPadre);
            if (nPadre != null) {
                if (nPadre.getHijoIzquierdo() == null) {
                    nPadre.setHijoIzquierdo(new NodoGen(elemNuevo, null, null));
                } else {
                    NodoGen hijo = nPadre.getHijoIzquierdo();
                    while (hijo.getHermanoDerecho() != null) {
                        hijo = hijo.getHermanoDerecho();
                    }
                    hijo.setHermanoDerecho(new NodoGen(elemNuevo, null, null));
                }
            } else {
                exito = false;
            }
        }
        return exito;
    }

    private NodoGen obtenerNodo(NodoGen actual, Object elem) {
        NodoGen resultado = null;
        if (actual != null) {
            if (actual.getElem().equals(elem)) {
                resultado = actual;
            } else {
                NodoGen hijo = actual.getHijoIzquierdo();
                while (hijo != null && resultado == null) {
                    resultado = obtenerNodo(hijo, elem);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return resultado;
    }

    public boolean insertarPorPosicion(Object elem, int posPadre) {
        boolean exito = true;
        int[] num = new int[1];
        num[0] = 1;
        if (posPadre < 0) {
            exito = true;
        } else {
            if (raiz == null) {
                this.raiz = new NodoGen(elem, null, null);
            } else {
                NodoGen nPadre = buscarPadre(this.raiz, posPadre, num);
                if (nPadre != null) {
                    NodoGen nuevo = new NodoGen(elem, null, null);
                    if (nPadre.getHijoIzquierdo() == null) {
                        nPadre.setHijoIzquierdo(nuevo);
                    } else {
                        NodoGen hijo = nPadre.getHijoIzquierdo();
                        while (hijo.getHermanoDerecho() != null) {
                            hijo = hijo.getHermanoDerecho();
                        }
                        hijo.setHermanoDerecho(nuevo);
                    }
                } else {
                    exito = false;
                }
            }
        }
        return exito;
    }

    private NodoGen buscarPadre(NodoGen actual, int posPadre, int[] contador) {
        NodoGen nPadre = null;
        if (actual != null) {
            if (contador[0] == posPadre) {
                nPadre = actual;
            } else {
                NodoGen hijo = actual.getHijoIzquierdo();
                while (hijo != null && nPadre == null) {
                    contador[0]++;
                    nPadre = buscarPadre(hijo, posPadre, contador);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return nPadre;
    }

    public boolean pertenece(Object elem) {
        boolean encontrado = false;
        if (!esVacio()) {
            encontrado = perteneceAux(this.raiz, elem);
        }
        return encontrado;
    }

    private boolean perteneceAux(NodoGen actual, Object elem) {
        boolean encontrado = false;
        if (actual != null) {
            if (actual.getElem().equals(elem)) {
                encontrado = true;
            } else {
                NodoGen hijo = actual.getHijoIzquierdo();
                while (hijo != null && !encontrado) {
                    if (hijo.getElem().equals(elem)) {
                        encontrado = true;
                    } else {
                        encontrado = perteneceAux(hijo, elem);
                        hijo = hijo.getHermanoDerecho();
                    }
                }
            }
        }
        return encontrado;
    }

    public boolean esVacio() {
        return (this.raiz == null);
    }

    public Object padre(Object elem) {
        Object elemPadre = null;
        if (!esVacio()) {
            if (!this.raiz.getElem().equals(elem)) {
                elemPadre = padreAux(this.raiz, elem);
            }
        }
        return elemPadre;
    }

    private Object padreAux(NodoGen actual, Object elem) {
        Object objeto = null;
        boolean encontrado = false;
        if (actual != null && !encontrado) {
            NodoGen hijo = actual.getHijoIzquierdo();
            while (hijo != null && !encontrado) {
                if (hijo.getElem().equals(elem)) {
                    objeto = actual.getElem();
                    encontrado = true;
                } else {
                    Object objetoHijo = padreAux(hijo, elem);
                    if (objetoHijo != null) {
                        objeto = objetoHijo;
                        encontrado = true;
                    }
                }
                hijo = hijo.getHermanoDerecho();
            }
        }
        return objeto;
    }

    public int altura() {
        int altura = -1;
        if (!esVacio()) {
            altura = recorrerAlturaAux(this.raiz);
        }
        return altura;
    }

    private int recorrerAlturaAux(NodoGen actual) {
        int altura = -1, altIzq = -1, altDer = -1;
        if (actual != null) {
            NodoGen hijo = actual.getHijoIzquierdo();
            if (hijo != null) {
                altIzq = recorrerAlturaAux(hijo);
            }
            while (hijo != null) {
                altDer = recorrerAlturaAux(hijo.getHermanoDerecho());
                hijo = hijo.getHermanoDerecho();
            }

            altura = Math.max(altIzq, altDer) + 1;
        }
        return altura;
    }

    public int nivel(Object elem) {
        int nivel = -1;
        if (!esVacio()) {
            nivel += nivelAux(this.raiz, elem);
        }
        return nivel;
    }

    private int nivelAux(NodoGen actual, Object elem) {
        int contador = 0;
        if (actual != null) {
            if (actual.getElem().equals(elem)) {
                contador = 1;
            } else {
                if (actual.getHijoIzquierdo() != null) {
                    NodoGen hijo = actual.getHijoIzquierdo();
                    int aux;
                    while (hijo != null && contador == 0) {
                        aux = nivelAux(hijo, elem);
                        if (aux > 0) {
                            contador = aux + 1;
                        }
                        hijo = hijo.getHermanoDerecho();
                    }
                }
            }
        }
        return contador;
    }

    public Lista ancestros(Object elem) {
        Lista salida = new Lista();
        if (!esVacio()) {
            ancestrosAux(this.raiz, elem, salida);
        }
        return salida;
    }

    private void ancestrosAux(NodoGen actual, Object elem, Lista salida) {
        boolean encontrado = false;
        if (actual != null && !encontrado) {
            salida.insertar(actual.getElem(), salida.longitud() + 1);
            NodoGen hijo = actual.getHijoIzquierdo();
            while (hijo != null && !encontrado) {
                if (hijo.getElem().equals(elem)) {
                    encontrado = true;
                } else {
                    ancestrosAux(hijo, elem, salida);
                    hijo = hijo.getHermanoDerecho();
                }
            }

        }
    }

    @Override
    public ArbolGen clone() throws CloneNotSupportedException {
        ArbolGen copia = new ArbolGen();
        if (!esVacio()) {
            copia.raiz = new NodoGen(this.raiz.getElem(), null, null);
            cloneAux(this.raiz, copia.raiz);
        }
        return copia;
    }

    private void cloneAux(NodoGen actual, NodoGen copia) {
        if (actual != null) {
            NodoGen hijo = actual.getHijoIzquierdo();
            if (hijo != null) {
                NodoGen copiaIzq = new NodoGen(hijo.getElem(), null, null);
                copia.setHijoIzquierdo(copiaIzq);
                NodoGen copiaHijo = copia.getHijoIzquierdo();
                cloneAux(hijo, copia.getHijoIzquierdo());
                while (hijo.getHermanoDerecho() != null) {
                    hijo = hijo.getHermanoDerecho();
                    NodoGen copiaDer = new NodoGen(hijo.getElem(), null, null);
                    copiaHijo.setHermanoDerecho(copiaDer);
                    copiaHijo = copiaHijo.getHermanoDerecho();
                    cloneAux(hijo, copiaHijo);
                }
            }
        }

    }

    public void vaciar() {
        if (!esVacio()) {
            raiz = null;
        }
    }

    public Lista listarPreorden() {
        Lista salida = new Lista();
        if (!esVacio()) {
            listarPreordenAux(this.raiz, salida);
        }
        return salida;
    }

    private void listarPreordenAux(NodoGen actual, Lista lis) {
        if (actual != null) {
            // visita del nodo actual
            lis.insertar(actual.getElem(), lis.longitud() + 1);

            // llamados recursivos con los hijos del nodo actual
            NodoGen hijo = actual.getHijoIzquierdo();
            while (hijo != null) {
                listarPreordenAux(hijo, lis);
                hijo = hijo.getHermanoDerecho();
            }
        }
    }

    public Lista listarInorden() {
        Lista salida = new Lista();
        if (!esVacio()) {
            listarInordenAux(this.raiz, salida);
        }
        return salida;
    }

    public void listarInordenAux(NodoGen actual, Lista lis) {
        if (actual != null) {
            // inserto el hijo izquierdo en la lista
            if (actual.getHijoIzquierdo() != null) {
                listarInordenAux(actual.getHijoIzquierdo(), lis);
            }
            lis.insertar(actual.getElem(), lis.longitud() + 1);
            // al pertenecer a la lista, paso a su hermano derecho

            if (actual.getHijoIzquierdo() != null) {
                NodoGen hijo = actual.getHijoIzquierdo().getHermanoDerecho();
                // inserto el elem actual en la lista
                while (hijo != null) {
                    listarInordenAux(hijo, lis);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }

    }

    public Lista listarPosorden() {
        Lista salida = new Lista();
        if (!esVacio()) {
            listarPosordenAux(this.raiz, salida);
        }
        return salida;
    }

    private void listarPosordenAux(NodoGen actual, Lista lis) {
        if (actual != null) {
            if (actual.getHijoIzquierdo() != null) {
                NodoGen hijo = actual.getHijoIzquierdo();
                while (hijo != null) {
                    listarPosordenAux(hijo, lis);
                    hijo = hijo.getHermanoDerecho();
                }
            }

            lis.insertar(actual.getElem(), lis.longitud() + 1);
        }
    }

    public Lista listarPorNiveles() {
        Lista salida = new Lista();
        Cola Q = new Cola();
        Q.poner(this.raiz);
        while (!Q.esVacia()) {
            NodoGen nodo = (NodoGen) Q.obtenerFrente();
            Q.sacar();
            salida.insertar(nodo.getElem(), salida.longitud() + 1);
            nodo = nodo.getHijoIzquierdo();
            while (nodo != null) {
                Q.poner(nodo);
                nodo = nodo.getHermanoDerecho();
            }
        }
        return salida;
    }

    @Override
    public String toString() {
        return toStringAux(this.raiz);
    }

    private String toStringAux(NodoGen actual) {
        String s = "";
        if (actual != null) {
            s += actual.getElem().toString() + " -> ";
            NodoGen hijo = actual.getHijoIzquierdo();
            while (hijo != null) {
                s += hijo.getElem().toString() + ", ";
                hijo = hijo.getHermanoDerecho();
            }
            hijo = actual.getHijoIzquierdo();
            while (hijo != null) {
                s += "\n" + toStringAux(hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
        return s;
    }

    public Lista listaQueJustificaAltura() {
        return null;

    }

    public boolean verificarCamino(Lista recorrido) {
        boolean exito = false;
        if (!esVacio() && !recorrido.esVacia()) {
            exito = caminoAux(this.raiz, recorrido);
        }
        return exito;
    }

    private boolean caminoAux(NodoGen actual, Lista recorrido) {
        boolean resultado = true;
        if (actual != null) {
            if (!recorrido.esVacia()) {
                Object elem = recorrido.recuperar(1);
                if (actual.getElem().equals(elem)) {
                    recorrido.eliminar(1);
                    resultado = caminoAux(actual.getHijoIzquierdo(), recorrido);
                } else {
                    resultado = caminoAux(actual.getHermanoDerecho(), recorrido);
                }
            } else {
                resultado = false;
            }
        } else {
            if (!recorrido.esVacia()) {
                resultado = false;
            }
        }
        return resultado;
    }

    public Lista listarEntreNiveles(int niv1, int niv2) {
        Lista lis = new Lista();
        if (!esVacio()) {
            listarAux(this.raiz, lis, niv1, niv2, 0);
        }
        return lis;
    }

    private void listarAux(NodoGen actual, Lista lis, int niv1, int niv2, int pos) {
        if (actual != null) {
            if (pos >= niv1 && pos <= niv2) {
                lis.insertar(actual.getElem(), lis.longitud() + 1);
            }
            if (pos < niv2) {
                NodoGen hijo = actual.getHijoIzquierdo();
                while (hijo != null) {
                    listarAux(hijo, lis, niv1, niv2, pos + 1);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
    }

    public boolean eliminar(Object unElem) {
        boolean exito = false;
        if (!esVacio()) {
            if (this.raiz.equals(unElem)) {
                exito = true;
                vaciar();
            } else {
                exito = eliminarAux(this.raiz, null, unElem);
            }
        }
        return exito;
    }

    private boolean eliminarAux(NodoGen n, NodoGen padre, Object elem) {
        boolean exit = false;
        if (n != null) {
            if (n.getElem().equals(elem)) {
                exit = true;
                eliminarNodo(n, padre);
            } else {
                NodoGen hijo = n.getHijoIzquierdo();
                while (hijo != null && !exit) {
                    exit = eliminarAux(hijo, n, elem);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return exit;
    }

    private void eliminarNodo(NodoGen hijo, NodoGen padre) {
        // hijo es el nodo a eliminar
        Object elemHijo = padre.getHijoIzquierdo().getElem();
        Object eliminar = hijo.getElem();
        // si elemento a eliminar es el HEI
        if (eliminar.equals(elemHijo)) {
            padre.setHijoIzquierdo(hijo.getHermanoDerecho());
        } else {
            NodoGen aux = padre.getHijoIzquierdo();
            System.out.println(aux.getElem());
            while (!aux.getHermanoDerecho().getElem().equals(eliminar)) {
                aux = aux.getHermanoDerecho();
            }
            aux.setHermanoDerecho(hijo.getHermanoDerecho());
        }
    }

    public int grado(Object unElem) {
        int[] contador = new int[1];
        contador[0] = -1;
        if (!esVacio()) {
            gradoAux(this.raiz, unElem, contador);
        }
        return contador[0];
    }

    private void gradoAux(NodoGen actual, Object elem, int[] contador) {
        if (actual != null) {
            NodoGen hijo = actual.getHijoIzquierdo();
            if (actual.getElem().equals(elem)) {
                contarHijos(hijo, contador);
            } else {
                while (hijo != null) {
                    gradoAux(hijo, elem, contador);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }

    }

    private void contarHijos(NodoGen hijo, int[] contador) {
        contador[0] = 0;
        while (hijo != null) {
            contador[0] += 1;
            hijo = hijo.getHermanoDerecho();
        }
    }

    public int gradoMaximo() {
        Lista contador = new Lista();
        int valor = 0;
        if (!esVacio()) {
            valor = valorGrado(this.raiz, contador);
        }
        return valor;
    }

    private int valorGrado(NodoGen actual, Lista contador) {
        int aux = 0;
        int mayor;
        contarGrado(actual, contador, 0);
        while (!contador.esVacia()) {
            mayor = (int) contador.recuperar(1);
            if (mayor > aux) {
                aux = mayor;
            }
            contador.eliminar(1);
        }
        return aux;
    }

    private void contarGrado(NodoGen actual, Lista contador, int pos) {
        int valor = 0;
        if (actual != null) {
            NodoGen hijo = actual.getHijoIzquierdo();
            while (hijo != null) {
                contarGrado(hijo, contador, pos + 1);
                valor++;
                hijo = hijo.getHermanoDerecho();
            }
            contador.insertar(valor, pos);
        }
    }

    public Lista caminoAHojaMasCercana() {
        Lista camino = new Lista();
        boolean[] encontrado = new boolean[1];
        encontrado[0] = false;
        if (raiz != null) {
            hojaCercanaAux(this.raiz, camino, encontrado);

        }
        return camino;
    }

    private void hojaCercanaAux(NodoGen actual, Lista camino, boolean[] encontrado) {
        NodoGen hijo;
        if (actual != null) {
            camino.insertar(actual.getElem(), camino.longitud() + 1);
            if (actual.getHijoIzquierdo() == null) {
                encontrado[0] = true;
            } else {
                hijo = actual.getHijoIzquierdo();
                while (hijo != null && !encontrado[0]) {
                    if (hijo.getHijoIzquierdo() == null) {
                        encontrado[0] = true;
                        camino.insertar(hijo.getElem(), camino.longitud() + 1);
                    }
                    hijo = hijo.getHermanoDerecho();
                }
                if (!encontrado[0]) {
                    hojaCercanaAux(actual.getHijoIzquierdo(), camino, encontrado);
                }
            }
        }
    }

}
