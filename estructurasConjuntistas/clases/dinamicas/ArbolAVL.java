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
public class ArbolAVL {

    private NodoAVL raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

    @SuppressWarnings("rawtypes")
    public boolean pertenece(Comparable unElem) {
        boolean exito = false;
        if (!esVacio()) {
            exito = perteneceAux(this.raiz, unElem);
        }
        return exito;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public boolean perteneceAux(NodoAVL actual, Comparable unElem) {
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

    @SuppressWarnings("rawtypes")
    public boolean insertar(Comparable unElem) {
        boolean exito = true;
        if (esVacio()) {
            this.raiz = new NodoAVL(unElem);
        } else {
            exito = insertarAux(this.raiz, unElem);
        }
        return exito;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private boolean insertarAux(NodoAVL actual, Comparable unElem) {
        boolean exito = true;
        if (unElem.compareTo(actual.getElem()) == 0) {
            exito = false;
        } else if (unElem.compareTo(actual.getElem()) < 0) {
            if (actual.getIzquierdo() != null) {
                exito = insertarAux(actual.getIzquierdo(), unElem);
            } else {
                actual.setIzquierdo(new NodoAVL(unElem));
            }
        } else {
            if (actual.getDerecho() != null) {
                exito = insertarAux(actual.getDerecho(), unElem);
            } else {
                actual.setDerecho(new NodoAVL(unElem));
            }
        }
        return exito;
    }

    @SuppressWarnings("rawtypes")
    public boolean eliminar(Comparable unElem) {
        boolean exito = false;
        if (!esVacio()) {
            exito = eliminarAux(this.raiz, unElem, null);
        }
        return exito;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public boolean eliminarAux(NodoAVL actual, Comparable unElem, NodoAVL padre) {
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

    @SuppressWarnings("unchecked")
    public void eliminarNodo(NodoAVL actual, NodoAVL padre) {
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
            NodoAVL nodo = null;
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

    @SuppressWarnings("unchecked")
    public void buscarMayor(NodoAVL aBorrar, NodoAVL actual, NodoAVL padre) {
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

    private void listarAux(NodoAVL actual, Lista lis) {
        if (actual != null) {
            listarAux(actual.getIzquierdo(), lis);
            lis.insertar(actual.getElem(), lis.longitud() + 1);
            listarAux(actual.getDerecho(), lis);
        }
    }

    @SuppressWarnings("rawtypes")
    public Lista listarRango(Comparable elemMinimo, Comparable elemMaximo) {
        Lista salida = new Lista();
        if (!esVacio()) {
            listarRangoAux(this.raiz, salida, elemMinimo, elemMaximo);
        }
        return salida;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void listarRangoAux(NodoAVL actual, Lista lis, Comparable min, Comparable max) {
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

    @SuppressWarnings("rawtypes")
    public Comparable minimoElem() {
        Comparable minimo = null;
        if (!esVacio()) {
            minimoAux(this.raiz, minimo);
        }
        return minimo;
    }

    @SuppressWarnings("rawtypes")
    private void minimoAux(NodoAVL actual, Comparable min) {
        if (actual.getIzquierdo() != null) {
            minimoAux(actual.getIzquierdo(), min);
        } else {
            min = actual.getElem();
        }
    }

    @SuppressWarnings("rawtypes")
    public Comparable maximoElem() {
        Comparable maximo = null;
        if (!esVacio()) {
            maximoAux(this.raiz, maximo);
        }
        return maximo;
    }

    @SuppressWarnings("rawtypes")
    private void maximoAux(NodoAVL actual, Comparable max) {
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

    private String recorridoString(NodoAVL n) {
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
}
