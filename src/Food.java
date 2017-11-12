public class Food extends Entity {
    protected int size;
    protected int rank;

    //make a constructor for food
    public Food ( Map map, Position pos, int size, int rank) {
        super(map,pos,0, false, 0, 0, 0, 0, 0, 0 );

        this.speed = 0;
        this.size = size;
        this.rank = 0;
    }
    //rank the food
    public void getEaten (double size){
        if (size > 1) {size --;}

    }

    public void regenerate (double size){
        if (size < 1) {size ++;}
    }

    public int getSize(){
        return size;
    }
}