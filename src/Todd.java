import java.util.Random;

public class Todd extends Entity {
    Random rand;
    public Todd(Position pos, int speed){
        super(pos, speed, false, 10, 100, 100, 0, 0,speed);
        rand = new Random();
    }

    @Override
    public void tick(){
        //super.tick();
        randomWalk();
    }
    public void randomWalk(){
        movePolar(speed*rand.nextDouble(), (2*3.141592)*rand.nextDouble());
    }

}
