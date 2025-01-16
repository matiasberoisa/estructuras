import clases.Cola;

public class GrafoEtiquetado {

    private NodoVert inicio;

    public GrafoEtiquetado() {
        inicio = null;
    }

    public boolean insertarVertice(Object nuevoVert) {
        boolean res = false;
        NodoVert aux = this.ubicarVertice(nuevoVert);
        if (aux == null) {
            this.inicio = new NodoVert(nuevoVert, this.inicio, null);
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
        Lista listaVisitados = new Lista();
        NodoVert aux = this.inicio;
        while (aux != null) {
            if (listaVisitados.localizar(aux.getElem()) < 0) {
                System.out.println(1);
                listarEnProfundidadAux(aux, listaVisitados);

            }
            aux = aux.getSigVertice();
        }

        return listaVisitados;
    }

    private void listarEnProfundidadAux(NodoVert vertActual, Lista lis) {
        NodoAdy ady;
        if (vertActual != null) {
            lis.insertar(vertActual.getElem(), lis.longitud() + 1);
            System.out.println(lis.toString());
            ady = vertActual.getPrimerAdy();
            while (ady != null) {
                if (lis.localizar(ady.getVertice().getElem()) < 0) {
                    listarEnProfundidadAux(ady.getVertice(), lis);
                }
                ady = ady.getSigAdyacente();
            }
        }

    }

    public boolean existeCamino(Object origen, Object destino) {
        boolean res = false;
        NodoVert vertOrigen = null, vertDestino = null, ini = this.inicio;
        Lista listaVisitados = new Lista();
        // verificar que existan los 2 colaVertices
        while ((vertOrigen == null || vertDestino == null) && ini != null) {
            if (ini.getElem().equals(origen)) {
                vertOrigen = ini;
            }
            if (ini.getElem().equals(destino)) {
                vertDestino = ini;
            }
            ini = ini.getSigVertice();
        }
        if (vertOrigen != null && vertDestino != null) {
            res = existeCaminoAux(vertOrigen, vertDestino, listaVisitados);
        }

        return res;
    }

    private boolean existeCaminoAux(NodoVert origen, Object dest, Lista lis) {
        boolean res = false;
        NodoAdy ady;
        if (origen != null) {
            if (origen.getElem().equals(dest)) {
                res = true;
            } else {
                lis.insertar(origen.getElem(), lis.longitud() + 1);
                ady = origen.getPrimerAdy();
                while (!res && ady != null) {
                    if (lis.localizar(ady.getVertice().getElem()) < 0) {
                        res = existeCaminoAux(ady.getVertice(), dest, lis);
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
        if (!esVacio()) {
            nodoOrigen = buscarVertice(this.inicio, origen);
            nodoFin = buscarVertice(this.inicio, dest);
            if (nodoOrigen != null && nodoFin != null) {
                crearArco(nodoOrigen, nodoFin, etiqueta);
                crearArco(nodoFin, nodoOrigen, etiqueta);
                res = true;
            }
        }

        return res;
    }

    private NodoVert buscarVertice(NodoVert vertActual, Object buscado) {
        NodoVert encontrado = null;
        if (vertActual.getElem().equals(buscado)) {
            encontrado = vertActual;
        } else {
            if (vertActual.getSigVertice() != null) {
                encontrado = buscarVertice(vertActual.getSigVertice(), buscado);
            }
        }

        return encontrado;
    }

    private void crearArco(NodoVert ini, NodoVert fin, Object eti) {
        NodoAdy guardado = new NodoAdy(fin, null, eti);
        NodoAdy recorrer = ini.getPrimerAdy();
        if (recorrer != null) {
            while (recorrer.getSigAdyacente() != null) {
                recorrer = recorrer.getSigAdyacente();
            }
            recorrer.setSigAdyacente(guardado);
        } else {
            ini.setPrimerAdy(guardado);
        }
    }

    public String toString() {
        String cad = "";
        NodoVert recorrercolaVertices = this.inicio;
        NodoAdy recorrerAdyacentes;
        if (!esVacio()) {
            recorrercolaVertices = this.inicio;
            while (recorrercolaVertices != null) {
                cad = cad + "Vertice: " + recorrercolaVertices.getElem().toString() + " arcos: ";
                recorrerAdyacentes = recorrercolaVertices.getPrimerAdy();
                while (recorrerAdyacentes != null) {
                    cad = cad + "[ etiqueta: " + recorrerAdyacentes.getEtiqueta() + " enlace: "
                            + recorrerAdyacentes.getVertice().getElem() + " ]";
                    if (recorrerAdyacentes.getSigAdyacente() != null) {
                        cad += ", ";
                    }
                    recorrerAdyacentes = recorrerAdyacentes.getSigAdyacente();
                }
                cad = cad + "\n";
                recorrercolaVertices = recorrercolaVertices.getSigVertice();
            }

        } else {
            cad = "el grafo no tiene colaVertices";
        }

        return cad;
    }

    public boolean eliminarVertice(Object eliminado) {
        boolean res = false;
        if (!esVacio()) {
            NodoVert buscado = this.inicio;
            if (buscado.getElem().equals(eliminado)) {
                this.inicio = this.inicio.getSigVertice();
                res = true;
            } else {
                NodoVert anterior = buscado;
                buscado = buscado.getSigVertice();
                while (buscado != null && !res) {
                    if (buscado.getElem().equals(eliminado)) {
                        anterior.setSigVertice(anterior.getSigVertice().getSigVertice());
                        res = true;
                    } else {
                        anterior = anterior.getSigVertice();
                        buscado = buscado.getSigVertice();
                    }
                }
            }
            if (res && buscado != null) {
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
            while (primero != null && primero.getVertice().getElem().equals(eliminado)) {
                recorrer.setPrimerAdy(recorrer.getPrimerAdy().getSigAdyacente());
                primero = recorrer.getPrimerAdy();
            }
            if (primero != null) {
                NodoAdy vertSiguiente = primero.getSigAdyacente();
                while (vertSiguiente != null) {
                    if (vertSiguiente.getVertice().getElem().equals(eliminado)) {
                        primero.setSigAdyacente(vertSiguiente.getSigAdyacente());
                    }
                    vertSiguiente = vertSiguiente.getSigAdyacente();
                    primero = primero.getSigAdyacente();
                }
            }

            losAdyacentes = losAdyacentes.getSigAdyacente();
        }

    }

    public boolean eliminarArco(Object origen, Object destino, Object etiqueta) {
        boolean res = false;
        NodoVert nodoOrigen, nodoDestino = null;
        if (!esVacio()) {
            nodoOrigen = buscarVertice(this.inicio, origen);
            if (nodoOrigen != null) {
                nodoDestino = buscarVertice(this.inicio, destino);
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
        if (!esVacio()) {
            NodoVert encontrado = buscarVertice(this.inicio, buscado);
            if (encontrado != null) {
                res = true;
            }
        }
        return res;
    }

    public boolean existeArco(Object origen, Object destino) {
        boolean res = false;
        if (!esVacio()) {
            NodoVert encontrado;
            encontrado = buscarVertice(this.inicio, origen);
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
        return this.inicio == null;
    }

    public void vaciar() {
        this.inicio = null;
    }

    public Object recuperar(Object elem) {
        NodoVert aux;
        Object buscado = null;
        if (!esVacio()) {
            aux = buscarVertice(this.inicio, elem);
            if (aux != null) {
                buscado = aux.getElem();
            }
        }
        return buscado;
    }

    public Lista caminoMasCorto(Object origen, Object destino) throws CloneNotSupportedException {
        Lista caminoCorto = new Lista();
        Lista camino = new Lista();
        if (!esVacio()) {
            NodoVert origenVertice = ubicarVertice(origen);
            NodoVert destinoVertice = ubicarVertice(destino);
            if (origenVertice != null && destinoVertice != null) {
                caminoCorto = caminoMasCortoAux(origenVertice, destinoVertice, camino, caminoCorto);
            }
        }
        return caminoCorto;
    }

    private Lista caminoMasCortoAux(NodoVert n, NodoVert destino, Lista camino, Lista caminoCorto)
            throws CloneNotSupportedException {
        if (n != null) {
            boolean res = false;
            camino.insertar(n.getElem(), camino.longitud() + 1);
            // System.out.println(camino.toString());
            NodoAdy ady = n.getPrimerAdy();
            while (ady != null && !res) {
                if (ady.getVertice().getElem().equals(destino.getElem())) {
                    // System.out.println("encontro el destino");
                    res = true;
                    if (caminoCorto.longitud() == 0 || camino.longitud() < caminoCorto.longitud() - 1) {
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

    public Lista caminoMenorTiempo(Object origen, Object destino) throws CloneNotSupportedException {
        Lista lista = new Lista();
        double[] peso = new double[1];
        peso[0] = 0.0;
        Lista caminoMenor = new Lista();
        if (!esVacio()) {
            NodoVert ubicarOrigen = ubicarVertice(origen);
            NodoVert ubicarDestino = ubicarVertice(destino);
            if (ubicarOrigen != null && ubicarDestino != null) {
                caminoMenor = caminoMenorAux(ubicarOrigen, ubicarDestino, lista, caminoMenor, peso, 0.0);

            }
        }

        return caminoMenor;
    }

    private Lista caminoMenorAux(NodoVert n, NodoVert destino, Lista camino, Lista caminoMenor, double[] actual,
            double menorPeso) throws CloneNotSupportedException { // llevar un arreglo de una posicion y un parametro
                                                                  // double
        if (n != null) {
            boolean res = false;
            camino.insertar(n.getElem(), camino.longitud() + 1);
            NodoAdy ady = n.getPrimerAdy();

            while (ady != null && !res) {
                if (ady.getVertice().getElem().equals(destino.getElem())) {
                    // System.out.println("encontrado");
                    res = true;
                    menorPeso = menorPeso + (Double) ady.getEtiqueta();
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
                                    menorPeso + (Double) ady.getEtiqueta());
                            camino.eliminar(camino.longitud());
                        }

                    }
                }
                ady = ady.getSigAdyacente();
            }
        }
        return caminoMenor;
    }

    public Lista caminoMasCorto(Object ciudadA, Object ciudadB, Object ciudadC) throws CloneNotSupportedException {
        Lista camino = new Lista(), caminoCorto = new Lista();
        NodoVert origen, destino, ciudadEvitada;
        if (!esVacio() && !ciudadA.equals(ciudadC)
                && !ciudadB.equals(ciudadC)) {
            origen = buscarVertice(this.inicio, ciudadA);
            destino = buscarVertice(this.inicio, ciudadB);
            ciudadEvitada = buscarVertice(this.inicio, ciudadC);
            if (origen != null && destino != null) {
                caminoCorto = caminoCortoAux(origen, destino, ciudadEvitada, camino, caminoCorto);
            }
        }
        return caminoCorto;
    }

    private Lista caminoCortoAux(NodoVert origen, NodoVert destino, NodoVert ciudadEvitada, Lista camino,
            Lista listaVisitados) throws CloneNotSupportedException {
        Cola colaVertices = new Cola();
        NodoAdy vertSiguiente;
        boolean res = false;
        if (origen != null) {
            listaVisitados.insertar(origen.getElem(), listaVisitados.longitud() + 1);
            colaVertices.poner(origen.getElem());
            camino.insertar(origen.getElem(), camino.longitud() + 1);
            while (!colaVertices.esVacia() && !res) {
                Object elemVertice = colaVertices.obtenerFrente();
                NodoVert vertActual = buscarVertice(this.inicio, elemVertice);
                colaVertices.sacar();
                vertSiguiente = vertActual.getPrimerAdy();
                while (vertSiguiente != null && !res) {
                    if (vertSiguiente.getVertice().getElem().equals(destino.getElem())) {
                        res = true;
                        camino.insertar(vertSiguiente.getVertice().getElem(), camino.longitud() + 1);
                    } else {
                        // if (vertSiguiente.getVertice().getElem().equals(ciudadEvitada.getElem())) {
                        if (listaVisitados.localizar(vertSiguiente.getVertice().getElem()) < 0) {
                            listaVisitados.insertar(vertSiguiente.getVertice().getElem(),
                                    listaVisitados.longitud() + 1);
                            colaVertices.poner(vertSiguiente.getVertice().getElem());
                        } else {
                            camino.insertar(vertSiguiente.getVertice().getElem(), camino.longitud() + 1);
                        }
                        // }
                    }
                    vertSiguiente = vertSiguiente.getSigAdyacente();
                }
            }
        }

        return camino;
    }
    /*
     * camino.insertar(origen.getElem(), camino.longitud() + 1);
     * vertSiguiente = origen.getPrimerAdy();
     * while (vertSiguiente != null && !res) {
     * if (vertSiguiente.getVertice().getElem().equals(destino.getElem())) {
     * res = true;
     * if (caminoCorto.longitud() == 0 || camino.longitud() < caminoCorto.longitud()
     * - 1) {
     * caminoCorto = camino.clone();
     * caminoCorto.insertar(destino.getElem(), camino.longitud() + 1);
     * }
     * } else {
     * }
     * vertSiguiente = vertSiguiente.getSigAdyacente();
     * }
     */
}
