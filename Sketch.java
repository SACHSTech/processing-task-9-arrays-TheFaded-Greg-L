import processing.core.PApplet;

/**
 * 
 * A program, "Sketch.java", that is used to play around with arrays and processing through the drawing of snow and a player based game.
 * @author G. Lui
 */
public class Sketch extends PApplet {
	

  // global variables
  int screenSize = 800;

    // snow variables
    float[] snowX = new float[screenSize/5];
    float[] snowY = new float[screenSize/5];
    boolean[] snowVisible = new boolean[screenSize/5];
    boolean snowClick;
    float snowWidth = screenSize/40;
    float snowHeight = screenSize/40;
    boolean speedUp;
    boolean slowDown;
    double fallSpeedChanger = 1;
    float fallSpeed = 1;

    // player variables
    boolean playerUp;
    boolean playerLeft;
    boolean playerDown;
    boolean playerRight;

    float playerWidth = snowWidth;
    float playerHeight = snowHeight;
    float playerX = screenSize/2 - playerWidth/2;
    float playerY = screenSize/2 - playerHeight/2;
    int playerLives = 3;

    // menu variables
    int menuScreen = 0;

  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	  // put your size call here
    size(screenSize, screenSize);
    // loops through the arrays and assigns data to each square before the program is executed
    for (int i = 0; i < snowY.length; i++){

      snowY[i] = random(-screenSize, 0);
      snowX[i] = random(screenSize);
      snowVisible[i] = true;
    }
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {

    ellipseMode(CORNER);
    background(210, 255, 173);
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {

    // draw main game
    if (menuScreen == 0){

      background(0);
      playerMovement();
      drawPlayer();
      drawSnow();

      //text(fallSpeed, 100, 25);
      //text(snowClick + "", 100, 40);
    }

    // clear to white when out of lives
    else if (menuScreen != 0){

      background(255);
    }
  }
  
  /**
   * Draws and controls behaviour for interaction of snow.
   * @author G. Lui
   */
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

      // snow movement
      snowY[i] += fallSpeed;

      if (snowY[i] > screenSize + 5){

        snowY[i] = 0 - snowHeight;
        snowX[i] = random(screenSize);
        snowVisible[i] = true;
      }

      // mouse interaction
      if (mouseX >= snowX[i] && mouseX <= snowX[i] + snowWidth && mouseY >= snowY[i] && mouseY <= snowY[i] + snowWidth && snowClick){

        snowVisible[i] = false;
        snowClick = false;
      }

      // player collision
      if (((playerX >= snowX[i] && playerX <= snowX[i] + snowWidth) || (playerX + playerWidth >= snowX[i] && playerX + playerWidth <= snowX[i] + snowWidth))
      && ((playerY >= snowY[i] && playerY <= snowY[i] + snowHeight) || (playerY + playerHeight >= snowY[i] && playerY + playerHeight <= snowY[i] + snowHeight))
      && snowVisible[i]){

        playerLives -= 1;
        snowVisible[i] = false;
      }
    }
  }

  /**
   * Draws and Affects behaviour of the player and other elements such as lives.
   * @author G. Lui
   */
  public void drawPlayer(){

    for (int i = 1; i <= playerLives; i++){

      fill(255, 0, 0);
      rect (i * 30, 25, 25, 25);
    }
    fill(0, 0, 255);
    ellipse(playerX, playerY, playerWidth, playerHeight);

    if (playerLives <= 0){

      menuScreen = 1;
    }
  }

  /**
   * responsible for the way the player moves when a keyboard input is detected.
   * @author G. Lui
   */
  public void playerMovement(){

    if (playerUp){

      playerY -= 2;
    }

    if (playerLeft){
      
      playerX -= 2;
    }

    if (playerDown){

      playerY += 2;
    }

    if (playerRight){

      playerX +=2;
    }
  }

  // keyboard inputs
  public void keyPressed(){

    // snow speed
    if (keyCode == UP){

      slowDown = true;
      fill(255);
      //text("snowDown True", 25, 25);
    }

    if (keyCode == DOWN){

      speedUp = true;
      fill(255);
      //text("speedUp True", 25, 25);
    }

    // player movement
    if (key == 'w'){

      playerUp = true;
    }

    if (key == 'a'){

      playerLeft = true;
    }

    if (key == 's'){

      playerDown = true;
    }

    if (key == 'd'){

      playerRight = true;
    }
  }

  public void keyReleased(){

    // snow speed
    if (keyCode == UP){

      slowDown = false;
    }

    if (keyCode == DOWN){

      speedUp = false;
    }

    // player movement
    if (key == 'w'){

      playerUp = false;
    }

    if (key == 'a'){

      playerLeft = false;
    }

    if (key == 's'){

      playerDown = false;
    }

    if (key == 'd'){

      playerRight = false;
    }
  }
  
  // mouse inputs
  public void mousePressed(){

    snowClick = true;
  }

  public void mouseReleased(){
    
    snowClick = false;
  }
}