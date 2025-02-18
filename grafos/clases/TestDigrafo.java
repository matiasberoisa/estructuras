public class TestDigrafo {
    public static void main(String[] args) {
        Digrafo grafo = new Digrafo();
        grafo.insertarVertice("A");
        grafo.insertarVertice("B");
        grafo.insertarVertice("C");
        grafo.insertarVertice("D");
        grafo.insertarVertice("E");
        grafo.insertarVertice("F");

        grafo.insertarArco("A", "B");
        grafo.insertarArco("B", "D");
        grafo.insertarArco("A", "F");
        grafo.insertarArco("D", "E");
        grafo.insertarArco("E", "C");
        grafo.insertarArco("C", "A");
        grafo.insertarArco("F", "C");
        grafo.insertarArco("B", "E");

        System.out.println(grafo.toString());

        grafo.eliminarVertice("E");

        System.out.println(grafo.toString());
    }
}
