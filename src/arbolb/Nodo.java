package arbolb;

import java.util.ArrayList;

public class Nodo {

    boolean esHoja;
    ArrayList<Integer> llaves = new ArrayList();
    ArrayList<Nodo> hijos = new ArrayList();
   

    public Nodo() {
    }

    public Nodo(int llave) {
     llaves.add(llave);   
    }

    public boolean isEsHoja() {
        return esHoja;
    }

    public void setEsHoja(boolean esHoja) {
        this.esHoja = esHoja;
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


}
