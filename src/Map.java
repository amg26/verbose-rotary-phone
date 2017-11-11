import java.util.Random;

public class Map {
    private double[][] map;

    public Map(int width, int height) {
        map = randomMap(width, height);
    }

    public double getElevation(double x, double y) {
        // TODO: fix.
        return map[(int)Math.round(y)][(int)Math.round(x)];

    }

    public double getElevation(Position pos) {
        return getElevation(pos.getY(), pos.getX());
    }

    public void procedurallyGenerate() {

    }

    public double[][] randomMap(int width, int height) {
        double randmap[][] = new double[width][height];
        Random rand = new Random();

        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                //nextDouble gives a val between 0 and 1
                randmap[i][j] = rand.nextDouble()*10;
            }
        }

        return randmap;
    }

    public double[][] testMap(){
        return new double[][]{
            {0,1,0,0,0,0,0,0,0,0},
            {0,0,1,1,0,0,0,0,0,0},
            {0,1,2,1,1,1,1,1,1,0},
            {0,1,2,1,2,2,2,2,1,0},
            {0,1,2,2,2,3,5,2,1,0},
            {0,1,1,1,2,3,4,2,1,0},
            {0,0,0,1,1,2,2,2,1,0},
            {0,0,0,0,1,1,1,1,0,0},
            {0,0,0,0,0,1,1,1,0,0},
            {0,0,0,0,0,0,0,0,0,0}
        };
    }
}
