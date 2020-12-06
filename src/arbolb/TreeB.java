package arbolb;

public class TreeB {

    int m;// orden del arbol
    Nodo raiz;

    public TreeB(int orden) {
        this.m = orden;
        raiz = new Nodo(m);
        //this.t = (int) Math.ceil((orden + 1) / 2);// responsabilizar a Jose.
    }

    public TreeB(int orden, int llave) {
        //this.t = (int) Math.floor((orden + 1) / 2);// responsabilizar a Jose.
        this.m = orden;
        raiz = new Nodo(m,llave);

    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public int upperBKeys() {
        return m-1;
    }

    public int lowerBKeys() {

        return m/2;
    }

    public int UpperBChild() {
        return m;
    }

    public void insert(int k) {
        Nodo r = raiz;
        //System.out.println(r.getLlaves().size());
        //System.out.println(raiz.getLlaves().size());
        if (r.getN() == upperBKeys()) {
            Nodo s = new Nodo(m);
            raiz = s;
            s.setLeaf(false);
            s.setN(0);
            s.getHijos().set(0, r);
            B_Tree_Split_Child(s, 0, r);
            B_Tree_Insert_NonFull(s, k);
        } else {
            B_Tree_Insert_NonFull(r, k);
        }
    }

    public void B_Tree_Split_Child(Nodo x, int i, Nodo y) {
        Nodo z = new Nodo(m);
        z.setLeaf(y.isLeaf());
        z.setN(this.upperBKeys()-this.lowerBKeys()-1);
        for (int j = 0; j < z.getN(); j++) {
            z.getLlaves().set(j, y.getLlaves().get(j + this.lowerBKeys()+1));
        }
        if (!y.isLeaf()) {
            for (int j = 0; j < this.lowerBKeys()+1; j++) {
                z.getHijos().set(j, y.getHijos().get(j + this.lowerBKeys()));
            }
        }
        y.setN(this.lowerBKeys());
        /*for (int j = x.getN(); j >= i+1; j--) {
            x.getHijos().set(j+1, x.getHijos().get(j));
        }
        x.getHijos().set(i+1, z);
        */
        x.getHijos().add(i+1, z);
        x.getHijos().remove(m);
        
        x.getLlaves().add(i, y.getLlaves().get(this.lowerBKeys()));
        x.getLlaves().remove(this.upperBKeys());
        
        x.setN(x.getN()+1);

    }

    public void B_Tree_Insert_NonFull(Nodo x, int k) {
        int i = x.getN()-1;
        if (x.isLeaf()) {
            while (i >= 0 && k < x.getLlaves().get(i)) {
                i--;
            }
            x.getLlaves().add(i+1,k);
            x.getLlaves().remove(this.upperBKeys());
            x.setN(x.getN()+1);
        } else {
            while (i >= 0 && k < x.getLlaves().get(i)) {
                i--;
            }
            i++;

            if (x.getHijos().get(i).getN() == this.upperBKeys()) {
                B_Tree_Split_Child(x, i, x.getHijos().get(i));
                if (k > x.getLlaves().get(i)) {
                    i++;
                }
            }
            B_Tree_Insert_NonFull(x.getHijos().get(i), k);
        }

    }

    public void imprimir_arbol(Nodo nodo_actual, int num) {
        //se debe iniciar num en 0 a la hora de llamar el metodo
        for (int i = 0; i < nodo_actual.getN(); i++) {
            if (nodo_actual.getLlaves().get(i) != null) {
                System.out.println("llave: " + nodo_actual.getLlaves().get(i) + ", nivel:" + num);
            }
        }
        //si no es hoja, se llama el metodo recursivo pero ahora con su hijo
        if (nodo_actual.isLeaf() == false) {
            for (int i = 0; i < nodo_actual.getN()+1; i++) {
                if (nodo_actual.getHijos().get(i) != null) {
                    imprimir_arbol(nodo_actual.getHijos().get(i), num + 1);
                }
            }
        }
    }

}
