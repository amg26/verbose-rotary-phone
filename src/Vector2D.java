import java.util.ArrayList;

public class Vector2D {
    double magnitude;
    double direction;

    /*
    * TODO - Flip base properties of vector2d from magnitude&direction to x&y
    * cuz would be more efficient methinks
    * */
    public Vector2D(double magnitude, double direction){
        this.magnitude = magnitude;
        this.direction = direction;
    }

    public Vector2D(double x, double y, boolean distinguishingTag){
        this(Math.sqrt(Math.pow(x,2)+Math.pow(y,2)),Math.atan2(y,x));
    }

    public Vector2D(Position tail, Position tip){
        this(tip.getX()-tail.getX(), tip.getY()-tip.getY(), false);
    }
    //the useful one, this returns a vector in direction of target of specified magnitude
    public Vector2D(double magnitude, Position pos, Position target){
        this(magnitude, Math.atan2(target.getY()-pos.getY(), target.getX()-pos.getX()));
    }

    public double getXComponent(){
        return magnitude*Math.cos(direction);
    }
    public double getYComponent(){
        return magnitude*Math.sin(direction);
    }

    public Vector2D add(Vector2D other){
        return new Vector2D(this.getXComponent()+other.getXComponent(), this.getYComponent()+other.getYComponent(), false);
    }

    public Vector2D subtract(Vector2D other){
        return new Vector2D(this.getXComponent()-other.getXComponent(), this.getYComponent()-other.getYComponent(),false);
    }

    public static Vector2D sumAll(ArrayList<Vector2D> vectorsToSum){
        Vector2D sum = new Vector2D(0,0);
        for(Vector2D v : vectorsToSum){
            sum = sum.add(v);
        }
        return sum;
    }

    public boolean isZero(){
        if(Math.abs(magnitude) < 0.0001)
            return true;
        else
            return false;
    }
    public double getMagnitude(){return magnitude;}
    public double getDirection(){return direction;}
    public String toString(){
        return "magnitude: "+magnitude+", direction:"+direction;
    }

}
