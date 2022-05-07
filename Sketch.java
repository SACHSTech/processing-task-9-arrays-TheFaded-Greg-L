import processing.core.PApplet;

public class Sketch extends PApplet {
	
  int screenSize = 800;
  // global variables
  float[] snowX = new float[screenSize/10];
  float[] snowY = new float[screenSize/10];
  boolean[] snowVisible = new boolean[screenSize/10];
  float snowWidth = screenSize/40;
  float snowHeight = screenSize/40;
  boolean speedUp;
  boolean slowDown;
  double fallSpeedChanger = 1;
  float fallSpeed = 1;
	
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(screenSize, screenSize);
    for (int i = 0; i < snowY.length; i++){

      snowY[i] = random(screenSize);
      snowX[i] = random(screenSize);
      snowVisible[i] = true;
    }
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    background(210, 255, 173);
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    background(0);
	  drawSnow();
    text(fallSpeed, 100, 25);
  }
  
  // define other methods down here.
  public void drawSnow(){

    if (slowDown && fallSpeed >= 0.5){

      fallSpeedChanger = 0.99;
      fallSpeed *= fallSpeedChanger;
    }
    if (speedUp && fallSpeed <= 10){

      fallSpeedChanger = 1.01;
      fallSpeed *= fallSpeedChanger;
    }

    for (int i = 0; i < snowY.length; i++){
      if (snowVisible[i]){     
        fill(255);
        ellipse(snowX[i], snowY[i], snowWidth, snowHeight);
      }

      snowY[i] += fallSpeed;

      if (snowY[i] > screenSize + 5){

        snowY[i] = 0 - snowHeight;
        snowX[i] = random(screenSize);
      }
    }
  }

  public void keyPressed(){

    if (keyCode == UP){

      slowDown = true;
      fill(255);
      text("snowDown True", 25, 25);
    }
    if (keyCode == DOWN){

      speedUp = true;
      fill(255);
      text("speedUp True", 25, 25);
    }
  }

  public void keyReleased(){

    if (keyCode == UP){

      slowDown = false;
    }
    if (keyCode == DOWN){

      speedUp = false;
    }
  }
}