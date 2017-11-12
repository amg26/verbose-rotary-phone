import java.util.ArrayList;
import java.util.Random;

public class Todd extends Entity {
    Random rand;
    public Todd(Map map, Position pos, double speed){
        super(map, pos, 1, false, 50, 100, 100, 100, 100,1);
        rand = new Random();
    }

    //@Override
    //public void tick(ArrayList<Entity> closeEntities){
    //    super.ti;
        //randomWalk();
    //}
    public void randomWalk(){
        movePolar(speed*rand.nextDouble(), (2*3.141592)*rand.nextDouble());
    }

}
