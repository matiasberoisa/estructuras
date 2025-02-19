public class TestDigrafo {
    public static void main(String[] args) {
        Digrafo grafito = new Digrafo();
        grafito.insertarVertice("L");
        grafito.insertarVertice("A");
        grafito.insertarVertice("B");
        grafito.insertarVertice("C");
        grafito.insertarVertice("D");
        grafito.insertarVertice("E");
        grafito.insertarVertice("F");
        grafito.insertarVertice("G");
        grafito.insertarVertice("H");
        grafito.insertarVertice("I");

        grafito.insertarArco("A", "B", 1);
        grafito.insertarArco("B", "C", 2);
        grafito.insertarArco("E", "A", 3);
        grafito.insertarArco("H", "C", 7);
        grafito.insertarArco("E", "F", 4);
        grafito.insertarArco("E", "G", 5);
        grafito.insertarArco("I", "E", 6);
        grafito.insertarArco("I", "D", 8);
        grafito.insertarArco("E", "D", 9);
        grafito.insertarArco("I", "C", 10);
        grafito.insertarArco("B", "D", 11);
        grafito.insertarArco("L", "F", 12);

        System.out.println(grafito.toString());

        System.out.println(grafito.caminosDePesoEntre("A", "I", 1, 10));
    }
}
