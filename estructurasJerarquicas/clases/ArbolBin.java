/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author Mati
 */
public class ArbolBin {

    private NodoArbol raiz;

    // constructores
    public ArbolBin() {
        raiz = null;
    }

    // metodos
    public boolean insertar(Object elemNuevo, Object elemPadre, char lugar) {
        /*
         * inserta elemNuevo como hijo del primer nodo encontrado en preorden igual
         * a elemPadre, como hijo izq o der, segun lo indique el parametro lugar
         */
        boolean exito = true;
        if (this.raiz == null) {
            // si el arbol esta vacio, pone elem nuevo en la raiz
            this.raiz = new NodoArbol(elemNuevo, null, null);
        } else {
            // si arbol no esta vacio, busca al padre
            NodoArbol nPadre = obtenerNodo(this.raiz, elemPadre);
            if (nPadre != null) {
                if (lugar == 'I' && nPadre.getIzquierdo() == null) {
                    nPadre.setIzquierdo(new NodoArbol(elemNuevo, null, null));
                } else if (lugar == 'D' && nPadre.getDerecho() == null) {
                    nPadre.setDerecho(new NodoArbol(elemNuevo, null, null));
                } else {
                    exito = false;
                }
            } else {
                exito = false;
            }
        }
        return exito;
    }

    private NodoArbol obtenerNodo(NodoArbol n, Object buscado) {
        // metodo PRIVADO que busca un elemento y devuelve el nodo que
        // lo contiene. si no se encuentra buscado devuelve null
        NodoArbol resultado = null;
        if (n != null) {
            if (n.getElem().equals(buscado)) {
                // si el buscado es n, lo devuelve
                resultado = n;
            } else {
                // no es el buscado: busca primero en el HI
                resultado = obtenerNodo(n.getIzquierdo(), buscado);
                // si no lo encuentro en el HI, busca en HD
                if (resultado == null) {
                    resultado = obtenerNodo(n.getDerecho(), buscado);
                }
            }
        }
        return resultado;
    }

    public boolean insertarPorPosicion(Object unElem, int posPadre, int posHijo) {
        return false;
    }

    public boolean esVacio() {
        return (this.raiz == null);
    }

    public Object padre(Object unElem) {
        Object elemPadre = this.raiz.getElem();
        if (!esVacio()) {
            if (this.raiz.getElem().equals(unElem)) {
                elemPadre = padreRecursivo(this.raiz);
            }
        }
        return elemPadre;
    }

    private Object padreRecursivo(NodoArbol actual) {

        return null;
    }

    public int altura() {
        int altura = -1;
        if (this.raiz != null) {
            altura = recorridoAltura(this.raiz);
        }
        return altura;
    }

    private int recorridoAltura(NodoArbol actual) {
        int alturaIzq, alturaDer, altura = -1;
        if (actual != null) {
            alturaIzq = recorridoAltura(actual.getIzquierdo());
            alturaDer = recorridoAltura(actual.getDerecho());
            altura = Math.max(alturaIzq, alturaDer) + 1;
        }
        return altura;
    }

    public int nivel(Object unElem) {
        int nivel = -1;
        if (this.raiz != null) {
            nivel += recorrerNivel(this.raiz, unElem);
        }
        return nivel;
    }

    private int recorrerNivel(NodoArbol actual, Object unElem) {
        boolean encontrado = false;
        int nivel = 0;
        if (actual != null && encontrado) {
            if (actual.getElem().equals(unElem)) {
                nivel = 1;
                encontrado = true;
            } else {
                if (actual.getIzquierdo() != null) {
                    nivel += recorrerNivel(actual.getIzquierdo(), unElem);
                }
                if (actual.getDerecho() != null) {
                    nivel += recorrerNivel(actual.getDerecho(), unElem);
                }
            }
        }
        return nivel;
    }

    public void vaciar() {
        if (this.raiz != null) {
            this.raiz = null;
        }
    }

    @Override
    public ArbolBin clone() throws CloneNotSupportedException {
        ArbolBin copia = new ArbolBin();
        if (this.raiz != null) {
            copia.raiz = new NodoArbol(this.raiz.getElem(), null, null);
            recorrerClone(this.raiz, copia.raiz);
        }
        return copia;
    }

    private void recorrerClone(NodoArbol original, NodoArbol copia) {
        if (original.getIzquierdo() != null) {
            NodoArbol copiaIzq = new NodoArbol(original.getIzquierdo().getElem(), null, null);
            copia.setIzquierdo(copiaIzq);
            recorrerClone(original.getIzquierdo(), copia.getIzquierdo());
        }
        if (original.getDerecho() != null) {
            NodoArbol copiaDer = new NodoArbol(original.getDerecho().getElem(), null, null);
            copia.setDerecho(copiaDer);
            recorrerClone(original.getDerecho(), copia.getDerecho());
        }
    }

    @Override
    public String toString() {
        String cadena = "arbol vacio";
        if (!esVacio()) {
            cadena = recorridoString(this.raiz);
        }
        return cadena;
    }

    private String recorridoString(NodoArbol n) {
        String cad = "";
        if (n != null) {
            cad += "(" + n.getElem() + ") -> ";
            if (n.getIzquierdo() != null) {
                cad += "HI: " + n.getIzquierdo().getElem() + "  ";
            } else {
                cad += "HI: -  ";
            }
            if (n.getDerecho() != null) {
                cad += "HD: " + n.getDerecho().getElem() + "\n";
            } else {
                cad += "HD: - \n";
            }
            cad += recorridoString(n.getIzquierdo());
            cad += recorridoString(n.getDerecho());
        }
        return cad;

    }

    public Lista listarPreorden() {
        // retorna una lista con los elementos del arbol en PREORDEN
        Lista lis = new Lista();
        listarPreordenAux(this.raiz, lis);
        return lis;
    }

    private void listarPreordenAux(NodoArbol nodo, Lista lis) {
        // metodo recursivo PRIVADO porque su parametro es de tipo NodoArbol

        if (nodo != null) {
            // visita al elemento en el nodo
            lis.insertar(nodo.getElem(), lis.longitud() + 1);

            // recorre a sus hijos en preorden
            listarPreordenAux(nodo.getIzquierdo(), lis);
            listarPreordenAux(nodo.getDerecho(), lis);
        }
    }

    public Lista listarPosorden() {
        Lista lis = new Lista();
        listarPosordenAux(this.raiz, lis);
        return lis;
    }

    private void listarPosordenAux(NodoArbol nodo, Lista lis) {
        if (nodo != null) {
            listarPosordenAux(nodo.getIzquierdo(), lis);
            listarPosordenAux(nodo.getDerecho(), lis);
            lis.insertar(nodo.getElem(), lis.longitud() + 1);
        }
    }

    public Lista listarInorden() {
        Lista lis = new Lista();
        listarInordenAux(this.raiz, lis);
        return lis;
    }

    private void listarInordenAux(NodoArbol nodo, Lista lis) {
        if (nodo != null) {
            listarInordenAux(nodo.getIzquierdo(), lis);
            lis.insertar(nodo.getElem(), lis.longitud() + 1);
            listarInordenAux(nodo.getDerecho(), lis);
        }
    }

    public Lista porNivel() {
        // usamos una cola para poder recorrer los niveles
        Cola cola = new Cola();
        Lista nivel = new Lista();
        // ponemos el nodo raiz en la cola
        cola.poner(this.raiz);
        // recorremos mientras la cola no este vacia
        while (!cola.esVacia()) {
            // obtenemos el tope de la cola
            NodoArbol aux = (NodoArbol) cola.obtenerFrente();
            nivel.insertar(aux.getElem(), nivel.longitud() + 1);
            cola.sacar();

            // si el hijo izquierdo no es vacio lo ponemos en la cola
            if (aux.getIzquierdo() != null) {
                cola.poner(aux.getIzquierdo());
            }
            // si el hijo derecho no es vacio lo ponemos en la cola
            if (aux.getDerecho() != null) {
                cola.poner(aux.getDerecho());
            }
        }
        return nivel;
    }

    public ArbolBin cloneInvertido() throws CloneNotSupportedException {
        ArbolBin copia = new ArbolBin();
        if (this.raiz != null) {
            copia.raiz = new NodoArbol(this.raiz.getElem(), null, null);
            recorrerCloneInvertido(this.raiz, copia.raiz);
        }
        return copia;
    }

    private void recorrerCloneInvertido(NodoArbol original, NodoArbol copia) {
        if (original.getIzquierdo() != null) {
            NodoArbol copiaIzq = new NodoArbol(original.getIzquierdo().getElem(), null, null);
            copia.setDerecho(copiaIzq);
            recorrerClone(original.getIzquierdo(), copia.getDerecho());
        }
        if (original.getDerecho() != null) {
            NodoArbol copiaDer = new NodoArbol(original.getDerecho().getElem(), null, null);
            copia.setIzquierdo(copiaDer);
            recorrerClone(original.getDerecho(), copia.getIzquierdo());
        }
    }

    public boolean verificarPatron(Lista lisPatron) {
        // la lista debe tener recorrido en preorden para funcionar
        boolean encontrado = false;
        if (lisPatron.esVacia()) {
            if (this.raiz == null) {
                encontrado = true;
            }
        } else {
            if (this.raiz != null) {
                encontrado = recorridoPatron(this.raiz, lisPatron);
            }
        }

        return encontrado;
    }

    private boolean recorridoPatron(NodoArbol actual, Lista lis) {
        boolean verificado = false;
        Object elemActual;
        if (actual != null && !lis.esVacia()) {
            elemActual = lis.recuperar(1);
            if (actual.getElem().equals(elemActual)) {
                boolean verificarIzq = false;
                lis.eliminar(1);
                verificado = true;
                if (actual.getIzquierdo() != null) {
                    verificado = recorridoPatron(actual.getIzquierdo(), lis);
                    verificado = verificarIzq;
                }
                if (actual.getDerecho() != null && !verificarIzq) {
                    verificado = recorridoPatron(actual.getDerecho(), lis);
                }
            }
        }
        return verificado;
    }

    public Lista listaQueJustificaLaAltura() {
        Lista lis = null;
        if (this.raiz != null) {
            lis = new Lista();
            recorrerListaAltura(this.raiz, lis);
        }
        return lis;
    }

    private void recorrerListaAltura(NodoArbol actual, Lista lis) {
        int alturaIzq = 0, alturaDer = 0;
        if (actual != null) {
            lis.insertar(actual.getElem(), lis.longitud() + 1);
            alturaIzq = altura(actual.getIzquierdo());
            alturaDer = altura(actual.getDerecho());
            if (alturaIzq > alturaDer) {
                recorrerListaAltura(actual.getIzquierdo(), lis);
            } else {
                recorrerListaAltura(actual.getDerecho(), lis);
            }
        }
    }

    private int altura(NodoArbol actual) {
        int alturaIzq, alturaDer, altura = -1;
        if (actual != null) {
            alturaIzq = recorridoAltura(actual.getIzquierdo());
            alturaDer = recorridoAltura(actual.getDerecho());
            altura = Math.max(alturaIzq, alturaDer) + 1;
        }
        return altura;
    }

    public Lista frontera() {
        Lista lis = new Lista();
        if (this.raiz != null) {
            fronteraAux(this.raiz, lis);
        }
        return lis;
    }

    private void fronteraAux(NodoArbol actual, Lista lis) {
        if (actual != null) {
            if (actual.getIzquierdo() == null && actual.getDerecho() == null) {
                lis.insertar(actual.getElem(), 1);
            } else {
                if (actual.getIzquierdo() != null) {
                    fronteraAux(actual.getIzquierdo(), lis);
                }
                if (actual.getDerecho() != null) {
                    fronteraAux(actual.getDerecho(), lis);
                }
            }
        }
    }

    public boolean estaRepetido(Object elem) {
        boolean repetido = false;
        int[] contador = new int[1];
        contador[0] = 0;
        if (this.raiz != null) {
            repetido = repetidoAux(this.raiz, contador, elem);
        }
        return repetido;
    }

    private boolean repetidoAux(NodoArbol actual, int[] contador, Object elem) {
        boolean repetido = false;
        if (actual != null) {
            if (contador[0] < 2) {
                if (actual.getElem().equals(elem)) {
                    contador[0] += 1;
                }
                if (actual.getIzquierdo() != null) {
                    repetido = repetidoAux(actual.getIzquierdo(), contador, elem);
                }
                if (actual.getDerecho() != null) {
                    repetido = repetidoAux(actual.getDerecho(), contador, elem);
                }
            } else {
                repetido = true;
            }
        }
        return repetido;
    }

    public boolean equals(ArbolBin otro) {
        boolean exito = false;
        if (!esVacio()) {
            exito = equalsAux(this.raiz, otro.raiz);
        }
        return exito;
    }

    private boolean equalsAux(NodoArbol actual, NodoArbol otro) {
        boolean exito = true;
        if (actual != null && otro != null) {
            System.out.println(actual.getElem() + " ; " + otro.getElem());
            if (actual.getElem().equals(otro.getElem())) {
                exito = equalsAux(actual.getIzquierdo(), otro.getIzquierdo());
                if (exito) {
                    exito = equalsAux(actual.getDerecho(), otro.getDerecho());
                }
            } else {
                exito = false;
            }
        } else {
            if (actual == null) {
                if (otro != null) {
                    exito = false;
                }
            } else {
                if (otro == null) {
                    exito = false;
                }
            }
        }
        return exito;
    }

    public Lista ancestros(Object elem) {
        Lista ancestros = new Lista();
        if (!esVacio()) {
            ancestrosAux(this.raiz, ancestros, elem);
        }
        return ancestros;
    }

    private void ancestrosAux(NodoArbol actual, Lista lis, Object elem) {
        boolean encontrado = false;
        if (actual != null && !encontrado) {
            lis.insertar(actual.getElem(), lis.longitud() + 1);
            if (actual.getElem().equals(elem)) {
                encontrado = true;
            } else {
                ancestrosAux(actual.getIzquierdo(), lis, elem);
                ancestrosAux(actual.getDerecho(), lis, elem);
            }
        }
    }

    public Lista descendientes(Object elem) {
        Lista descendientes = new Lista();
        if (!esVacio()) {
            descendientesAux(this.raiz, descendientes, elem, false);
        }
        return descendientes;
    }

    private void descendientesAux(NodoArbol actual, Lista lis, Object elem, boolean encontrado) {
        if (actual != null) {
            if (actual.getElem().equals(elem) || !encontrado) {
                encontrado = true;
                if (actual.getIzquierdo() != null) {
                    descendientesAux(actual.getIzquierdo(), lis, elem, encontrado);
                }
                lis.insertar(actual.getElem(), lis.longitud() + 1);
                descendientesAux(actual.getDerecho(), lis, elem, encontrado);
            } else {
                descendientesAux(actual.getIzquierdo(), lis, elem, encontrado);
                descendientesAux(actual.getDerecho(), lis, elem, encontrado);
            }
        }
    }
}
