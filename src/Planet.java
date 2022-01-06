
import processing.core.PApplet;
import processing.core.PVector;

public class Planet extends PApplet{
   
    double diameter; // diameter of the planet
    double g; // constant
    double mass; // mass of the planet
    PVector position; // position of the planet
    PVector velocity; // velocity of the planet
    PVector acceleration; // acceleration of the planet
    PVector gravitation; // gravitation of the planet
    PVector sun; // position of the sun
    double force; // force on the planet
    PVector distance; // distance between sun and planet
    PVector unit; // unit vector
    
    public Planet(float mass, float diameter, float x, float y, float z, PVector velocity) {
        g = 6.67e-11;
        this.diameter = diameter/4;
        this.mass=mass;
        position = new PVector(x,y,z);
        distance = new PVector();
        this.velocity = velocity;
        gravitation = new PVector();
        sun = new PVector(1500,955); // sun is in the middle of the screen
    }
    
    public void update(int dT) {
        Universe.dT = dT; // amount time updates each iteration
        distance = PVector.sub(position,sun); // finds distance between position and sun
        force = -(g * mass * 1.989e30)/(Math.pow(distance.mag(),2)); // calculates force
        unit = PVector.div(distance, distance.mag()); // creates unit vector in the direction of the distance
        gravitation = PVector.mult(unit, (float) force); // finds gravitation by multiplying the direction by the force
        acceleration = PVector.div(gravitation, (float) mass); // calculates acceleration by dividing mass by force
        velocity.add(PVector.mult(acceleration,dT)); // adds acceleration to velocity
        position.add(PVector.mult(velocity,dT)); // adds velocity to position
    }
}
