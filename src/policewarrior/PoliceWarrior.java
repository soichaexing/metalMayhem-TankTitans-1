/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package policewarrior;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Michael
 */
public class PoliceWarrior extends PApplet {
    /* Default */
    private static final Random rnd = new Random();
    private static final Scanner sc = new Scanner(System.in);
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final int FPS = 60;

    /* Untuk animasi | processing grafik */
    /* List Unit/Entity seperti tank, bullet, dll. */
    private PImage bg;
    private Player p;
    private ArrayList<Bullet> bullets;
    private ArrayList<Enemy> enemies;
    private PImage[] temp_bullet;
    private PImage[] temp_enemy;
    private int frame_ctr = 0;
    private int max_bullet = 100;
    private int enemy_rows = 1;

    /* Status sprite */
    private boolean idle = true;
    private boolean running = false;

    /* Gerak Sprite */
    private int limit_movement = 1;
    private int movement_ctr = 0;
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
        p = new Player(temp_player, 192, 328, 64);

        /* Bullets */
        bullets = new ArrayList<>();
        temp_bullet = new PImage[1];
        for (int i = 0; i < temp_bullet.length; i++) {
            temp_bullet[i] = loadImage("src/assets/bullet/Idle/(" + (i + 1) + ").png");
        }

        /* Enemy */
        enemies = new ArrayList<>();
        temp_enemy = new PImage[1];
        for (int i = 0; i < temp_enemy.length; i++) {
            temp_enemy[i] = loadImage("src/assets/enemy/Idle/(" + (i + 1) + ").png");
        }
    }

    public void draw() {
        background(255);
        if (running) {
            if (movement_ctr < limit_movement) {
                System.out.println("mvm: " + movement_ctr);
                p.movement(up, down, left, right);
                movement_ctr++;
            }
        }
        p.drawIdle(this, frame_ctr);
        enemiesMechanism();
        bulletMechanism();
        frame_ctr++;
    }

    private void enemiesMechanism() {
        if (enemies.size() == 0) {
            int gap = 32/2;
            int[] y_spawn = new int[enemy_rows];

            /* Enemy positioning */
            if (enemy_rows == 1) {
                y_spawn[0] = 680/2;
            } else if (enemy_rows == 2) {
                y_spawn[0] = 680/2 + gap - 32 - gap;
                y_spawn[1] = 680/2 + gap + 32 - gap;
            } else if (enemy_rows == 3) {
                y_spawn[0] = 680/2;
                y_spawn[1] = 680/2 - 32 - gap;
                y_spawn[2] = 680/2 + 32 + gap;
            } else if (enemy_rows == 4) {
                y_spawn[0] = 680/2 + gap - 32 - gap;
                y_spawn[1] = 680/2 + gap + 32 - gap;
                y_spawn[2] = 680/2 + gap - 32 - gap - 32 - gap;
                y_spawn[3] = 680/2 + gap + 32 - gap + 32 + gap;
            } else if (enemy_rows == 5) {
                y_spawn[0] = 680/2;
                y_spawn[1] = 680/2 - 32 - gap;
                y_spawn[2] = 680/2 + 32 + gap;
                y_spawn[3] = 680/2 - 32 - gap - 32 - gap;
                y_spawn[4] = 680/2 + 32 + gap + 32 + gap;
            } else if (enemy_rows == 6) {
                y_spawn[0] = 680/2 + gap - 32 - gap;
                y_spawn[1] = 680/2 + gap + 32 - gap;
                y_spawn[2] = 680/2 + gap - 32 - gap - 32 - gap;
                y_spawn[3] = 680/2 + gap + 32 - gap + 32 + gap;
                y_spawn[4] = 680/2 + gap - 32 - gap - 32 - gap - 32 - gap;
                y_spawn[5] = 680/2 + gap + 32 - gap + 32 + gap + 32 + gap;
            } else {
                if (enemy_rows % 2 == 0) {
                    int reference_line = 680 / 2 + gap;
                    for (int i = 0; i < enemy_rows; i++) {
                        if (i % 2 == 0) {
                            y_spawn[i] = reference_line - 32 - gap;
                            reference_line = reference_line - gap;
                        } else {
                            y_spawn[i] = reference_line - 32 - gap;
                            reference_line = y_spawn[i];
                        }
                    }
                } else {

                }
            }

            for (int i = 0; i < enemy_rows; i++) {
                System.out.println("Enemy " + i);
                enemies.add(new Enemy(temp_enemy, 1130, y_spawn[i], 32));
            }

            enemy_rows++;
            if (enemy_rows >= 6) {
                enemy_rows = 6;
            }
        } else {
            for (int i = enemies.size() - 1; i >= 0; i--) {
//                enemies.get(i).movement();
                enemies.get(i).drawIdle(this, frame_ctr);
            }
        }
    }

    private void bulletMechanism() {
        /* Generate bullet saat tekan spasi */
        int bullet_distance = 5;
        if (fire) {
            if (bullets.size() <= max_bullet) {
                bullets.add(new Bullet(temp_bullet, p.getX() + bullet_distance, p.getY(), 64));
            }
            System.out.println("Test");
            fire = false;
        }

        if (bullets.size() > 0) {
            for (int i = bullets.size() - 1; i >= 0; i--) {
                bullets.get(i).movement();
                bullets.get(i).drawIdle(this, frame_ctr);

                /* Cek terkena musuh */
                boolean hit = false;
                if (enemies.size() > 0) {
                    for (int j = enemies.size() - 1; j >= 0; j--) {
                        System.out.println("Hitbox " + i + ", " + j);
                        /* Penentuan hitbox */
                        int bounding_left = enemies.get(j).getX() - enemies.get(j).getRes()/2;
                        int bounding_top = enemies.get(j).getY() - enemies.get(j).getRes()/2;
                        int bounding_bottom = enemies.get(j).getY() + enemies.get(j).getRes()/2;

                        if (bullets.get(i).getX() >= bounding_left) {
                            if (bullets.get(i).getY() >= bounding_top && bullets.get(i).getY() <= bounding_bottom) {
                                hit = true;

                                enemies.remove(j);
                            }
                        }
                    }
                }

                /* Max distance */
                if (bullets.get(i).getX() >= 1200) {
                    hit = true;
                }

                /* Kena dong BOOM */
                if (hit) {
                    bullets.remove(i);
                    System.out.println("DUARRRRR!!!!!, enemy sisa " + enemies.size());
                }
            }
        }
    }

    public void keyPressed() {
        if(key == 'w') {up = true; running = true; idle = false;}
        if(key == 's') {down = true; running = true; idle = false;}
//        if(key == 'a') {left = true; running = true; idle = false;}
//        if(key == 'd') {right = true; running = true; idle = false;}

        if(key == ' ') {fire = true;}
    }

    public void keyReleased() {
        if(key == 'w') {up = false; running = false; idle = true; movement_ctr = 0;}
        if(key == 's') {down = false; running = false; idle = true; movement_ctr = 0;}
        if(key == 'a') {left = false; running = false; idle = true; movement_ctr = 0;}
        if(key == 'd') {right = false; running = false; idle = true; movement_ctr = 0;}
        if(key == ' ') {fire = false;}
    }
}
