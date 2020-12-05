package arbolb;

public class Keyy {

    private int llave;
    private long pos;

    public Keyy(int llave, long pos) {
        this.llave = llave;
        this.pos = pos;
    }

    public int getLlave() {
        return llave;
    }

    public void setLlave(int llave) {
        this.llave = llave;
    }

    public long getPos() {
        return pos;
    }

    public void setPos(long pos) {
        this.pos = pos;
    }

    @Override
    public String toString() {
        return "Keyy{" + "llave=" + llave + ", pos=" + pos + '}';
    }
    
    

}
