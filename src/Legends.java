import java.awt.*;

/**
 * Created by chales on 11/6/2017.
 */
public class Legends {

    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive;            //a boolean to denote if the hero is alive or dead.
    public Rectangle rec;
    public boolean isCrashing;
    public int maxSize = 100;

    // METHOD DEFINITION SECTION

    // Constructor Definition
    // A constructor builds the object when called and sets variable values.


    //This is a SECOND constructor that takes 3 parameters.  This allows us to specify the hero's name and position when we build it.
    // if you put in a String, an int and an int the program will use this constructor instead of the one above.
    public Legends(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx =-4;
        dy =-5;
        width = 100;
        height = 50;
        isAlive = true;
        rec = new Rectangle(xpos, ypos, width, height);
        isCrashing = false;
    } // constructor

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;
        rec = new Rectangle(xpos, ypos, width, height);

    }
    public void bounce(){
        if(xpos>900){
            dx = -dx;

        }
        ///make them bounce off the walls nesw
        if(xpos<-50){
            dx= -dx;
        }
        if(ypos>600){
            dy= -dy;
        }
        if(ypos<0){
            dy= -dy;
        }
        xpos = xpos + dx;
        ypos = ypos + dy;
        rec = new Rectangle(xpos, ypos, width, height);
        if(width>maxSize){
            width=maxSize;
        }
    }
    public void wrap(){
///hits east wall
        if(xpos>1000){
            xpos = 0;

        }
        if (xpos < 0){
            xpos = 1000;
        }
        if (ypos > 700) {
            ypos = 0;
        }
        if (ypos < 0){
            ypos = 700;
        }

        xpos = xpos + dx;
        ypos = ypos + dy;
        rec = new Rectangle(xpos, ypos, width, height);
    }
}







