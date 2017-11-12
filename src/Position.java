public class Position {
    private double x;
    private double y;

    public Position(double x, double y) {
        this.x = x;
        this.y  = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distanceTo(Position other) {
        return Math.sqrt(Math.pow(x - other.getX(), 2) + Math.pow(y - other.getY(), 2));
    }

}
