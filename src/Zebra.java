import java.util.ArrayList;

public class Zebra extends Entity{
    public Zebra(Map map, Position pos, double maxspeed, boolean gender){
        super(map, pos, 2.6, gender, 30, 3, 100, 100, 100, 2.6);
    }
    public ArrayList<Entity> see(int sightradius){
        // entitiesWithinRadius(sightradius);
        return null;
    }
    public void tick(ArrayList<Entity> closeEntities){
        super.tick(closeEntities);
        //System.out.println("hunger:"+hunger);
        //System.out.println("closeEntities: "+closeEntities);

    }
    public void dance(double vigour){

    }
    public void reproduce(Entity e){

    }
}
