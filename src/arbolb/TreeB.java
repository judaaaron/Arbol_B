package arbolb;

public class TreeB {

    int m;// orden del arbol
    int T;
    Nodo raiz;

    public TreeB(int orden) {
        this.m = orden;
        this.T = Math.floorDiv(orden,2);
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

        return (m/2) - 1;
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
    
    private void Remove(Nodo x, int key) {
        int pos = x.getLlaves().subList(0, x.n).indexOf(key);
        if (pos != -1) {
            if (x.isLeaf()) {
                int i = 0;
                for (i = 0; i < x.getN() && x.getLlaves().get(i) != key; i++) {
                }
                ;
                for (; i < x.getN(); i++) {
                    if (i != 2 * T - 2) {
                        x.getLlaves().set(i, x.getLlaves().get(i+1));
                    }
                }
                x.setN(x.getN()-1);
                return;
            }
            if (!x.isLeaf()) {

                Nodo pred = x.getHijos().get(pos);
                int predKey = 0;
                if (pred.getN() >= T) {
                    for (;;) {
                        if (pred.isLeaf()) {
                            //System.out.println(pred.getN());
                            predKey = pred.getLlaves().get(pred.getN()-1);
                            break;
                        } else {
                            pred = pred.getHijos().get(pred.getN());
                        }
                    }
                    Remove(pred, predKey);
                    x.getLlaves().set(pos, predKey);
                    return;
                }

                Nodo nextNode = x.getHijos().get(pos+1);
                if (nextNode.n >= T) {
                    int nextKey = nextNode.getLlaves().get(0);
                    if (!nextNode.isLeaf()) {
                        nextNode = nextNode.getHijos().get(0);
                        for (;;) {
                            if (nextNode.leaf) {
                                nextKey = nextNode.getLlaves().get(nextNode.getN()-1);
                                break;
                            } else {
                                nextNode = nextNode.getHijos().get(nextNode.getN());
                            }
                        }
                    }
                    Remove(nextNode, nextKey);
                    x.getLlaves().set(pos, nextKey);
                    return;
                }

                int temp = pred.getN() + 1;
                pred.getLlaves().set(pred.n++,x.getLlaves().get(pos));
                for (int i = 0, j = pred.getN(); i < nextNode.getN(); i++) {
                    pred.getLlaves().set(j++, nextNode.getLlaves().get(i));
                    pred.n++;
                }
                for (int i = 0; i < nextNode.n + 1; i++) {
                    pred.getHijos().set(temp++, nextNode.getHijos().get(i));
                }

                x.getHijos().set(pos, pred);
                for (int i = pos; i < x.n; i++) {
                    if (i != 2 * T - 2) {
                        x.getLlaves().set(i, x.getLlaves().get(i+1));
                    }
                }
                for (int i = pos + 1; i < x.n + 1; i++) {
                    if (i != 2 * T - 1) {
                        x.getHijos().set(i, x.getHijos().get(i+1));
                    }
                }
                x.n--;
                if (x.n == 0) {
                    if (x == raiz) {
                        raiz = x.getHijos().get(0);
                    }
                    x = x.getHijos().get(0);
                }
                Remove(pred, key);
                return;
            }
        } else {
            for (pos = 0; pos < x.n; pos++) {
                if (x.getLlaves().get(pos) > key) {
                    break;
                }
            }
            Nodo tmp = x.getHijos().get(pos);
            if (tmp.n >= T) {
                Remove(tmp, key);
                return;
            }
            if (true) {
                Nodo nb = null;
                int devider = -1;

                if (pos != x.n && x.getHijos().get(pos+1).n >= T) {
                    devider = x.getLlaves().get(pos);
                    nb = x.getHijos().get(pos+1);
                    x.getLlaves().set(pos, nb.getLlaves().get(0));
                    tmp.getLlaves().set(tmp.n++, devider);
                    tmp.getHijos().set(tmp.n, nb.getHijos().get(0));
                    for (int i = 1; i < nb.n; i++) {
                        nb.getLlaves().set(i-1, nb.getLlaves().get(i));
                    }
                    for (int i = 1; i <= nb.n; i++) {
                        nb.getHijos().set(i-1, nb.getHijos().get(i));
                    }
                    nb.n--;
                    Remove(tmp, key);
                    return;
                } else if (pos != 0 && x.getHijos().get(pos-1).n >= T) {

                    devider = x.getLlaves().get(pos-1);
                    nb = x.getHijos().get(pos-1);
                    x.getLlaves().set(pos-1, nb.getLlaves().get(nb.n-1));
                    Nodo child = nb.getHijos().get(nb.n);
                    nb.n--;

                    for (int i = tmp.n; i > 0; i--) {
                        tmp.getLlaves().set(i, tmp.getLlaves().get(i-1));
                    }
                    tmp.getLlaves().set(0, devider);
                    for (int i = tmp.n + 1; i > 0; i--) {
                        tmp.getHijos().set(i, tmp.getHijos().get(i-1));
                    }
                    tmp.getHijos().set(0, child);
                    tmp.n++;
                    Remove(tmp, key);
                    return;
                } else {
                    Nodo lt = null;
                    Nodo rt = null;
                    boolean last = false;
                    if (pos != x.n) {
                        devider = x.getLlaves().get(pos);
                        lt = x.getHijos().get(pos);
                        rt = x.getHijos().get(pos+1);
                    } else {
                        devider = x.getLlaves().get(pos-1);
                        rt = x.getHijos().get(pos);
                        lt = x.getHijos().get(pos-1);
                        last = true;
                        pos--;
                    }
                    for (int i = pos; i < x.n - 1; i++) {
                        x.getLlaves().set(i, x.getLlaves().get(i+1));
                    }
                    for (int i = pos + 1; i < x.n; i++) {
                        x.getHijos().set(i, x.getHijos().get(i+1));
                    }
                    x.n--;
                    lt.getLlaves().set(lt.n++, devider);

                    for (int i = 0, j = lt.n; i < rt.n + 1; i++, j++) {
                        if (i < rt.n) {
                            lt.getLlaves().set(j, rt.getLlaves().get(i));
                        }
                        lt.getHijos().set(j, rt.getHijos().get(i));
                    }
                    lt.n += rt.n;
                    if (x.n == 0) {
                        if (x == raiz) {
                            raiz = x.getHijos().get(0);
                        }
                        x = x.getHijos().get(0);
                    }
                    Remove(lt, key);
                    return;
                }
            }
        }
    }

    public void Remove(int key) {
        Nodo x = this.B_Tree_Search(raiz, key).getNodo();
        if (x == null) {
            return;
        }
        Remove(raiz, key);
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
