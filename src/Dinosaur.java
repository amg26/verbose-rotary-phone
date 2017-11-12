import java.util.ArrayList;

public class Dinosaur extends Entity {
    public Dinosaur(Map map, Position pos, int maxspeed, boolean gender) {
        super(map, pos, maxspeed, gender, 25, 80, 100, 10000, 10000, maxspeed);
    }

    public void tick(ArrayList<Entity> closeEntities) {
        super.tick(closeEntities);
    }
    public void extinct (){
        //die out after a certain amount of time, doesn't matter health. Also do not reproduce
    }
}
