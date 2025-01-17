
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

    public Lista profundidad() {
        Lista listaVisitados = new Lista();
        NodoVert aux = this.inicio;
        while (aux != null) {
            if (listaVisitados.localizar(aux.getElem()) < 0) {
                listaVisitados = ProfundidadDesde(aux, listaVisitados);
            }
            aux = aux.getSigVertice();
        }
        return listaVisitados;
    }

    private Lista ProfundidadDesde(NodoVert vertActual, Lista visitados) {
        NodoAdy siguiente;
        if (vertActual != null) {
            visitados.insertar(vertActual.getElem(), visitados.longitud() + 1);
            siguiente = vertActual.getPrimerAdy();
            while (siguiente != null) {
                if (visitados.localizar(siguiente.getVertice().getElem()) < 0) {
                    ProfundidadDesde(siguiente.getVertice(), visitados);
                }
                siguiente = siguiente.getSigAdyacente();
            }
        }
        return visitados;
    }

    public boolean existeCamino(Object origen, Object destino) {
        boolean res = false;
        Lista listaVisitados = new Lista();
        NodoVert nodoOrigen = buscarVertice(this.inicio, origen);
        NodoVert nodoDestino = buscarVertice(this.inicio, destino);
        if (nodoOrigen != null && nodoDestino != null) {
            res = buscarCamino(nodoOrigen, nodoDestino, listaVisitados);
        }
        return res;
    }

    private boolean buscarCamino(NodoVert nodoOrigen, NodoVert nodoDestino, Lista visitados) {
        boolean encontrado = false;
        NodoAdy siguiente = nodoOrigen.getPrimerAdy();
        if (nodoOrigen != null) {
            if (siguiente.getVertice().getElem().equals(nodoDestino.getElem())) {
                encontrado = true;
            } else {
                visitados.insertar(nodoOrigen.getElem(), visitados.longitud() + 1);
                while (!encontrado && siguiente != null) {
                    if (visitados.localizar(siguiente.getVertice().getElem()) < 0) {
                        encontrado = buscarCamino(siguiente.getVertice(), nodoDestino, visitados);
                    }
                    siguiente = siguiente.getSigAdyacente();
                }
            }
        }
        return encontrado;
    }

    public boolean insertarArco(Object origen, Object dest, Object etiqueta) {
        boolean res = false;
        NodoVert nodoOrigen = buscarVertice(this.inicio, origen);
        NodoVert nodoDestino = buscarVertice(this.inicio, dest);
        if (nodoOrigen != null && nodoDestino != null) {
            res = true;
            crearEnlace(nodoOrigen, nodoDestino, etiqueta);
            crearEnlace(nodoDestino, nodoOrigen, etiqueta);
        }
        return res;
    }

    private NodoVert buscarVertice(NodoVert vertActual, Object buscado) {
        NodoVert nodoBuscado = null;
        if (vertActual != null) {
            if (vertActual.getElem().equals(buscado)) {
                nodoBuscado = vertActual;
            } else {
                nodoBuscado = buscarVertice(vertActual.getSigVertice(), buscado);
            }
        }
        return nodoBuscado;
    }

    private void crearEnlace(NodoVert nodoOrigen, NodoVert nodoDestino, Object etiqueta) {
        NodoAdy ady = new NodoAdy(nodoDestino, null, etiqueta);
        if (nodoOrigen.getPrimerAdy() == null) {
            nodoOrigen.setPrimerAdy(ady);
        } else {
            NodoAdy siguienteAdy = nodoOrigen.getPrimerAdy();
            while (siguienteAdy.getSigAdyacente() != null) {
                siguienteAdy = siguienteAdy.getSigAdyacente();
            }
            siguienteAdy.setSigAdyacente(ady);
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
            cad = "el grafo no tiene vertices";
        }

        return cad;
    }

    public boolean eliminarVertice(Object eliminado) {
        boolean res = false;

        return res;

    }

    public boolean eliminarArco(Object origen, Object destino, Object etiqueta) {
        boolean res = false;
        NodoVert nodoOrigen = buscarVertice(this.inicio, origen);
        NodoVert nodoDestino = buscarVertice(this.inicio, destino);
        if (nodoOrigen != null && nodoDestino != null && existeArcoConEtiqueta(nodoOrigen, nodoDestino, etiqueta)) {
            res = true;
            eliminarEnlace(nodoOrigen, nodoDestino, etiqueta);
            eliminarEnlace(nodoDestino, nodoOrigen, etiqueta);
        }
        return res;
    }

    private boolean existeArcoConEtiqueta(NodoVert nodoOrigen, NodoVert nodoDestino, Object etiqueta) {
        boolean res = false;
        NodoAdy siguiente = nodoOrigen.getPrimerAdy();
        if (nodoOrigen != null && nodoDestino != null) {
            while (siguiente != null && !res) {
                if (siguiente.getVertice().getElem().equals(nodoDestino.getElem())
                        && siguiente.getEtiqueta().equals(etiqueta)) {
                    res = true;
                }
                siguiente = siguiente.getSigAdyacente();
            }
        }
        return res;
    }

    private void eliminarEnlace(NodoVert nodoOrigen, NodoVert nodoDestino, Object etiqueta) {
        NodoAdy siguiente = nodoOrigen.getPrimerAdy();
        NodoAdy anterior = null;
        boolean encontrado = false;
        if (siguiente.getSigAdyacente() == null) {
            nodoOrigen.setPrimerAdy(null);
        } else {
            while (!encontrado && siguiente != null) {
                System.out.println(siguiente.getVertice().getElem());
                if (siguiente.getVertice().getElem().equals(nodoDestino.getElem())
                        && siguiente.getEtiqueta().equals(etiqueta)) {
                    encontrado = true;
                    anterior.setSigAdyacente(siguiente.getSigAdyacente());
                }
                anterior = siguiente;
                siguiente = siguiente.getSigAdyacente();
            }
        }

    }

    public boolean existeVertice(Object buscado) {
        boolean res = false;
        if (this.inicio != null && buscarVertice(this.inicio, buscado) != null) {
            res = true;
        }
        return res;
    }

    public boolean existeArco(Object origen, Object destino) {
        boolean res = false;
        NodoVert nodoOrigen = buscarVertice(this.inicio, origen);
        NodoVert nodoDestino = buscarVertice(this.inicio, destino);
        if (nodoOrigen != null && nodoDestino != null) {
            res = buscarArco(nodoOrigen.getPrimerAdy(), nodoDestino);
        }
        return res;
    }

    private boolean buscarArco(NodoAdy nodoSiguiente, NodoVert nodoDestino) {
        boolean encontrado = false;
        while (nodoSiguiente != null && !encontrado) {
            if (nodoSiguiente.getVertice().getElem().equals(nodoDestino.getElem())) {
                encontrado = true;
            }
            nodoSiguiente = nodoSiguiente.getSigAdyacente();
        }
        return encontrado;
    }

    public boolean esVacio() {
        return this.inicio == null;
    }

    public void vaciar() {
        this.inicio = null;
    }

    public Object recuperar(Object elem) {
        Object buscado = null;
        return buscado;
    }

    public Lista caminoMasCorto(Object origen, Object destino) throws CloneNotSupportedException {
        Lista caminoCorto = new Lista();
        return caminoCorto;
    }

    public Lista caminoMenorTiempo(Object origen, Object destino) throws CloneNotSupportedException {
        Lista caminoMenor = new Lista();

        return caminoMenor;
    }

    /*
     * public Lista caminoMasCorto(Object ciudadA, Object ciudadB, Object ciudadC)
     * throws CloneNotSupportedException {
     * Lista camino = new Lista(), caminoCorto = new Lista();
     * NodoVert origen, destino, ciudadEvitada;
     * if (!esVacio() && !ciudadA.equals(ciudadC)
     * && !ciudadB.equals(ciudadC)) {
     * origen = buscarVertice(this.inicio, ciudadA);
     * destino = buscarVertice(this.inicio, ciudadB);
     * ciudadEvitada = buscarVertice(this.inicio, ciudadC);
     * if (origen != null && destino != null) {
     * caminoCorto = caminoCortoAux(origen, destino, ciudadEvitada, camino,
     * caminoCorto);
     * }
     * }
     * return caminoCorto;
     * }
     * 
     * private Lista caminoCortoAux(NodoVert origen, NodoVert destino, NodoVert
     * ciudadEvitada, Lista camino,
     * Lista listaVisitados) throws CloneNotSupportedException {
     * Cola colaVertices = new Cola();
     * NodoAdy vertSiguiente;
     * boolean res = false;
     * if (origen != null) {
     * listaVisitados.insertar(origen.getElem(), listaVisitados.longitud() + 1);
     * colaVertices.poner(origen.getElem());
     * camino.insertar(origen.getElem(), camino.longitud() + 1);
     * while (!colaVertices.esVacia() && !res) {
     * Object elemVertice = colaVertices.obtenerFrente();
     * NodoVert vertActual = buscarVertice(this.inicio, elemVertice);
     * colaVertices.sacar();
     * vertSiguiente = vertActual.getPrimerAdy();
     * while (vertSiguiente != null && !res) {
     * if (vertSiguiente.getVertice().getElem().equals(destino.getElem())) {
     * res = true;
     * camino.insertar(vertSiguiente.getVertice().getElem(), camino.longitud() + 1);
     * } else {
     * // if (vertSiguiente.getVertice().getElem().equals(ciudadEvitada.getElem()))
     * {
     * if (listaVisitados.localizar(vertSiguiente.getVertice().getElem()) < 0) {
     * listaVisitados.insertar(vertSiguiente.getVertice().getElem(),
     * listaVisitados.longitud() + 1);
     * colaVertices.poner(vertSiguiente.getVertice().getElem());
     * } else {
     * camino.insertar(vertSiguiente.getVertice().getElem(), camino.longitud() + 1);
     * }
     * // }
     * }
     * vertSiguiente = vertSiguiente.getSigAdyacente();
     * }
     * }
     * }
     * 
     * return camino;
     * }
     */
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
