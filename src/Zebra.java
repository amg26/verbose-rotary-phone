import java.util.ArrayList;

public class Zebra extends Entity{
    public Zebra(Map map, Position pos, int maxspeed, boolean gender){
        super(map, pos, maxspeed, gender, 10, 3, 100, 100, 100, maxspeed);
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
    public void reproduce(Entity e){

    }
}
