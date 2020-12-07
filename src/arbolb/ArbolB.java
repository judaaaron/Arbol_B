package arbolb;

import java.util.Scanner;

public class ArbolB {

    static Scanner leer = new Scanner(System.in);

    public static void main(String[] args) {

        TreeB tb = new TreeB(4);
        int n = 0;
        for (int i = 1; i <= 20; i++) {
            try {

                n = i;

            } catch (Exception e) {
                break;
            }
            tb.insert(n);
        }
        tb.imprimir_arbol(tb.raiz, 0);
        NodoIndice ni = tb.B_Tree_Search(tb.getRaiz(), 12);
        
        System.out.println("Nodo: " + ni.getNodo() + "| Indice: " + ni.getIndice());

    }

}
