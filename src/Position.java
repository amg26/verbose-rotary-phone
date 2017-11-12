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
    public double getDirectionTo(Position p){
        double tempx = p.getX() - getX();
        double tempy = p.getY() - getY();
        return Math.atan2(tempy,tempx);
    }
    public void setX(double val){
        this.x = val;
    }

    public void setY(double val){
        this.y = val;
    }

    public void addX(double val){
        this.x += val;
    }
    public void addY(double val){
        this.y += val;
    }
    public double distanceTo(Position other) {
        return Math.sqrt(Math.pow(x - other.getX(), 2) + Math.pow(y - other.getY(), 2));
    }

}
