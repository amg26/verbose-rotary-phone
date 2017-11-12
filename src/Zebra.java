import java.util.ArrayList;

public class Zebra extends Entity{
    public Zebra(Position pos, int maxspeed, boolean gender){
        super(pos, maxspeed, gender, 10, 3, 100, 100000, 100000, maxspeed);
    }
    public ArrayList<Entity> see(int sightradius){
        // entitiesWithinRadius(sightradius);
        return null;
    }
    public void tick(ArrayList<Entity> closeEntities){
        super.tick(closeEntities);

    }
    public void dance(double vigour){

    }
}
