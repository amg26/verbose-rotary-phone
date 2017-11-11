import java.util.Random;

public class Todd extends Entity {
    Random rand;
    public Todd(Position pos, int speed){
        super(pos, speed, false, 10, 100, 100, 0, 0);
        rand = new Random();
    }

    public void randomWalk(){
        movePolar(5*rand.nextDouble(), (2*3.141592)*rand.nextDouble());
    }

}
