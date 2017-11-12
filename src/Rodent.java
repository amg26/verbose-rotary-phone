import java.util.ArrayList;

public class Rodent extends Entity {
    public Rodent(Map map, Position pos, int maxspeed, boolean gender) {
        super(map, pos, maxspeed, gender, 7, 2, 100, 1000, 1000, maxspeed);
    }

    public void tick(ArrayList<Entity> closeEntities) {
        super.tick(closeEntities);
        //randomDisappear () - because rodents hide in the underground
    }

}
