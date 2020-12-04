package arbolb;

public class TreeB {

    int orden;
    Nodo raiz;

    public TreeB() {
    }

    public TreeB(int orden, int llave) {
        this.orden = orden;
        raiz = new Nodo(llave);

    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public int upperBKeys() {

        return orden - 1;
    }

    public int lowerBKeys() {

        return (orden - 1) / 2;
    }

}
