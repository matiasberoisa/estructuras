/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases.dinamicas;

import clases.Lista;

public class ArbolAVL {

    private NodoAVL raiz;

    public ArbolAVL() {
        raiz = null;
    }

    @SuppressWarnings("rawtypes")
    public boolean insertar(Comparable elem) {
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoAVL(elem, null, null);
        } else {
            exito = insertarAux(this.raiz, elem);
            if (exito) {
                this.raiz = analizarBalance(this.raiz);
                // this.raiz.recalcularAltura();
            }
        }
        return exito;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private boolean insertarAux(NodoAVL n, Comparable elem) {
        boolean exito;
        if ((elem.compareTo(n.getElem()) == 0)) {
            exito = false;
        } else {
            if (elem.compareTo(n.getElem()) < 0) {
                if (n.getIzquierdo() != null) {
                    exito = insertarAux(n.getIzquierdo(), elem);
                    if (exito) {
                        n.setIzquierdo(analizarBalance(n.getIzquierdo()));
                    }
                } else {
                    n.setIzquierdo(new NodoAVL(elem, null, null));
                    n.recalcularAltura();
                    exito = true;
                }
            } else {
                if (n.getDerecho() != null) {
                    exito = insertarAux(n.getDerecho(), elem);
                    if (exito) {
                        n.setDerecho(analizarBalance(n.getDerecho()));
                    }
                } else {
                    n.setDerecho(new NodoAVL(elem, null, null));
                    n.recalcularAltura();
                    exito = true;
                }
            }
        }
        return exito;
    }

    private NodoAVL analizarBalance(NodoAVL n) {
        n.recalcularAltura();
        NodoAVL nuevaRaiz = n;
        if (obtenerBalance(n) == 2) {
            if (obtenerBalance(n.getIzquierdo()) >= 0) {
                nuevaRaiz = rotacionSimpleDerecha(n);
            } else {
                n.setIzquierdo(rotacionSimpleIzquierda(n.getIzquierdo()));
                nuevaRaiz = rotacionSimpleDerecha(n);
            }
        }
        if (obtenerBalance(n) == -2) {
            if (obtenerBalance(n.getDerecho()) <= 0) {
                nuevaRaiz = rotacionSimpleIzquierda(n);
            } else {
                n.setDerecho(rotacionSimpleDerecha(n.getDerecho()));
                nuevaRaiz = rotacionSimpleIzquierda(n);
            }
        }
        n.recalcularAltura();
        return nuevaRaiz;
    }

    private int obtenerBalance(NodoAVL n) {
        int balance;
        if (n.getIzquierdo() == null) {
            balance = -1;
        } else {
            balance = n.getIzquierdo().getAltura();
        }
        if (n.getDerecho() == null) {
            balance = balance - (-1);
        } else {
            balance = balance - (n.getDerecho().getAltura());
        }
        return balance;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public boolean eliminar(Comparable elemento) {
        boolean exito = false;
        if (this.raiz != null) {
            if (this.raiz.getElem().compareTo(elemento) == 0) {
                exito = true;
                if (this.raiz.getIzquierdo() == null && this.raiz.getDerecho() == null) {
                    this.raiz = null;
                } else {
                    borrarNodo(this.raiz);
                }
            } else {
                exito = eliminarAux(this.raiz, elemento);
                this.raiz = analizarBalance(this.raiz);
                // this.raiz.recalcularAltura();
            }
        }
        return exito;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private boolean eliminarAux(NodoAVL n, Comparable elemento) {
        boolean exito = false;
        if (n != null) {
            if (elemento.compareTo(n.getElem()) < 0) {
                if (n.getIzquierdo() != null) {
                    if (n.getIzquierdo().getElem().compareTo(elemento) == 0) {
                        exito = true;
                        NodoAVL hijo = n.getIzquierdo();
                        if (hijo.getIzquierdo() == null && hijo.getDerecho() == null) {
                            n.setIzquierdo(null);
                            n.recalcularAltura();
                        } else {
                            borrarNodo(hijo);
                            n.setIzquierdo(analizarBalance(hijo));
                            // n.recalcularAltura();
                        }
                    } else {
                        exito = eliminarAux(n.getIzquierdo(), elemento);
                        n.setIzquierdo(analizarBalance(n.getIzquierdo()));
                        // n.recalcularAltura();
                    }
                }
            } else {
                if (n.getDerecho() != null) {
                    if (n.getDerecho().getElem().compareTo(elemento) == 0) {
                        exito = true;
                        NodoAVL hijo = n.getDerecho();
                        if (hijo.getIzquierdo() == null && hijo.getDerecho() == null) {
                            n.setDerecho(null);
                            n.recalcularAltura();
                        } else {
                            borrarNodo(hijo);
                            n.setDerecho(analizarBalance(hijo));
                            // n.recalcularAltura();
                        }
                    } else {
                        exito = eliminarAux(n.getDerecho(), elemento);
                        n.setDerecho(analizarBalance(n.getDerecho()));
                        // n.recalcularAltura();
                    }
                }
            }
        }
        return exito;
    }

    @SuppressWarnings({ "rawtypes" })
    private Comparable buscarCandidato(NodoAVL n) {
        Comparable candidato = null;
        if (n != null) {
            if (n.getDerecho().getDerecho() == null) {
                candidato = n.getDerecho().getElem();
                n.setDerecho(n.getDerecho().getIzquierdo());
            } else {
                candidato = buscarCandidato(n.getDerecho());
                n.setDerecho(n.getDerecho());
            }
            n.recalcularAltura();
        }
        return candidato;
    }

    private void borrarNodo(NodoAVL n) {
        if (n.getDerecho() != null && n.getIzquierdo() != null) {
            if (n.getIzquierdo().getDerecho() != null) {
                n.setElem(buscarCandidato(n.getIzquierdo()));
            } else {
                n.setElem(n.getIzquierdo().getElem());
                n.setIzquierdo(n.getIzquierdo().getIzquierdo());
            }
        } else {
            if (n.getIzquierdo() != null) {
                n.setElem(n.getIzquierdo().getElem());
                n.setIzquierdo(n.getIzquierdo().getIzquierdo());
            } else {
                n.setElem(n.getDerecho().getElem());
                n.setDerecho(n.getDerecho().getDerecho());
            }
        }
        n.recalcularAltura();
    }

    private NodoAVL rotacionSimpleDerecha(NodoAVL pivote) {
        NodoAVL h = pivote.getIzquierdo();
        NodoAVL temp = h.getDerecho();
        h.setDerecho(pivote);
        pivote.setIzquierdo(temp);
        pivote.recalcularAltura();
        h.recalcularAltura();
        return h;
    }

    private NodoAVL rotacionSimpleIzquierda(NodoAVL pivote) {
        NodoAVL h = pivote.getDerecho();
        NodoAVL temp = h.getIzquierdo();
        h.setIzquierdo(pivote);
        pivote.setDerecho(temp);
        pivote.recalcularAltura();
        h.recalcularAltura();
        return h;
    }

    public String toString() {
        String cad;
        if (raiz != null) {
            cad = privateToString(raiz);
        } else {
            cad = "el arbol esta vacio";
        }

        return cad;
    }

    private String privateToString(NodoAVL n) {
        String cad = "";

        if (n.getIzquierdo() != null) {
            cad = cad + privateToString(n.getIzquierdo());

        }
        cad += " elemento: " + n.getElem().toString();
        if (n.getIzquierdo() != null) {
            cad = cad + " HI: " + n.getIzquierdo().getElem().toString();
        }
        if (n.getDerecho() != null) {
            cad = cad + " HD : " + n.getDerecho().getElem().toString();
        }
        cad = cad + " balance: " + obtenerBalance(n) + " altura: " + n.getAltura() + "\n";
        if (n.getDerecho() != null) {
            cad = cad + privateToString(n.getDerecho());
        }

        return cad;
    }

    @SuppressWarnings({ "rawtypes" })
    public boolean pertenece(Comparable elem) {
        boolean res = false;
        if (raiz != null) {
            res = privatePertenece(raiz, elem);
        }

        return res;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private boolean privatePertenece(NodoAVL n, Comparable elem) {
        boolean res = false;
        if (elem.compareTo(n.getElem()) == 0) {
            res = true;
        } else {
            if (elem.compareTo(n.getElem()) < 0) {
                if (n.getIzquierdo() != null) {
                    res = privatePertenece(n.getIzquierdo(), elem);
                }
            } else {
                if (n.getDerecho() != null) {
                    res = privatePertenece(n.getDerecho(), elem);
                }
            }
        }

        return res;
    }

    public void vaciar() {
        raiz = null;
    }

    public boolean esVacio() {
        return (raiz != null);
    }

    public Lista listar() {
        Lista listita = new Lista();
        if (raiz != null) {
            privateListar(raiz, listita);
        }

        return listita;

    }

    private void privateListar(NodoAVL n, Lista listita) {
        if (n.getIzquierdo() != null) {
            privateListar(n.getIzquierdo(), listita);

        }
        listita.insertar(n.getElem(), listita.longitud() + 1);

        if (n.getDerecho() != null) {
            privateListar(n.getDerecho(), listita);
        }
    }

    @SuppressWarnings({ "rawtypes" })
    public Comparable minimoElem() {
        Comparable min = null;
        if (raiz != null) {
            min = privateMinimo(raiz);
        }

        return min;
    }

    @SuppressWarnings({ "rawtypes" })
    private Comparable privateMinimo(NodoAVL n) {
        Comparable min = null;
        if (n.getIzquierdo() != null) {
            min = privateMinimo(n.getIzquierdo());
        } else {
            min = n.getElem();
        }
        return min;
    }

    @SuppressWarnings({ "rawtypes" })
    public Comparable maximoElem() {
        Comparable max = null;
        if (raiz != null) {
            max = privateMaximo(raiz);
        }

        return max;
    }

    @SuppressWarnings({ "rawtypes" })
    private Comparable privateMaximo(NodoAVL n) {
        Comparable max = null;
        if (n.getDerecho() != null) {
            max = privateMaximo(n.getDerecho());
        } else {
            max = n.getElem();
        }
        return max;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Lista listarPorRango(Comparable min, Comparable max) {
        Lista lis = new Lista();
        if (min.compareTo(max) > 0) {
            Comparable aux = max;
            max = min;
            min = aux;
        }
        if (this.raiz != null) {
            listarPorRangoAux(this.raiz, min, max, lis);
        }
        return lis;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void listarPorRangoAux(NodoAVL n, Comparable min, Comparable max, Lista lis) {
        if (n != null) {

            if (min.compareTo(n.getElem().toString()) <= 0 && max.compareTo(n.getElem().toString()) >= 0) {
                lis.insertar(n.getElem(), lis.longitud() + 1);
            }
            if (min.compareTo(n.getElem().toString()) <= 0) {
                listarPorRangoAux(n.getIzquierdo(), min, max, lis);
            }
            if (max.compareTo(n.getElem().toString()) >= 0) {
                listarPorRangoAux(n.getDerecho(), min, max, lis);
            }
        }
    }

    public ArbolAVL clone() {
        ArbolAVL arbolClone = new ArbolAVL();

        if (raiz != null) {
            NodoAVL aux = new NodoAVL(raiz.getElem(), null, null);
            arbolClone.raiz = aux;
            privateClone(raiz, arbolClone.raiz);
        }

        return arbolClone;
    }

    private void privateClone(NodoAVL n, NodoAVL c) {

        if (n.getIzquierdo() != null) {
            NodoAVL Izquierdo = new NodoAVL(n.getIzquierdo().getElem(), null, null);
            c.setIzquierdo(Izquierdo);
            privateClone(n.getIzquierdo(), c.getIzquierdo());
        }
        if (n.getDerecho() != null) {
            NodoAVL Derecho = new NodoAVL(n.getDerecho().getElem(), null, null);
            c.setDerecho(Derecho);
            privateClone(n.getDerecho(), c.getDerecho());
        }

    }

    @SuppressWarnings({ "rawtypes" })
    public Comparable recuperar(Comparable buscado) {
        Comparable elem = null;
        if (raiz != null) {
            elem = privateRecuperar(raiz, buscado);
        }
        return elem;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private Comparable privateRecuperar(NodoAVL n, Comparable buscado) {
        Comparable elem = null;
        Comparable aux = n.getElem();
        if (aux.compareTo(buscado) == 0) { // cast y .toString()
            elem = aux;
        } else {
            if (buscado.compareTo(aux) < 0) { // toString
                if (n.getIzquierdo() != null) {
                    elem = privateRecuperar(n.getIzquierdo(), buscado);
                }
            } else {
                if (buscado.compareTo(aux) > 0) { // toString
                    if (n.getDerecho() != null) {
                        elem = privateRecuperar(n.getDerecho(), buscado);
                    }
                }
            }
        }
        return elem;
    }

}
