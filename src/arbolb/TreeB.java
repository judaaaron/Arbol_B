package arbolb;

public class TreeB {
    
    int t;// orden del arbol
    Nodo raiz;
    
    public TreeB() {
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
        if (r.getLlaves().size() == upperBKeys()) {
            Nodo nodo_temp = new Nodo(Llave);
            raiz = nodo_temp;
            //split2(nodo_temp, 1, r
            B_Tree_Split_Child(nodo_temp, 1, r);
            //insert_no_full(nodo_temp, Llave);
        } else {
            // insert_no_full(r, Llave);
        }
    }
    
    public void B_Tree_Split_Child(Nodo x, int i, Nodo y) {
        Nodo z = new Nodo();
        for (int j = 0; j < upperBKeys(); j++) {
            z.getLlaves().set(j, y.getLlaves().get(j + t));
        }
        if (!y.leaf()) {
            for (int j = 0; j < t; j++) {
                z.getHijos().set(j, y.getHijos().get(j + t));
            }
        }
        for (int j = x.getLlaves().size() + 1; j > i + 1; j--) {
            x.getHijos().set(j + 1, x.getHijos().get(j));
        }
        x.getHijos().set(i + 1, z);
        for (int j = x.getLlaves().size(); j > i; j--) {
            x.getLlaves().set(j + 1, x.getLlaves().get(j));
        }
        x.getLlaves().set(i, y.getLlaves().get(t));
        
    }
    
    public void B_Tree_Insert_NonFull(Nodo x, int k) {
        int i = x.getLlaves().size();
        if (x.leaf()) {
            while (i >= 1 && k < x.getLlaves().get(i)) {
                x.getLlaves().set(i + 1, x.getLlaves().get(i));
                i--;
            }
            x.getLlaves().set(i + 1, k);
            
        } else {
            while (i >= 1 && k < x.getLlaves().get(i)) {
                i--;
            }
            i++;
            
            if (x.getHijos().get(i).getLlaves().size() == 2 * t - 1) {
                B_Tree_Split_Child(x, i, x.getHijos().get(i));
                
                if (k > x.getLlaves().get(i)) {
                    i++;
                }
            }
            
            B_Tree_Insert_NonFull(x.getHijos().get(i), k);
        }
        
    }
    
}
