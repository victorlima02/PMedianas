package pMedianas.ambiente;

/**
 *
 * @author Victor de Lima Soares
 */
public class Ponto {
    private final Integer x,y;
    
    public Ponto(Integer x,Integer y){
        this.x=x;
        this.y=y;
    }
    
    double distancia(Ponto outro){
        return Math.sqrt(Math.pow(x-outro.x, 2)+Math.pow(y-outro.y, 2));
    }
}
