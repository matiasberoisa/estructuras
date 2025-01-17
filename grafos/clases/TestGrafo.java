
public class TestGrafo {
        public static void main(String[] args) {
                GrafoEtiquetado grafito = new GrafoEtiquetado();
                Lista lista = new Lista();

                System.out.println("imprimimos un grafo vacio\t\n" + grafito.toString());

                System.out.println("insertamos el vertice A esperamos rta true\t" + grafito.insertarVertice("A"));
                System.out.println("insertamos el vertice B esperamos rta true\t" + grafito.insertarVertice("B"));
                System.out.println("insertamos el vertice C esperamos rta true\t" + grafito.insertarVertice("C"));
                System.out.println("insertamos el vertice D esperamos rta true\t" + grafito.insertarVertice("D"));
                System.out.println("insertamos el vertice E esperamos rta true\t" + grafito.insertarVertice("E"));
                System.out.println("insertamos el vertice F esperamos rta true\t" + grafito.insertarVertice("F"));
                System.out.println("insertamos el vertice G esperamos rta true\t" + grafito.insertarVertice("G"));
                System.out.println("insertamos el vertice H esperamos rta true\t" + grafito.insertarVertice("H"));
                System.out.println("insertamos el vertice I esperamos rta true\t" + grafito.insertarVertice("I"));
                System.out.println("mostramos un grafo con cuatro vertices no conectados entre si");
                System.out.println(grafito.toString());

                System.out.println("intentamos agregar un vertice repetido esperamos rta false\t"
                                + grafito.insertarVertice("A"));
                System.out.println(grafito.toString());

                System.out.println("ya probamos todos los casos del metodo INSERTAR VERTICE");
                System.out.println(
                                "--------------------------------------------------------------------------------------------------------------------------------");

                System.out.println("agregamos un arco con etiqueta '1' que va desde A hasta B esperamos rta true\t\t"
                                + grafito.insertarArco("A", "B", 1));
                System.out.println("agregamos un arco con etiqueta '2' que va desde B hasta C esperamos rta true\t\t"
                                + grafito.insertarArco("B", "C", 2));
                System.out.println("agregamos un arco con etiqueta '3' que va desde E hasta A esperamos rta true\t\t"
                                + grafito.insertarArco("E", "A", 3));
                System.out.println("agregamos un arco con etiqueta '7' que va desde H hasta C esperamos rta true\t\t"
                                + grafito.insertarArco("H", "C", 7));
                System.out.println("agregamos un arco con etiqueta '4' que va desde E hasta F esperamos rta true\t\t"
                                + grafito.insertarArco("E", "F", 4));
                System.out.println("agregamos un arco con etiqueta '5' que va desde E hasta G esperamos rta true\t\t"
                                + grafito.insertarArco("E", "G", 5));
                System.out.println("agregamos un arco con etiqueta '6' que va desde I hasta E esperamos rta true\t\t"
                                + grafito.insertarArco("I", "E", 6));
                System.out.println("agregamos un arco con etiqueta '8' que va desde I hasta D esperamos rta true\t\t"
                                + grafito.insertarArco("I", "D", 8));

                System.out.println("mostramos un grafo con 9 vertices y 8 arcos");
                System.out.println(grafito.toString());

                lista = grafito.profundidad();
                System.out.println(lista.toString());
                System.out.println(grafito.existeCamino("A", "I"));
        }
}
