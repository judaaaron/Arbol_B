package arbolb;

import java.util.ArrayList;

public class Nodo {

    //boolean esHoja;
    ArrayList<Integer> llaves;
    ArrayList<Nodo> hijos;
    int n;
    boolean leaf;

    public Nodo(int m) {
        llaves = new ArrayList<>();
        hijos = new ArrayList<>();
        for (int i = 0; i < m-1; i++) {
            llaves.add(0);
            hijos.add(null);
        }
        hijos.add(null);
        n = 0;
        leaf = true;
    }

    public Nodo(int m, int llave) {
        llaves = new ArrayList<>(m-1);
        hijos = new ArrayList<>(m);
        llaves.set(0, llave);
        n = 1;
        leaf = true;
    }

//    public boolean isEsHoja() {
//        return esHoja;
//    }
//
//    public void setEsHoja(boolean esHoja) {
//        this.esHoja = esHoja;
//    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }
    
    public ArrayList<Integer> getLlaves() {
        return llaves;
    }

    public void setLlaves(ArrayList<Integer> llaves) {
        this.llaves = llaves;
    }

    public ArrayList<Nodo> getHijos() {
        return hijos;
    }

    public void setHijos(ArrayList<Nodo> hijos) {
        this.hijos = hijos;
    }

    /*public boolean leaf() {
        return hijos.isEmpty();
    }*/

}
