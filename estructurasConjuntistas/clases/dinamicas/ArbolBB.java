/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases.dinamicas;

import clases.Lista;

/**
 *
 * @author Mati
 */
public class ArbolBB {

    private NodoABB raiz;

    public ArbolBB() {
        this.raiz = null;
    }

    @SuppressWarnings({ "rawtypes" })
    public boolean pertenece(Comparable unElem) {
        boolean exito = false;
        if (!esVacio()) {
            exito = perteneceAux(this.raiz, unElem);
        }
        return exito;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public boolean perteneceAux(NodoABB actual, Comparable unElem) {
        boolean exito = false;
        if (unElem.compareTo(actual.getElem()) == 0) {
            exito = true;
        } else if (unElem.compareTo(actual.getElem()) < 0) {
            exito = perteneceAux(actual.getIzquierdo(), unElem);
        } else {
            exito = perteneceAux(actual.getDerecho(), unElem);
        }
        return exito;
    }

    @SuppressWarnings({ "rawtypes" })
    public boolean insertar(Comparable unElem) {
        boolean exito = true;
        if (esVacio()) {
            this.raiz = new NodoABB(unElem);
        } else {
            exito = insertarAux(this.raiz, unElem);
        }
        return exito;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private boolean insertarAux(NodoABB actual, Comparable unElem) {
        boolean exito = true;
        if (unElem.compareTo(actual.getElem()) == 0) {
            exito = false;
        } else if (unElem.compareTo(actual.getElem()) < 0) {
            if (actual.getIzquierdo() != null) {
                exito = insertarAux(actual.getIzquierdo(), unElem);
            } else {
                actual.setIzquierdo(new NodoABB(unElem));
            }
        } else {
            if (actual.getDerecho() != null) {
                exito = insertarAux(actual.getDerecho(), unElem);
            } else {
                actual.setDerecho(new NodoABB(unElem));
            }
        }
        return exito;
    }

    @SuppressWarnings({ "rawtypes" })
    public boolean eliminar(Comparable unElem) {
        boolean exito = false;
        if (!esVacio()) {
            exito = eliminarAux(this.raiz, unElem, null);
        }
        return exito;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public boolean eliminarAux(NodoABB actual, Comparable unElem, NodoABB padre) {
        boolean exito = false;
        if (unElem.compareTo(actual.getElem()) == 0) {
            exito = true;
            eliminarNodo(actual, padre);
        } else if (unElem.compareTo(actual.getElem()) < 0) {
            exito = eliminarAux(actual.getIzquierdo(), unElem, actual);
        } else {
            exito = eliminarAux(actual.getDerecho(), unElem, actual);
        }
        return exito;
    }

    @SuppressWarnings({ "unchecked" })
    public void eliminarNodo(NodoABB actual, NodoABB padre) {
        int aux = padre.getElem().compareTo(actual.getElem());

        if (actual.getIzquierdo() == null && actual.getDerecho() == null) {
            // Caso 1: El nodo a eliminar es una hoja
            if (aux > 0) {
                padre.setIzquierdo(null);
            } else {
                padre.setDerecho(null);
            }
        } else if (actual.getIzquierdo() == null || actual.getDerecho() == null) {
            // Caso 2: El nodo tiene un solo hijo
            NodoABB nodo = null;
            if (actual.getIzquierdo() != null) {
                nodo = actual.getIzquierdo();
            }
            if (actual.getDerecho() != null) {
                nodo = actual.getDerecho();
            }
            if (aux > 0) {
                padre.setIzquierdo(nodo);
            } else {
                padre.setDerecho(nodo);
            }
        } else {
            // Caso 3: El nodo tiene ambos hijos
            buscarMayor(actual, actual.getIzquierdo(), null);
        }
    }

    @SuppressWarnings({ "unchecked" })
    public void buscarMayor(NodoABB aBorrar, NodoABB actual, NodoABB padre) {
        if (actual != null) {
            if (actual.getDerecho() == null) {
                if (aBorrar.getElem().compareTo(padre.getElem()) == 0) {
                    aBorrar.setElem(actual.getElem());
                    padre.setDerecho(actual.getDerecho());
                } else {
                    actual.getElem();
                    padre.setIzquierdo(actual.getDerecho());
                }
            } else {
                buscarMayor(aBorrar, actual.getDerecho(), actual);
            }
        }
    }

    public Lista listar() {
        Lista salida = new Lista();
        if (!esVacio()) {
            listarAux(this.raiz, salida);
        }
        return salida;
    }

    private void listarAux(NodoABB actual, Lista lis) {
        if (actual != null) {
            listarAux(actual.getIzquierdo(), lis);
            lis.insertar(actual.getElem(), lis.longitud() + 1);
            listarAux(actual.getDerecho(), lis);
        }
    }

    @SuppressWarnings({ "rawtypes" })
    public Lista listarRango(Comparable elemMinimo, Comparable elemMaximo) {
        Lista salida = new Lista();
        if (!esVacio()) {
            listarRangoAux(this.raiz, salida, elemMinimo, elemMaximo);
        }
        return salida;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void listarRangoAux(NodoABB actual, Lista lis, Comparable min, Comparable max) {
        if (actual != null) {
            if (min.compareTo(actual.getElem()) < 0) {
                listarRangoAux(actual.getIzquierdo(), lis, min, max);
            }
            if (min.compareTo(actual.getElem()) < 0 && max.compareTo(actual.getElem()) > 0) {
                lis.insertar(actual.getElem(), lis.longitud() + 1);
            }
            if (max.compareTo(actual.getElem()) > 0) {
                listarRangoAux(actual.getDerecho(), lis, min, max);
            }
        }
    }

    @SuppressWarnings({ "rawtypes" })
    public Comparable minimoElem() {
        Comparable minimo = null;
        if (!esVacio()) {
            minimoAux(this.raiz, minimo);
        }
        return minimo;
    }

    @SuppressWarnings({ "rawtypes" })
    private void minimoAux(NodoABB actual, Comparable min) {
        if (actual.getIzquierdo() != null) {
            minimoAux(actual.getIzquierdo(), min);
        } else {
            min = actual.getElem();
        }
    }

    @SuppressWarnings({ "rawtypes" })
    public Comparable maximoElem() {
        Comparable maximo = null;
        if (!esVacio()) {
            maximoAux(this.raiz, maximo);
        }
        return maximo;
    }

    @SuppressWarnings({ "rawtypes" })
    private void maximoAux(NodoABB actual, Comparable max) {
        if (actual.getDerecho() != null) {
            maximoAux(actual.getDerecho(), max);
        } else {
            max = actual.getElem();
        }
    }

    public boolean esVacio() {
        return (this.raiz == null);
    }

    public String toString() {
        String cadena = "arbol vacio";
        if (!esVacio()) {
            cadena = recorridoString(this.raiz);
        }
        return cadena;
    }

    private String recorridoString(NodoABB n) {
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

    public boolean eliminarMinimo() {
        boolean exito = true;
        if (!esVacio()) {
            eliminarMinAux(this.raiz, null);
        } else {
            exito = false;
        }
        return exito;
    }

    @SuppressWarnings({ "unchecked" })
    private void eliminarMinAux(NodoABB actual, NodoABB padre) {
        if (actual != null) {
            if (actual.getIzquierdo() != null && actual.getIzquierdo().getElem().compareTo(actual.getElem()) < 0) {
                eliminarMinAux(actual.getIzquierdo(), actual);
            } else {
                if (actual.getDerecho() != null && actual.getDerecho().getElem().compareTo(actual.getElem()) < 0) {
                    eliminarMinAux(actual.getDerecho(), actual);
                } else {
                    int aux = padre.getElem().compareTo(actual.getElem());
                    if (aux > 0) {
                        padre.setIzquierdo(null);
                    } else {
                        padre.setDerecho(null);
                    }
                }
            }
        }
    }

    @SuppressWarnings({ "rawtypes" })
    public ArbolBB clonarParteInvertida(Comparable unElem) {
        ArbolBB clon = new ArbolBB();
        if (!esVacio()) {
            clonarAux(this.raiz, clon, unElem);
        }
        return clon;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void clonarAux(NodoABB actual, ArbolBB clon, Comparable unElem) {
        if (actual != null) {
            if (unElem.compareTo(actual.getElem()) == 0) {
                clon.raiz = new NodoABB(unElem, null, null);
                copiarSubarbol(actual, clon.raiz);
            } else if (unElem.compareTo(actual.getElem()) < 0) {
                clonarAux(actual.getIzquierdo(), clon, unElem);
            } else {
                clonarAux(actual.getDerecho(), clon, unElem);
            }
        }
    }

    private void copiarSubarbol(NodoABB actual, NodoABB clon) {
        if (actual.getIzquierdo() != null) {
            NodoABB copiaDer = new NodoABB(actual.getIzquierdo().getElem(), null, null);
            clon.setDerecho(copiaDer);
            copiarSubarbol(actual.getIzquierdo(), clon.getDerecho());
        }
        if (actual.getDerecho() != null) {
            NodoABB copiaIzq = new NodoABB(actual.getDerecho().getElem(), null, null);
            clon.setIzquierdo(copiaIzq);
            copiarSubarbol(actual.getDerecho(), clon.getIzquierdo());
        }
    }

    @SuppressWarnings({ "rawtypes" })
    public Lista listarMayorIgual(Comparable elem) {
        Lista salida = new Lista();
        if (!esVacio()) {
            listarMayoresAux(this.raiz, salida, elem);
        }
        return salida;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void listarMayoresAux(NodoABB actual, Lista lis, Comparable unElem) {
        if (actual != null) {
            if (unElem.compareTo(actual.getElem()) < 0) {
                listarMayoresAux(actual.getIzquierdo(), lis, unElem);
            }
            if (unElem.compareTo(actual.getElem()) <= 0) {
                lis.insertar(actual.getElem(), 1);
            }
            listarMayoresAux(actual.getDerecho(), lis, unElem);
        }
    }

    @SuppressWarnings({ "rawtypes" })
    public Lista listarMenores(Comparable elem) {
        Lista salida = new Lista();
        if (!esVacio()) {
            listarMenoresAux(this.raiz, salida, elem);
        }
        return salida;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void listarMenoresAux(NodoABB actual, Lista lis, Comparable unElem) {
        if (actual != null) {
            listarMenoresAux(actual.getIzquierdo(), lis, unElem);
            if (unElem.compareTo(actual.getElem()) > 0) {
                lis.insertar(actual.getElem(), lis.longitud() + 1);
            }
            if (unElem.compareTo(actual.getElem()) < 0) {
                listarMenoresAux(actual.getDerecho(), lis, unElem);
            }
        }
    }

    @SuppressWarnings({ "rawtypes" })
    public boolean eliminarMinSubarbol(Comparable unElem) {
        boolean exito = false;
        if (!esVacio()) {
            exito = subarbolAux(this.raiz, unElem, this.raiz);
        }
        return exito;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private boolean subarbolAux(NodoABB actual, Comparable unElem, NodoABB padre) {
        boolean encontrado = false;
        if (actual != null) {
            if (unElem.compareTo(actual.getElem()) == 0) {
                if (actual.getIzquierdo() == null) {
                    // si el subarbol no tiene hijo izq y tiene derecho, los debe cambiar
                    if (actual.getDerecho() != null) {
                        actual.setElem(actual.getDerecho().getElem());
                        actual.setDerecho(null);
                    } else {
                        // si el subarbol no tiene hijos, el es su elem minimo, lo elimino
                        padre.setIzquierdo(null);
                    }
                } else {
                    // si el subarbol tiene hijo izquierdo, busca el minimo y lo elimina
                    buscarMinimo(actual, actual);
                }
            } else if (unElem.compareTo(actual.getElem()) < 0) {
                encontrado = subarbolAux(actual.getIzquierdo(), unElem, actual);
            } else {
                encontrado = subarbolAux(actual.getDerecho(), unElem, actual);
            }
        }
        return encontrado;
    }

    private void buscarMinimo(NodoABB actual, NodoABB padre) {
        if (actual != null) {
            if (actual.getIzquierdo() != null) {
                buscarMinimo(actual.getIzquierdo(), actual);
            } else {
                padre.setIzquierdo(null);
            }
        }
    }
}
