package tankTitans;

import processing.core.PApplet;
import processing.core.PImage;

public class BulletEnemy extends Entity implements SpriteAnimation {
    protected int bullet_speed = 20;
    protected int bullet_timing = 0;

    public BulletEnemy(PImage[] idle, int x, int y, int res, int ATK) {
        super(0, ATK, 0, x, y);
        super.idle = idle;
        super.timing = 3;
        super.frame = 0;
        super.total_frame = 1;
        super.reset_frame = -1;
        super.res = res;
    }

    public void drawIdle(PApplet app, int frame_ctr){
        if(frame_ctr == reset_frame){
            frame = 0;
            frame_ctr = 0;
        }
        if(frame_ctr % timing == 0){
            frame++;
        }
        if(frame > total_frame - 1){
            frame = 0;
        }
        app.image(this.idle[frame], x - (res / 2), y - (res / 2), res, res);

        bullet_timing++;
    }

    public void movement() {
        if (bullet_timing < 5) x -= bullet_speed;
        else if (bullet_timing < 10) x -= bullet_speed - (bullet_speed / (5.2 + 0.2));
        else if (bullet_timing < 15) x -= bullet_speed - (bullet_speed / (4.5 + 0.2));
        else if (bullet_timing < 20) x -= bullet_speed - (bullet_speed / (3.9 + 0.2));
        else if (bullet_timing < 25) x -= bullet_speed - (bullet_speed / (3.4 + 0.2));
        else if (bullet_timing < 30) x -= bullet_speed - (bullet_speed / (2.9 + 0.2));
        else if (bullet_timing < 35) x -= bullet_speed - (bullet_speed / (2.5 + 0.2));
        else if (bullet_timing < 40) x -= bullet_speed - (bullet_speed / (2.2 + 0.2));
        else if (bullet_timing < 45) x -= bullet_speed - (bullet_speed / (1.9 + 0.2));
        else if (bullet_timing < 50) x -= bullet_speed - (bullet_speed / (1.8 + 0.2));
        else if (bullet_timing < 55) x -= bullet_speed - (bullet_speed / (1.7 + 0.2));
        else if (bullet_timing < 60) x -= bullet_speed - (bullet_speed / (1.6 + 0.2));
        else if (bullet_timing < 65) x -= bullet_speed - (bullet_speed / (1.5 + 0.2));
        else if (bullet_timing < 70) x -= bullet_speed - (bullet_speed / (1.4 + 0.2));
        else if (bullet_timing < 75) x -= bullet_speed - (bullet_speed / (1.35 + 0.2));
        else if (bullet_timing < 80) x -= bullet_speed - (bullet_speed / (1.3 + 0.2));
        else if (bullet_timing < 85) x -= bullet_speed - (bullet_speed / (1.25 + 0.2));
        else if (bullet_timing < 90) x -= bullet_speed - (bullet_speed / (1.2 + 0.2));
        else if (bullet_timing < 95) x -= bullet_speed - (bullet_speed / (1.15 + 0.2));
        else x -= bullet_speed - (bullet_speed / 1.1);
    }
}
