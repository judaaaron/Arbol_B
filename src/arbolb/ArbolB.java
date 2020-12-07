package arbolb;

import java.util.Scanner;

public class ArbolB {

    static Scanner leer = new Scanner(System.in);

    public static void main(String[] args) {
        int orden;
        System.out.println("Ingrese el orden");
        orden = leer.nextInt();
        TreeB tb = new TreeB(orden);
        int n = 0;
        boolean flag = true;
        System.out.println("Inserte más de "+orden+" numeros");
        
        while(flag){
        // (int i = 1; i <= 16; i++) {
            System.out.println("Ingrese el numero que desea insertar");
            int i;
            i = leer.nextInt();
            try {

                n = i;

            } catch (Exception e) {
                break;
            }
            tb.insert(n);
            System.out.println("Desea seguir insertando? \n1. Si \n2. No");
            int res;
            res = leer.nextInt();
            if(res==2){
                flag = false;
            }
        }
        tb.imprimir_arbol(tb.raiz, 0);
        /*NodoIndice ni = tb.B_Tree_Search(tb.getRaiz(), 8);
        
        System.out.println("Nodo: " + ni.getNodo() + "| Indice: " + ni.getIndice());*/

    }

}
