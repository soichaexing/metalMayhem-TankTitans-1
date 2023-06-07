package tankTitans;

import processing.core.PApplet;
import processing.core.PImage;

public class Player extends Entity implements SpriteAnimation {
    protected String nama;
    protected int movement_speed;

    public Player(PImage[] idle, int x, int y, int res) {
        super(20, 3, 0, x, y);
        super.idle = idle;
        super.timing = 3;
        super.frame = 0;
        super.total_frame = 4;
        super.reset_frame = -1;
        super.res = res;
//        this.movement_speed = 32 + 16;
        this.movement_speed = 5;
    }

    public void drawIdle(PApplet app, int frame_ctr){
//        if (idle) {
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
//        }
    }

    public void movement(boolean up, boolean down, boolean left, boolean right) {
        if (up) y -= movement_speed;
        if (down) y += movement_speed;
        if (left) x -= movement_speed;
        if (right) x += movement_speed;
    }


}
