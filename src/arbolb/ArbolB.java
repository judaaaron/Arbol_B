package arbolb;

import java.util.Scanner;

public class ArbolB {

    static Scanner leer = new Scanner(System.in);

    public static void main(String[] args) {

        TreeB tb = new TreeB(5);
        int n = 0;
        for (int i = 1; i <= 10; i++) {
            try {

                n = i;

            } catch (Exception e) {
                break;
            }
            tb.insert(n);
        }
        tb.imprimir_arbol(tb.raiz, 0);

    }

}
