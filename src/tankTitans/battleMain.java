/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tankTitans;

import processing.core.PApplet;
import processing.core.PImage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author RAFAEL MATTHEW
 */
public class battleMain extends PApplet {
    /* Default */
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final int FPS = 60;

    /* Rounds */
    private boolean is_battle = true;

    /**
     * Round: Battle
     */

    /* Inialisasi untuk battle */
    /* List Unit/Entity seperti tank, bullet, dll. */
    /* Note : ganti jadi local variable supaya tidak boros memori */
    private PlayMusic shoot;
    private PImage bg_battle;
    private Player p;
    private ArrayList<Bullet> bullets;
    private ArrayList<BulletEnemy> bullets_enemy;
    private ArrayList<Enemy> enemies;
    private PImage[] temp_bullet;
    private PImage[] temp_bullet_enemy;
    private PImage[] temp_enemy;
    private int fire_rate = 30;
    private int fire_ctr = 60;
    private int max_bullet = 100;
    private int enemy_rows = 9;
    private int frame_ctr = 0;

    /* Status sprite player */
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


//    public static void main(String[] args) {
//        // TODO code application logic here
//        PApplet.main("tankTitans.tankTitans");
//    }

    /**
     * Inisialisasi objek
     * seperti karakter, player, npc, menu, image, dll.
     */
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        /* FPS */
        frameRate(FPS);

        /* Backgrounds */
//        bg_mainMenu = loadImage("src/assets/background/background_1.png");

        /* Player */
        PImage[] temp_player = new PImage[4];
        for (int i = 0; i < temp_player.length; i++) {
            temp_player[i] = loadImage("src/assets/player/Idle/(" + (i + 1) + ").png");
        }
        p = new Player(temp_player, 192, 360, 64);

        /* Bullets */
        bullets = new ArrayList<>();
        temp_bullet = new PImage[1];
        for (int i = 0; i < temp_bullet.length; i++) {
            temp_bullet[i] = loadImage("src/assets/bullet/Idle/(" + (i + 1) + ").png");
        }

        /* Enemy */
        enemies = new ArrayList<>();
        temp_enemy = new PImage[4];
        for (int i = 0; i < temp_enemy.length; i++) {
            temp_enemy[i] = loadImage("src/assets/enemy/Idle/(" + (i + 1) + ").png");
        }

        /* Bullets */
        bullets_enemy = new ArrayList<>();
        temp_bullet_enemy = new PImage[1];
        for (int i = 0; i < temp_bullet_enemy.length; i++) {
            temp_bullet_enemy[i] = loadImage("src/assets/bullet/Idle/(" + (i + 1) + ").png");
        }

        shoot = new PlayMusic("src/assets/sounds/Tank_fire-Sound_alerts.wav");
        shoot.PlayMusic();
    }

    /**
     * Main program
     */
    public void draw() {
        if (is_battle) {
            background(255);
            testingFrame();
            playerMechanism();
            try {
                bulletMechanism();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            enemiesMechanism();

            frame_ctr++;
        }
    }

    private void testingFrame() {
        int center_y = 720 / 2;
        int center_x = 1280 / 2;
        int movement = 48;
        line(0, center_y - (0 * movement), 1280, center_y - (0 * movement));
        line(0, center_y - (1 * movement), 1280, center_y - (1 * movement));
        line(0, center_y - (2 * movement), 1280, center_y - (2 * movement));
        line(0, center_y - (3 * movement), 1280, center_y - (3 * movement));
        line(0, center_y - (4 * movement), 1280, center_y - (4 * movement));
        line(0, center_y + (1 * movement), 1280, center_y + (1 * movement));
        line(0, center_y + (2 * movement), 1280, center_y + (2 * movement));
        line(0, center_y + (3 * movement), 1280, center_y + (3 * movement));
        line(0, center_y + (4 * movement), 1280, center_y + (4 * movement));

        line(192, 0, 192, 720);
        line(1280 - 192, 0, 1280 - 192, 720);

        fill(0, 0, 0);
        stroke(0, 0, 0);
    }

    private void playerMechanism() {
        if (p.getY() < 360 - (4 * 48) - 32) {
            up = false;
        }
        if (p.getY() > 360 + (4 * 48) + 32) {
            down = false;
        }
        p.movement(up, down, left, right);
//        if (running) {
//            if (movement_ctr < limit_movement) {
//                System.out.println("mvm: " + movement_ctr);
//                movement_ctr++;
//            }
//        }
        p.drawIdle(this, frame_ctr);
    }

    private void enemiesMechanism() {
        if (enemies.size() == 0) {
            int gap = 32 / 2;
            int[] y_spawn = new int[enemy_rows];

            /* Enemy positioning */
            if (enemy_rows == 1) {
                y_spawn[0] = 360;
            } else if (enemy_rows == 2) {
                y_spawn[0] = 360 - (1 * 48);
                y_spawn[1] = 360 + (1 * 48);
            } else if (enemy_rows == 3) {
                y_spawn[0] = 360;
                y_spawn[1] = 360 - (1 * 48);
                y_spawn[2] = 360 + (1 * 48);
            } else if (enemy_rows == 4) {
                y_spawn[0] = 360 - (1 * 48);
                y_spawn[1] = 360 + (1 * 48);
                y_spawn[2] = 360 - (2 * 48);
                y_spawn[3] = 360 + (2 * 48);
            } else if (enemy_rows == 5) {
                y_spawn[0] = 360;
                y_spawn[1] = 360 - (1 * 48);
                y_spawn[2] = 360 + (1 * 48);
                y_spawn[3] = 360 - (2 * 48);
                y_spawn[4] = 360 + (2 * 48);
            } else if (enemy_rows == 6) {
                y_spawn[0] = 360 - (1 * 48);
                y_spawn[1] = 360 + (1 * 48);
                y_spawn[2] = 360 - (2 * 48);
                y_spawn[3] = 360 + (2 * 48);
                y_spawn[4] = 360 - (3 * 48);
                y_spawn[5] = 360 + (3 * 48);
            } else if (enemy_rows == 7) {
                y_spawn[0] = 360;
                y_spawn[1] = 360 - (1 * 48);
                y_spawn[2] = 360 + (1 * 48);
                y_spawn[3] = 360 - (2 * 48);
                y_spawn[4] = 360 + (2 * 48);
                y_spawn[5] = 360 - (3 * 48);
                y_spawn[6] = 360 + (3 * 48);
            } else if (enemy_rows == 9) {
                y_spawn[0] = 360;
                y_spawn[1] = 360 - (1 * 48);
                y_spawn[2] = 360 + (1 * 48);
                y_spawn[3] = 360 - (2 * 48);
                y_spawn[4] = 360 + (2 * 48);
                y_spawn[5] = 360 - (3 * 48);
                y_spawn[6] = 360 + (3 * 48);
                y_spawn[7] = 360 - (4 * 48);
                y_spawn[8] = 360 + (4 * 48);
            }
//            else {
//                if (enemy_rows % 2 == 0) {
//                    int reference_line = 640 / 2 + gap;
//                    for (int i = 0; i < enemy_rows; i++) {
//                        if (i % 2 == 0) {
//                            y_spawn[i] = reference_line - 32 - gap;
//                            reference_line = reference_line - gap;
//                        } else {
//                            y_spawn[i] = reference_line - 32 - gap;
//                            reference_line = y_spawn[i];
//                        }
//                    }
//                } else {
//
//                }
//            }

            for (int i = 0; i < enemy_rows; i++) {
                System.out.println("Enemy " + i);
                enemies.add(new Enemy(temp_enemy, 1280 - 192, y_spawn[i], 32));
            }

            if (enemy_rows < 5) {
                enemy_rows++;
            } else {
                enemy_rows += 2;

                if (enemy_rows > 9) {
                    enemy_rows = 9;
                }
            }
        } else {
            for (int i = enemies.size() - 1; i >= 0; i--) {
//                enemies.get(i).movement();
                enemies.get(i).drawIdle(this, frame_ctr);
            }

            bulletEnemyMechanism();
        }
    }

    private void bulletEnemyMechanism() {
        for (int i = 0; i < enemies.size(); i++) {
            Random rnd = new Random();
            int fire_chance = rnd.nextInt(1, 250);

            int bullet_distance = 3;
            if (fire_chance >= 1 && fire_chance <= 1) {
                bullets_enemy.add(new BulletEnemy(temp_bullet, enemies.get(i).getX() - bullet_distance, enemies.get(i).getY(), 64));
                System.out.println("Dor dor");
            }
        }

        if (bullets_enemy.size() > 0) {
            for (int i = bullets_enemy.size() - 1; i >= 0; i--) {
                bullets_enemy.get(i).movement();
                bullets_enemy.get(i).drawIdle(this, frame_ctr);

                /* Cek terkena musuh */
                boolean hit = false;
//                System.out.println("Hitbox " + i + ", " + j);

                /* Penentuan hitbox */
                int bounding_right = p.getX() + p.getRes() / 2;
                int bounding_top = p.getY() - p.getRes() / 2;
                int bounding_bottom = p.getY() + p.getRes() / 2;

                if (bullets_enemy.get(i).getX() <= bounding_right) {
                    if (bullets_enemy.get(i).getY() >= bounding_top && bullets_enemy.get(i).getY() <= bounding_bottom) {
                        hit = true;

                    }
                }

                /* Max distance */
                if (bullets_enemy.get(i).getX() <= 16) {
                    hit = true;
                }

                /* Kena dong BOOM */
                if (hit) {
                    bullets_enemy.remove(i);
                    System.out.println("DUARRRRR!!!!! kenek kon thoel");
                }
            }
        }
    }

    private void bulletMechanism() throws InterruptedException{
        /* Generate bullet saat tekan spasi */
        int bullet_distance = 5;
        if (fire) {
            if (fire_ctr == fire_rate) {
                if (bullets.size() <= max_bullet) {

                    bullets.add(new Bullet(temp_bullet, p.getX() + bullet_distance, p.getY(), 64));
                }
                System.out.println("Test");
                fire = false;
            }
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
                        int bounding_left = enemies.get(j).getX() - enemies.get(j).getRes() / 2;
                        int bounding_top = enemies.get(j).getY() - enemies.get(j).getRes() / 2;
                        int bounding_bottom = enemies.get(j).getY() + enemies.get(j).getRes() / 2;

                        if (bullets.get(i).getX() >= bounding_left) {
                            if (bullets.get(i).getY() >= bounding_top && bullets.get(i).getY() <= bounding_bottom) {
                                hit = true;

                                enemies.get(j).getHit(p.getATK());
//                                if () {
//                                }
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

            fire_ctr--;

            if (fire_ctr <= 0) {
                fire_ctr = fire_rate;
            }
        } else {
            fire_ctr = fire_rate;
        }

        if (fire_ctr <= 0) {
            fire_ctr = fire_rate;
        }
    }

    public void keyPressed() {
        if (key == 'w') {
            up = true;
            running = true;
            idle = false;
        }
        if (key == 's') {
            down = true;
            running = true;
            idle = false;
        }
//        if(key == 'a') {left = true; running = true; idle = false;}
//        if(key == 'd') {right = true; running = true; idle = false;}

        if (key == ' ') {
            fire = true;
        }
        if (key == 'x') {
            System.out.println(p.getX() + ", " + p.getY());
        }
    }

    public void keyReleased() {
        if (key == 'w') {
            up = false;
            running = false;
            idle = true;
            movement_ctr = 0;
        }
        if (key == 's') {
            down = false;
            running = false;
            idle = true;
            movement_ctr = 0;
        }
        if (key == 'a') {
            left = false;
            running = false;
            idle = true;
            movement_ctr = 0;
        }
        if (key == 'd') {
            right = false;
            running = false;
            idle = true;
            movement_ctr = 0;
        }
        if (key == ' ') {
            fire = false;
        }
    }
}
