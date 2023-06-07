package tankTitans;

import processing.core.PApplet;
import processing.core.PImage;
import java.awt.*;

/**
 * @author Michael
 */

public class gameOver extends PApplet {
    /* Default */
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final int FPS = 60;

    /* Rounds */
    private boolean is_mainMenu = true;

    /**
     *  Round: Main Menu
     */
    private PImage bg_mainMenu;
    private GUIButton b_playGame = new GUIButton(620, 180, 100, 75, Color.CYAN);
    private GUIButton b_highscore = new GUIButton(620, 400, 100, 75, Color.CYAN);
    private GUIButton b_exit = new GUIButton(620, 500, 100, 75, Color.CYAN);
    private boolean click_playGame = false;
    private int ctr = 0;

//    public static void main(String[] args) {
//        // TODO code application logic here
//        PApplet.main("tankTitans.tankTitans");
//    }

    public gameOver(String[] args){
        this.args = args;
    }

    /**
     * Inisialisasi objek
     * seperti karakter, player, npc, menu, image, dll.
     */
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        /* Backgrounds */
        frameRate(FPS);
        bg_mainMenu = loadImage("src/assets/background/background_1.png");
    }

    /**
     * Main program
     */
    public void draw() {
        if (is_mainMenu) {
            update(mouseX, mouseY, b_playGame);
            background(0, 0, 0);
            fill(255, 245, 248);
            stroke(255, 245, 258);

            subMenu();
            ctr++;

            if (ctr == 120) {
                is_mainMenu = false;
            }
        } else {
            String[] args = {"mainMenu"};
            PApplet.runSketch(args, new tankTitans());
            surface.setVisible(false);
            stop();
        }
    }

    private void subMenu() {
        if (args[0].equals("gameOver")) {
            textSize(128);
//            fill(0, 408, 612);
            textAlign(CENTER);
            text("GAME OVER", 640, 360);
        }
        if (args[0].equals("winnerChickenDinner")) {
            textSize(128);
            textAlign(CENTER);
            text("YOU WIN", 640, 360);
        }
    }

    public void keyPressed() {
    }

    public void keyReleased() {
    }

    public void mousePressed(){
        if (click_playGame) {

        }
    }

    void update(int x, int y, GUIButton b) {
        if ( overRect(b_playGame.getX(), b_playGame.getY(), b_playGame.getWidth(), b_playGame.getHeight()) ) {
            click_playGame = true;
        } else {
            click_playGame = false;
        }
    }

    private boolean overRect(int x, int y, int width, int height){
        if (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height) {
            return true;
        } else {
            return false;
        }
    }
}
