/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tankTitans;

import processing.core.PApplet;
import processing.core.PImage;
import java.awt.*;


/**
 * @author Michael
 */
public class tankTitans extends PApplet {
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
    private GUIButton b_playGame = new GUIButton("Play!",620, 180, 100, 75, Color.CYAN);
    private GUIButton b_highscore = new GUIButton("Highscore",620, 400, 100, 75, Color.CYAN);
    private GUIButton b_exit = new GUIButton("Exit",620, 500, 100, 75, Color.CYAN);
    private boolean click_playGame = false;
    private boolean click_highScore = false;

    public static void main(String[] args) {
        // TODO code application logic here
        PApplet.main("tankTitans.tankTitans");
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
        bg_mainMenu = loadImage("src/assets/background/Main_Menu-1.png");
    }

    /**
     * Main program
     */
    public void draw() {
        if (is_mainMenu) {
            update(mouseX, mouseY, b_playGame);
            background(bg_mainMenu);
            fill(255, 245, 248);
            stroke(255, 245, 258);

            rect(b_playGame.getX(), b_playGame.getY(), b_playGame.getWidth(), b_playGame.getHeight());
            rect(b_highscore.getX(), b_highscore.getY(), b_highscore.getWidth(), b_highscore.getHeight());
            rect(b_exit.getX(), b_exit.getY(), b_exit.getWidth(), b_exit.getHeight());
        }
    }

    public void keyPressed() {
    }

    public void keyReleased() {
    }

    public void mousePressed(){
        if (click_playGame) {
            String[] args = {"runBattle"};
            PApplet.runSketch(args, new battleMain());
            surface.setVisible(false);
        }
        if (click_highScore) {
            String[] args = {"runHighscore"};
            PApplet.runSketch(args, new Highscore());
            surface.setVisible(false);
        }
    }

    void update(int x, int y, GUIButton b) {
        if ( overRect(b_playGame.getX(), b_playGame.getY(), b_playGame.getWidth(), b_playGame.getHeight()) ) {
            click_playGame = true;
        } else {
            click_playGame = false;
        }

        if ( overRect(b_highscore.getX(), b_highscore.getY(), b_highscore.getWidth(), b_highscore.getHeight()) ) {
            click_highScore = true;
        } else {
            click_highScore = false;
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
