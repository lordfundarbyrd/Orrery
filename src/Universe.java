
import java.util.ArrayList;
import java.util.List;
import processing.core.PApplet;
import processing.core.PVector;

public class Universe extends PApplet {

    public static double dT; // amount time updates by each draw loop
    private static float force; // force of each planet
    private static float g; // universal gravitation constant
    private static float r; // distance
    private float sunMass; // mass of the sun
    private float diameter; // diameter of the sun scaled down
    private float time; // time in days
    public double scale; // scaling the program

    public List<Planet> planets; // list containing all the planets

    // for translating sun & planets
    int mX; 
    int mY; 

    float zoom = 1.0F; // zoom amount
    float translatex; // zooming x
    float translatey; // zooming y

    public static void main(String[] args) {
        PApplet.main(new String[]{Universe.class.getName()});
    }

    public void settings() {
        size(3000, 1910, P3D);
    }

    public void setup() {
        background(0);
        scale = .25e9;
        diameter = (1391 / 8);
        
        mX = width/2; // sun starts in middle
        mY = height/2; // sun starts in middle

        planets = new ArrayList(); // arraylist with planets
        //mass,diameter,x,y,z,velocity vector
        planets.add(new Planet((float) 3.285e23, (float) 4.8794, (float) 5.791e10, 0, 0, new PVector(0, -47400, 0))); //mercury
        planets.add(new Planet((float) 4.867e24, (float) 12.104, (float) 1.082e11, 0, 0, new PVector(0, -35020, 0))); //venus
        planets.add(new Planet((float) 5.972e24, (float) 12.742, (float) 1.496e11, 0, 0, new PVector(0, -30000, 0))); //earth
        planets.add(new Planet((float) 227.9e9, (float) 6.794, (float) 2.279e11, 0, 0, new PVector(0, -24000, 0))); //mars
        planets.add(new Planet((float) 1.898e27, (float) 139.82, (float) 7.785e11, 0, 0, new PVector(0, -13100, 0))); //jupiter
        planets.add(new Planet((float) 5.683e26, (float) 116.46, (float) 1.434e12, 0, 0, new PVector(0, -9600, 0))); //saturn
        planets.add(new Planet((float) 8.681e25, (float) 50.724, (float) 2.871e12, 0, 0, new PVector(0, -6800, 0))); //uranus
        planets.add(new Planet((float) 1.024e26, (float) 49.244, (float) 4.495e12, 0, 0, new PVector(0, -5430, 0))); //neptune
    }

    public void draw() {
        time += dT; // update time
        background(0);
        
        fill(255);
        textSize(30);
        text(getDays() + " days", width/4, 1700); // displays time
        text("Click to focus on a different point", width-width/4, 1650);
        text("Press 'enter' to return to the origin", width-width/4, 1700);

        translate(mX, mY); // puts sun in center

         // translates when mouse moves
        translatex += (mouseX - translatex) / 20;
        translatey += (mouseY - translatey) / 20;

        if (mousePressed) { // changes zoom variable
            zoom += 0.01;
        } else {
            zoom -= 0.01;
        }

        zoom = constrain(zoom, 1F, 2000.0F); // keeps zoom between 1 and 2000

        translate(width / 2 - translatex * zoom - 100, height / 2 - translatey * zoom - 100, -50); // translate amount when mouse moves depends on zoom
        scale(zoom); // scales the screen based on the zoom
        
        //rotates screen when zooming
        rotateZ((float) (PI / 9 - zoom + 1.0));
        rotateX((float) (PI / zoom / 8 - 0.125));
        rotateY((float) (zoom / 8 - 0.125));

        translate(-width/2, -height/2, 0); // sun and mouse meet in middle of the screen

        //sun
        lights();
        translate(width / 2, height / 2);
        fill(255, 255, 0);
        stroke(255, 255, 0);
        sphere(diameter);

        pushMatrix(); // mercury
        translate(planets.get(0).position.x / (float) scale, (float) planets.get(0).position.y / (float) scale);
        fill(192, 192, 192);
        stroke(192, 192, 192);
        sphere(((float) (planets.get(0).diameter)));
        popMatrix();

        pushMatrix(); // venus
        translate(planets.get(1).position.x / (float) scale, (float) planets.get(1).position.y / (float) scale);
        fill(238, 232, 170);
        stroke(238, 232, 170);
        sphere(((float) (planets.get(1).diameter)));
        popMatrix();

        pushMatrix(); // earth
        translate(planets.get(2).position.x / (float) scale, (float) planets.get(2).position.y / (float) scale);
        fill(0, 0, 255);
        stroke(0, 0, 255);
        sphere(((float) (planets.get(2).diameter)));
        popMatrix();

        pushMatrix(); // mars
        translate(planets.get(3).position.x / (float) scale, (float) planets.get(3).position.y / (float) scale);
        fill(255, 0, 0);
        stroke(255, 0, 0);
        sphere(((float) (planets.get(3).diameter)));
        popMatrix();

        pushMatrix(); // jupiter
        translate(planets.get(4).position.x / (float) scale, (float) planets.get(4).position.y / (float) scale);
        fill(240, 230, 140);
        stroke(240, 230, 140);
        sphere(((float) (planets.get(4).diameter)));
        popMatrix();

        pushMatrix(); // saturn
        translate(planets.get(5).position.x / (float) scale, (float) planets.get(5).position.y / (float) scale);
        fill(255, 255, 102);
        stroke(255, 255, 102);
        sphere(((float) (planets.get(5).diameter)));
        popMatrix();

        pushMatrix(); // uranus
        translate(planets.get(6).position.x / (float) scale, (float) planets.get(6).position.y / (float) scale);
        fill(240, 248, 255);
        stroke(240, 248, 255);
        sphere(((float) (planets.get(6).diameter)));
        popMatrix();

        pushMatrix(); // neptune
        translate(planets.get(7).position.x / (float) scale, (float) planets.get(7).position.y / (float) scale);
        fill(30, 144, 255);
        stroke(30, 144, 255);
        sphere(((float) (planets.get(7).diameter)));
        popMatrix();

        for (int i = 0; i < planets.size(); i++) {
            planets.get(i).update((int) 86400); // updating planet positions
        }
    }
    
    private double getDays() {
        return time / 86400; // gets amount of days
    }
    
    public void mouseClicked() { //changes origin to see different planets
       mX -= mouseX;
       mY -= mouseY;
    }
    
    public void keyPressed() { //resets when the enter key is pressed
        if (key == ENTER) {
            mX = 1500;
            mY = 955;     
            zoom = 1.0F;
        }
    }
}
