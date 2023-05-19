package policewarrior;

import processing.core.PApplet;
import processing.core.PImage;

public class Player extends Entity implements SpriteAnimation {
    protected String nama;

    public Player(PImage[] idle, int x, int y, int HP, int ATK, int DEF) {
        super(HP, ATK, DEF, x, y);
        super.idle = idle;
        super.timing = 3;
        super.frame = 0;
        super.total_frame = 1;
        super.reset_frame = -1;
    }

    public Player(PImage[] idle, int x, int y) {
        super(0, 0, 0, x, y);
        super.idle = idle;
        super.timing = 3;
        super.frame = 0;
        super.total_frame = 1;
        super.reset_frame = -1;
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
            app.image(this.idle[frame], x, y, 64, 64);
//        }
    }

    public void movement(boolean up, boolean down, boolean left, boolean right) {
        if (up) y -= 5;
        if (down) y += 5;
        if (left) x -= 5;
        if (right) x += 5;
    }
}
