/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package policewarrior;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

/**
 * @author Michael
 */
public class PoliceWarrior extends PApplet {
    /* Default */
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final int FPS = 60;

    /* Untuk animasi | processing grafik */
    /* List Unit/Entity seperti tank, bullet, dll. */
    private PImage bg;
    private Player p;
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private PImage[] temp_bullet;
    private int frame_ctr = 0;

    /* Status sprite */
    private boolean idle = true;
    private boolean running = false;

    /* Gerak Sprite */
    private boolean up = false;
    private boolean down = false;
    private boolean left = false;
    private boolean right = false;
    private boolean fire = false;
    private boolean boom = false;

    public static void main(String[] args) {
        // TODO code application logic here
        PApplet.main("policewarrior.PoliceWarrior");
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    /**
     * Inisialisasi objek
     * seperti karakter, player, npc, menu, image, dll.
     */
    public void setup() {
        /* Background a*/
        frameRate(FPS);
        bg = loadImage("src/assets/background/background_1.png");

        /* Player */
        PImage[] temp_player = new PImage[1];
        for (int i = 0; i < temp_player.length; i++) {
            temp_player[i] = loadImage("src/assets/player/Idle/(" + (i + 1) + ").png");
        }
        p = new Player(temp_player, 150, 150);

        /* Bullets */
        temp_bullet = new PImage[1];
        for (int i = 0; i < temp_bullet.length; i++) {
            temp_bullet[i] = loadImage("src/assets/bullet/Idle/(" + (i + 1) + ").png");
        }
    }

    public void draw() {
        background(bg);
        p.movement(up, down, left, right);
        p.drawIdle(this, frame_ctr);
        bulletMechanism();
        frame_ctr++;
    }

    private void bulletMechanism() {
        /* Generate bullet saat tekan spasi */
        int bullet_distance = 5;
        if (fire) {
            bullets.add(new Bullet(temp_bullet, p.getX() + bullet_distance, p.getY()));
            System.out.println("Test");
            fire = false;
        }

        if (bullets.size() > 0) {
            for (int i = bullets.size(); i > 0; i--) {
                bullets.get(i).movement();
                bullets.get(i).drawIdle(this, frame_ctr);
            }
        }
        if (bullets.size() > 0) {
            for (int i = bullets.size(); i > 0; i--) {
                if (bullets.get(i).getX() == 1200) {
                    bullets.remove(i);
                }
            }
        }
    }

    public void keyPressed() {
        if(key == 'w') {up = true; running = true; idle = false;}
        if(key == 's') {down = true; running = true; idle = false;}
        if(key == 'a') {left = true; running = true; idle = false;}
        if(key == 'd') {right = true; running = true; idle = false;}
        if(key == ' ') {fire = true; boom = false;}
    }

    public void keyReleased() {
        up = false;
        down = false;
        left = false;
        right = false;
        running = false;
        idle = true;
    }
}
