
public class TestGrafo {
    public static void main(String[] args) {
        GrafoEtiquetado g1 = new GrafoEtiquetado();
        System.out.println("muestro un grafo vacio");
        System.out.println(g1.toString());
        System.out.println("inserto un vertice en el grafo");
        g1.insertarVertice(1);
        System.out.println(g1.toString());
        System.out.println("elimino el vertice");
        g1.eliminarVertice(1);
        System.out.println(g1.toString());
        System.out.println("agrego vertices");
        g1.insertarVertice(6);
        g1.insertarVertice(5);
        g1.insertarVertice(4);
        g1.insertarVertice(3);
        g1.insertarVertice(2);
        System.out.println(g1.toString());
        System.out.println("agrego arcos a los vertices");
        System.out.println(g1.insertarArco(2, 3, 100));

        System.out.println(g1.toString());
        System.out.println("elimino un arco");
        System.out.println(g1.eliminarArco(2, 3, 100));
        g1.insertarArco(2, 4, 200);
        g1.insertarArco(3, 6, 300);
        g1.insertarArco(4, 5, 400);
        g1.insertarArco(6, 2, 500);
        g1.insertarArco(3, 5, 600);
        System.out.println(g1.toString());
        System.out.println("metodo existe un vertice");
        System.out.println("un vertice que se encuentre en el grafo: 5");
        System.out.println(g1.existeVertice(5));
        System.out.println("un vertice que NO se encuentre en el grafo: 8");
        System.out.println(g1.existeVertice(8));
        System.out.println(g1.listarEnProfundidad());
    }
}
