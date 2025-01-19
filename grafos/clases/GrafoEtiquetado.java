import java.util.ArrayList;

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
        if (vertActual != null) {
            visitados.insertar(vertActual.getElem(), visitados.longitud() + 1);
            NodoAdy siguiente = vertActual.getPrimerAdy();
            while (siguiente != null) {
                if (visitados.localizar(siguiente.getVertice().getElem()) < 0) {
                    visitados = ProfundidadDesde(siguiente.getVertice(), visitados);
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
            if (origen.equals(dest)) {
                crearEnlace(nodoOrigen, nodoDestino, etiqueta);
            } else {
                crearEnlace(nodoOrigen, nodoDestino, etiqueta);
                crearEnlace(nodoDestino, nodoOrigen, etiqueta);
            }

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
        NodoVert nodoBuscado = buscarVertice(this.inicio, eliminado), nodoSiguiente = this.inicio.getSigVertice(),
                anterior = this.inicio;
        NodoAdy siguiente, nuevoInicio;
        if (!esVacio() && nodoBuscado != null) {
            res = true;
            siguiente = nodoBuscado.getPrimerAdy();
            if (this.inicio.getElem().equals(eliminado)) {
                if (this.inicio.getSigVertice() == null) {
                    vaciar();
                } else {
                    nuevoInicio = siguiente;
                    while (siguiente != null) {
                        eliminarEnlace(nodoBuscado, siguiente.getVertice(), siguiente.getEtiqueta());
                        eliminarEnlace(siguiente.getVertice(), nodoBuscado, siguiente.getEtiqueta());
                        siguiente = siguiente.getSigAdyacente();
                    }
                    this.inicio = nuevoInicio.getVertice();
                }
            } else {
                while (siguiente != null) {
                    eliminarEnlace(nodoBuscado, siguiente.getVertice(), siguiente.getEtiqueta());
                    eliminarEnlace(siguiente.getVertice(), nodoBuscado, siguiente.getEtiqueta());
                    siguiente = siguiente.getSigAdyacente();
                }
                if (nodoSiguiente.getElem().equals(eliminado)) {
                    nodoSiguiente = nodoSiguiente.getSigVertice();
                    anterior.setSigVertice(nodoSiguiente);
                } else {
                    while (nodoSiguiente != null && !nodoSiguiente.getElem().equals(eliminado)) {
                        anterior = nodoSiguiente;
                        nodoSiguiente = nodoSiguiente.getSigVertice();
                    }
                    anterior.setSigVertice(nodoSiguiente.getSigVertice());
                }
            }

        }
        return res;
    }

    public boolean eliminarArco(Object origen, Object destino, Object etiqueta) {
        boolean res = false;
        NodoVert nodoOrigen = buscarVertice(this.inicio, origen);
        NodoVert nodoDestino = buscarVertice(this.inicio, destino);
        if (nodoOrigen != null && nodoDestino != null && existeArcoConEtiqueta(nodoOrigen, nodoDestino, etiqueta)) {
            res = true;
            if (origen.equals(destino)) {
                eliminarEnlace(nodoOrigen, nodoDestino, etiqueta);
            } else {
                eliminarEnlace(nodoOrigen, nodoDestino, etiqueta);
                eliminarEnlace(nodoDestino, nodoOrigen, etiqueta);
            }

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
                if (siguiente.getVertice().getElem().equals(nodoDestino.getElem())
                        && siguiente.getEtiqueta().equals(etiqueta)) {
                    encontrado = true;
                    if (anterior == null) {
                        nodoOrigen.setPrimerAdy(siguiente.getSigAdyacente());
                    } else {
                        anterior.setSigAdyacente(siguiente.getSigAdyacente());
                    }
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

    public Lista anchura(Object origen) {
        Lista caminoCorto = new Lista();
        NodoVert nodoOrigen = buscarVertice(this.inicio, origen);
        if (!esVacio() && nodoOrigen != null) {
            while (nodoOrigen != null) {
                if (caminoCorto.localizar(nodoOrigen.getElem()) < 0) {
                    caminoCorto = anchuraDesde(nodoOrigen, caminoCorto);
                }
                nodoOrigen = nodoOrigen.getSigVertice();
            }
        }
        return caminoCorto;
    }

    private Lista anchuraDesde(NodoVert nodoOrigen, Lista visitados) {
        Cola verticesFaltantes = new Cola();
        Object objetoVert;
        NodoVert vertActual;
        NodoAdy siguiente;
        visitados.insertar(nodoOrigen.getElem(), visitados.longitud() + 1);
        verticesFaltantes.poner(nodoOrigen.getElem());
        while (!verticesFaltantes.esVacia()) {
            objetoVert = verticesFaltantes.obtenerFrente();
            verticesFaltantes.sacar();
            vertActual = buscarVertice(this.inicio, objetoVert);
            siguiente = vertActual.getPrimerAdy();
            while (siguiente != null) {
                if (visitados.localizar(siguiente.getVertice().getElem()) < 0) {
                    visitados.insertar(siguiente.getVertice().getElem(), visitados.longitud() + 1);
                    verticesFaltantes.poner(siguiente.getVertice().getElem());
                }
                siguiente = siguiente.getSigAdyacente();
            }
        }
        return visitados;
    }

    public Lista caminoMasCorto(Object origen, Object destino) {
        Lista caminoCorto = new Lista(), listaVisitados = new Lista();
        NodoVert nodoOrigen = buscarVertice(this.inicio, origen), nodoDestino = buscarVertice(this.inicio, destino);
        if (!esVacio() && nodoOrigen != null && nodoDestino != null) {
            if (origen.equals(destino)) {
                caminoCorto.insertar(destino, caminoCorto.longitud() + 1);
            } else {
                while (nodoOrigen != null) {
                    if (caminoCorto.localizar(nodoOrigen.getElem()) < 0) {
                        caminoCorto = caminoCortoAux(nodoOrigen, nodoDestino, listaVisitados, caminoCorto);
                    }
                    nodoOrigen = nodoOrigen.getSigVertice();
                }
            }
        }
        return caminoCorto;
    }

    private Lista caminoCortoAux(NodoVert nodoOrigen, NodoVert nodoDestino, Lista visitados, Lista caminoCorto) {
        Cola verticesFaltantes = new Cola();
        Object objetoVert;
        NodoVert vertActual;
        NodoAdy siguiente;
        Boolean encontrado = false;
        visitados.insertar(nodoOrigen.getElem(), visitados.longitud() + 1);
        caminoCorto.insertar(nodoOrigen.getElem(), caminoCorto.longitud() + 1);
        verticesFaltantes.poner(nodoOrigen.getElem());
        while (!verticesFaltantes.esVacia()) {
            objetoVert = verticesFaltantes.obtenerFrente();
            verticesFaltantes.sacar();
            vertActual = buscarVertice(this.inicio, objetoVert);
            siguiente = vertActual.getPrimerAdy();
            while (siguiente != null && !encontrado) {
                if (visitados.localizar(siguiente.getVertice().getElem()) < 0) {
                    visitados.insertar(siguiente.getVertice().getElem(), visitados.longitud() + 1);
                    caminoCorto.insertar(siguiente.getVertice().getElem(), caminoCorto.longitud() + 1);
                    verticesFaltantes.poner(siguiente.getVertice().getElem());
                }
                siguiente = siguiente.getSigAdyacente();
            }
        }
        return visitados;
    }

    public Lista caminoMenorTiempo(Object origen, Object destino) {
        Lista caminoMenor = new Lista();

        return caminoMenor;
    }

    public Lista caminoMasCorto(Object ciudadA, Object ciudadB, Object ciudadC) {
        return null;
    }

    @SuppressWarnings("rawtypes")
    public ArrayList todosLosCaminos() {
        return null;
    }
}
