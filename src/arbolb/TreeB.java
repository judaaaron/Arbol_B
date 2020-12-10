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
        raiz = new Nodo(m, llave);

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
        return m - 1;
    }

    public int lowerBKeys() {

        return m / 2;
    }

    public int UpperBChild() {
        return m;
    }

    public NodoIndice B_Tree_Search(Nodo x, int k) {

        int i = 0;

        while (i < x.getN() && k > x.getLlaves().get(i)) {
            i++;
        }

        if (i < x.getN() && k == x.getLlaves().get(i)) {
            return new NodoIndice(x, i);
        }
        if (x.isLeaf()) {
            return null;
        } else {
            return B_Tree_Search(x.getHijos().get(i), k);
        }
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
        z.setN(this.upperBKeys() - this.lowerBKeys() - 1);
        for (int j = 0; j < z.getN(); j++) {
            z.getLlaves().set(j, y.getLlaves().get(j + this.lowerBKeys() + 1));
        }
        if (!y.isLeaf()) {
            for (int j = 0; j < z.getN() + 1; j++) {
                z.getHijos().set(j, y.getHijos().get(j + this.lowerBKeys() + 1));
            }
        }
        y.setN(this.lowerBKeys());
        x.getHijos().add(i + 1, z);
        x.getHijos().remove(m);

        x.getLlaves().add(i, y.getLlaves().get(this.lowerBKeys()));
        x.getLlaves().remove(this.upperBKeys());

        x.setN(x.getN() + 1);

    }

    public void B_Tree_Insert_NonFull(Nodo x, int k) {
        int i = x.getN() - 1;
        if (x.isLeaf()) {
            while (i >= 0 && k < x.getLlaves().get(i)) {
                i--;
            }
            x.getLlaves().add(i + 1, k);
            x.getLlaves().remove(this.upperBKeys());
            x.setN(x.getN() + 1);
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
    
    public Nodo Preceding_Child(Nodo x, int k){
        int iy = x.getLlaves().indexOf(k);
        Nodo y = x.getHijos().get(iy);
        return y;
    }
    
    public Nodo Successor_Child(Nodo x, int k){
        int iz = x.getLlaves().indexOf(k)+1;
        Nodo z = x.getHijos().get(iz);
        return z;
    }
    
    public Nodo Find_Sibling (Nodo x){
        return null;
    }
    
    public Nodo Find_Root(Nodo x) {
        return null;
    }
    
    public void Move_Key(int k, Nodo n1, Nodo n2){
        
    }
    
    public void Merge_Nodes(Nodo n1, Nodo n2) {
        
    }
    
    public int Find_Predecessor_Key(Nodo n, int k) {
        Nodo y = this.Preceding_Child(n, k);
        int k1 = y.getLlaves().get(y.getN()-1);
        return k1;
    }
    
    public int Find_Successor_Key(Nodo n, int k) {
        Nodo z = this.Successor_Child(n, k);
        int k1 = z.getLlaves().get(0);
        return k1;
    }
    
    public int Root_Key(Nodo n) {
        return 0;
    }
    
    public void Remove_Key(Nodo n, int k) {
        
    }
    
    public void B_Tree_Delete_Key(Nodo x,int k){
        
        if(!x.isLeaf()){
            Nodo y = this.Preceding_Child(x,k);
            Nodo z = this.Successor_Child(x,k);
            if(y.getN() > this.lowerBKeys()){
                int k1 = this.Find_Predecessor_Key(x, k);
                this.Move_Key(k1, y, x);
                this.Move_Key(k, x, z);
                this.B_Tree_Delete_Key(z, k);
            } else if(z.getN() > this.lowerBKeys()) {
                int k1 = this.Find_Successor_Key(x, k);
                this.Move_Key(k1, z, x);
                this.Move_Key(k, x, y);
                this.B_Tree_Delete_Key(y, k);
            } else {
                this.Move_Key(k, x, y);
                this.Merge_Nodes(y, z);
                this.B_Tree_Delete_Key(y, k);
            }
        } else {
            Nodo y = this.Preceding_Child(x,k);
            Nodo z = this.Successor_Child(x,k);
            Nodo w = this.getRaiz();
            int v = this.Root_Key(x);
            if(x.getN() > this.lowerBKeys()){
                this.Remove_Key(x, k);
            } else if (y.getN() > this.lowerBKeys()) {
                int k1 = this.Find_Predecessor_Key(w, v);
                this.Move_Key(k1, y, w);
                k1 = this.Find_Successor_Key(w, v);
                this.Move_Key(k1, w, x);
                this.B_Tree_Delete_Key(x, k);
            } else {
                Nodo s = this.Find_Sibling(w);
                Nodo w1 = (new TreeB(this.getM(),this.Root_Key(s))).getRaiz();
                if(w1.getN() == this.lowerBKeys()) {
                    this.Merge_Nodes(w1, w);
                    this.Merge_Nodes(w, s);
                    this.B_Tree_Delete_Key(x, k);
                } else {
                    this.Move_Key(v, w, x);
                    this.B_Tree_Delete_Key(x, k);
                }
            }
            
        }
    }

    public void imprimir_arbol(Nodo nodo_actual, int num) {
        //se debe iniciar num en 0 a la hora de llamar el metodo
        String indent = new String(new char[1024]).replace('\0', ' ');
        for (int i = 0; i < nodo_actual.getN(); i++) {
            if (nodo_actual.isLeaf() == false && nodo_actual.getHijos().get(i) != null) {
                imprimir_arbol(nodo_actual.getHijos().get(i), num + 1);
            }
            if (nodo_actual.getLlaves().get(i) != null) {
                System.out.println(indent.substring(0, num * 4) + "llave: " + nodo_actual.getLlaves().get(i) + ", nivel:" + num);
            }

        }
        //si no es hoja, se llama el metodo recursivo pero ahora con su hijo
        if (nodo_actual.isLeaf() == false) {
            imprimir_arbol(nodo_actual.getHijos().get(nodo_actual.getN()), num + 1);
        }
    }

}
