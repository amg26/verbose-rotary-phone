import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.sqrt;

/*
What should a generic entity have?

-position
-an instance of the Map, probably a bad way to do this
-basic movement (every object should be able to be moved i'd say, like if an animal pushes a rock or something)
    -direction
    -speed
    -speed modifier
    -precalculated piOver8 lelelel

*/
/*
TODO:
Consumable interface?

 */

public abstract class Entity {
    protected Position pos;
    protected Map map;
    protected double direction, maxSpeed;
    protected double speed, speedModifier=0;

    double sightRadius = 10;

    static final double piOver8 = Math.PI/8.0;


    public Entity(Map map, Position pos, double direction) {
        this.pos = pos;
        this.map = map;
        this.direction = direction;
        this.speed = maxSpeed;

        direction = 2*Math.PI*RNG.getInstance().nextDouble();
    }
    //based on needs + want to reproduce + not die
    public void tick(ArrayList<Entity> closeEntities) {
        //empty
    }





    public void movePolar(double r, double theta){
        Position initialPos = new Position(pos);

        //NE CCW to E
        if(theta>piOver8 && theta < 3*piOver8)
            speedModifier = map.slope(initialPos, 1, 1);
        else if(theta>3*piOver8 && theta < 5*piOver8)
            speedModifier = map.slope(initialPos, 1, 0);
        else if(theta>5*piOver8 && theta < 7*piOver8)
            speedModifier = map.slope(initialPos, 1, -1);
        else if(theta>7*piOver8 && theta < 9*piOver8)
            speedModifier = map.slope(initialPos, 0, -1);
        else if(theta>9*piOver8 && theta < 11*piOver8)
            speedModifier = map.slope(initialPos, -1, -1);
        else if(theta>11*piOver8 && theta < 13*piOver8)
            speedModifier = map.slope(initialPos, -1, 0);
        else if(theta>13*piOver8 && theta < 15*piOver8)
            speedModifier = map.slope(initialPos, -1, 1);
        else
            speedModifier = map.slope(initialPos, 0, 1);

        double nextElevation = 0;

        //HAHAHAHA IDK
        if((initialPos.getX()<1 || initialPos.getX()>map.getMap().length*5-1) || (initialPos.getY()<1 || initialPos.getY()>map.getMap()[0].length*5-1)) {
            if (theta > piOver8 && theta < 3 * piOver8)
                nextElevation = map.getElevation(initialPos, 1, 1);
            else if (theta > 3 * piOver8 && theta < 5 * piOver8)
                nextElevation = map.getElevation(initialPos, 1, 0);
            else if (theta > 5 * piOver8 && theta < 7 * piOver8)
                nextElevation = map.getElevation(initialPos, 1, -1);
            else if (theta > 7 * piOver8 && theta < 9 * piOver8)
                nextElevation = map.getElevation(initialPos, 0, -1);
            else if (theta > 9 * piOver8 && theta < 11 * piOver8)
                nextElevation = map.getElevation(initialPos, -1, -1);
            else if (theta > 11 * piOver8 && theta < 13 * piOver8)
                nextElevation = map.getElevation(initialPos, -1, 0);
            else if (theta > 13 * piOver8 && theta < 15 * piOver8)
                nextElevation = map.getElevation(initialPos, -1, 1);
            else if ((initialPos.getX() < 5 || initialPos.getX() > map.getMap().length * 5 - 5) || (initialPos.getY() < 5 || initialPos.getY() > map.getMap()[0].length * 5 - 5))
                nextElevation = map.getElevation(initialPos, 0, 1);
        }



        //if(nextElevation<Map.WATER_DEPTH){
        //    theta+=(Math.PI);
        //    direction+=Math.PI;
        //}


        //System.out.println(speedModifier  + ",<altered, "+speed*(speedModifier/1000)*r*Math.cos(theta) +" Direction:"+direction);
        //if(speedModifier == -3.14159){
        //    return;
        //}



        pos.addX(((speed/5)*r*Math.cos(theta)));//-Math.abs(speed*(speedModifier/10))*r*Math.cos(theta));
        pos.addY(((speed/5)*r*Math.sin(theta)));//-Math.abs(speed*(speedModifier/10))*r*Math.sin(theta));
        //pos.addX(speed);
        //pos.addY(speed);

        //altered by terrain

    }
    //public void move(Position target){

    //}

    public void move(Position target){
        double tempx = target.getX() - pos.getX();
        double tempy = target.getY() - pos.getY();
        direction = Math.atan2(tempy,tempx);

        if(direction<0){
            direction+=(2*Math.PI);
        }else if(direction > Math.PI){
            direction-=(2*Math.PI);
        }
        movePolar(speed, direction);
    }

    //replace void with ArrayList<Entity> vv
    public ArrayList<Entity> scan(int sightradius){
        entitiesWithinRadius(sightradius);
        return null;

    }

    public ArrayList<Entity> entitiesWithinRadius(int test){
        return null;
    }

    public Position getPosition(){
        return pos;
    }
    public double getX(){
        return getPosition().getX();
    }
    public double getY(){
        return getPosition().getY();
    }

    //all entities must be able to 'see' i guess for entitiesWithinRadius to work
    public double getSightRadius(){
        return sightRadius;
    }


}
