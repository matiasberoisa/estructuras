/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class Grafo {

    private NodoVert inicio;

    public Grafo() {
        inicio = null;
    }

    public boolean insertarVertice(Object nuevoVert) {
        boolean res = false;
        NodoVert aux = this.ubicarVertice(nuevoVert);
        if (aux == null) {
            inicio = new NodoVert(nuevoVert, this.inicio, null);
            res = true;
        }
        return res;
    }

    private NodoVert ubicarVertice(Object buscado) {
        NodoVert aux = this.inicio;
        while (aux != null && !(aux.getElem().equals(buscado))) {
            aux = aux.getSigVertice();
        }
        return aux;
    }

    public Lista listarEnProfundidad() {
        Lista visitados = new Lista();
        NodoVert aux = inicio;
        while (aux != null) {
            if (visitados.localizar(aux.getElem()) < 0) {
                listarEnProfundidadAux(aux, visitados);
            }
            aux = aux.getSigVertice();
        }

        return visitados;
    }

    private void listarEnProfundidadAux(NodoVert n, Lista vis) {
        if (n != null) {
            vis.insertar(n.getElem(), vis.longitud() + 1);
            NodoAdy ady = n.getPrimerAdy();
            while (ady != null) {
                if (vis.localizar(ady.getVertice().getElem()) < 0) {
                    listarEnProfundidadAux(ady.getVertice(), vis);
                }
                ady = ady.getSigAdyacente();
            }
        }

    }

    public boolean existeCamino(Object origen, Object destino) {
        boolean res = false;
        NodoVert aux = null;
        NodoVert otroAux = null;
        NodoVert ini = inicio;

        while ((aux == null || otroAux == null) && ini != null) {
            if (ini.getElem().equals(origen)) {
                aux = ini;
            }
            if (ini.getElem().equals(destino)) {
                otroAux = ini;
            }
            ini = ini.getSigVertice();
        }
        if (aux != null && otroAux != null) {
            Lista visitados = new Lista();
            res = existeCaminoAux(aux, destino, visitados);
        }

        return res;
    }

    private boolean existeCaminoAux(NodoVert n, Object dest, Lista visit) {
        boolean res = false;
        if (n != null) {
            if (n.getElem().equals(dest)) {
                res = true;
            } else {
                visit.insertar(n.getElem(), visit.longitud() + 1);
                NodoAdy ady = n.getPrimerAdy();
                while (!res && ady != null) {
                    if (visit.localizar(ady.getVertice().getElem()) < 0) {
                        res = existeCaminoAux(ady.getVertice(), dest, visit);
                    }
                    ady = ady.getSigAdyacente();
                }
            }
        }
        return res;
    }

    public boolean insertarArco(Object origen, Object dest, Object etiqueta) {
        boolean res = false;
        NodoVert nodoOrigen, nodoFin = null;
        if (inicio != null) {
            nodoOrigen = buscarVertice(inicio, origen);
            if (nodoOrigen != null) {
                nodoFin = buscarVertice(inicio, dest);
            }
            if (nodoFin != null) {
                guardarArco(nodoOrigen, nodoFin, etiqueta); // guardo el enlace que va de inicio a fin
                guardarArco(nodoFin, nodoOrigen, etiqueta); // guardo el enlace que va de fin a inicio
                res = true;
            }
        }

        return res;
    }

    private NodoVert buscarVertice(NodoVert n, Object buscado) {
        NodoVert encontrado = null;
        if (n.getElem().toString().equals(buscado.toString())) { // solucion temporal con los 2 .toString
            encontrado = n;
        } else {
            if (n.getSigVertice() != null) {
                encontrado = buscarVertice(n.getSigVertice(), buscado);
            }
        }

        return encontrado;
    }

    private void guardarArco(NodoVert ini, NodoVert fin, Object eti) {
        NodoAdy guardado = new NodoAdy(fin, null, eti); // creo el nodoAdy a guardar
        NodoAdy recorrer = ini.getPrimerAdy(); // creo el nodo auxiliar para recorrer los adyacentes
        if (recorrer != null) {
            while (recorrer.getSigAdyacente() != null) { // recorro la "lista" de adyacentes y me paro en el ultimo
                recorrer = recorrer.getSigAdyacente();
            }
            recorrer.setSigAdyacente(guardado);
        } else {
            ini.setPrimerAdy(guardado);
        }
    }

    public String toString() {
        String cad = "";
        if (inicio != null) {
            NodoVert recorrerVertices = inicio;
            NodoAdy recorrerAdyacentes;
            while (recorrerVertices != null) {
                cad = cad + "Vertice: " + recorrerVertices.getElem().toString() + " arcos: ";
                recorrerAdyacentes = recorrerVertices.getPrimerAdy();
                while (recorrerAdyacentes != null) {
                    cad = cad + "etiqueta: " + recorrerAdyacentes.getEtiqueta() + " enlace: "
                            + recorrerAdyacentes.getVertice().getElem() + "\t\t";
                    recorrerAdyacentes = recorrerAdyacentes.getSigAdyacente();
                }
                cad = cad + "\n";
                recorrerVertices = recorrerVertices.getSigVertice();
            }

        } else {
            cad = "el grafo no tiene vertices";
        }

        return cad;
    }

    public boolean eliminarVertice(Object eliminado) {
        boolean res = false;
        if (inicio != null) {
            NodoVert buscado = inicio;
            if (buscado.getElem().equals(eliminado)) {
                inicio = inicio.getSigVertice();
                res = true;
            } else {
                NodoVert anterior = buscado;
                buscado = buscado.getSigVertice();
                while (buscado != null && !res) {
                    if (buscado.getElem().equals(eliminado)) {
                        anterior.setSigVertice(anterior.getSigVertice().getSigVertice()); // seteo el nodo anterior al
                                                                                          // siguiente del eliminado
                        res = true;
                    } else {
                        anterior = anterior.getSigVertice();
                        buscado = buscado.getSigVertice();
                    }
                }
            }
            if (res && buscado != null) {
                // Lista listaAdyacentes = new Lista(); //no crear una lista
                NodoAdy adyacente = buscado.getPrimerAdy();

                quitarEnlaces(adyacente, eliminado);
            }
        }

        return res;
    }

    private void quitarEnlaces(NodoAdy n, Object eliminado) {
        NodoAdy losAdyacentes = n;
        while (losAdyacentes != null) {

            NodoAdy primero = losAdyacentes.getVertice().getPrimerAdy();

            NodoVert recorrer = losAdyacentes.getVertice();

            while (primero != null && primero.getVertice().getElem().equals(eliminado)) { // no crear una lista,
                                                                                          // trabajar con los
                                                                                          // NodosAdyacentes
                recorrer.setPrimerAdy(recorrer.getPrimerAdy().getSigAdyacente());
                primero = recorrer.getPrimerAdy();
            }
            if (primero != null) {
                NodoAdy siguiente = primero.getSigAdyacente();
                while (siguiente != null) {
                    if (siguiente.getVertice().getElem().equals(eliminado)) {
                        primero.setSigAdyacente(siguiente.getSigAdyacente());
                    }
                    siguiente = siguiente.getSigAdyacente();
                    primero = primero.getSigAdyacente();
                }
            }

            losAdyacentes = losAdyacentes.getSigAdyacente();
        }

    }

    public boolean eliminarArco(Object origen, Object destino, Object etiqueta) {
        boolean res = false;
        NodoVert nodoOrigen, nodoDestino = null;
        if (inicio != null) {
            nodoOrigen = buscarVertice(inicio, origen);
            if (nodoOrigen != null) {
                nodoDestino = buscarVertice(inicio, destino);
            }
            if (nodoDestino != null) {
                res = eliminarEtiqueta(nodoOrigen, nodoDestino, etiqueta);
                if (res) {
                    res = eliminarEtiqueta(nodoDestino, nodoOrigen, etiqueta);
                }
            }
        }
        return res;
    }

    private boolean eliminarEtiqueta(NodoVert n, NodoVert destino, Object eti) {
        boolean res = false;
        if (n.getPrimerAdy() != null) {
            if ((n.getPrimerAdy().getEtiqueta().equals(eti))
                    && (n.getPrimerAdy().getVertice().getElem().equals(destino.getElem()))) {
                n.setPrimerAdy(n.getPrimerAdy().getSigAdyacente());
                res = true;
            } else {
                NodoAdy recorrer = n.getPrimerAdy().getSigAdyacente();
                NodoAdy anterior = n.getPrimerAdy();
                while (recorrer != null && !res) {
                    if (recorrer.getEtiqueta().equals(eti)
                            && (recorrer.getVertice().getElem().equals(destino.getElem()))) {
                        anterior.setSigAdyacente(anterior.getSigAdyacente().getSigAdyacente());
                        res = true;
                    }
                    anterior = anterior.getSigAdyacente();
                    recorrer = recorrer.getSigAdyacente();
                }
            }
        }
        return res;
    }

    public boolean existeVertice(Object buscado) {
        boolean res = false;
        if (inicio != null) {
            NodoVert encontrado = buscarVertice(inicio, buscado);
            if (encontrado != null) {
                res = true;
            }
        }
        return res;
    }

    public boolean existeArco(Object origen, Object destino) {
        boolean res = false;
        if (inicio != null) {
            NodoVert encontrado;
            encontrado = buscarVertice(inicio, origen);
            if (encontrado != null) {
                NodoAdy buscar = encontrado.getPrimerAdy();
                while (buscar != null && !res) {
                    if (buscar.getVertice().getElem().equals(destino)) {
                        res = true;
                    }
                    buscar = buscar.getSigAdyacente();
                }
            }
        }
        return res;
    }

    public boolean esVacio() {
        boolean res = false;
        if (inicio == null) {
            res = true;
        }
        return res;
    }

    public void vaciar() {
        inicio = null;
    }

    public Object recuperar(Object elem) {
        NodoVert aux;
        Object buscado = null;
        if (inicio != null) {
            aux = buscarVertice(inicio, elem);
            if (aux != null) {
                buscado = aux.getElem();
            }
        }
        return buscado;
    }

    public Lista caminoMasCorto(Object origen, Object destino) {
        Lista caminoCorto = new Lista();
        Lista camino = new Lista();
        if (this.inicio != null) {
            NodoVert origenVertice = ubicarVertice(origen);
            NodoVert destinoVertice = ubicarVertice(destino);
            if (origenVertice != null && destinoVertice != null) {
                System.out.println("entra");
                caminoCorto = caminoMasCortoAux(origenVertice, destinoVertice, camino, caminoCorto);
            }
        }
        return caminoCorto;
    }

    private Lista caminoMasCortoAux(NodoVert n, NodoVert destino, Lista camino, Lista caminoCorto) {
        if (n != null) {
            boolean res = false;
            camino.insertar(n.getElem(), camino.longitud() + 1);
            // System.out.println(camino.toString());
            NodoAdy ady = n.getPrimerAdy();
            while (ady != null && !res) {
                if (ady.getVertice().getElem().equals(destino.getElem())) {
                    // System.out.println("encontro el destino");
                    res = true;
                    if (caminoCorto.longitud() == 0 || camino.longitud() < caminoCorto.longitud() - 1) { // este if//
                                                                                                         // puede//
                                                                                                         // juntarse
                                                                                                         // con// el de
                                                                                                         // abajo// con
                                                                                                         // un OR
                        caminoCorto = camino.clone();
                        caminoCorto.insertar(destino.getElem(), camino.longitud() + 1);
                    }
                    // System.out.println(caminoCorto.toString());
                } else {
                    if (camino.localizar(ady.getVertice().getElem()) < 0) {
                        if (camino.longitud() < caminoCorto.longitud() - 1 || camino.longitud() == 1
                                || caminoCorto.longitud() == 0) {
                            caminoCorto = caminoMasCortoAux(ady.getVertice(), destino, camino, caminoCorto);
                            camino.eliminar(camino.longitud());
                        }

                    }
                }
                ady = ady.getSigAdyacente();
            }
        }
        return caminoCorto;
    }

    public Lista caminoMenorTiempo(Object origen, Object destino) {
        Lista lista = new Lista();
        double[] peso = new double[1];
        peso[0] = 0.0;
        Lista caminoMenor = new Lista();
        if (inicio != null) {
            NodoVert ubicarOrigen = ubicarVertice(origen);
            NodoVert ubicarDestino = ubicarVertice(destino);
            if (ubicarOrigen != null && ubicarDestino != null) {
                caminoMenor = caminoMenorAux(ubicarOrigen, ubicarDestino, lista, caminoMenor, peso, 0.0);

            }
        }

        return caminoMenor;
    }

    private Lista caminoMenorAux(NodoVert n, NodoVert destino, Lista camino, Lista caminoMenor, double[] actual,
            double menorPeso) { // llevar un arreglo de una posicion y un parametro double
        if (n != null) {
            boolean res = false;
            camino.insertar(n.getElem(), camino.longitud() + 1);
            NodoAdy ady = n.getPrimerAdy();

            while (ady != null && !res) {
                if (ady.getVertice().getElem().equals(destino.getElem())) {
                    // System.out.println("encontrado");
                    res = true;
                    menorPeso = menorPeso + (int) ady.getEtiqueta();
                    System.out.println(menorPeso);
                    if (caminoMenor.longitud() == 0 || actual[0] > menorPeso || actual[0] == 0) { // caminoMenor.longitud()
                                                                                                  // == 0
                                                                                                  // etiquetaMenor.longitud()==0
                                                                                                  // &&
                        // menorPeso = actual[0]; chequear
                        caminoMenor = camino.clone();
                        caminoMenor.insertar(destino.getElem(), camino.longitud() + 1);
                        actual[0] = menorPeso;

                    }
                    // System.out.println(caminoMenor.toString());
                } else {
                    if (camino.localizar(ady.getVertice().getElem()) < 0) {
                        if (actual[0] < menorPeso || camino.longitud() == 1 || actual[0] == 0) {
                            caminoMenor = caminoMenorAux(ady.getVertice(), destino, camino, caminoMenor, actual,
                                    menorPeso + (int) ady.getEtiqueta());
                            camino.eliminar(camino.longitud());
                        }

                    }
                }
                ady = ady.getSigAdyacente();
            }
        }
        return caminoMenor;
    }

}
