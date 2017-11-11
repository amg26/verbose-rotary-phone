public class Map {
    private int[][] map;

    public Map(int width, int height) {
        map = new int[height][width];

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                map[i][j] = 10;
            }
        }
    }

    public int getElevation(int x, int y) {
        // TODO: fix.
        return map[y][x];
    }

    public int getElevation(Position pos) {
        return map[(int) pos.getY()][(int) pos.getX()];
    }

    public void procedurallyGenerate() {

    }
}
