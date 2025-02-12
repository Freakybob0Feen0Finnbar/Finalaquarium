//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable {

	//Variable Definition Section
	//Declare the variables used in the program
	//You can set their initial values too

	//Sets the width and height of the program window
	final int WIDTH = 1000;
	final int HEIGHT = 700;

	//Declare the variables needed for the graphics
	public JFrame frame;
	public Canvas canvas;
	public JPanel panel;

	public BufferStrategy bufferStrategy;
	public Image backgroundPic;
	public Image DadPIC;
	public Image Ianbabepic;
	public Image TrophyPic;


	//Declare the objects used in the program
	//These are things that are made up of more than one variable type
	private Legends mrSmith;
	private Legends Ian;
	private Legends Trophy;



	// Main method definition
	// This is the code that runs first and automatically
	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
		new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
	}


	// Constructor Method
	// This has the same name as the class
	// This section is the setup portion of the program
	// Initialize your variables and construct your program objects here.
	public BasicGameApp() {

		setUpGraphics();

		//variable and objects
		//create (construct) the objects needed for the game and load up

		DadPIC = Toolkit.getDefaultToolkit().getImage("DAD.JPG"); //load the picture
		Ianbabepic = Toolkit.getDefaultToolkit().getImage("IanGB.png");
		TrophyPic = Toolkit.getDefaultToolkit().getImage("RealTrophy.png");//load the picture
		backgroundPic = Toolkit.getDefaultToolkit().getImage("Laxfield.jpg");
		mrSmith = new Legends(200,500);
		Ian = new Legends(200,600);
		Trophy = new Legends(10,60);


	}// BasicGameApp()


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

	// main thread22
	// this is the code that plays the game after you set things up
	public void run() {

		//for the moment we will loop things forever.
		while (true) {

			moveThings();  //move all the game objects
			render();  // paint the graphics
			pause(20); // sleep for 10 ms
		}
	}


	public void moveThings()
	{

		//calls the move( ) code in the objects
		mrSmith.bounce();
		Ian.wrap();
		Trophy.bounce();
		collisions();

	}

	public void collisions() {
		if (Trophy.rec.intersects(Ian.rec) && mrSmith.isCrashing == false && mrSmith.isAlive && Ian.isAlive) {
			System.out.println("Champion!!!!");
			mrSmith.dx = -mrSmith.dx;
			mrSmith.dy = -mrSmith.dy;
			mrSmith.isAlive = false;
			Ian.dx = -mrSmith.dx;
			Ian.dy = -mrSmith.dy;
			Ian.width = Ian.width +100;
			Ian.height = Ian.height +100;
			Trophy.width = Trophy.width +5;
			Trophy.height = Trophy.height +5;
			mrSmith.dx = mrSmith.dx +5;
			mrSmith.dy = mrSmith.dy +5;
			mrSmith.isCrashing = true;


		}
		if(!mrSmith.rec.intersects(Ian.rec)){
			mrSmith.isCrashing = false;

		}
		if (Trophy.rec.intersects(Ian.rec) && Trophy.isCrashing == false && Trophy.isAlive && Ian.isAlive) {
			System.out.println("explosion!!!!");
			Trophy.dx = -Trophy.dx;
			Trophy.dy = -Trophy.dy;
			Trophy.isAlive = false;
			Ian.dx = -Trophy.dx;
			Ian.dy = -Trophy.dy;
			Ian.width = Ian.width +2;
			Ian.height = Ian.height +2;
			Trophy.width = Trophy.width +200;
			Trophy.height = Trophy.height +200;
			Trophy.dx = Trophy.dx +1;
			Trophy.dy = Trophy.dy +1;
			Trophy.isCrashing = true;
			//


		}
		if(!Trophy.rec.intersects(Ian.rec)){
			Trophy.isCrashing = false;

		}
	}
	//Pauses or sleeps the computer for the amount specified in milliseconds
	public void pause(int time ){
		//sleep
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {

		}
	}

	//Graphics setup method
	private void setUpGraphics() {
		frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

		panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
		panel.setLayout(null);   //set the layout

		// creates a canvas which is a blank rectangular area of the screen onto which the application can draw
		// and trap input events (Mouse and Keyboard events)
		canvas = new Canvas();
		canvas.setBounds(0, 0, WIDTH, HEIGHT);
		canvas.setIgnoreRepaint(true);

		panel.add(canvas);  // adds the canvas to the panel.

		// frame operations
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
		frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
		frame.setResizable(false);   //makes it so the frame cannot be resized
		frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

		// sets up things so the screen displays images nicely.
		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();
		canvas.requestFocus();
		System.out.println("DONE graphic setup");

	}


	//paints things on the screen using bufferStrategy
	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.drawImage(backgroundPic,  0,  0,WIDTH, HEIGHT, null);

		//draw the image of the mrSmithnaut
		if (mrSmith.isAlive == true) {
			g.drawImage(DadPIC, mrSmith.xpos, mrSmith.ypos, mrSmith.width, mrSmith.height, null);
		}
		g.drawImage(DadPIC, Ian.xpos, Ian.ypos, Ian.width, Ian.height, null);
		g.drawImage(Ianbabepic, Ian.xpos, Ian.ypos, Ian.width, Ian.height, null);
		g.drawImage(TrophyPic, Trophy.xpos, Trophy.ypos, Trophy.width, Trophy.height, null);

		g.dispose();

		bufferStrategy.show();
	}
}