import java.util.ArrayList;

public class Zebra extends Entity{
    public Zebra(Position pos, int speed, boolean gender){
        super(pos, speed, gender, 10, 3, 100, 0, 0);
    }
    public ArrayList<Entity> see(int sightradius){
        entitiesWithinRadius(sightradius);
        return null;
    }

}
