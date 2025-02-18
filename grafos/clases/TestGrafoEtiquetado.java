
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author agusf
 */
public class TestGrafoEtiquetado {
        public static void main(String[] args) {
                GrafoEtiquetado grafito = new GrafoEtiquetado();
                Lista lista = new Lista();
                Lista caminos = new Lista();

                System.out.println("imprimimos un grafo vacio\t\n" + grafito.toString());
                System.out.println("insertamos el vertice A esperamos rta true\t" +
                                grafito.insertarVertice("A"));
                System.out.println("insertamos el vertice B esperamos rta true\t" +
                                grafito.insertarVertice("B"));
                System.out.println("insertamos el vertice C esperamos rta true\t" +
                                grafito.insertarVertice("C"));
                System.out.println("insertamos el vertice D esperamos rta true\t" +
                                grafito.insertarVertice("D"));
                System.out.println("insertamos el vertice E esperamos rta true\t" +
                                grafito.insertarVertice("E"));
                System.out.println("insertamos el vertice F esperamos rta true\t" +
                                grafito.insertarVertice("F"));
                System.out.println("insertamos el vertice G esperamos rta true\t" +
                                grafito.insertarVertice("G"));
                System.out.println("insertamos el vertice H esperamos rta true\t" +
                                grafito.insertarVertice("H"));
                System.out.println("insertamos el vertice I esperamos rta true\t" +
                                grafito.insertarVertice("I"));
                System.out.println("mostramos un grafo con cuatro vertices no conectados entre si");
                System.out.println(grafito.toString());

                System.out.println("intentamos agregar un vertice repetido esperamos rta false\t" +
                                grafito.insertarVertice("A"));
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
                System.out.println(
                                "intentamos agregar un arco con el vertice inicial que no se encuentra en el grafo, esperamos rta false\t\n"
                                                + grafito.insertarArco("Y", "A", 800));
                System.out.println(
                                "intentamos agregar un arco con el vertice final que no se encuentra en el grafo, esperamos rta false\t\n"
                                                + grafito.insertarArco("A", "X", 1500));
                System.out
                                .println(
                                                "intentamos agregar un arco con con vertices que no se encuentran en el grafo, esperamos rta false\t\n"
                                                                + grafito.insertarArco("Z", "X", 420));
                System.out.println("chequeamos que el grafo no haya cambiado");
                System.out.println(grafito.toString());
                System.out.println("ya probamos todos los casos del metodo INSERTAR ARCO");
                System.out.println(
                                "--------------------------------------------------------------------------------------------------------------------------------");
                grafito.vaciar();

                System.out.println("Creamos un nuevo grafo donde con solo 2 nodos y un arco entre si");
                System.out.println("insertamos el vertice A");
                System.out.println("insertamos el vertice B ");

                System.out.println("agregamos un arco con etiqueta '1' que va desde A hasta B esperamos ");
                grafito.insertarVertice("A");
                grafito.insertarVertice("B");
                grafito.insertarArco("A", "B", 1);

                System.out.println("intentaremos eliminar este arco esperamos rta true" +
                                grafito.eliminarArco("A", "B", 1));
                System.out.println(grafito.toString());
                System.out.println("");
                grafito.insertarArco("A", "A", 1);
                System.out.println("agregamos un arco A-A-1 y lo intentamos eliminar " +
                                grafito.eliminarArco("A", "A", 1));
                System.out.println(grafito.toString());
                System.out.println("agregamos un arco con etiqueta '1' que va desde A hasta B esperamos rta true\t\t"
                                + grafito.insertarArco("A", "B", 1));
                System.out.println("agregamos un arco con etiqueta '2' que va desde A hasta B esperamos rta true\t\t"
                                + grafito.insertarArco("A", "B", 2));
                System.out.println(grafito.toString());
                System.out.println("intentamos romper el segundo arco sin afectar el primero ");
                grafito.eliminarArco("A", "B", 2);
                System.out.println(grafito.toString());
                System.out.println("");
                System.out.println("intentamos eliminar un arco con etiqueta inexistente , esperamos rta FALSE"
                                + grafito.eliminarArco("A", "B", 3));
                System.out.println("");
                System.out
                                .println(
                                                "intentamos eliminar un arco donde el vertice  de origen no exista en el grafo esperamos rta false "
                                                                + grafito.eliminarArco("C", "B", 1));
                System.out
                                .println(
                                                "intentamos eliminar un arco donde el vertice de destino no exista en el grafo esperamos rta false "
                                                                + grafito.eliminarArco("A", "C", 1));

                System.out.println("creamos nuevamente el grafo principal e intentamos algunas eliminaciones ");
                grafito.vaciar();

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

                System.out.println(grafito.toString());
                System.out.println("eliminamos el arco de E a A 3 esperamos rta true " +
                                grafito.eliminarArco("E", "A", 3));
                System.out.println(grafito.toString());
                System.out.println("eliminamos el arco de I a D  8 esperamos rta true " +
                                grafito.eliminarArco("I", "D", 8));
                System.out.println(grafito.toString());
                grafito.insertarArco("E", "A", 3);
                grafito.insertarArco("I", "D", 8);
                System.out.println("restauramos el grafo ppal");
                System.out.println("ya probamos todos los casos del metodo ELIMINAR ARCO");
                System.out.println(
                                "--------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("");

                grafito.vaciar();

                System.out.println("Creamos un nuevo grafo donde con solo 2 nodos y un arco entre si");
                System.out.println("insertamos el vertice A");
                System.out.println("insertamos el vertice B ");
                System.out.println("agregamos un arco con etiqueta '1' que va desde A hasta B esperamos ");
                grafito.insertarVertice("A");
                grafito.insertarVertice("B");
                grafito.insertarArco("A", "B", 1);

                System.out.println(grafito.toString());

                System.out.println("eliminamos el vertice B esperamos rta true " + grafito.eliminarVertice("B"));
                System.out.println("esperamos ver el grafo que solo contenga el vertice A ");
                System.out.println(grafito.toString());
                System.out.println("eliminamos el vertice A " + grafito.eliminarVertice("A"));
                System.out.println("esperamos ver un grafo vacio ");
                System.out.println(grafito.toString());
                System.out.println("");

                System.out.println("restauramos el grafo ppal ");
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
                System.out.println(grafito.toString());
                System.out
                                .println("eliminamos un vertices no existente , esperamos rta false  "
                                                + grafito.eliminarVertice("J"));
                System.out.println("eliminamos el vertice con mayor cantidad de enlaces E "
                                + grafito.eliminarVertice("E"));
                System.out.println(grafito.toString());
                System.out.println("ya probamos todos los casos del metodo ELIMINAR VERTICE");
                System.out.println(
                                "--------------------------------------------------------------------------------------------------------------------------------");
                grafito.vaciar();
                grafito.insertarVertice("A");
                System.out.println(grafito.toString());
                // System.out.println(
                // "Creo un grafo de un solo vertice 'A' y lo recorro en profundidad espero Rta
                // :'A'"
                // + grafito.profundidad());
                grafito.insertarVertice("B");
                grafito.insertarArco("A", "B", 1);
                System.out.println(grafito.toString());
                // System.out.println("agrego un vertice B y un arco A-B-1 . recorro espero rta
                // B-A "
                // + grafito.profundidad());
                grafito.insertarVertice("C");
                grafito.insertarVertice("D");
                grafito.insertarVertice("E");
                grafito.insertarArco("B", "C", 2);
                grafito.insertarArco("C", "D", 3);
                grafito.insertarArco("D", "E", 4);
                System.out.println(grafito.toString());
                // System.out
                // .println("incerto vertices C , D , E y conexiones B-C-2 C-D-3 Y D-E-4 y
                // recorro espero rta E D C B A "
                // + grafito.profundidad());

                System.out.println("intento recorrer este grafo en profundidad");
                // lista = grafito.profundidad();
                System.out.println(lista.toString());

                System.out.println(
                                "--------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("METODO CAMINO MAS CORTO DE A A B");
                grafito.vaciar();
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
                grafito.insertarArco("B", "C", 0);
                grafito.insertarArco("E", "A", 3);
                grafito.insertarArco("H", "C", 7);
                grafito.insertarArco("E", "F", 4);
                grafito.insertarArco("E", "G", 5);
                grafito.insertarArco("I", "E", 6);
                grafito.insertarArco("I", "D", 8);
                grafito.insertarArco("E", "D", 9);
                grafito.insertarArco("I", "C", 0);
                grafito.insertarArco("B", "D", 11);
                System.out.println(grafito.toString());

                // System.out.println("metodo anchura desde I : " + grafito.anchura("I"));

                System.out.println("camino mas corto de A a I: " + grafito.caminoMasCorto("A", "I"));
                System.out.println("camino mas corto de A a A: " + grafito.caminoMasCorto("A", "A"));
                System.out.println("camino mas corto de A a B: " + grafito.caminoMasCorto("A", "B"));
                System.out.println("camino mas corto de I a G: " + grafito.caminoMasCorto("I", "G"));
                System.out.println("camino mas corto de M a I: " + grafito.caminoMasCorto("M", "I"));
                System.out.println("camino mas corto de A a P: " + grafito.caminoMasCorto("A", "P"));

                System.out.println(
                                "--------------------------------------------------------------------------------------------------------------------------------");

                System.out.println("camino mas corte de A a I sin pasar por E: "
                                + grafito.caminoMasCortoSinCiudad("A", "I", "E"));
                System.out.println("camino mas corte de A a H sin pasar por I: "
                                + grafito.caminoMasCortoSinCiudad("A", "H", "I"));
                System.out.println("camino mas corte de G a A sin pasar por E: "
                                + grafito.caminoMasCortoSinCiudad("G", "A", "E"));
                System.out.println("camino mas corte de A a B sin pasar por B: "
                                + grafito.caminoMasCortoSinCiudad("A", "B", "B"));
                System.out.println("camino mas corte de C a G sin pasar por D: "
                                + grafito.caminoMasCortoSinCiudad("C", "G", "D"));
                System.out.println("camino mas corte de C a G sin pasar por B: "
                                + grafito.caminoMasCortoSinCiudad("C", "G", "B"));
                System.out.println("camino mas corte de A a I sin pasar por P: "
                                + grafito.caminoMasCortoSinCiudad("A", "I", "P"));

                System.out.println(
                                "--------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("METODO CAMINO MAS CORTO SEGUN ETIQUETA ");
                grafito.vaciar();
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
                System.out.println("camino mas corto con origen y destino inexistentes "
                                + grafito.caminoMenorTiempo("k", "P"));
                System.out.println(
                                "camino mas corto con un vertice origen perteneciente al grafo y un destino inexistente "
                                                + grafito.caminoMenorTiempo("A", "P"));
                System.out.println(
                                "camino mas corto con un vertice origen inexistente en el grafo y un destino  existente "
                                                + grafito.caminoMenorTiempo("P", "A"));
                System.out.println("camino mas corto entre un vertice con sigo mismo (en este caso A) "
                                + grafito.caminoMenorTiempo("A", "A"));
                System.out.println(" ");
                System.out.println("camino mas corto entre dos vertices que solo cuentan con un camino  "
                                + grafito.caminoMenorTiempo("L", "F"));
                System.out.println(" ");
                System.out.println("camino mas corto entre dos vertices con mas de un camino posible de A a I "
                                + grafito.caminoMenorTiempo("A", "I"));
                System.out.println("pruebo este caso con otros vertices");
                System.out.println(" entre A y L " + grafito.caminoMenorTiempo("A", "L"));
                System.out.println("entre F y I " + grafito.caminoMenorTiempo("F", "I"));
                System.out.println(
                                "--------------------------------------------------------------------------------------------------------------------------------");
                caminos = grafito.todosLosCaminos("A", "I", null);
                System.out.println("todos los caminos de A a I: " + caminos.toString());
                /*
                 * System.out.println(
                 * "desea filtrar por alojamiento en ciudad destinos(escriba 1) o que pase por una ciudad(escriba 2)"
                 * );
                 * opcion = dato.nextInt();
                 * if (opcion == 1) {
                 * caminos = grafito.todosLosCaminos("A", "I", true);
                 * } else {
                 * System.out.println("ingrese ciudad que sea incluida en el camino");
                 * ciudad = dato.next();
                 * caminos = grafito.todosLosCaminos("A", "I", ciudad);
                 * System.out.println(caminos.toString());
                 * }
                 */

        }
}
