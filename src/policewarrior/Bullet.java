package policewarrior;

import processing.core.PApplet;
import processing.core.PImage;

public class Bullet extends Entity implements SpriteAnimation {
    protected int bullet_speed = 10;
    public Bullet(PImage[] idle, int x, int y, int HP, int ATK, int DEF) {
        super(HP, ATK, DEF, x, y);
        super.idle = idle;
        super.timing = 3;
        super.frame = 0;
        super.total_frame = 1;
        super.reset_frame = -1;
    }

    public Bullet(PImage[] idle, int x, int y, int res) {
        super(0, 0, 0, x, y);
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
        app.image(this.idle[frame], x, y, res, res);
    }

    public void movement() {
        x += bullet_speed;
    }
}
