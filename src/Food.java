public class Food extends Entity {
    protected double size;
    protected int rank;

    //make a constructor for food
    public Food (Position pos, double size, int rank) {
        super(pos,0, false, 0 );
        this.size = size;
        this.rank = rank;
    }
    //rank the food
    public void getEaten (double size){
        if (size > 1) {size --;}

    }

    public void regenerate (double size){
        if (size < 1) {size ++;}
    }
}
