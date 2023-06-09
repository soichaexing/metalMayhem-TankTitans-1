package tankTitans;

import processing.core.PApplet;
import processing.core.PImage;

public class Bullet extends Entity implements SpriteAnimation {
    protected int bullet_speed = 15;
    protected int bullet_timing = 0;

    public Bullet(PImage[] idle, int x, int y, int res, int ATK) {
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
        if (bullet_timing < 15) {
            x += bullet_speed;
        } else if (bullet_timing < 30) {
            x += bullet_speed - (bullet_speed / 5);
        } else if (bullet_timing < 45) {
            x += bullet_speed - (bullet_speed / 4);
        } else if (bullet_timing < 60) {
            x += bullet_speed - (bullet_speed / 2.5);
        } else if (bullet_timing < 75) {
            x += bullet_speed - (bullet_speed / 2);
        } else {
            x += bullet_speed - (bullet_speed / 1.5);
        }
    }
}
