package arbolb;

import java.util.Scanner;

public class ArbolB {

    static Scanner leer = new Scanner(System.in);

    public static void main(String[] args) {

        TreeB tb = new TreeB(5);
        int n;
        while (true) {
            try {
                n = leer.nextInt();
                
            } catch (Exception e) {
                break;
            }
            tb.insert(n);
        }
        tb.imprimir_arbol(tb.raiz, 0);

    }

}
