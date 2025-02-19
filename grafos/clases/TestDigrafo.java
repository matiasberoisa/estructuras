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
        grafito.insertarArco("A", "E", 3);
        grafito.insertarArco("C", "H", 7);
        grafito.insertarArco("E", "F", 4);
        grafito.insertarArco("E", "G", 5);
        grafito.insertarArco("E", "I", 6);
        grafito.insertarArco("D", "I", 8);
        grafito.insertarArco("E", "D", 9);
        grafito.insertarArco("C", "I", 0);
        grafito.insertarArco("B", "D", 11);
        grafito.insertarArco("F", "L", 12);

        System.out.println(grafito.toString());

        System.out.println(grafito.caminosDePesoEntre("A", "I", 1, 10));
    }
}
