import java.util.ArrayList;

public class Lion extends Entity{
    public Lion(Map map, Position pos, double maxspeed, boolean gender){
        super(map, pos, 2.3, gender, 30, 200, 100, 100, 100, 2.3);
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
