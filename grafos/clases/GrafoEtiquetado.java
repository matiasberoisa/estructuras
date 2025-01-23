
public class GrafoEtiquetado {

    private NodoVert inicio;

    public GrafoEtiquetado() {
        inicio = null;
    }

    public boolean insertarVertice(Object nuevoVert) {
        boolean encontrado = false;
        NodoVert aux = this.ubicarVertice(nuevoVert);
        if (aux == null) {
            this.inicio = new NodoVert(nuevoVert, this.inicio, null);
            encontrado = true;
        }
        return encontrado;
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

    public boolean insertarArco(Object origen, Object dest, Object etiqueta) {
        boolean encontrado = false;
        NodoVert nodoOrigen = buscarVertice(this.inicio, origen);
        NodoVert nodoDestino = buscarVertice(this.inicio, dest);
        if (nodoOrigen != null && nodoDestino != null) {
            encontrado = true;
            if (origen.equals(dest)) {
                crearEnlace(nodoOrigen, nodoDestino, etiqueta);
            } else {
                crearEnlace(nodoOrigen, nodoDestino, etiqueta);
                crearEnlace(nodoDestino, nodoOrigen, etiqueta);
            }

        }
        return encontrado;
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
        NodoAdy siguiente = new NodoAdy(nodoDestino, null, etiqueta);
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
        boolean encontrado = false;
        NodoVert nodoBuscado = buscarVertice(this.inicio, eliminado), nodoSiguiente = this.inicio.getSigVertice(),
                anterior = this.inicio;
        NodoAdy siguiente, nuevoInicio;
        if (!esVacio() && nodoBuscado != null) {
            encontrado = true;
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
        return encontrado;
    }

    public boolean eliminarArco(Object origen, Object destino, Object etiqueta) {
        boolean encontrado = false;
        NodoVert nodoOrigen = buscarVertice(this.inicio, origen);
        NodoVert nodoDestino = buscarVertice(this.inicio, destino);
        if (nodoOrigen != null && nodoDestino != null && existeArcoConEtiqueta(nodoOrigen, nodoDestino, etiqueta)) {
            encontrado = true;
            if (origen.equals(destino)) {
                eliminarEnlace(nodoOrigen, nodoDestino, etiqueta);
            } else {
                eliminarEnlace(nodoOrigen, nodoDestino, etiqueta);
                eliminarEnlace(nodoDestino, nodoOrigen, etiqueta);
            }

        }
        return encontrado;
    }

    private boolean existeArcoConEtiqueta(NodoVert nodoOrigen, NodoVert nodoDestino, Object etiqueta) {
        boolean encontrado = false;
        NodoAdy siguiente = nodoOrigen.getPrimerAdy();
        if (nodoOrigen != null && nodoDestino != null) {
            while (siguiente != null && !encontrado) {
                if (siguiente.getVertice().getElem().equals(nodoDestino.getElem())
                        && siguiente.getEtiqueta().equals(etiqueta)) {
                    encontrado = true;
                }
                siguiente = siguiente.getSigAdyacente();
            }
        }
        return encontrado;
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
        boolean encontrado = false;
        if (this.inicio != null && buscarVertice(this.inicio, buscado) != null) {
            encontrado = true;
        }
        return encontrado;
    }

    public boolean existeArco(Object origen, Object destino) {
        boolean encontrado = false;
        NodoVert nodoOrigen = buscarVertice(this.inicio, origen);
        NodoVert nodoDestino = buscarVertice(this.inicio, destino);
        if (nodoOrigen != null && nodoDestino != null) {
            encontrado = buscarArco(nodoOrigen.getPrimerAdy(), nodoDestino);
        }
        return encontrado;
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
        Lista listaVisitados = new Lista(), caminoCorto = new Lista();
        NodoVert nodoOrigen = buscarVertice(this.inicio, origen), nodoDestino = buscarVertice(this.inicio, destino);
        if (!esVacio() && nodoOrigen != null && nodoDestino != null) {
            if (origen.equals(destino)) {
                caminoCorto.insertar(destino, caminoCorto.longitud() + 1);
            } else {
                caminoCorto = caminoCortoDesde(nodoOrigen, nodoDestino, listaVisitados, caminoCorto);
            }
        }
        return caminoCorto;
    }

    private Lista caminoCortoDesde(NodoVert n, NodoVert destino, Lista camino, Lista caminoCorto) {
        if (n != null) {
            boolean encontrado = false;
            camino.insertar(n.getElem(), camino.longitud() + 1);
            NodoAdy siguiente = n.getPrimerAdy();
            while (siguiente != null && !encontrado) {
                if (siguiente.getVertice().getElem().equals(destino.getElem())) {
                    encontrado = true;
                    if (caminoCorto.longitud() == 0 || camino.longitud() < caminoCorto.longitud() - 1) {
                        caminoCorto = camino.clone();
                        caminoCorto.insertar(destino.getElem(), camino.longitud() + 1);
                    }
                } else {
                    if (camino.localizar(siguiente.getVertice().getElem()) < 0) {
                        if (camino.longitud() < caminoCorto.longitud() - 1 || camino.longitud() == 1
                                || caminoCorto.longitud() == 0) {
                            caminoCorto = caminoCortoDesde(siguiente.getVertice(), destino, camino, caminoCorto);
                            camino.eliminar(camino.longitud());
                        }

                    }
                }
                siguiente = siguiente.getSigAdyacente();
            }
        }
        return caminoCorto;
    }

    public Lista caminoMenorTiempo(Object origen, Object destino) {
        Lista caminoCorto = new Lista(), listaVisitados = new Lista();
        Double tiempo = 0.0;
        Double[] menorTiempo = new Double[1];
        menorTiempo[0] = 0.0;
        NodoVert nodoOrigen = buscarVertice(this.inicio, origen), nodoDestino = buscarVertice(this.inicio, destino);
        if (!esVacio() && nodoOrigen != null && nodoDestino != null) {
            if (origen.equals(destino)) {
                caminoCorto.insertar(destino, caminoCorto.longitud() + 1);
            } else {
                caminoCorto = caminoMenorTiempoDesde(nodoOrigen, nodoDestino, tiempo, menorTiempo, listaVisitados,
                        caminoCorto);
            }
        }
        return caminoCorto;
    }

    private Lista caminoMenorTiempoDesde(NodoVert nodoOrigen, NodoVert nodoDestino, Double tiempo,
            Double[] menorTiempo, Lista camino, Lista caminoCorto) {
        if (nodoOrigen != null) {
            boolean encontrado = false;
            camino.insertar(nodoOrigen.getElem(), camino.longitud() + 1);
            NodoAdy siguiente = nodoOrigen.getPrimerAdy();
            while (siguiente != null && !encontrado) {
                if (siguiente.getVertice().getElem().equals(nodoDestino.getElem())) {
                    encontrado = true;
                    tiempo += (int) siguiente.getEtiqueta();
                    if (caminoCorto.longitud() == 0 || menorTiempo[0] == 0 || tiempo < menorTiempo[0]) {
                        caminoCorto = camino.clone();
                        menorTiempo[0] = tiempo;
                        caminoCorto.insertar(nodoDestino.getElem(), caminoCorto.longitud() + 1);
                    }
                } else {
                    if (camino.localizar(siguiente.getVertice().getElem()) < 0) {
                        tiempo += (int) siguiente.getEtiqueta();
                        if (menorTiempo[0] == 0 || tiempo < menorTiempo[0] || camino.longitud() == 1) {
                            caminoCorto = caminoMenorTiempoDesde(siguiente.getVertice(), nodoDestino, tiempo,
                                    menorTiempo, camino,
                                    caminoCorto);
                            camino.eliminar(camino.longitud());
                        }

                    }

                }
                siguiente = siguiente.getSigAdyacente();
            }
        }
        return caminoCorto;
    }

    public Lista caminoMasCortoSinCiudad(Object origen, Object destino, Object evitada) {
        Lista listaVisitados = new Lista(), caminoCorto = new Lista();
        NodoVert nodoOrigen = buscarVertice(this.inicio, origen), nodoDestino = buscarVertice(this.inicio, destino),
                nodoEvitar = buscarVertice(this.inicio, evitada);
        if (!esVacio() && nodoOrigen != null && nodoDestino != null && !evitada.equals(origen)
                && !evitada.equals(destino)) {
            if (origen.equals(destino)) {
                caminoCorto.insertar(destino, caminoCorto.longitud() + 1);
            } else {
                if (nodoEvitar == null) {
                    caminoCorto = caminoCortoDesde(nodoOrigen, nodoDestino, listaVisitados, caminoCorto);
                } else {
                    caminoCorto = caminoCortoAux(nodoOrigen, nodoDestino, nodoEvitar, listaVisitados, caminoCorto);
                }
            }
        }
        return caminoCorto;
    }

    private Lista caminoCortoAux(NodoVert nodoOrigen, NodoVert nodoDestino, NodoVert ciudadEvitada, Lista camino,
            Lista caminoCorto) {
        if (nodoOrigen != null) {
            boolean encontrado = false;
            camino.insertar(nodoOrigen.getElem(), camino.longitud() + 1);
            NodoAdy siguiente = nodoOrigen.getPrimerAdy();
            while (siguiente != null && !encontrado) {
                if (!siguiente.getVertice().getElem().equals(ciudadEvitada.getElem())) {
                    if (siguiente.getVertice().getElem().equals(nodoDestino.getElem())) {
                        encontrado = true;
                        if (caminoCorto.longitud() == 0 || camino.longitud() < caminoCorto.longitud() - 1) {
                            caminoCorto = camino.clone();
                            caminoCorto.insertar(nodoDestino.getElem(), caminoCorto.longitud() + 1);
                        }
                    } else {
                        if (camino.localizar(siguiente.getVertice().getElem()) < 0) {
                            if (camino.longitud() < caminoCorto.longitud() - 1 || camino.longitud() == 1
                                    || caminoCorto.longitud() == 0) {
                                caminoCorto = caminoCortoAux(siguiente.getVertice(), nodoDestino, ciudadEvitada, camino,
                                        caminoCorto);
                                camino.eliminar(camino.longitud());
                            }

                        }
                    }
                }
                siguiente = siguiente.getSigAdyacente();
            }
        }
        return caminoCorto;
    }

    public Lista todosLosCaminos(Object origen, Object destino, Object opcion) {
        Lista listaVisitados = new Lista(), caminos = new Lista(), listadoCaminos = new Lista();
        NodoVert nodoOrigen = buscarVertice(this.inicio, origen), nodoDestino = buscarVertice(this.inicio, destino);
        if (!esVacio() && nodoOrigen != null && nodoDestino != null) {
            if (opcion == null) {
                listadoCaminos = todosCaminos(nodoOrigen, nodoDestino, listaVisitados, caminos, listadoCaminos);
            } else {
                if (opcion.equals("True")) {
                    listadoCaminos = todosCaminosConAlojamiento(nodoOrigen, nodoDestino, true,
                            listaVisitados, caminos, listadoCaminos);
                } else {
                    listadoCaminos = todosCaminosPorCiudad(nodoOrigen, nodoDestino, opcion, listaVisitados, caminos,
                            listadoCaminos);
                }
            }

        }
        return listadoCaminos;

    }

    private Lista todosCaminos(NodoVert nodoOrigen, NodoVert nodoDestino, Lista listaVisitados, Lista camino,
            Lista listadoCaminos) {
        if (nodoOrigen != null) {
            int posDestino = 0, posElemento = 0;
            camino.insertar(nodoOrigen.getElem(), camino.longitud() + 1);
            listaVisitados.insertar(nodoOrigen.getElem(), listaVisitados.longitud() + 1);
            if (nodoOrigen.getElem().equals(nodoDestino.getElem())) {
                listadoCaminos.insertar(camino.toString(), listadoCaminos.longitud() + 1);
            } else {
                NodoAdy siguiente = nodoOrigen.getPrimerAdy();
                while (siguiente != null) {
                    if (listaVisitados.localizar(siguiente.getVertice().getElem()) < 0) {
                        posDestino = listaVisitados.localizar(nodoDestino.getElem());
                        if (posDestino > 0) {
                            listaVisitados.eliminar(posDestino);
                        }
                        listadoCaminos = todosCaminos(siguiente.getVertice(), nodoDestino, listaVisitados, camino,
                                listadoCaminos);
                        Object elemNodo = camino.recuperar(camino.longitud());
                        while (!elemNodo.equals(nodoOrigen.getElem())) {
                            posElemento = listaVisitados.localizar(elemNodo);
                            listaVisitados.eliminar(posElemento);
                            camino.eliminar(camino.longitud());
                            elemNodo = camino.recuperar(camino.longitud());
                        }
                    }
                    siguiente = siguiente.getSigAdyacente();
                }
            }
        }
        return listadoCaminos;
    }

    private Lista todosCaminosPorCiudad(NodoVert nodoOrigen, NodoVert nodoDestino, Object ciudad, Lista listaVisitados,
            Lista camino,
            Lista listadoCaminos) {
        if (nodoOrigen != null) {
            int posDestino = 0, posElemento = 0;
            camino.insertar(nodoOrigen.getElem(), camino.longitud() + 1);
            listaVisitados.insertar(nodoOrigen.getElem(), listaVisitados.longitud() + 1);
            if (nodoOrigen.getElem().equals(nodoDestino.getElem())) {
                if (camino.localizar(ciudad) > 0) {
                    listadoCaminos.insertar(camino.toString(), listadoCaminos.longitud() + 1);
                }
            } else {
                NodoAdy siguiente = nodoOrigen.getPrimerAdy();
                while (siguiente != null) {
                    if (listaVisitados.localizar(siguiente.getVertice().getElem()) < 0) {
                        posDestino = listaVisitados.localizar(nodoDestino.getElem());
                        if (posDestino > 0) {
                            listaVisitados.eliminar(posDestino);
                        }
                        listadoCaminos = todosCaminosPorCiudad(siguiente.getVertice(), nodoDestino, ciudad,
                                listaVisitados,
                                camino,
                                listadoCaminos);
                        Object elemNodo = camino.recuperar(camino.longitud());
                        while (!elemNodo.equals(nodoOrigen.getElem())) {
                            posElemento = listaVisitados.localizar(elemNodo);
                            listaVisitados.eliminar(posElemento);
                            camino.eliminar(camino.longitud());
                            elemNodo = camino.recuperar(camino.longitud());
                        }
                    }
                    siguiente = siguiente.getSigAdyacente();
                }
            }
        }
        return listadoCaminos;
    }

    private Lista todosCaminosConAlojamiento(NodoVert nodoOrigen, NodoVert nodoDestino, Object ciudad,
            Lista listaVisitados,
            Lista camino,
            Lista listadoCaminos) {
        if (nodoOrigen != null) {
            int posDestino = 0, posElemento = 0;
            camino.insertar(nodoOrigen.getElem(), camino.longitud() + 1);
            listaVisitados.insertar(nodoOrigen.getElem(), listaVisitados.longitud() + 1);
            if (nodoOrigen.getElem().equals(nodoDestino.getElem())) {
                if (camino.localizar(ciudad) > 0) {
                    listadoCaminos.insertar(camino.toString(), listadoCaminos.longitud() + 1);
                }
            } else {
                NodoAdy siguiente = nodoOrigen.getPrimerAdy();
                while (siguiente != null) {
                    if (listaVisitados.localizar(siguiente.getVertice().getElem()) < 0) {
                        posDestino = listaVisitados.localizar(nodoDestino.getElem());
                        if (posDestino > 0) {
                            listaVisitados.eliminar(posDestino);
                        }
                        listadoCaminos = todosCaminosPorCiudad(siguiente.getVertice(), nodoDestino, ciudad,
                                listaVisitados,
                                camino,
                                listadoCaminos);
                        Object elemNodo = camino.recuperar(camino.longitud());
                        while (!elemNodo.equals(nodoOrigen.getElem())) {
                            posElemento = listaVisitados.localizar(elemNodo);
                            listaVisitados.eliminar(posElemento);
                            camino.eliminar(camino.longitud());
                            elemNodo = camino.recuperar(camino.longitud());
                        }
                    }
                    siguiente = siguiente.getSigAdyacente();
                }
            }
        }
        return listadoCaminos;
    }

}
