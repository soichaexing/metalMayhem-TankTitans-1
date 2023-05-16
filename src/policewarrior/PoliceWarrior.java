/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package policewarrior;

import processing.core.PApplet;
import processing.core.PImage;

/**
 *
 * @author Michael
 */
public class PoliceWarrior extends PApplet {
    /* Default */
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static final int FPS = 60;

    /* Untuk animasi | processing grafik */
    PImage bg;
    Player p;
    int f = 0;
    private boolean idle = true;
    private boolean running = false;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        PApplet.main("contohgame.ContohGame");
    }

    public void settings(){
        size(WIDTH, HEIGHT);
    }

    public void setup(){
        // inisialisasi objek, seperti karakter, player, npc, menu, image, dll.
        bg = loadImage("src/assets/background/background_1.png");
        PImage[] temp = new PImage[18];
        frameRate(FPS);
        for (int i = 0; i < temp.length; i++) {
            temp[i] = loadImage("src/assets/player/Idle_Blinking/(" + (i+1) + ").png");
        }
        p = new Player(temp, 100, 100);
    }

    public void draw(){
        background(bg);
        p.drawIdle(this, f);
        f++;
    }

    public void keyPressed(){
//        if(key == 'w') { up = true; running = true; idle = false}
    }

    public void keyRelease(){

    }
}
