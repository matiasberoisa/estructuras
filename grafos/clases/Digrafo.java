public class Digrafo {
    private NodoVert inicio;

    public Digrafo() {
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

    public boolean existeCamino(Object origen, Object destino) {
        boolean encontrado = false;
        Lista listaVisitados = new Lista();
        NodoVert nodoOrigen = buscarVertice(this.inicio, origen);
        NodoVert nodoDestino = buscarVertice(this.inicio, destino);
        if (nodoOrigen != null && nodoDestino != null) {
            encontrado = buscarCamino(nodoOrigen, nodoDestino, listaVisitados);
        }
        return encontrado;
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
                    cad = cad + "[ enlace: " + recorrerAdyacentes.getVertice().getElem() + " ]";
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

    public boolean esVacio() {
        return this.inicio == null;
    }

    public boolean eliminarArco(Object origen, Object destino) {
        NodoVert nodoOrigen = buscarVertice(inicio, origen), nodoDestino = buscarVertice(this.inicio, destino);
        boolean eliminado = false;
        if (!esVacio() && nodoOrigen != null && nodoDestino != null) {
            eliminarEnlace(nodoOrigen, nodoDestino);
            eliminado = true;
        }
        return eliminado;
    }

    public void eliminarEnlace(NodoVert nodoOrigen, NodoVert nodoDestino) {
        boolean eliminado = false;
        NodoAdy siguiente = nodoOrigen.getPrimerAdy(), anterior = null;
        while (siguiente != null && !eliminado) {
            if (siguiente.getVertice().getElem().equals(nodoDestino.getElem())) {
                eliminado = true;
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

    public boolean existeVertice(Object elem) {
        boolean encontrado = false;
        if (!esVacio() && buscarVertice(this.inicio, elem) != null) {
            encontrado = true;
        }
        return encontrado;
    }

    public boolean insertarArco(Object origen, Object destino) {
        NodoVert nodoOrigen = buscarVertice(inicio, origen), nodoDestino = buscarVertice(this.inicio, destino);
        boolean encontrado = false;
        if (!esVacio() && nodoOrigen != null && nodoDestino != null) {
            encontrado = true;
            crearEnlace(nodoOrigen, nodoDestino);
        }
        return encontrado;
    }

    private void crearEnlace(NodoVert nodoOrigen, NodoVert nodoDestino) {
        NodoAdy siguiente = new NodoAdy(nodoDestino, null);
        if (nodoOrigen.getPrimerAdy() == null) {
            nodoOrigen.setPrimerAdy(siguiente);
        } else {
            NodoAdy siguienteAdy = nodoOrigen.getPrimerAdy();
            while (siguienteAdy.getSigAdyacente() != null) {
                siguienteAdy = siguienteAdy.getSigAdyacente();
            }
            siguienteAdy.setSigAdyacente(siguiente);
        }
    }

    public boolean eliminarVertice(Object elem) {
        boolean encontrado = false;
        NodoVert nodoEliminar = buscarVertice(inicio, elem), siguiente = this.inicio, anterior = null;
        NodoAdy ady;
        if (!esVacio() && nodoEliminar != null) {
            encontrado = true;
            while (siguiente != null) {
                ady = siguiente.getPrimerAdy();
                if (siguiente.getElem().equals(nodoEliminar.getElem())) {
                    if (anterior == null) {
                        this.inicio = this.inicio.getSigVertice();
                    } else {
                        anterior.setSigVertice(siguiente.getSigVertice());
                    }
                }
                while (ady != null) {
                    if (ady.getVertice().getElem().equals(nodoEliminar.getElem())) {
                        eliminarEnlace(ady.getVertice(), nodoEliminar);
                    }
                    ady = ady.getSigAdyacente();
                }
                anterior = siguiente;
                siguiente = siguiente.getSigVertice();
            }
        }
        return encontrado;
    }
}
