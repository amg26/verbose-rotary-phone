import java.util.ArrayList;

public class Food extends Entity {
    protected int size;
    protected int rank;

    //make a constructor for food
    public Food ( Map map, Position pos, int size, int rank) {
        super(map,pos,0);
        this.speed = 0;
        this.size = size;
        this.rank = 0;
    }
    //rank the food
    public void getEaten (){

        if (size > 0) {size --;}

    }

    @Override
    public void tick(ArrayList<Entity> entities)
    {
        //System.out.println("DONT MOVE PLZ");

    }

    public int getRank(){
        return rank;
    }

    public void regenerate (double size){
        if (size < 1) {size ++;}
    }

    public int getSize(){
        return size;
    }
}