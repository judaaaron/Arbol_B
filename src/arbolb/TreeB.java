package arbolb;

public class TreeB {

    int t;// orden del arbol
    Nodo raiz;

    public TreeB(int orden) {
        raiz = new Nodo();
        this.t = (int) Math.ceil((orden + 1) / 2);// responsabilizar a Jose.
    }

    public TreeB(int orden, int llave) {
        this.t = (int) Math.floor((orden + 1) / 2);// responsabilizar a Jose.
        raiz = new Nodo(llave);

    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public int upperBKeys() {
        return 2 * t - 1;
    }

    public int lowerBKeys() {

        return t - 1;
    }

    public int UpperBChild() {
        return 2 * t;
    }

    public void insert(int Llave) {
        Nodo r = raiz;
        System.out.println(r.getLlaves().size());
        System.out.println(raiz.getLlaves().size());
        if (!r.getLlaves().isEmpty() && r.getLlaves().size() == upperBKeys()) {
            Nodo nodo_temp = new Nodo(Llave);
            raiz = nodo_temp;
            B_Tree_Split_Child(nodo_temp, 1, r);
            B_Tree_Insert_NonFull(r, Llave);
        } else {
            B_Tree_Insert_NonFull(r, Llave);
        }
    }

    public void B_Tree_Split_Child(Nodo x, int i, Nodo y) {
        Nodo z = new Nodo();
        for (int j = 0; j < t-1; j++) {
            z.getLlaves().add(j, y.getLlaves().get(j + t));
        }
        if (!y.leaf()) {
            for (int j = 0; j < t; j++) {
                z.getHijos().add(j, y.getHijos().get(j + t));
            }
        }
        for (int j = x.getLlaves().size() + 1; j > i + 1; j--) {
            x.getHijos().add(j + 1, x.getHijos().get(j));
        }
        x.getHijos().add(i-1, z);
        
        for (int j = x.getLlaves().size(); j > i; j--) {
            x.getLlaves().add(j + 1, x.getLlaves().get(j));
        }
        x.getLlaves().add(i, y.getLlaves().get(t));

    }

    public void B_Tree_Insert_NonFull(Nodo x, int k) {
        int i = x.getLlaves().size()-1;
        if (x.leaf()) {
            while (i >= 1 && k < x.getLlaves().get(i)) {
                x.getLlaves().add(i + 1, x.getLlaves().get(i));
                i--;
            }
            x.getLlaves().add(i+1,k);

        } else {
            while (i >= 1 && k < x.getLlaves().get(i)) {
                i--;
            }
            i++;

            if (x.getHijos().get(i-2).getLlaves().size() == 2 * t - 1) {
                B_Tree_Split_Child(x, i, x.getHijos().get(i));

                if (k > x.getLlaves().get(i)) {
                    i++;
                }
            }

            B_Tree_Insert_NonFull(x.getHijos().get(i-2), k);
        }

    }

    public void imprimir_arbol(Nodo nodo_actual, int num) {
        //se debe iniciar num en 0 a la hora de llamar el metodo
        for (int i = 0; i < nodo_actual.getLlaves().size(); i++) {
            if (nodo_actual.getLlaves().get(i) != null) {
                System.out.println("llave: " + nodo_actual.getLlaves().get(i) + ", nivel:" + num);
            }
        }
        //si no es hoja, se llama el metodo recursivo pero ahora con su hijo
        if (nodo_actual.leaf() == false) {
            for (int i = 0; i < nodo_actual.getHijos().size(); i++) {
                if (nodo_actual.getHijos().get(i) != null) {
                    imprimir_arbol(nodo_actual.getHijos().get(i), num + 1);
                }
            }
        }
    }

}
