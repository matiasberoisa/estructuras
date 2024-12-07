/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases.estaticas;

/**
 *
 * @author Mati
 */
public class HeapMin {

    @SuppressWarnings("rawtypes")
    private final Comparable[] heap;
    private int ultimo;
    private final int tamaño = 20;

    public HeapMin() {
        this.heap = new Comparable[tamaño];
        this.ultimo = 0;
    }

    @SuppressWarnings("rawtypes")
    public boolean insertar(Comparable elem) {
        boolean exito;
        if (this.ultimo > this.tamaño) {
            exito = false;
        } else {
            this.ultimo++;
            this.heap[ultimo] = elem;
            hacerSubir(this.ultimo);
            exito = true;
        }
        return exito;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void hacerSubir(int ultPos) {
        int posCima = ultPos / 2;
        int i = ultPos;
        Comparable aux;
        boolean salida = false;
        while (!salida && posCima > 0) {
            if (this.heap[i].compareTo(this.heap[posCima]) < 0) {
                aux = this.heap[i];
                this.heap[i] = this.heap[posCima];
                this.heap[posCima] = aux;
                i = posCima;
                posCima = i / 2;
            } else {
                salida = true;
            }
        }
    }

    public boolean eliminarCima() {
        boolean exito;
        if (esVacio()) {
            exito = false;
        } else {
            this.heap[1] = this.heap[ultimo];
            this.ultimo--;
            hacerBajar(1);
            exito = true;
        }
        return exito;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void hacerBajar(int posPadre) {
        int posH;
        Comparable temp = this.heap[posPadre];
        boolean salir = false;
        while (!salir) {
            posH = posPadre * 2;
            if (posH <= this.ultimo) {
                // temp tiene al menos un hijo (izq) y lo considera menor
                if (posH < this.ultimo) {
                    // hijoMenor tiene hermano derecho
                    if (this.heap[posH + 1].compareTo(this.heap[posH]) < 0) {
                        // el hijo derecho es el menor de los dos
                        posH++;
                    }
                }

                // compara al hijo menor con el padre
                if (this.heap[posH].compareTo(temp) < 0) {
                    // el hijo es menor que el padre, los intercambia
                    this.heap[posPadre] = this.heap[posH];
                    this.heap[posH] = temp;
                    posPadre = posH;
                } else {
                    // el padre es menor que sus hijos, esta bien ubicado
                    salir = true;
                }
            } else {
                salir = true;
            }
        }
    }

    public Object recuperarCima() {
        Object cima = null;
        if (!esVacio()) {
            cima = heap[1];
        }
        return cima;
    }

    public boolean esVacio() {
        return (this.ultimo == 0);
    }

    public String toString() {
        String cadena = "";
        if (!esVacio()) {
            cadena += "[";
            for (int i = 1; i <= this.ultimo; i++) {
                cadena += heap[i];
                if (i < this.ultimo) {
                    cadena += ",";
                }
            }
            cadena += "]";
        } else {
            cadena = "cadena vacia";
        }

        return cadena;
    }
}
