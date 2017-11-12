import java.util.ArrayList;
import java.util.Random;

public class Todd extends Entity {
    Random rand;
    public Todd(Map map, Position pos, int speed){
        super(map, pos, speed, false, 10, 100, 100, 0, 0,speed);
        rand = new Random();
    }

    @Override
    public void tick(ArrayList<Entity> closeEntities){
        //super.tick();
        randomWalk();
    }
    public void randomWalk(){
        movePolar(speed*rand.nextDouble(), (2*3.141592)*rand.nextDouble());
    }

}
